package com.synopsys.integration.test.annotation;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ExternalConnectionTestAnnotationTest {

    @Test
    @Category(ExternalConnectionTest.class)
    public void testExternalConnectionTestAnnotation() {
        assertTrue(true);
    }
}
