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

package org.ylzl.eden.spring.framework.core.bind;

import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.core.env.Environment;

import java.util.Properties;

/**
 * 配置属性绑定助手
 *
 * <p>从 Spring Boot 1.X 升级到 2.X
 *
 * <ul>
 *   <li>org.springframework.boot.bind.RelaxedPropertyResolver 变更为 {@link Binder}
 * </ul>
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.0.0
 */
public class SpringBinderHelper {

	private final Environment env;

	public SpringBinderHelper(Environment env) {
		this.env = env;
	}

	public <T> T bind(String prefix, Class<T> type) {
		Binder binder = Binder.get(env);
		BindResult<T> bindResult = binder.bind(prefix, type);
		return bindResult.get();
	}

	public Properties getProperties(String prefix) {
		Iterable<ConfigurationPropertySource> sources = ConfigurationPropertySources.get(env);
		Binder binder = new Binder(sources);
		BindResult<Properties> bindResult = binder.bind(prefix, Properties.class);
		return bindResult.get();
	}
}
