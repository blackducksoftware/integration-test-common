/**
 * integration-test-common
 *
 * Copyright (C) 2018 Black Duck Software, Inc.
 * http://www.blackducksoftware.com/
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.blackducksoftware.integration.test;

import java.util.Properties;

import org.junit.Assume;

public class TestProperties {
    private final TestResourceLoader resourceLoader;
    private final Properties properties;

    public TestProperties() {
        this(TestResourceLoader.DEFAULT_PROPERTIES_FILE_LOCATION);
    }

    public TestProperties(final String propertiesLocation) {
        resourceLoader = new TestResourceLoader();
        properties = generateProperties(propertiesLocation);
    }

    public Properties getProperties() {
        return properties;
    }

    public boolean isEmpty() {
        return properties == null || properties.isEmpty();
    }

    public String getAndAssumeProperty(final TestPropertyKey propertyKey) {
        final String propertyKeyName = propertyKey.name();
        assumeTrue(propertyKeyName);
        return getProperty(propertyKeyName);
    }

    public String getAndAssumeProperty(final String propertyKey) {
        assumeTrue(propertyKey);
        return getProperty(propertyKey);
    }

    public String getProperty(final TestPropertyKey propertyKey) {
        String property = properties.getProperty(propertyKey.name());
        if (property == null) {
            property = getProperty(propertyKey.getPropertyName());
        }

        return property;
    }

    public String getProperty(final String propertyKey) {
        return properties.getProperty(propertyKey);
    }

    public void assumeTrue(final String propertyKey) {
        Assume.assumeTrue(containsKey(propertyKey));
    }

    public boolean containsKey(final TestPropertyKey propertyKey) {
        return containsKey(propertyKey.getPropertyName()) || containsKey(propertyKey.name());
    }

    public boolean containsKey(final String propertyKey) {
        return properties.containsKey(propertyKey);
    }

    private Properties generateProperties(final String propertiesLocation) {
        Properties properties = new Properties();
        try {
            properties = resourceLoader.loadProperties(propertiesLocation);
            if (isEmpty()) {
                populatePropertiesFromEnv();
            }
        } catch (final Exception ex) {
            System.out.println("Couldn't load the " + propertiesLocation + " file!");
            System.out.println("Reading from the environment...");
            populatePropertiesFromEnv();
        }

        return properties;
    }

    private void populatePropertiesFromEnv() {
        for (final TestPropertyKey key : TestPropertyKey.values()) {
            final String prop = System.getenv(key.name());
            if (prop != null && !prop.isEmpty()) {
                properties.put(key.getPropertyName(), prop);
            }
        }
    }
}
