class GenericFoo<T> {
	private T foo;

	private T[] fooArray;
	
	// 不可以使用泛型建立数组实例
	// private T[] fooArray = new T[10];

	public void setFoo(T foo) {
		this.foo = foo;
	}

	public T getFoo() {
		return foo;
	}
}

public class GenericFooDemo {
	public static void main(String[] args) {
		GenericFoo<Boolean> foo1 = new GenericFoo<Boolean>();
		GenericFoo<Integer> foo2 = new GenericFoo<Integer>();

		foo1.setFoo(new Boolean(true));
		Boolean b = foo1.getFoo();	// 不需要再转换类型
		System.out.println(b);

		foo2.setFoo(new Integer(10));
		Integer i = foo2.getFoo();	// 不需要再转换类型
		System.out.println(i);
	}
}