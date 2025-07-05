package com.dcm.thread.model.design.openclose;

/**
 * @author : yyyao
 * @date : 2025/6/9
 * @description :
 **/
public class Phone {

    private Dialer dialer;
    private Button[] digitButtons;
    private Button sendButton;

    public Phone() {
        dialer = new Dialer();
        digitButtons = new Button[10];

        for (int i = 0; i < digitButtons.length; i++) {
            digitButtons[i] = new Button();
            final int digit = i;
            digitButtons[i].addListener(new ButtonListener() {
                @Override
                public void buttonPressed() {
                    dialer.enterDigit(digit);
                }
            });

        }
        sendButton = new Button();
        sendButton.addListener(new ButtonListener() {
            @Override
            public void buttonPressed() {
                dialer.dial();
            }
        });


    }

}
