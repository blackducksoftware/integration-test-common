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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.blackducksoftware.integration.log.IntLogger;
import com.blackducksoftware.integration.log.LogLevel;
import com.google.common.collect.EvictingQueue;

public class TestLogger extends IntLogger {
    public static final int DEFAULT_LOG_SIZE = 1000;

    private LogLevel logLevel = LogLevel.TRACE;

    private final EvictingQueue<String> outputQueue;
    private final EvictingQueue<Throwable> errorQueue;

    public TestLogger() {
        this(DEFAULT_LOG_SIZE);
    }

    public TestLogger(final int listSize) {
        outputQueue = EvictingQueue.create(listSize);
        errorQueue = EvictingQueue.create(listSize);
    }

    public EvictingQueue<String> getOutputQueue() {
        return outputQueue;
    }

    public EvictingQueue<Throwable> getErrorQueue() {
        return errorQueue;
    }

    public void resetOutputList() {
        outputQueue.clear();
    }

    public void resetErrorList() {
        errorQueue.clear();
    }

    public void resetAllOutput() {
        resetOutputList();
        resetErrorList();
    }

    public String getOutputString() {
        return StringUtils.join(outputQueue, System.getProperty("line.separator"));
    }

    public String getErrorOutputString() {
        if (errorQueue == null || errorQueue.isEmpty()) {
            return "";
        }

        final List<String> stackTraces = new ArrayList<>();
        for (final Throwable e : errorQueue) {
            final StringWriter stringWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(stringWriter));
            stackTraces.add(stringWriter.toString());
        }

        return StringUtils.join(stackTraces, System.getProperty("line.separator"));
    }

    @Override
    public void alwaysLog(final String txt) {
        System.out.println(String.format("always log: %s", txt));
        outputQueue.add(txt);
    }

    @Override
    public void debug(final String txt) {
        System.out.println(String.format("debug: %s", txt));
        outputQueue.add(txt);
    }

    @Override
    public void debug(final String txt, final Throwable e) {
        debug(txt);
        System.out.println(String.format("exception: %s", e.getMessage()));
        errorQueue.add(e);
    }

    @Override
    public void error(final Throwable e) {
        System.out.println(String.format("error: exception: %s", e.getMessage()));
        errorQueue.add(e);
    }

    @Override
    public void error(final String txt) {
        System.out.println(String.format("error: %s", txt));
        outputQueue.add(txt);
    }

    @Override
    public void error(final String txt, final Throwable e) {
        error(txt);
        error(e);
    }

    @Override
    public void info(final String txt) {
        System.out.println(String.format("info: %s", txt));
        outputQueue.add(txt);
    }

    @Override
    public void trace(final String txt) {
        outputQueue.add(txt);
    }

    @Override
    public void trace(final String txt, final Throwable e) {
        trace(txt);
        errorQueue.add(e);
    }

    @Override
    public void warn(final String txt) {
        System.out.println(String.format("warn: %s", txt));
        outputQueue.add(txt);
    }

    @Override
    public void setLogLevel(final LogLevel level) {
        logLevel = level;
    }

    @Override
    public LogLevel getLogLevel() {
        return logLevel;
    }

}
