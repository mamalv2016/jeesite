package com.renjie120.common.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelExample2 {
	static String fileName = "e:\\offline2.log";
 
	public static void useNioReadFile() throws IOException{
		String fileName = "e:\\offline4.log"; 
		RandomAccessFile aFile = new RandomAccessFile(fileName,"rw");
		//FileChannel没有非阻塞模式！都是阻塞模式的。
		FileChannel channel = aFile.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(1024); 
		while(channel.read(buffer)!=-1){
			buffer.flip(); 
			//读出来buffer里面的内容
			while (buffer.hasRemaining()) {
				System.out.println((char) buffer.get());
			}
			buffer.clear();
		}
		
		System.out.println("以上是读Channel.以下是写Channel");
		String str = "准备向文件写数据。。。"+System.currentTimeMillis();
		buffer.put(str.getBytes());
		buffer.flip();
		//向buffer写内容 
		while(buffer.hasRemaining()){
			channel.write(buffer);
		} 
		
		//截取4个字节后面的内容，全部删除.
		//channel.truncate(4);
		
		//FileChannel.force()方法将通道里尚未写入磁盘的数据强制写到磁盘上。
		//出于性能方面的考虑，操作系统会将数据缓存在内存中，所以无法保证写入到FileChannel里的数据一定会即时写到磁盘上。
		//要保证这一点，需要调用force()方法。
		//channel.force();
		
		channel.close();
		aFile.close(); 
	}
	
	 
	
	public static void main(String[] args) throws IOException {
		useNioReadFile();
		 
	}
}
