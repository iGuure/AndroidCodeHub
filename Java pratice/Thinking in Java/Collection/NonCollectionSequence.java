import typeinfo.pets.*;
import java.util.*;

class PetSequence {
	protected Pet[] pets = Pets.createArray(8);
}

public class NonCollectionSequence extends PetSequence {
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
	}
}