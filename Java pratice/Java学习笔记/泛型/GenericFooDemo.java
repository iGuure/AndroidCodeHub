// 定义泛型类，类型通配符
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

		// 类型通配符的作用：
		// 限制某声明可以参考的对象，其类型持有者实例化的对象是实现某接口的类或其子类
		//
		// 类型通配符的特点：
		// 1. 不可以对它加入新的信息
		// 2. 只能取得它当中的信息或是移除当中的信息
		GenericFoo<?> immutableFoo = foo2;
		System.out.println(immutableFoo.getFoo());

		immutableFoo.setFoo(null);
		System.out.println(immutableFoo.getFoo());

		// 不可通过immutableFoo来设置新的信息给Foo所参考的实例
		// 所以下面这行无法通过编译
		// immutableFoo.setFoo(false);
	}
}