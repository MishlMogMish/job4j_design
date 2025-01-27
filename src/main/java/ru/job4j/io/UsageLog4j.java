package ru.job4j.io;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte b = 2;
        short s = 40;
        int i = 56789;
        boolean bool = true;
        double d = Math.PI;
        float f = 1.123F;
        long l = 12345678987654321L;
        char c = 'A';

        LOG.debug("1.byte : {}, 2.short : {}, 3.int : {},  4.boolean : {}, "
                + "5.double : {}, 6.float : {}, 7.long : {}, 8.char : {}", b, s, i, bool, d, f, l, c);
    }
}