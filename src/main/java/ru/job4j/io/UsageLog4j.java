package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        /**
         LOG.trace("trace message");
         LOG.debug("debug message");
         LOG.info("info message");
         LOG.warn("warn message");
         LOG.error("error message");
         */
        double str = 2.7;
        int num = 1;
        boolean flag = true;
        char ch = 120;
        float fl = 30.6f;
        byte num2 = 11;
        short num3 = 111;
        long num4 = 2147483649L;
        LOG.debug("This is double : {}, this is int: {}, boolean - {} and char - {}", str, num, flag, ch);
        LOG.debug("also float : {}, byte: {}, short - {} and long - {}", fl, num2, num3, num4);
    }
}
