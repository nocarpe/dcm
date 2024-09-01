package biz.pattern.chain;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author : yyyao
 * @date : 2024/8/11
 * @description :
 **/
public class UserCard extends AbstractUserRightsTemplate{

    @Autowired
    private IdCard idCard;


    public UserCard(IdCard idCard) {
        super(idCard);
    }

    @Override
    public void beforeProcedure() {

    }

    @Override
    public void specialProcedure() {

    }
}
