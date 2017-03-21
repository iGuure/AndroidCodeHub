// 如果所有的非 Daemon 的线程都结束了，则 Daemon 线程自动就会终止
public class DaemonThread {
	public static void main(String[] args) {
		Thread thread = new Thread(
			// 这是匿名类的写法
			new Runnable() {
				public void run() {
					while (true) {
						System.out.print("T");
					}
				}
			}
		);
		// 设置为 Daemon 线程
		thread.setDaemon(true);
		thread.start();
		try {
			Thread.sleep(500);			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}