import com.alibaba.fastjson.JSON;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @author : yaoximing
 * @date : 2021-04-09
 * @description :
 **/
public class Test1 {

    public static void main(String[] args) {
        BigDecimal bi = new BigDecimal("3.45");
        BigDecimal bi2 = new BigDecimal("1.45");
        BigDecimal bi3 = new BigDecimal("1.115");
        List<BigDecimal> list = new ArrayList<>();
        list.add(bi);
        list.add(bi2);
        list.sort(new Comparator<BigDecimal>() {
            @Override
            public int compare(BigDecimal o1, BigDecimal o2) {
                return o2.subtract(o1).compareTo(BigDecimal.ZERO);
            }
        });

        for(BigDecimal b:list){
            bi3 =bi3.add(b);
        }

        BigDecimal[] bigDecimals = bi2.divideAndRemainder(bi);
        System.out.println(bi.divide(bi2,3,BigDecimal.ROUND_HALF_UP));
        System.out.println(list.size());
        System.out.println(JSON.toJSONString(list));
    }

}
