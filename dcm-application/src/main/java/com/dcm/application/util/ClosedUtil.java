package com.dcm.application.util;

public class ClosedUtil {
    private static boolean closed = false;

    public ClosedUtil() {
    }

    public static boolean isClosed() {
        return closed;
    }

    public static void setClosed() {
        closed = true;
    }

    public static void reSetClosed() {
        closed = false;
    }
}
