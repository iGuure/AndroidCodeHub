import java.util.concurrent.atomic.*;

public class VolatileSolution3 {
	/* 解法三：使用AtomicInteger */
	public AtomicInteger inc = new AtomicInteger();

	public synchronized void increase() {
		inc.getAndIncrement();
	}

	public static void main(String[] args) {
		final VolatileSolution3 volatileSolution3 = new VolatileSolution3();
		for (int i = 0; i < 10; i++) {
			new Thread() {
				public void run() {
					for (int j = 0; j < 1000; j++)
						volatileSolution3.increase();
				}
			}.start();
		}

		while (Thread.activeCount() > 1)
			Thread.yield();
		System.out.println(volatileSolution3.inc);
	}
}