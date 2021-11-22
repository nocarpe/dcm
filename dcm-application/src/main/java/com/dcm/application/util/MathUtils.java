package com.dcm.application.util;

import java.math.BigDecimal;
import org.apache.commons.lang.math.NumberUtils;

public final class MathUtils {
    private static int DEF_SCALE = 10;

    public MathUtils() {
    }

    public static final strictfp BigDecimal createBigDecimal(String decimalStr) {
        return NumberUtils.createBigDecimal(decimalStr);
    }

    public static final strictfp BigDecimal createBigDecimal(Number number) {
        return createBigDecimal(String.valueOf(number));
    }

    public static final strictfp BigDecimal createBigDecimal(float number) {
        return createBigDecimal(String.valueOf(number));
    }

    public static final strictfp double add(Number num1, Number num2) {
        BigDecimal result = createBigDecimal(num1).add(createBigDecimal(num2));
        return result.setScale(DEF_SCALE, 4).doubleValue();
    }

    public static final strictfp double subtract(Number num1, Number num2) {
        BigDecimal result = createBigDecimal(num1).subtract(createBigDecimal(num2));
        return result.setScale(DEF_SCALE, 4).doubleValue();
    }

    public static final strictfp float subtract(float num1, float num2) {
        BigDecimal result = createBigDecimal(num1).subtract(createBigDecimal(num2));
        return result.setScale(DEF_SCALE, 4).floatValue();
    }

    public static final strictfp double multiply(Number num1, Number num2) {
        BigDecimal result = createBigDecimal(num1).multiply(createBigDecimal(num2));
        return result.setScale(DEF_SCALE, 4).doubleValue();
    }

    public static final strictfp double multiply(Number num1, Number num2, int scale) {
        return multiplyDecimal(num1, num2, scale).doubleValue();
    }

    public static final strictfp BigDecimal multiplyDecimal(Number num1, Number num2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("精度必须>=0");
        } else {
            BigDecimal result = createBigDecimal(num1).multiply(createBigDecimal(num2));
            return result.setScale(scale, 4);
        }
    }

    public static final strictfp double divide(Number num1, Number num2) {
        return divide(num1, num2, DEF_SCALE);
    }

    public static final strictfp double divide(Number num1, Number num2, int scale) {
        return divideDecimal(num1, num2, scale).doubleValue();
    }

    public static final strictfp BigDecimal divideDecimal(Number num1, Number num2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("精度必须>=0");
        } else {
            BigDecimal dividend = createBigDecimal(num1);
            BigDecimal divisor = createBigDecimal(num2);
            return dividend.divide(divisor, scale, 4);
        }
    }

    public static final strictfp double round(double num, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("精度必须>=0");
        } else {
            BigDecimal result = createBigDecimal((Number)num).divide(createBigDecimal("1"), scale, 4);
            return result.doubleValue();
        }
    }

    public static strictfp void main(String[] args) {
        System.out.println(round(0.3959243086D, 4));
    }
}