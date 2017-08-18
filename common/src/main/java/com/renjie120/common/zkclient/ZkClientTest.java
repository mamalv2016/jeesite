package com.renjie120.common.zkclient;

import java.io.IOException;

import com.github.zkclient.IZkDataListener;
import com.github.zkclient.ZkClient;

public class ZkClientTest {
	public static void main(String[] aergs) {
		ZkClient zkcli = new ZkClient("127.0.0.1:2181");
		zkcli.subscribeDataChanges("/db",new IZkDataListener(){

			@Override
			public void handleDataChange(String dataPath, byte[] data)
					throws Exception {
				System.out.println(dataPath+"数据变化"+new String(data));
			}

			@Override
			public void handleDataDeleted(String dataPath) throws Exception {
				System.out.println(dataPath+"数据删除");
			}
						
		});
		
		System.out.println("输入任意字符结束.");
		try {
			System.in.read();
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}
}
