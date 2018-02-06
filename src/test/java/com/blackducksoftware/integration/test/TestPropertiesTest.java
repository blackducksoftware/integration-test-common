package com.blackducksoftware.integration.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestPropertiesTest {
    private static final String CUSTOM_TEST_PROPERTIES = "test_properties.properties";

    @Test
    public void testNewTestPropertiesName() {
        final TestProperties testProperties = new TestProperties(CUSTOM_TEST_PROPERTIES);

        final String test1 = testProperties.getProperty("test");
        final String test2 = testProperties.getProperty("test2");

        assertEquals("1", test1);
        assertEquals("2", test2);

        final String propertyKeyEnum = testProperties.getProperty(TestPropertyKey.TEST_HUB_SERVER_URL);
        final String propertyKeyString = testProperties.getProperty(TestPropertyKey.TEST_HUB_PORT);

        assertEquals("fakeUrl", propertyKeyEnum);
        assertEquals("44", propertyKeyString);
    }

    @Test
    public void testPropertyUtilMethods() {
        final TestProperties testProperties = new TestProperties(CUSTOM_TEST_PROPERTIES);

        assertFalse(testProperties.isEmpty());
        assertFalse(testProperties.containsKey("empty"));
        assertTrue(testProperties.containsKey("blackduck.hub.port"));
        assertTrue(testProperties.containsKey(TestPropertyKey.TEST_HUB_SERVER_URL));
    }

    @Test
    public void testEmptyTestPropertyFile() {
        final TestProperties testProperties = new TestProperties("empty.properties");

        assertTrue(testProperties.isEmpty());

        final TestProperties testPropertiesFull = new TestProperties(CUSTOM_TEST_PROPERTIES);
        
        assertFalse(testPropertiesFull.isEmpty());
        assertEquals("1", testPropertiesFull.getProperty("test"));
    }
}
