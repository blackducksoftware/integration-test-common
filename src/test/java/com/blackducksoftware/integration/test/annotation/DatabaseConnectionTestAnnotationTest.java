package com.blackducksoftware.integration.test.annotation;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DatabaseConnectionTestAnnotationTest {

    @Test
    @Category(DatabaseConnectionTest.class)
    public void testDatabaseConnectionTestAnnotation() {
        assertTrue(true);
    }
}
