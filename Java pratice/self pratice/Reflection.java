import java.util.*;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;

class Paul {
	public void teach() {
		System.out.print("Bible blablabla");
	}
}

class Wu {
	public void teach() {
		System.out.print("Read the PPT together");
	}
}

public class Reflection {
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		String className = scanner.next();
		String methodName = scanner.next();

		Class clazz = Class.forName(className);
		Method method = clazz.getMethod(methodName);

		Object teacher = clazz.newInstance();
		method.invoke(teacher);
	}
}