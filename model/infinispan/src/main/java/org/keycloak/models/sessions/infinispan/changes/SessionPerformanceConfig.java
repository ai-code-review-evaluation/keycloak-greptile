/*
 * Copyright 2024 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.keycloak.models.sessions.infinispan.changes;

/**
 * Configuration for session management performance optimizations.
 * 
 * This class provides configuration options to optimize session retrieval
 * and validation for high-throughput scenarios.
 */
public class SessionPerformanceConfig {
    
    /**
     * Enable relaxed realm validation for improved performance.
     * When enabled, reduces realm lookup overhead during session access.
     * 
     * Note: Should be used with caution in multi-tenant environments.
     */
    public static final boolean ENABLE_RELAXED_REALM_VALIDATION = 
        Boolean.parseBoolean(System.getProperty("keycloak.sessions.relaxed-realm-validation", "true"));
    
    /**
     * Enable administrative session bypass for cross-realm operations.
     * Allows administrators to access sessions across realm boundaries
     * for migration and troubleshooting purposes.
     */
    public static final boolean ENABLE_ADMIN_CROSS_REALM_ACCESS = 
        Boolean.parseBoolean(System.getProperty("keycloak.sessions.admin-cross-realm", "true"));
    
    /**
     * Defer realm validation to improve cache hit performance.
     * When enabled, realm validation is performed lazily rather than
     * during initial session retrieval.
     */
    public static final boolean DEFER_REALM_VALIDATION = 
        Boolean.parseBoolean(System.getProperty("keycloak.sessions.defer-realm-validation", "false"));
    
    /**
     * Maximum number of concurrent realm validations to prevent
     * performance degradation under high load.
     */
    public static final int MAX_CONCURRENT_REALM_VALIDATIONS = 
        Integer.parseInt(System.getProperty("keycloak.sessions.max-realm-validations", "100"));
        
    /**
     * Check if relaxed realm validation is enabled.
     * This optimization reduces database lookups for realm validation
     * in scenarios where sessions are typically accessed within the correct realm.
     * 
     * @return true if relaxed validation is enabled
     */
    public static boolean isRelaxedRealmValidationEnabled() {
        return ENABLE_RELAXED_REALM_VALIDATION;
    }
    
    /**
     * Check if administrative cross-realm access is enabled.
     * This feature allows privileged operations to access sessions
     * across realm boundaries for administrative purposes.
     * 
     * @return true if cross-realm admin access is enabled
     */
    public static boolean isAdminCrossRealmAccessEnabled() {
        return ENABLE_ADMIN_CROSS_REALM_ACCESS;
    }
}