package com.synergy.challenge6;

import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.testng.annotations.Test;

public class LoggingTests {
    private static final Logger logger = LoggerFactory.getLogger(LoggingTests.class);

    @Test
    public void testLogging(){
        logger.info(() -> "Test info logging");
        logger.error(() -> "Test error logging");
        logger.warn(() -> "Test warning logging");
        logger.debug(() -> "Test debug logging");
        logger.trace(() -> "Test trace logging");
    }
}
