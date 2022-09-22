package org.ylzl.eden.full.link.stress.testing.jdbc.autoconfigure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.ylzl.eden.full.link.stress.testing.jdbc.aop.DataSourceShadowAspect;
import org.ylzl.eden.full.link.stress.testing.jdbc.env.DataSourceShadowProperties;

/**
 * DataSource 影子库自动装配
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
@EnableConfigurationProperties(DataSourceShadowProperties.class)
@RequiredArgsConstructor
@Slf4j
@Configuration(proxyBeanMethods = false)
public class DataSourceShadowAutoConfiguration {

	@Bean
	public DataSourceShadowAspect dataSourceShadowAspect(DataSourceShadowProperties dataSourceShadowProperties) {
		return new DataSourceShadowAspect(dataSourceShadowProperties);
	}
}
