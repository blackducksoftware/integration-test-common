package com.synopsys.integration.test;

import java.io.IOException;

import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class TestResourceLoaderTest {
    private static final String RESOURCE_LOADER_TEST = "resource_loader_test.json";

    @Test
    public void testJsonLoader() throws IOException, JSONException {
        final TestResourceLoader resourceLoader = new TestResourceLoader();
        final String resourceJson = resourceLoader.loadJsonResource(RESOURCE_LOADER_TEST);
        final String expectedJson = "{test: 1}";

        JSONAssert.assertEquals(expectedJson, resourceJson, false);
    }
}
