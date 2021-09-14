package com.dcm.thread.utils.common;

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
