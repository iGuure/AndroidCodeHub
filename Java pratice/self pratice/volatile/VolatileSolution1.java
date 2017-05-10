public class VolatileSolution1 {
	public int inc = 0;

	/* 解法一：使用synchronized将其转化成原子操作 */
	public synchronized void increase() {
		inc++;
	}

	public static void main(String[] args) {
		final VolatileSolution1 volatileSolution1 = new VolatileSolution1();
		for (int i = 0; i < 10; i++) {
			new Thread() {
				public void run() {
					for (int j = 0; j < 1000; j++)
						volatileSolution1.increase();
				}
			}.start();
		}

		while (Thread.activeCount() > 1)
			Thread.yield();
		System.out.println(volatileSolution1.inc);
	}
}