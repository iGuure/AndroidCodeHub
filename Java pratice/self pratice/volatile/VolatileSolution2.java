import java.util.concurrent.locks.*;

public class VolatileSolution2 {
	public int inc = 0;
    Lock lock = new ReentrantLock();

	/* 解法二：使用Lock将其转化成原子操作 */
	public synchronized void increase() {
		lock.lock();
		try {
			inc++;	
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		final VolatileSolution2 volatileSolution2 = new VolatileSolution2();
		for (int i = 0; i < 10; i++) {
			new Thread() {
				public void run() {
					for (int j = 0; j < 1000; j++)
						volatileSolution2.increase();
				}
			}.start();
		}

		while (Thread.activeCount() > 1)
			Thread.yield();
		System.out.println(volatileSolution2.inc);
	}
}