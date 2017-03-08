/*
 ~ 如何使用Java的回调机制 ~

 Q：如何理解回调机制？
 A：假设有三个方法。方法a为回调方法，方法b的参数包含方法a（或为包含方法a的抽象类/接口），方法c调用方法b。在方法c调用方法b的时候，我们可以重写方法a的实现。这样当方法b中调用方法a的部分运行时，调用的是方法c中写的实现。
*/
public class Test {

	/*
	 2. 定义方法，把1中定义的接口作为参数传进方法，调用接口中的回调方法。
	*/
	static void function(Callback cb) {
		System.out.println("this is a function");
		cb.callBackFunction();
	}

	public static void main(String[] args) {
		/*
		 3. 在另外的方法体中运行2中定义的方法，在这里实现接口中的回调方法。
		*/
		function(new Callback() {
			public void callBackFunction() {
				System.out.println("this is a callback function");
			}
		});
	}

}

/*
 1. 定义接口，在里面定义回调方法。
*/
interface Callback {
	void callBackFunction();
}