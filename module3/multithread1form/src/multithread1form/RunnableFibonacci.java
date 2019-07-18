package multithread1form;

public class RunnableFibonacci implements Runnable {

	
	@Override
	public void run() {
		// Thread.currentThread() renvoie le thread actuel (en execution)
		for (int i = 0; i < 42; i++) {
			System.out.println(Thread.currentThread().getName() + " -> " + fibonacci(i));
		}

	}

	private long fibonacci(long n) {
		if (n == 0) return 0;
		if (n == 1) return 1;
		return fibonacci(n-1) + fibonacci(n - 2);
	}
}
