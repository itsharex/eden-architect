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

package org.ylzl.eden.spring.boot.integration.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.LoggingProducerListener;
import org.springframework.kafka.support.ProducerListener;
import org.ylzl.eden.spring.boot.framework.core.FrameworkConstants;

/**
 * Kafka 配置
 *
 * @author gyl
 * @since 1.0.0
 */
@ConditionalOnClass({
    KafkaTemplate.class,
    EnableKafka.class
})
@ConditionalOnExpression(EnhancedKafkaAutoConfiguration.EXPS_KEFKA_ENABLED)
@EnableConfigurationProperties(EnhancedKafkaProperties.class)
@Slf4j
@Configuration
public class EnhancedKafkaAutoConfiguration {

    public static final String EXPS_KEFKA_ENABLED = "${" + FrameworkConstants.PROP_SPRING_PREFIX + ".kafka.enabled:true}";

    public static final String MSG_INJECT_KAFKA_TEMPLATE = "Inject KafkaTemplate";

    private final EnhancedKafkaProperties properties;

    public EnhancedKafkaAutoConfiguration(EnhancedKafkaProperties enhancedKafkaProperties) {
        this.properties = enhancedKafkaProperties;
    }
}