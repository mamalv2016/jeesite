package com.renjie120.common.zkconfig;

import org.I0Itec.zkclient.ZkClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * Created by lishuiqing on 2017/9/25.
 */
public class ZkPropertyChangeListenerImpl implements DynamicPropertiesHelper.PropertyChangeListener
{
    private String key;
    private String dir;

    /**
     *
     * @param zkClient
     * @param data zk键的路径
     * @param key zk值的key
     */
    public ZkPropertyChangeListenerImpl(  String dir,String key){
        this.dir = dir;
        this.key = key;
    }

    @Override
    public void propertyChanged(String paramString1, String paramString2)
    {
//        String content = (String) zkClient.readData(dir);
//        Properties props = DynamicPropertiesHelper.parse(content);
//        props.put(key,paramString2);
        System.out.println("zk属性变化了！dir:"+dir+",key变化："+paramString1+",-->"+paramString2);
//        zkClient.writeData(dir,getPropertyStr(props));
    }

    /**
     * 得到一个属性对象的字符串形式.
     * @param props
     * @return
     */
    private String getPropertyStr(Properties props){
        if(props==null){
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream(10);
        try {
            props.store(out,"test");
            return out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args){
        Properties props = new Properties();
        props.put("abcdef","1");
        props.put("abcdefccc","2");
        ByteArrayOutputStream out = new ByteArrayOutputStream(10);
        try {
            props.store(out,"test");
            System.out.println(out.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
