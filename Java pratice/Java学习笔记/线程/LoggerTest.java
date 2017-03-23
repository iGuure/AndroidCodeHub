import java.io.*;
import java.util.logging.*;

class SimpleThreadLogger {
	private static final ThreadLocal<Logger> threadLocal = new ThreadLocal<Logger>();

	// 输出信息
	public static void log(String msg) {
		getThreadLogger().log(Level.INFO, msg);
	}

	private static Logger getThreadLogger() {
		Logger logger = threadLocal.get();

		if (logger == null) {
			try {
				logger = Logger.getLogger(Thread.currentThread().getName());
				// Logger 默认是在主控台输出
				// 我们加入一个文件输出的 Handler
				// 它会输出XML的记录文件
				logger.addHandler(new FileHandler(Thread.currentThread().getName() + ".log"));
			} catch (IOException e) {}

			threadLocal.set(logger);
		}

		return logger;
	}
}

class TestThread extends Thread {
	public TestThread(String name) {
		super(name);
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			SimpleThreadLogger.log(getName() + ": message " + i);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				SimpleThreadLogger.log(e.toString());
			}
		}
	}
}

public class LoggerTest {
	public static void main(String[] args) {
		new TestThread("thread1").start();
		new TestThread("thread2").start();
		new TestThread("thread3").start();
	}
}