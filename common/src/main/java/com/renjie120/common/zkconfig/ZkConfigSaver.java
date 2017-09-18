package com.renjie120.common.zkconfig;

import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.List;
import java.util.Properties;

/**
 * zk配置文件下载类.将远程的zk里面的配置信息保存到本地的文件路径.
 * @author june
 *
 */
public class ZkConfigSaver {
	public static final String CONF_ENCODING = "UTF-8";
	public static String ZK_CONFIG_ROOTNODE = "/zkSample/conf";
	public static String ZK_CONF_ENCODING = "UTF-8";
	public static int ZK_TIMEOUT = 30000;
	public static String ZK_ADDRESS = "";

	private static final void loadProperties() {
		InputStream is = ZkConfigPublisher.class
				.getResourceAsStream("/zkpublisher.properties");
		if (is == null) {
			throw new RuntimeException("找不到config.properties资源文件.");
		}
		Properties props = new Properties();
		try {
			props.load(new BufferedReader(new InputStreamReader(is, "UTF-8")));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		ZK_CONFIG_ROOTNODE = props.getProperty("ZK_CONFIG_ROOTNODE");
		ZK_CONF_ENCODING = props.getProperty("ZK_CONF_ENCODING");
		ZK_TIMEOUT = Integer.parseInt(props.getProperty("ZK_TIMEOUT"));
		ZK_ADDRESS = props.getProperty("ZK_ADDRESS");
	}

	public static void main(String[] args) {
		String dirName = "D:\\properties";
		loadProperties();

		ZkClient client = new ZkClient(ZK_ADDRESS, ZK_TIMEOUT);
		client.setZkSerializer(new ZkUtils.StringSerializer(ZK_CONF_ENCODING));

		File confDir = new File(dirName);
		confDir.mkdirs();

		saveConfigs(client, ZK_CONFIG_ROOTNODE, confDir);
	}

	private static void saveConfigs(ZkClient client, String rootNode,
			File confDir) {
		List<String> configs = client.getChildren(rootNode);
		for (String config : configs) {
			String content = (String) client.readData(rootNode + "/" + config);
			File confFile = new File(confDir, config);
			try {
				FileUtils.writeStringToFile(confFile, content, "UTF-8");
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("配置成功保存到本地: " + confFile.getAbsolutePath());
		}
	}
}