package com.blackducksoftware.integration.test.tool;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestLoggerTest {

    @Test
    public void testOutputLogger() {
        final TestLogger testLogger = new TestLogger(1);

        testLogger.info("First");
        testLogger.info("Second");
        testLogger.info("Third");

        assertEquals(1, testLogger.getOutputQueue().size());
        assertEquals("Third", testLogger.getOutputString());
    }

    @Test
    public void testErrorLogger() {
        final TestLogger testLogger = new TestLogger();
        final NullPointerException npe = new NullPointerException();

        testLogger.error("First", npe);
        testLogger.debug("Second", npe);

        final String errorOutput = testLogger.getErrorOutputString();
        System.out.println(errorOutput);
        assertTrue(errorOutput.length() > 1);
    }
}
