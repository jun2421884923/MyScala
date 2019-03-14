package com.java;

import org.apache.log4j.Logger;

public class TestLog {

    private static Logger logger=Logger.getLogger("TestLog");

    public static void main(String[] args) {
        logger.debug("debugtest");
        logger.info("infotest1");
        logger.info("infotest2");
//        logger.warn("warntest");
        logger.error("error");

    }

}
