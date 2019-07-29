package superQueryBuilderFrom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QueryBuilder {
	private final String tableName;
	private final Connection base;
	private TypeRequette type;
	private List<String> selectedFields;
	private List<WhereClause> whereClauses;
	private List<OrderByClause> orderByClauses;
	
	
	public enum TypeRequette {
		SELECT,
		UPDATE,
		INSERT,
		DELETE
	}
	public enum TypeWhere {
		LESS,
		MORE,
		EQUAL,
		NOT_EQUAL,
		LESS_OR_EQUAL,
		MORE_OR_EQUAL
	}
	// représente un tri par un champ
	public static class OrderByClause {
		private final String fieldName;
		private final boolean direction;
		
		public OrderByClause(String fieldName, boolean direction) {
			this.fieldName = fieldName;
			this.direction = direction;
		}
		public String getFieldName() { return fieldName; }
		public boolean isDirection() { return direction; }
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder(" `");
			sb.append(fieldName).append("` ")
								.append(direction? "ASC " : "DESC ");
			return sb.toString();					
		}
	}
	
	
	// représente une condition du where
	public static class WhereClause {
		private final String fieldName;
		private final TypeWhere typeWhere;
		private final int position;
		
		public WhereClause(String fieldName, TypeWhere typeWhere, int position) {
			this.fieldName = fieldName;
			this.typeWhere = typeWhere;
			this.position = position;
		}
		public String getFieldName() { return fieldName; }
		public TypeWhere getTypeWhere() { return typeWhere; }
		public int getPosition() { return position; }
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder(" `");
			sb.append(fieldName).append("` ");
			switch(typeWhere) {
				case EQUAL: sb.append("="); break;
				case LESS: sb.append("<"); break;
				case MORE: sb.append(">"); break;
				case LESS_OR_EQUAL: sb.append("<="); break;
				case MORE_OR_EQUAL: sb.append(">="); break;
				case NOT_EQUAL: sb.append("<>"); break;
			}
			sb.append(" ?");
			return sb.toString();
		}
	}

	// ---------------------------------------------------------
	public QueryBuilder(String tableName, Connection base) {
		this.tableName = tableName;
		this.base = base;
		this.type = TypeRequette.SELECT;
		this.selectedFields = new ArrayList<String>();
		this.whereClauses = new ArrayList<>();
		this.orderByClauses = new ArrayList<>();
	}

	public QueryBuilder addField(String fieldName) {
		this.selectedFields.add(fieldName);
		return this;
	}
	
	public QueryBuilder addWhere(String fieldName, TypeWhere type, int position) {
		this.whereClauses.add(new WhereClause(fieldName, type, position));
		return this;
	}
	
	public QueryBuilder addSort(String fieldName, boolean direction) {
		this.orderByClauses.add(new OrderByClause(fieldName, direction));
		return this;
	}
	
	public QueryBuilder select() {
		this.type = TypeRequette.SELECT;
		return this;
	}
	
	public QueryBuilder insert() {
		this.type = TypeRequette.INSERT;
		return this;
	}

	public QueryBuilder update() {
		this.type = TypeRequette.UPDATE;
		return this;
	}

	public QueryBuilder delete() {
		this.type = TypeRequette.DELETE;
		return this;
	}
	
	public PreparedStatement build() {
		try {
			switch (type) {
				case SELECT: return buildSelect();
				case UPDATE: return buildUpdate();
				case INSERT: return buildInsert();
				case DELETE: return buildDelete();
			}
		} catch (SQLException e) {e.printStackTrace();}
		return null;
	}


	private PreparedStatement buildSelect() throws SQLException {
		StringBuilder sb = new StringBuilder("SELECT ");
		if (selectedFields.isEmpty())
			sb.append("*");
		else 
			sb.append('`')
			  .append(String.join("`,`", selectedFields))
			  .append('`');
		sb.append(" FROM `")
		  .append(tableName)
		  .append('`');
		
		if (!whereClauses.isEmpty()) {
			List<String> clauses = this.whereClauses.stream()
									   .sorted((c1, c2) -> Integer.compare(c1.position, c2.position))
									   .map(c -> c.toString())
									   .collect(Collectors.toList());
			sb.append(" WHERE ")
			  .append(String.join(" AND ", clauses));
		}
		
		if (!orderByClauses.isEmpty()) {
			// je transforme mes OrderClause en chaine de caractere avec map et toString()
			// puis je les colles ensemble séparé par des virgules (avec String.join)
			// et enfin je l'ajoute a la requette derrière un "ORDER BY"
			sb.append(" ORDER BY ")
			  .append(String.join(",",
					  orderByClauses.stream()
					  				.map(o -> o.toString())
					  				.collect(Collectors.toList())));
		}

		System.out.println("requette = " + sb.toString());
		return base.prepareStatement(sb.toString());
	}
	
	private PreparedStatement buildUpdate() throws SQLException {
		StringBuilder sb = new StringBuilder("UPDATE ");
		if (this.selectedFields.isEmpty())
			throw new SQLException("can not update with no fields selected");
		sb.append('`').append(tableName).append("` SET `");
		sb.append(String.join("` = ?, `", this.selectedFields))
		  			  .append("` = ? ");
		
		if (!whereClauses.isEmpty()) {
			List<String> clauses = this.whereClauses.stream()
									   .sorted((c1, c2) -> Integer.compare(c1.position, c2.position))
									   .map(c -> c.toString())
									   .collect(Collectors.toList());
			sb.append(" WHERE ")
			  .append(String.join(" AND ", clauses));
		}
		System.out.println(sb.toString());
		return base.prepareStatement(sb.toString());
	}
	
	private PreparedStatement buildInsert() throws SQLException {
		StringBuilder sb = new StringBuilder("INSERT INTO `");
		sb.append(tableName).append("` (`");
		if (this.selectedFields.isEmpty())
			throw new SQLException("can not insert with no fields");
		sb.append(String.join("`,`", this.selectedFields))
		  .append("`) VALUES (")
		  .append(String.join(",", selectedFields.stream().map(c -> "?").collect(Collectors.toList())))
		  .append(") ");
		 System.out.println("requette = " + sb.toString());
		 return base.prepareStatement(sb.toString());
	}
	
	private PreparedStatement buildDelete() throws SQLException {
		StringBuilder sb = new StringBuilder("DELETE FROM `");
		sb.append(tableName).append("` WHERE ");
		if (whereClauses.isEmpty())
			throw new SQLException("what are you doing!! use where clause with delete");
		
		List<String> clauses = this.whereClauses.stream()
				   .sorted((c1, c2) -> Integer.compare(c1.position, c2.position))
				   .map(c -> c.toString())
				   .collect(Collectors.toList());
		sb.append(String.join(" AND ", clauses));
		
		System.out.println("requete = " + sb.toString());
		return base.prepareStatement(sb.toString());
	}
	
	

}
