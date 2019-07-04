package collectionEtFeneriquesForm;

public class Paire <T1, T2> {
	
	private T1 left;
	private T2 right;
	
	public T1 getLeft() {return left;}
	public void setLeft(T1 left) {this.left = left;}
	public T2 getRight() {return right;}
	public void setRight(T2 right) {this.right = right;}
	
	public Paire(T1 left, T2 right) {
		this.left = left;
		this.right = right;
	}
	
	@Override
	public String toString() {
		return "Paire [left=" + left + ", right=" + right + "]";
	}

	
	
	
}
