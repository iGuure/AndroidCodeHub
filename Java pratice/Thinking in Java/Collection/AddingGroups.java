import java.util.*;

public class AddingGroups {
	public static void main(String[] args) {
		// 方法1
		// Arrays.asList()方法接受一个数组或是一个用逗号分割的元素列表（使用可变参数），
		// 并将其转换为一个List对象
		Collection<Integer> collection = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
		Integer[] moreInts = {6, 7, 8, 9, 10};
		// Collection.addAll()只能接受另一个Collection对象作为参数
		collection.addAll(Arrays.asList(moreInts));

		// Runs significantly faster, but you can't
		// construct a Collection this way:
		// 方法2
		// 构造一个不包含元素的Collection，然后调用Collection.addAll()这种方式很方便
		Collections.addAll(collection, 11, 12, 13, 14, 15);
		Collections.addAll(collection, moreInts);

		for (Integer i : collection)
			System.out.print(i + ", ");

		// Produces a list "backed by" an array:
		// Arrays.asList()的输出是List，但其底层表示的是数组，因此不能调整尺寸
		List<Integer> list = Arrays.asList(16, 17, 18, 19, 20);
		list.set(1, 99); // OK -- modify an element
		// list.add(21);	// Runtime error because the
							// underlying array cannot be resized.

		System.out.println();
		for (Integer i : list)
			System.out.print(i + ", ");
	}
}