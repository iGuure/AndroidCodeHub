import java.util.Scanner;

class Eraser implements Runnable {	// 实现Runnable
	private boolean active;
	private String mask;

	public Eraser() {
		this('*');
	}

	public Eraser(char maskChar) {
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

public class EraserDemo {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.print("输入名称：");
			String name = scanner.next();

			System.out.print("输入密码： ");

			// Eraser 实现 Runnable 界面
			Eraser eraser = new Eraser('#');

			// 启动 Eraser 线程
			Thread eraserThread = new Thread(eraser);
			eraserThread.start();
			String password = scanner.next();
			eraser.setActive(false);

			if ("guure".equals(name) && "123456".equals(password)) {
				System.out.println("欢迎 guure");
				break;
			} else {
				System.out.printf("%s，名字或密码错误，请重新输入！%n", name);
			}
		}
	}
}