package com.blackducksoftware.integration.test.annotation;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.experimental.categories.Category;

public class HubConnectionTestAnnotationTest {

    @Test
    @Category(HubConnectionTest.class)
    public void testHubConnectionTestAnnotation() {
        assertTrue(true);
    }
}
