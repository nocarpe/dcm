package com.dcm.thread.model.design.openclose;

import com.sun.tools.javac.util.Assert;
import java.util.LinkedList;
import java.util.List;

/**
 * @author : yyyao
 * @date : 2025/6/9
 * @description :
 **/
public class Button {

    public final static int SEND_BUTTION = -99;
    private Dialer dialer;
    private int token;

    public Button(int token, Dialer dialer) {
        this.token = token;
        this.dialer = dialer;
    }

    public Button(){
        this.listenerList =new LinkedList<>();
    }

    private List<ButtonListener> listenerList;

    public void addListener(ButtonListener listener){
        assert listener !=null;
        listenerList.add(listener);
    }
    public void prees2(){
        for(ButtonListener bt :listenerList){
            bt.buttonPressed();
        }
    }

    public void prees() throws Exception {
        switch (token) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 9:
                dialer.enterDigit(token);
                break;
            case SEND_BUTTION:
                dialer.dial();
            default:
                throw new Exception("");
        }


    }

}
