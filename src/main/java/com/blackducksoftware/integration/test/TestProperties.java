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
    private Properties properties;

    public TestProperties() {
        this(TestPropertyKey.values());
    }

    public TestProperties(final Enum<?>[] enumValues) {
        this(TestResourceLoader.DEFAULT_PROPERTIES_FILE_LOCATION, enumValues);
    }

    public TestProperties(final String propertiesLocation) {
        this(propertiesLocation, TestPropertyKey.values());
    }

    public TestProperties(final String propertiesLocation, final Enum<?>[] enumValues) {
        resourceLoader = new TestResourceLoader();
        generateProperties(propertiesLocation, enumValues);
    }

    public Properties getProperties() {
        return properties;
    }

    public boolean isEmpty() {
        return properties == null || properties.isEmpty();
    }

    public String getAndAssumeProperty(final PropertyKey propertyKey) {
        final String propertyKeyName = propertyKey.getEnumName();
        return getAndAssumeProperty(propertyKeyName);
    }

    public String getAndAssumeProperty(final String propertyKey) {
        assumeTrue(propertyKey);
        return getProperty(propertyKey);
    }

    public String getProperty(final PropertyKey propertyKey) {
        String property = properties.getProperty(propertyKey.getEnumName());
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

    public boolean containsKey(final PropertyKey propertyKey) {
        return containsKey(propertyKey.getPropertyName()) || containsKey(propertyKey.getEnumName());
    }

    public boolean containsKey(final String propertyKey) {
        return properties.containsKey(propertyKey);
    }

    private void generateProperties(final String propertiesLocation, final Enum<?>[] enumValues) {
        try {
            properties = resourceLoader.loadProperties(propertiesLocation);
            if (isEmpty()) {
                populatePropertiesFromEnv(enumValues);
            }
        } catch (final Exception ex) {
            System.out.println("Couldn't load the " + propertiesLocation + " file!");
            System.out.println("Reading from the environment...");
            populatePropertiesFromEnv(enumValues);
        }
    }

    private void populatePropertiesFromEnv(final Enum<?>[] enumValues) {
        for (final Enum<?> key : enumValues) {
            final PropertyKey propertyKey = PropertyKey.class.cast(key);
            final String prop = System.getenv(propertyKey.getEnumName());
            if (prop != null && !prop.isEmpty()) {
                properties.put(propertyKey.getPropertyName(), prop);
            }
        }
    }
}
