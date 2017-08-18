package common.renjie120.common.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.junit.Test;

public class CharsetReadAndWrite {
	/**
	 * 测试写入和读出汉字.
	 * 使用的编码都是一样的，就不太可能出现编码异常.
	 * @throws IOException
	 */
	@Test
	public void testWriteAndRead() throws IOException {
		String file = "e:\\book1.txt";
		String charset="UTF-8";
		FileOutputStream outputStream = new FileOutputStream(file);
		OutputStreamWriter writer = new OutputStreamWriter(outputStream,charset);
		try{
			writer.write("保存中文看看");
		}finally{
			writer.close();
		}
		
		FileInputStream inputStream = new FileInputStream(file);
		InputStreamReader reader = new InputStreamReader(inputStream,charset);
		StringBuffer buffer = new StringBuffer();
		char[] buf = new char[64];
		int count = 0;
		try{
			while((count=reader.read(buf))!=-1){
				buffer.append(buf,0,count);
			}
		}finally{
			reader.close();
		}
		System.out.println(buffer);
	}
}
