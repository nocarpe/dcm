package exe;

import org.apache.dubbo.common.threadlocal.InternalThreadLocal;
import org.apache.dubbo.rpc.RpcContext;

/**
 * @author : yaoximing
 * @date : 2020/9/14
 * @description :
 **/
public class FeignContext {


    private static final InternalContext<FeignContext> FEING_LOCAL = new InternalContext<FeignContext>() {
        @Override
        protected FeignContext initInterValue() throws Exception {
            System.out.println(1);
            return new FeignContext();
        }
    };

    public static void main(String[] args) {
        FeignContext feignContext = new FeignContext();

    }

}
