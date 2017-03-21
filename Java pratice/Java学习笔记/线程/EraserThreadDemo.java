import java.util.Scanner;

class EraserThread extends Thread {
	private boolean active;
	private String mask;

	public EraserThread() {
		this('*');
	}

	public EraserThread(char maskChar) {
		active = true;
		mask = "\010" + maskChar;
	}

	// 停止线程时设置为false
	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isActive() {
		return active;
	}

	public void run () {
		while (isActive()) {
			System.out.print(mask);
			try {
				// 暂停目前的线程50毫秒
				// 并不是切换线程的作用！
				Thread.currentThread().sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class EraserThreadDemo {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.print("输入名称：");
			String name = scanner.next();

			System.out.print("输入密码： ");

			// 启动 Eraser 线程
			EraserThread eraserThread = new EraserThread('#');
			eraserThread.start();
			String password = scanner.next();
			eraserThread.setActive(false);

			if ("guure".equals(name) && "123456".equals(password)) {
				System.out.println("欢迎 guure");
				break;
			} else {
				System.out.printf("%s，名字或密码错误，请重新输入！%n", name);
			}
		}
	}
}