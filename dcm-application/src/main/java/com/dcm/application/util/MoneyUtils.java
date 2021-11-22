package com.dcm.application.util;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class MoneyUtils {
    public MoneyUtils() {
    }

    public static String fromFenToYuan(String fen) {
        return (new BigDecimal(fen)).divide(new BigDecimal(100)).setScale(3).toString();
    }

    public static String fromYuanToFen(final String yuan) {
        NumberFormat format = NumberFormat.getInstance();

        Number number;
        try {
            number = format.parse(yuan);
        } catch (Exception var5) {
            return null;
        }

        double temp = number.doubleValue() * 100.0D;
        format.setGroupingUsed(false);
        format.setMaximumFractionDigits(0);
        return format.format(temp);
    }

    public static void main(String[] args) {
        System.out.println(fromFenToYuan("468590.3"));
        System.out.println(fromYuanToFen("468590.236"));
    }
}