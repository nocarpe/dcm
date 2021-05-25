package proxy;

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
        IRemote obj =   (IRemote)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IRemote.class},
            new ProxyClass());

        obj.testa("aa");
        obj.testb("bb");
    }


    public static class ProxyClass implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("====" + args);
            return null;
        }
    }

}
