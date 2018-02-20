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
package com.blackducksoftware.integration.test.annotation;

import java.util.HashMap;
import java.util.Map;

public class TestCategories {
    public Map<String, String> getTestTasksAndPackages() {
        final Map<String, String> testTasksAndPackages = new HashMap<>();
        testTasksAndPackages.put("testIntegration", "com.blackducksoftware.integration.test.annotation.IntegrationTest");
        testTasksAndPackages.put("testDatabaseConnection", "com.blackducksoftware.integration.test.annotation.DatabaseConnectionTest");
        testTasksAndPackages.put("testExternalConnection", "com.blackducksoftware.integration.test.annotation.ExternalConnectionTest");
        testTasksAndPackages.put("testHubConnection", "com.blackducksoftware.integration.test.annotation.HubConnectionTest");
        testTasksAndPackages.put("testPerformance", "com.blackducksoftware.integration.test.annotation.PerformanceTest");
        return testTasksAndPackages;
    }

}
