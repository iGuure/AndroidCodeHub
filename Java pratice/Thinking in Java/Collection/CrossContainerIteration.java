import typeinfo.pets.*;
import java.util.*;

public class CrossContainerIteration {
	// display()方法不包含任何有关它所遍历的序列的类型信息
	// Iterator的真正威力：能够将遍历序列的操作与序列底层的结构分离
	//
	// 迭代器统一了对容器的访问方式
	public static void display(Iterator<Pet> it) {
		while (it.hasNext()) {
			Pet p = it.next();
			System.out.print(p.id() + ":" + p + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		ArrayList<Pet> pets = Pets.arrayList(8);
		LinkedList<Pet> petsLL = new LinkedList<Pet>(pets);
		HashSet<Pet> petsHS = new HashSet<Pet>(pets);
		TreeSet<Pet> petsTS = new TreeSet<Pet>(pets);
		display(pets.iterator());
		display(petsLL.iterator());
		display(petsHS.iterator());
		display(petsTS.iterator());
	}
}