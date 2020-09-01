import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author : yaoximing
 * @date : 2020/8/20
 * @description :
 **/
public class Test {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        //Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),);
    }


     class ProxyClass implements InvocationHandler{


         @Override
         public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
             return null;
         }
     }

}
