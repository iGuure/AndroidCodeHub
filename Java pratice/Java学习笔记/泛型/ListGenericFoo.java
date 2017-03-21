// 限制泛型可用类型
import java.util.*;

public class ListGenericFoo<T extends List> {
	private T[] fooArray;

	public void setFooArray(T[] fooArray) {
		this.fooArray = fooArray;
	}

	public T[] getFooArray() {
		return fooArray;
	}

	public static void main(String[] args) {
		ListGenericFoo<LinkedList> foo1 = new ListGenericFoo<LinkedList>();
		ListGenericFoo<ArrayList> foo2 = new ListGenericFoo<ArrayList>();
	}
}