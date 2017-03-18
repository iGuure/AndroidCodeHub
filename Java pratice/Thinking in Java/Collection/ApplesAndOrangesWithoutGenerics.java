import java.util.*;

class Apple {
	private static long counter;
	private final long id = counter++;
	public long id() {
		return id;
	}
}

class Orange {}

public class ApplesAndOrangesWithoutGenerics {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		ArrayList apples = new ArrayList();
		for (int i = 0; i < 3; i++)
			apples.add(new Apple());
		// Not prevented from adding an orange to apples:
		apples.add(new Orange());
		for (int i = 0; i < apples.size(); i++)
			// ArrayList的get()方法得到的只是Object引用
			((Apple)apples.get(i)).id();
			// Orange is detected only at run time

		ArrayList<Apple> apples2 = new ArrayList<Apple>();
		for (int i = 0; i < 3; i++)
			apples2.add(new Apple());
		// Compile-time error:
		// apples2.add(new Orange());
		for (int i = 0; i < apples2.size(); i++)
			// 通过使用泛型，ArrayList知道它保存的是什么类型，因此它会在调用get()时替你执行转型
			System.out.println(apples2.get(i).id());
		for (Apple c : apples2)
			System.out.println(c.id());
	}
}