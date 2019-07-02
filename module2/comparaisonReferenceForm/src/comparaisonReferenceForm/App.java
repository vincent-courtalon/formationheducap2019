package comparaisonReferenceForm;

public class App {

	public static void main(String[] args) {
		
		String str1 = "patrick";
		String str2 = "patrick";
		String str3 = "carlo";
		String str4 = "pat";
		str4 += "rick";
		
		System.out.println("str1 == str3 -> " + (str1 == str3));
		System.out.println("str1 == str2 -> " + (str1 == str2));
		System.out.println("str1 == str4 -> " + (str1 == str4));

		System.out.println("str1 equals str3 -> " + (str1.equals(str3)));
		System.out.println("str1 equals str2 -> " + (str1.equals(str2)));
		System.out.println("str1 equals str4 -> " + (str1.equals(str4)));

		str2 = str2.replace('a', 'o');
		System.out.println(str2);
	}

}
