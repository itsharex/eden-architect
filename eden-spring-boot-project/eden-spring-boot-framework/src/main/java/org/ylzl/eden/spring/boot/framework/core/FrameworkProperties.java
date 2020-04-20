/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ylzl.eden.spring.boot.framework.core;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

/**
 * 框架配置属性
 *
 * @author gyl
 * @since 1.0.0
 */
@Getter
@Setter
@ConfigurationProperties(prefix = FrameworkConstants.PROP_PREFIX)
public class FrameworkProperties {

    private final CorsConfiguration cors = new CorsConfiguration();

    private final Http http = new Http();

    @Getter
    @Setter
    public static class Http {

        public Version version = FrameworkDefaults.Http.version;

        private boolean useUndertowUserCipherSuitesOrder = FrameworkDefaults.Http.useUndertowUserCipherSuitesOrder;

        private final Cache cache = new Cache();

        public enum Version {V_1_1, V_2_0;}

        @Getter
        @Setter
        public static class Cache {

            private int timeToLiveInDays = FrameworkDefaults.Http.Cache.timeToLiveInDays;
        }
    }
}
