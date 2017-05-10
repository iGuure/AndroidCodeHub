public class VolatileTest {
	public volatile int inc = 0;

	public void increase() {
		// 非原子操作
		inc++;
	}

	public static void main(String[] args) {
		final VolatileTest volatileTest = new VolatileTest();
		for (int i = 0; i < 10; i++) {
			new Thread() {
				public void run() {
					for (int j = 0; j < 1000; j++)
						volatileTest.increase();
				}
			}.start();
		}

		while (Thread.activeCount() > 1)
			Thread.yield();
		System.out.println(volatileTest.inc);
	}
}