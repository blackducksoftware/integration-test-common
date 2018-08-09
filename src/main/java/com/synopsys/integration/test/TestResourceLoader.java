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
package com.synopsys.integration.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.stream.Collectors;

public class TestResourceLoader {
    public static final String DEFAULT_PROPERTIES_FILE_LOCATION = "test.properties";
    public static final File DEFAULT_RESOURCE_DIR = new File("./src/test/resources/");

    private final File resourceDirectory;

    public TestResourceLoader() {
        this(DEFAULT_RESOURCE_DIR);
    }

    public TestResourceLoader(final String resourceDirectory) {
        this.resourceDirectory = new File(resourceDirectory);
    }

    public TestResourceLoader(final File resourceDirectory) {
        this.resourceDirectory = resourceDirectory;
    }

    public String loadJsonResource(final String resourceLocation) throws IOException {
        final File file = new File(resourceDirectory, resourceLocation);
        final BufferedReader reader = new BufferedReader(new FileReader(file));
        final String contents = String.join(System.lineSeparator(), reader.lines().collect(Collectors.toList()));
        reader.close();
        return contents;
    }

    public Properties loadProperties(final String resourceLocation) throws IOException {
        final Properties properties = new Properties();

        final File propertiesFile = new File(resourceDirectory, resourceLocation);
        final InputStream iStream = new FileInputStream(propertiesFile);
        properties.load(iStream);
        iStream.close();
        return properties;
    }

}
