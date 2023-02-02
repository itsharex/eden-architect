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

package org.ylzl.eden.distributed.uid.integration.leaf;

import org.ylzl.eden.distributed.uid.IdGeneratorType;
import org.ylzl.eden.distributed.uid.SegmentGenerator;
import org.ylzl.eden.distributed.uid.config.SegmentGeneratorConfig;
import org.ylzl.eden.distributed.uid.integration.leaf.segement.DatabaseSegmentGenerator;

import javax.sql.DataSource;

/**
 * Leaf号段生成器
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.x
 */
public class LeafSegmentGenerator implements SegmentGenerator {

	private final DatabaseSegmentGenerator databaseSegmentGenerator;

	public LeafSegmentGenerator(SegmentGeneratorConfig config, DataSource dataSource) {
		this.databaseSegmentGenerator = new DatabaseSegmentGenerator(config, dataSource);
	}

	/**
	 * 生成器类型
	 *
	 * @return 生成器类型
	 */
	@Override
	public String generatorType() {
		return IdGeneratorType.LEAF.name();
	}

	/**
	 * 从号段获取ID
	 *
	 * @return 号段
	 */
	@Override
	public long nextId() {
		return databaseSegmentGenerator.nextId();
	}
}
