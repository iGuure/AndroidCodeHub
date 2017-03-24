public class ProductTest {
	public static void main(String[] args) {
		Clerk clerk = new Clerk();

		// 生产者线程
		Thread producerThread = new Thread(new Producer(clerk));

		// 消费者线程
		Thread consumerThread = new Thread(new Consumer(clerk));

		producerThread.start();
		consumerThread.start();
	}
}

class Clerk {
	// -1 表示目前没有产品
	private int product = -1;

	// 这个方法由生产者调用
	public synchronized void setProduct(int product) {
		if (this.product != -1) {
			try {
				// 目前店员没有空间收产品，请稍候！
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		this.product = product;
		System.out.printf("生产者设置 (%d)%n", this.product);

		// 通知等待区中的一个消费者可以继续工作了
		// 其实是回到等待区！
		notify();
	}

	public synchronized int getProduct() {
		if (this.product == -1) {
			try {
				// 缺货了，请稍候！
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		int p = this.product;
		System.out.printf("消费者取走 (%d)%n", this.product);
		this.product = -1;	// 取走产品，-1表示目前店员手上无产品

		// 通知等待区中的一个生产者可以继续工作了
		// 其实是回到等待区！
		notify();

		return p;
	}
}

class Producer implements Runnable {
	private Clerk clerk;

	public Producer(Clerk clerk) {
		this.clerk = clerk;
	}

	public void run() {
		System.out.println("生产者开始生产整数....");

		// 生产1到10的整数
		for (int product = 1; product <= 10; product++) {
			try {
				// 暂停随机时间
				Thread.sleep((int) (Math.random() * 3000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// 将产品交给店员
			clerk.setProduct(product);
		}
	}
}

class Consumer implements Runnable {
	private Clerk clerk;

	public Consumer(Clerk clerk) {
		this.clerk = clerk;
	}

	public void run() {
		System.out.println("消费者开始消耗整数....");

		// 消耗10个整数
		for (int i = 1; i <= 10; i++) {
			try {
				// 等待随机时间
				Thread.sleep((int) (Math.random() * 3000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// 从店员处取走整数
			clerk.getProduct();
		}
	}
}