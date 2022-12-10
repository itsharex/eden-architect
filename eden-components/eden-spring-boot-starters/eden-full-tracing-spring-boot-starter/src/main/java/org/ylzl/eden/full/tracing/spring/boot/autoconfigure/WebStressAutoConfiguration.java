/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ylzl.eden.full.tracing.spring.boot.autoconfigure;

import brave.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.ylzl.eden.full.tracing.integration.web.WebStressTagFilter;

/**
 * Web 压测标记自动装配
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@ConditionalOnProperty(value = WebStressAutoConfiguration.ENABLED, havingValue = "true")
@RequiredArgsConstructor
@Slf4j
@Configuration(proxyBeanMethods = false)
public class WebStressAutoConfiguration {

	public static final String ENABLED ="stress.web.enabled";

	@ConditionalOnMissingBean
	@Bean
	public WebStressTagFilter webStressTagFilter(Tracer tracer) {
		return new WebStressTagFilter(tracer);
	}
}
