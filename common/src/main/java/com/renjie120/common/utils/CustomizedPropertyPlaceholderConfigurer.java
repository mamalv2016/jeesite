package com.renjie120.common.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.ServiceLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.renjie120.common.config.ConfigClient;

/**
 * 自定义对属性文件进行扩展.
 * 通过实现ConfigClient接口装载属性配置到spring的属性容器中.
 * 可以通过:CustomizedPropertyPlaceholderConfigurer.getContextProperty(name)得到属性的值。
 * @author Administrator
 *
 */
public class CustomizedPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	private static final Logger logger = LoggerFactory.getLogger(CustomizedPropertyPlaceholderConfigurer.class);
	
	private static Map<String, Object> ctxPropertiesMap; 
	
    @Override 
    protected void processProperties( 
            ConfigurableListableBeanFactory beanFactoryToProcess, 
            Properties props) throws BeansException { 
        super.processProperties(beanFactoryToProcess, props); 
        ctxPropertiesMap = new HashMap<String, Object>(); 
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr); 
            ctxPropertiesMap.put(keyStr, value);
        }
    } 
 
    public static <T> T getContextProperty(String name) {
    	if(ctxPropertiesMap!=null)
       		 return (T)ctxPropertiesMap.get(name);
    	else
    		return null;
    } 
    
    public void reload(){
    	this.loadFromProperties();
    	//下面使用ServiceLoader方式扩展属性.通过实现ConfigClient接口装载属性配置到spring的属性容器中.
    	ServiceLoader<ConfigClient> serviceLoader = ServiceLoader.load(ConfigClient.class);
		Iterator<ConfigClient> iterators = serviceLoader.iterator();
		while (iterators.hasNext()) {
			this.loadFromCustomizedSources(iterators.next());
		}
    }
    
    //原有的配置文件properties读取（铺打底数据）
    private void loadFromProperties(){
    	try{
    		Properties result = mergeProperties(); 
    		HashMap nc =new HashMap<String, Object>();
    		nc.putAll(result);
    		ctxPropertiesMap=nc;
    	}catch(Exception ex){
    		logger.error("reload properties error!",ex);
    	}
    }
    
    //从自定义数据源读取配置，进行覆盖（增量更新）
    private void loadFromCustomizedSources(ConfigClient configClient){
    	try{
    		Map<String, String> map = configClient.queryAll();
    		if(!map.isEmpty()){
    			ctxPropertiesMap.putAll(map);
			}
    	}catch(Exception ex){
    		logger.error("reload Customized properties error!",ex);
    	}
    }
    
}
