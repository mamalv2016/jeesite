package com.renjie120.common.zkconfig;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Enumeration;
import java.util.Properties;

/**
 * 修改spring里面读取属性文件的定义结构.
 * 动态属性配置文件，通过修改zk里面的属性实时修改属性！
 */
public class DynamicPropertiesSpringConfigurer extends
        PropertyPlaceholderConfigurer
{
	private static final Logger logger = Logger
			.getLogger(DynamicPropertiesSpringConfigurer.class);
	private DynamicPropertiesHelperFactory helperFactory;
	private String[] propertiesKeys;

	public void setHelperFactory(DynamicPropertiesHelperFactory helperFactory) {
		this.helperFactory = helperFactory;
	}

	public void setPropertiesKeys(String[] propertiesKeys) {
		this.propertiesKeys = propertiesKeys;
	}

	protected void processProperties(
			ConfigurableListableBeanFactory beanFactoryToProcess,
			Properties props) throws BeansException
    {
		if (this.propertiesKeys != null) {
			for (String propsKey : this.propertiesKeys) {
				DynamicPropertiesHelper helper = this.helperFactory
						.getHelper(propsKey);
				if (helper != null) {
					Enumeration<String> keys = helper.getPropertyKeys();
					while (keys.hasMoreElements()) {
						String key = (String) keys.nextElement();
						props.put(key, helper.getProperty(key));
					}
				} else {
					logger.warn("配置不存在: " + propsKey);
				}
			}
		}
		super.processProperties(beanFactoryToProcess, props);
	}
}