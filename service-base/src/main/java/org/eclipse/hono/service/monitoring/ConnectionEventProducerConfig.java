/**
 * Copyright (c) 2020 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */


package org.eclipse.hono.service.monitoring;

import java.util.Objects;

/**
 * Properties for selecting and configuring a {@code ConnectionEventProducer}.
 *
 */
public final class ConnectionEventProducerConfig {

    public static final String DEFAULT_LOG_LEVEL = "info";
    public static final ConnectionEventProducerType DEFAULT_TYPE = ConnectionEventProducerType.logging;

    private ConnectionEventProducerType type = DEFAULT_TYPE;
    private String logLevel = DEFAULT_LOG_LEVEL;
    private boolean debugLogLevel = false;

//    public void setProducer(final String type) {
//        this.type = ConnectionEventProducerType.from(type);
//    }

    public void setProducer(final ConnectionEventProducerType type) {
        this.type = Objects.requireNonNull(type);
    }

    public ConnectionEventProducerType getType() {
        return type;
    }

    /**
     * Sets the level to log information at if the <em>type</em> is {@code logging}.
     * <p>
     * The default value of this property is {@value #DEFAULT_LOG_LEVEL}.
     *
     * @param level The level to log at.
     * @throws NullPointerException if level is {@code null}.
     * @throws IllegalArgumentException if level is anything other than <em>debug</em> or <em>info</em>.
     */
    public void setLogLevel(final String level) {
        Objects.requireNonNull(level);
        final String levelToUse = level.toLowerCase();
        switch (levelToUse) {
        case "debug":
            debugLogLevel = true;
            // fall through
        case "info":
            logLevel = levelToUse;
            break;
        default:
            throw new IllegalArgumentException("unsupported log level");
        }
    }

    public String getLogLevel() {
        return logLevel;
    }

    public boolean isDebugLogLevel() {
        return debugLogLevel;
    }

    /**
     * Types of event producers.
     *
     */
    public enum ConnectionEventProducerType {

        none, logging, events;

        /**
         * Gets a type for a type name.
         *
         * @param name The type's name.
         * @return The type.
         */
        public static ConnectionEventProducerType from(final String name) {

            final String nameToCheck = Objects.requireNonNull(name).toLowerCase();
            for (ConnectionEventProducerType type : values()) {
                if (type.name().equals(nameToCheck)) {
                    return type;
                }
            }
            return none;
        }
    }
}
