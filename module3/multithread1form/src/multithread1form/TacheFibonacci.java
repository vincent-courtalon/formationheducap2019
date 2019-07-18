package multithread1form;

public class TacheFibonacci extends Thread {
	
	public TacheFibonacci(String name) {
		super(name);
	}


	@Override
	public void run() {
		for (int i = 0; i < 42; i++) {
			System.out.println(getName() + " -> " + fibonacci(i));
		}
	}
	
	private long fibonacci(long n) {
		if (n == 0) return 0;
		if (n == 1) return 1;
		return fibonacci(n-1) + fibonacci(n - 2);
	}

}
