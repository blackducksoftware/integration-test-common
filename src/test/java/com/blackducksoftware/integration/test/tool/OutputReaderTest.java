package com.blackducksoftware.integration.test.tool;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OutputReaderTest {

    @Test
    public void testOutputReader() throws IOException {
        try (OutputReader outputReader = new OutputReader()) {
            System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "DEBUG");
            final Logger logger = LoggerFactory.getLogger(OutputReaderTest.class);

            logger.info("First log message");
            logger.debug("First debug");

            assertTrue(outputReader.containsText("First"));
            assertTrue(outputReader.containsText("debug"));
            assertFalse(outputReader.containsText("Second"));
            assertTrue(outputReader.containsText("t l"));
            assertFalse(outputReader.containsText("debug message"));

            assertEquals(2, outputReader.getOutputLines().length);
        }
    }
}
