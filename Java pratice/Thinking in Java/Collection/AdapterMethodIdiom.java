// The "Adapter Method" idiom allows you to use foreach
// with additional kinds of Iterables.
import java.util.*;

class ReversibleArrayList<T> extends ArrayList<T> {
	public ReversibleArrayList(Collection<T> c) { super(c); }
	// 注意这里的写法！
	public Iterable<T> reversed() {
		return new Iterable<T>() {
			public Iterator<T> iterator() {
				return new Iterator<T>() {
					int current = size() - 1;
					public boolean hasNext() { return current > -1; }
					public T next() { return get(current--); }
					public void remove() { // Not implemented
						throw new UnsupportedOperationException();
					}
				};
			}
		};
	}
}

public class AdapterMethodIdiom {
	public static void main(String[] args) {
		ReversibleArrayList<String> ral = new ReversibleArrayList<String>(Arrays.asList("You can't see me".split(" ")));
		// Grabs the ordinary iterator via iterator():
		for (String s : ral)
			System.out.print(s + " ");
		System.out.println();
		// Hand it the Iterable of your choice
		for (String s : ral.reversed())
			System.out.print(s + " ");
		System.out.println();
	}
}