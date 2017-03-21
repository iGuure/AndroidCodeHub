public class InterruptDemo {
	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable() {
			public void run() {
				try {
					// 暂停9999毫秒
					Thread.sleep(9999);
				} catch (InterruptedException e) {
					System.out.println("I'm interrupted!");
				}
			}
		});
		thread.start();
		thread.interrupt();	// 立即中断它
	}
}