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

public enum TestPropertyKey {
    TEST_HUB_SERVER_URL("blackduck.hub.url"),
    TEST_HUB_PORT("blackduck.hub.port"),
    TEST_USERNAME("blackduck.hub.username"),
    TEST_PASSWORD("blackduck.hub.password"),
    TEST_HUB_API_KEY("blackduck.hub.api.key"),
    TEST_TRUST_HTTPS_CERT("blackduck.hub.trust.cert"),
    TEST_HUB_TIMEOUT("blackduck.hub.timeout"),
    TEST_ACTIVE_USER("blackduck.hub.active.user"),
    TEST_INACTIVE_USER("blackduck.hub.inactive.user");

    private String propertyName;

    TestPropertyKey(final String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyName() {
        return propertyName;
    }
}
