package com.renjie120.common.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelExample {
	static String fileName = "e:\\offline2.log";

	/**
	 * 使用nio的RandomAccessFile类读取文件
	 * @throws IOException
	 */
	public static void useRandomAccessFileReadFile() throws IOException {
		// RandomAccessFile:RandomAccessFile的工作方式是，把DataInputStream和DataOutputStream结合起来，再加上它自己的一些方法，比如定位用的getFilePointer(
		// )，
		// 在文件里移动用的seek( )，以及判断文件大小的length( )、skipBytes()跳过多少字节数。
		// 支持：rw，和r两种方式， 不支持只写w方式。
		// 已经被nio里面的内存映射文件类MappedByteBuffer替代。
		RandomAccessFile aFile = new RandomAccessFile(fileName, "rw");
		FileChannel inChannel = aFile.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(48);
		/**
		 * 使用Buffer读写数据一般遵循以下四个步骤：
			写入数据到Buffer
			调用flip()方法
			从Buffer中读取数据
			调用clear()方法或者compact()方法
		 */
		
		//写数据到Buffer有两种方式:从Channel写到Buffer--channel.read(buf)。通过Buffer的put()方法写到Buffer里--buf.put('s')。
		int bytesRead = inChannel.read(buf);
		//从Buffer中读取数据有两种方式：从Buffer读取数据到Channel--channel.write(buf)。使用get()方法从Buffer中读取数据--buf.get()。
		while (bytesRead != -1) {
			System.out.println("read::" + bytesRead);
			buf.flip(); //使Buffer准备进行读.会将position设回0，并将limit设置成之前position的值

			while (buf.hasRemaining()) {
				System.out.println((char) buf.get());
			}

			buf.clear();   //是Buffer准备进行写。
			bytesRead = inChannel.read(buf);
		}
		aFile.close();
	}

	/**
	 * 使用普通的nio方式读取一个大文件.
	 * @throws IOException
	 */
	public static void useNioReadFile() throws IOException{
		String fileName = "e:\\offline.log";
		File file = new File(fileName);
		FileInputStream in = new FileInputStream(file);
		FileChannel channel = in.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		long start = System.currentTimeMillis();
		while(channel.read(buffer)!=-1){
			buffer.flip();
			buffer.clear();
		}
		long end = System.currentTimeMillis();
		System.out.println("普通nio耗时："+(end-start));
		channel.close();
		in.close();
	}
	
	/**
	 * 使用内存映射文件读取大文件
	 * @throws IOException
	 */
	public static void useMappedFileReadFile() throws IOException{
		String fileName = "e:\\offline.log";
		File file = new File(fileName);
		FileInputStream in = new FileInputStream(file);
		FileChannel channel = in.getChannel();
		MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
		byte[] b = new byte[1024];
		int len = (int)file.length();
		long start = System.currentTimeMillis();
		for(int offset = 0;offset<len;offset+=1024){
			if(len-offset>1024){
				buffer.get(b);
			}else{
				buffer.get(new byte[len-offset]);
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("内存映射文件耗时："+(end-start));
		channel.close();
		in.close();
	}
	
	public static void channelTransfer() throws IOException{
		String fileName = "e:\\大文件.rar";
		RandomAccessFile fromFile = new RandomAccessFile(fileName,"r");
		FileChannel fromChannel = fromFile.getChannel();
		
		RandomAccessFile toFile = new RandomAccessFile("e:\\大文件2.rar", "rw");
		FileChannel toChannel = toFile.getChannel();
		
		long position = 0;
		long count = fromChannel.size();
		
		long start = System.currentTimeMillis();
//		toChannel.transferFrom(fromChannel, position, count);
		
		fromChannel.transferTo(position, count, toChannel);
		long end = System.currentTimeMillis();
		System.out.println("转移文件耗时："+(end-start));
	}
	
	public static void main(String[] args) throws IOException {
//		useNioReadFile();
//		
//		useMappedFileReadFile();
		
		channelTransfer();
	}
}
