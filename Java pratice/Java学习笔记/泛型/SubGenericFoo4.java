// 扩充泛型类
class GenericFoo4<T1, T2> {
	private T1 foo1;
	private T2 foo2;
	
	public void setFoo1(T1 foo1) {
	    this.foo1 = foo1;
	}
	
	public T1 getFoo1() {
	    return foo1;
	}
	
	public void setFoo2(T2 foo2) {
	    this.foo2 = foo2;
	}
	
	public T2 getFoo2() {
	    return foo2;
	}
}

public class SubGenericFoo4<T1, T2, T3> extends GenericFoo4<T1, T2> {
	private T3 foo3;
	
	public void setFoo3(T3 foo3) {
	    this.foo3 = foo3;
	}
	
	public T3 getFoo3() {
	    return foo3;
	}

	public static void main(String[] args) {
		
	}
}