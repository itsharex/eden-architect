package org.ylzl.eden.dynamic.mq.spring.boot.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.autoconfigure.RocketMQProperties;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.ylzl.eden.dynamic.mq.core.MessageQueueConsumer;
import org.ylzl.eden.dynamic.mq.core.MessageQueueProvider;
import org.ylzl.eden.dynamic.mq.integration.rocketmq.RocketMQConsumer;
import org.ylzl.eden.dynamic.mq.integration.rocketmq.RocketMQProvider;
import org.ylzl.eden.dynamic.mq.spring.boot.support.MessageQueueBeanNames;
import org.ylzl.eden.dynamic.mq.spring.boot.env.RocketMQConsumerProperties;
import org.ylzl.eden.dynamic.mq.spring.boot.env.RocketMQProducerProperties;
import org.ylzl.eden.dynamic.mq.spring.boot.env.MessageQueueProperties;
import org.ylzl.eden.dynamic.mq.spring.boot.env.convertor.RocketMQConsumerConvertor;
import org.ylzl.eden.dynamic.mq.spring.boot.env.convertor.RocketMQProducerConvertor;
import org.ylzl.eden.commons.lang.StringUtils;
import org.ylzl.eden.spring.boot.bootstrap.constant.Conditions;

import java.util.List;
import java.util.function.Function;

/**
 * RocketMQ 自动配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@ConditionalOnProperty(
	prefix = MessageQueueProperties.Kafka.PREFIX,
	name = Conditions.ENABLED,
	havingValue = Conditions.ENABLED_TRUE
)
@ConditionalOnExpression("${rocketmq.enabled:true}")
@ConditionalOnBean(RocketMQProperties.class)
@ConditionalOnClass(RocketMQTemplate.class)
@AutoConfigureAfter(MessageQueueAutoConfiguration.class)
@EnableConfigurationProperties({
	RocketMQProducerProperties.class,
	RocketMQConsumerProperties.class
})
@Slf4j
@Configuration(proxyBeanMethods = false)
public class RocketMQMessageQueueAutoConfiguration {

	private static final String AUTOWIRED_ROCKET_MQ_CONSUMER = "Autowired RocketMQConsumer";

	private static final String AUTOWIRED_ROCKET_MQ_PROVIDER = "Autowired RocketMQProvider";

	@Bean(MessageQueueBeanNames.ROCKETMQ_CONSUMER)
	public RocketMQConsumer rocketMQConsumer(MessageQueueProperties messageQueueProperties,
											 RocketMQProperties rocketMQProperties,
											 RocketMQConsumerProperties rocketMQConsumerProperties,
											 ObjectProvider<List<MessageQueueConsumer>> messageListeners) {
		log.debug(AUTOWIRED_ROCKET_MQ_CONSUMER);
		Function<String, Boolean> matcher = type -> StringUtils.isBlank(type)?
			MessageQueueBeanNames.ROCKETMQ.name().equalsIgnoreCase(messageQueueProperties.getPrimary().name()):
			MessageQueueBeanNames.ROCKETMQ.name().equalsIgnoreCase(type);
		return new RocketMQConsumer(matcher, rocketMQProperties,
			RocketMQConsumerConvertor.INSTANCE.toConfig(rocketMQConsumerProperties),
			messageListeners.getIfAvailable());
	}

	@Bean(MessageQueueBeanNames.ROCKETMQ_PROVIDER)
	public MessageQueueProvider messageQueueProvider(RocketMQTemplate rocketMQTemplate,
                                                     RocketMQProducerProperties rocketMQProducerProperties) {
		log.debug(AUTOWIRED_ROCKET_MQ_PROVIDER);
		return new RocketMQProvider(rocketMQTemplate,
			RocketMQProducerConvertor.INSTANCE.toConfig(rocketMQProducerProperties));
	}
}
