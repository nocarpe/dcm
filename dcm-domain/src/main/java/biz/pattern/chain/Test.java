package biz.pattern.chain;

/**
 * @author : yyyao
 * @date : 2024/8/11
 * @description :
 **/
public class Test {


    public static void main(String[] args) {

    }



    public static void testCard(){

        IdCard idCard = new IdCard();
     UserCard userCard = new UserCard(idCard);
        userCard.beforeProcedure();
        userCard.specialProcedure();
        userCard.afterProcedure();
        userCard.commonProcedure();


    }
}
