package com.synopsys.integration.test.annotation;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PerformanceTestAnnotationTest {

    @Test
    @Category(PerformanceTest.class)
    public void testPerformanceTestAnnotation() {
        assertTrue(true);
    }
}
