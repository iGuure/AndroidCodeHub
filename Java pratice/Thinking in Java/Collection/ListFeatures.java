import typeinfo.pets.*;
import java.util.*;
import static net.mindview.util.Print.*;

public class ListFeatures {
	public static void main(String[] args) {
		Random rand = new Random(47);
		List<Pet> pets = Pets.arrayList(7);
		print("1: " + pets);
		Hamster h = new Hamster();
		pets.add(h); // Automatically resizes
		print("2: " + pets);
		print("3: " + pets.contains(h));
		pets.remove(h); // Remove by object
		Pet p = pets.get(2);
		print("4: " + p + " " + pets.indexOf(p));
		Pet cymric = new Cymric();
		print("5: " + pets.indexOf(cymric));
		print("6: " + pets.remove(cymric));
		// Must be the exact object:
		print("7: " + pets.remove(p));
		print("8: " + pets);
		pets.add(3, new Mouse()); // Insert at an index
		print("9: " + pets);
		List<Pet> sub = pets.subList(1, 4);
		print("subList: " + sub);
		print("10: " + pets.containsAll(sub));
		Collections.sort(sub); // In-place sort
		print("sorted subList: " + sub);
		// Order is not important in containsAll():
		// containsAll()不注重顺序
		print("11: " + pets.containsAll(sub));
		Collections.shuffle(sub, rand); // Mix it up
		print("shuffled subList: " + sub);
		print("12: " + pets.containsAll(sub));

		// subList()所产生的列表的幕后就是初始列表，因此，对所返回的列表的修改都会反映到初始列表中，反之亦然
		print("pets: " + pets);

		List<Pet> copy = new ArrayList<Pet>(pets);
		sub = Arrays.asList(pets.get(1), pets.get(4));
		print("sub: " + sub);
		copy.retainAll(sub);
		print("13: " + copy);
		copy = new ArrayList<Pet>(pets); // Get a fresh copy
		copy.remove(2); // Remove by index
		print("14: " + copy);
		copy.removeAll(sub); // Only removes exact objects
		print("15: " + copy);
		copy.set(1, new Mouse()); // Replace an element
		print("16: " + copy);
		copy.addAll(2, sub);
		print("17: " + copy);
		print("18: " + pets.isEmpty());
		pets.clear();
		print("19: " + pets);
		print("20: " + pets.isEmpty());
		pets.addAll(Pets.arrayList(4));
		print("21: " + pets);
		Object[] o = pets.toArray();
		print("22: " + o[3]);
		// 如果参数数组太小，存放不下List中的所有元素，toArray()方法将创建一个具有合适尺寸的数组
		Pet[] pa  = pets.toArray(new Pet[0]);
		print("23: " + pa[3].id());
	}
}