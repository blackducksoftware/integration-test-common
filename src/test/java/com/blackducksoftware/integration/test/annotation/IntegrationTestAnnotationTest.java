package com.blackducksoftware.integration.test.annotation;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.experimental.categories.Category;

public class IntegrationTestAnnotationTest {

    @Test
    @Category(IntegrationTest.class)
    public void testIntegrationTestAnnotation() {
        assertTrue(true);
    }
}
