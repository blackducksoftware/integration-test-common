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
package com.blackducksoftware.integration.test.tool;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class OutputReader implements Closeable {
    private OutputStream systemOut;
    private OutputStream systemErr;
    private OutputStream loggerOutput;

    public OutputReader() throws IOException {
        init();
    }

    public void init() throws IOException {
        systemOut = System.out;
        systemErr = System.err;
        loggerOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(loggerOutput));
        System.setErr(new PrintStream(loggerOutput));
    }

    public String[] getOutputLines() throws IOException {
        loggerOutput.flush();
        final String[] consoleLines = loggerOutput.toString().split(System.lineSeparator());
        return consoleLines;
    }

    public void printLoggerOutput() throws IOException {
        final String[] consoleLines = getOutputLines();
        for (final String line : consoleLines) {
            System.out.println(line);
        }
    }

    public boolean containsText(final String text) throws IOException {
        final String[] consoleLines = getOutputLines();
        for (final String line : consoleLines) {
            if (line.contains(text)) {
                return true;
            }
        }
        return false;
    }

    public void cleanup() throws IOException {
        loggerOutput.close();
        System.setOut(new PrintStream(systemOut));
        System.setErr(new PrintStream(systemErr));
        printLoggerOutput();
    }

    @Override
    public void close() throws IOException {
        cleanup();
    }
}
