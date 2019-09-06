package annotation.demo2;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Демонстрация примера, как с помощью рефлексии получить сведения об
 * аннотациях полей, методов, и классов.
 */
class MainClass {
	public static void main(String... args) {    
		Class1 class1 = new Class1();
		class1.method1();
		try {
			Class c = Class.forName("annotation.demo2.Class1");
			Field[] fs = c.getDeclaredFields();
			for (Field f: fs ) {
				//System.out.println(f.getName());
				if (f.isAnnotationPresent(FieldAn.class)) {
				  System.out.println("annotation for field: "
                  + f.getAnnotation(FieldAn.class));
        }
			}

			Method[] methods = c.getDeclaredMethods();
			for (Method m: methods) {
				//System.out.println(m.getName());
				if (m.isAnnotationPresent(MethodAn.class)) {
					System.out.println("annotation for method: "
      					+ m.getAnnotation(MethodAn.class));
				} else {
					System.out.println("no annotation ");
				}
			}

			//System.out.println(c.getName());
			if (c.isAnnotationPresent(ClassAn.class)) {
			  System.out.println("annotation for class: "
                + c.getAnnotation(ClassAn.class));
      } else {
			  System.out.println("no annotation ");
      }
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}






		

    
    
    
    
    
    
    
    