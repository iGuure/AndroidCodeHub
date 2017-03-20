import typeinfo.pets.*;
import java.util.*;

class PetSequence {
	protected Pet[] pets = Pets.createArray(8);
}

public class NonCollectionSequence extends PetSequence implements Iterable<Pet> {
	// 为什么要创建迭代器：
	// 1. 该类已经继承了PetSequence，不能再继承AbstractCollection
	// 2. 如果要实现Collection，就必须实现该接口中的所有方法
	// 3. 此时，继承并提供创建迭代器的能力就会显得容易的多！
	public Iterator<Pet> iterator() {
		return new Iterator<Pet>() {
			private int index = 0;
			public boolean hasNext() {
				return index < pets.length;
			}
			public Pet next() { return pets[index++]; }
			public void remove() {	// Not implemented
				throw new UnsupportedOperationException();
			}
		};
	}

	public static void main(String[] args) {
		NonCollectionSequence nc = new NonCollectionSequence();
		InterfaceVsIterator.display(nc.iterator());

		// 任何实现Iterable的类，都可以用于foreach语句中
		for (Pet p : nc)
			System.out.print(p + " ");
		System.out.println();
	}
}

// foreach语句可以用于数组或其他任何Iterable
// 但是：数组不是一个Iterable！也不存在任何从数组到Iterable的自动转换
// 必须使用Arrays.asList(array)手动执行这种转换