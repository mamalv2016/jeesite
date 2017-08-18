package com.renjie120.common.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * nio按照行读取文件.
 * 
 * @author Administrator
 *
 */
public class NioReadFileWithLines {
	static String fileName = "e:\\adapter-service.log";
	static String fileName2 = "e:\\out.log";
	static int bufSize = 100;

	public static void main(String[] a) throws IOException {
		RandomAccessFile fcIn = new RandomAccessFile(fileName, "r");
		FileChannel inChannel = fcIn.getChannel();
		ByteBuffer rBuffer = ByteBuffer.allocate(bufSize);

		RandomAccessFile fcOut = new RandomAccessFile(fileName2, "rws");
		FileChannel outChannel = fcOut.getChannel();
		ByteBuffer wRuffer = ByteBuffer.allocateDirect(bufSize);

		long start = System.currentTimeMillis();
		readFileByLine(bufSize, inChannel, rBuffer, outChannel, wRuffer);
		long end = System.currentTimeMillis();
		System.out.println("ok.耗时："+(end-start));
		if(inChannel.isOpen()){  
			inChannel.close();  
        }  
        if(outChannel.isOpen()){  
        	outChannel.close();  
        }  
	}

	public static void logBuffer(Buffer buf,String pre){
		System.out.println(pre+"-->position:"+buf.position()+",limit="+buf.limit()+",capacity="+buf.capacity());
	}
	 
//	 public static void readFileByLine(int bufSize, FileChannel fcin,  
//	            ByteBuffer rBuffer, FileChannel fcout, ByteBuffer wBuffer) {  
//	        String enter = "\n";  
//	        List<String> dataList = new ArrayList<String>();//存储读取的每行数据  
//	        byte[] lineByte = new byte[0];  
//	          
//	        String encode = "GBK";  
////	      String encode = "UTF-8";  
//	        try {  
//	            //temp：由于是按固定字节读取，在一次读取中，第一行和最后一行经常是不完整的行，因此定义此变量来存储上次的最后一行和这次的第一行的内容，  
//	            //并将之连接成完成的一行，否则会出现汉字被拆分成2个字节，并被提前转换成字符串而乱码的问题  
//	            byte[] temp = new byte[0];  
//	            while (fcin.read(rBuffer) != -1) {//fcin.read(rBuffer)：从文件管道读取内容到缓冲区(rBuffer)  
//	                int rSize = rBuffer.position();//读取结束后的位置，相当于读取的长度  
//	                byte[] bs = new byte[rSize];//用来存放读取的内容的数组  
//	                rBuffer.rewind();//将position设回0,所以你可以重读Buffer中的所有数据,此处如果不设置,无法使用下面的get方法  
//	                rBuffer.get(bs);//相当于rBuffer.get(bs,0,bs.length())：从position初始位置开始相对读,读bs.length个byte,并写入bs[0]到bs[bs.length-1]的区域  
//	                rBuffer.clear();  
//	                  
//	                int startNum = 0;  
//	                int LF = 10;//换行符  
//	                int CR = 13;//回车符  
//	                boolean hasLF = false;//是否有换行符  
//	                for(int i = 0; i < rSize; i++){  
//	                    if(bs[i] == LF){  
//	                        hasLF = true;  
//	                        int tempNum = temp.length;  
//	                        int lineNum = i - startNum;  
//	                        lineByte = new byte[tempNum + lineNum];//数组大小已经去掉换行符  
//	                          
//	                        System.arraycopy(temp, 0, lineByte, 0, tempNum);//填充了lineByte[0]~lineByte[tempNum-1]  
//	                        temp = new byte[0];  
//	                        System.arraycopy(bs, startNum, lineByte, tempNum, lineNum);//填充lineByte[tempNum]~lineByte[tempNum+lineNum-1]  
//	                          
//	                        String line = new String(lineByte, 0, lineByte.length, encode);//一行完整的字符串(过滤了换行和回车)  
//	                        dataList.add(line);  
////	                      System.out.println(line);  
//	                        writeFileByLine(fcout, wBuffer, line + enter);  
//	                          
//	                        //过滤回车符和换行符  
//	                        if(i + 1 < rSize && bs[i + 1] == CR){  
//	                            startNum = i + 2;  
//	                        }else{  
//	                            startNum = i + 1;  
//	                        }  
//	                          
//	                    }  
//	                }  
//	                if(hasLF){  
//	                    temp = new byte[bs.length - startNum];  
//	                    System.arraycopy(bs, startNum, temp, 0, temp.length);  
//	                }else{//兼容单次读取的内容不足一行的情况  
//	                    byte[] toTemp = new byte[temp.length + bs.length];  
//	                    System.arraycopy(temp, 0, toTemp, 0, temp.length);  
//	                    System.arraycopy(bs, 0, toTemp, temp.length, bs.length);  
//	                    temp = toTemp;  
//	                }  
//	            }  
//	            if(temp != null && temp.length > 0){//兼容文件最后一行没有换行的情况  
//	                String line = new String(temp, 0, temp.length, encode);  
//	                dataList.add(line);  
////	              System.out.println(line);  
//	                writeFileByLine(fcout, wBuffer, line + enter);  
//	            }  
//	        } catch (IOException e) {  
//	            e.printStackTrace();  
//	        }   
//	    }  
	 
	public static void readFileByLine(int bufSize, FileChannel in,
			ByteBuffer rBuffer, FileChannel out, ByteBuffer wBuffer)  {
		String enterStr = "\n";
		byte[] bs = new byte[bufSize];
		
		int size = 0;
		StringBuffer strBuffer = new StringBuffer();
		try {
			logBuffer(rBuffer,"读之前");
			while(in.read(rBuffer)!=-1){
				logBuffer(rBuffer,"read()之后");
				int rSize = rBuffer.position();
				rBuffer.rewind();// position = 0;mark = -1;
				logBuffer(rBuffer,"rewind()之后");
				rBuffer.get(bs);
				logBuffer(rBuffer,"get()之后");
				rBuffer.clear();//position = 0;limit = capacity;mark = -1;
				logBuffer(rBuffer,"clear()之后");
				String tempString = new String(bs,0,rSize,"GBK");//把缓冲区里面的字节数组转换为字符串，准备进行处理。
				
				int fromIndex = 0;
				int endIndex = 0;
				//下面从第0个位置往后找，找到每一个换行符号。
				while((endIndex = tempString.indexOf(enterStr,fromIndex))!=-1){
					//得到当前这一行（行头不一定是改行的最前面，行尾肯定在这里！）
					String line = tempString.substring(fromIndex,endIndex);
					//下面的目的是，在strBuffer里面还存储了可能是上一次缓冲区里面的没有找到换行的另外一半。。。
					//strBuffer里面的内容是在本while循环后面添加进去的。。。
					line = new String(strBuffer.toString()+line);
					
					writeFileByLine(out,wBuffer,line+enterStr);
					
					strBuffer.delete(0, strBuffer.length());
					fromIndex = endIndex+1;
				}
				logBuffer(rBuffer,"writeFile()之后");
				if(rSize>tempString.length()){
					strBuffer.append(tempString.substring(fromIndex, tempString.length()));
				}else{
					strBuffer.append(tempString.substring(fromIndex, rSize));
				}			
			} 
			writeFileByLine(out,wBuffer,strBuffer.toString());
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}
	
	/**
	 * 向channel追加字符串的方法。
	 * @param channel
	 * @param buffer
	 * @param str
	 */
	public static void writeFileByLine(FileChannel channel,ByteBuffer buffer,String str){
		try {
			channel.write(buffer.wrap(str.getBytes()),channel.size());
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}
}
