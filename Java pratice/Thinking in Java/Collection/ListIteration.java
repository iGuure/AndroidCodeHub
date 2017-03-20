import typeinfo.pets.*;
import java.util.*;

public class ListIteration {
	public static void main(String[] args) {
		List<Pet> pets = Pets.arrayList(8);
		ListIterator<Pet> it = pets.listIterator();
		while (it.hasNext())
			// System.out.print()作用于指针指向的元素
			System.out.print(it.next() + ", " + it.nextIndex() + ", " + it.previousIndex() + "; ");
		System.out.println();
		// Backwards:
		// iterator指针在两个元素的中间！
		// 当执行iterator.next()或iterator.previous()时，先执行使指针指向后/前一个元素，再向后/前移动指针！
		// 区分指针“在哪里”和“指向哪个元素”
		while (it.hasPrevious())
			System.out.print(it.previous().id() + " ");
		System.out.println();
		System.out.println(pets);
		it = pets.listIterator(3);
		while (it.hasNext()) {
			it.next();
			// iterator.set()作用于指针指向的元素
			it.set(Pets.randomPet());
		}
		System.out.println(pets);
	}
}