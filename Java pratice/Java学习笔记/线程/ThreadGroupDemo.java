// ThreadGroup
import java.io.*;

public class ThreadGroupDemo {
	public static void main(String[] args) {
		ThreadGroup threadGroup1 = new ThreadGroup("group1") {
			// 继承 ThreadGroup 并重新定义以下方法
			// 在线程成员抛出 unchecked exception
			// 会执行此方法
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println(t.getName() + ": " + e.getMessage());
			}
		};

		Thread thread1 = new Thread(threadGroup1, new Runnable() {
			public void run() {
				// 抛出 unchecked 异常
				throw new RuntimeException("测试异常");
			}
		});

		thread1.start();
	}
}