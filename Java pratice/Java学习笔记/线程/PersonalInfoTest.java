class PersonalInfo {
	private String name;
	private String id;
	private int count;

	public PersonalInfo() {
		name = "nobody";
		id = "N/A";
	}

	public synchronized void setNameAndId(String name, String id) {
		this.name = name;
		this.id = id;
		if (!checkNameAndIdEqual()) {
			System.out.println(count + " -> illegal name or id");
		}
		count++;
	}

	private boolean checkNameAndIdEqual() {
		return name.charAt(0) == id.charAt(0);
	}
}

public class PersonalInfoTest {
	public static void main(String[] args) {
		final PersonalInfo person = new PersonalInfo();

		// 假设会有两个线程可能更新person对象
		Thread thread1 = new Thread(new Runnable() {
			public void run() {
				while (true)
					person.setNameAndId("Justin Lin", "J.L");
			}
		});

		Thread thread2 = new Thread(new Runnable() {
			public void run() {
				while (true)
					person.setNameAndId("Shang Hwang", "S.H");
			}
		});

		System.out.println("开始测试....");

		thread1.start();
		thread2.start();
	}
}