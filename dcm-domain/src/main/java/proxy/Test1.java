package proxy;

/**
 * @author : yaoximing
 * @date : 2020/9/4
 * @description :
 **/
public class Test1 implements IRemote {

    @Override
    public void testa(String a) {
        System.out.print("test1a:" + a);
    }

    @Override
    public void testb(String a) {
        System.out.print("test1b:" + a);
    }
}
