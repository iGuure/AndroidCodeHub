// 当线程使用join()加入至另一个线程时，
// 另一个线程会等待这个被加入的线程工作完毕，然后再继续他的动作
public class ThreadA {
	public static void main(String[] args) {
		System.out.println("Thread A 执行");

		Thread threadB = new Thread(new Runnable() {
			public void run() {
				try {
					System.out.println("Thread B 开始");
					for (int i = 0; i < 5; i++) {
						Thread.sleep(1000);
						System.out.println("Thread B 执行" + i);
					}
					System.out.println("Thread B 即将结束");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});


		threadB.start();

		try {
			// Thread B 加入 Thread A
			threadB.join();

			// threadB.join(2000)表示threadB至多处理2秒，
			// 如果加入的线程还没执行完毕，那么它的优先权就会消失
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Thread A 执行");
	}
}