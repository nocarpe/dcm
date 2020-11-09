import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @author : yaoximing
 * @date : 2020/10/30
 * @description :
 **/
public class Test {

    public static void main(String[] args) {

        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        Date date1 = Date.from(zonedDateTime.toInstant());
        LocalDateTime localDateTime1= localDateTime.plusMinutes(1l).plusSeconds(2l);
        //localDateTime1 = localDateTime1.plusSeconds(2l);
        System.out.println(localDateTime);
        System.out.println(localDateTime1);
        System.out.println(Duration.between(localDateTime1, localDateTime).getSeconds());

    }

}
