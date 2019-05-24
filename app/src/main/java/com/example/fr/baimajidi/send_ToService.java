package com.example.fr.baimajidi;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class send_ToService {

	public static String send_Message(String str){
//	String path="http://192.168.43.134:8888/myApps";
//		String path="http://192.168.127.200:8888/myApps";
		String path="http://121.196.198.106:8888/myApps";
//		String path="http://172.20.10.2:8888/myApps";
//		String path="http://192.168.43.232:8888/myApps";
//		String path="http://192.168.43.222:8888/myApps";
		try {
			URL url =new URL(path);
			HttpURLConnection urlConn=(HttpURLConnection) url.openConnection();
			urlConn.setConnectTimeout(5000);
			urlConn.setDoOutput(true);
			urlConn.setDoInput(true);
			urlConn.setRequestMethod("GET");
			
			urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			//测试包内容�����
			String teststr=str;
			OutputStream out =urlConn.getOutputStream();
			out.write(teststr.getBytes());
			out.flush();
			while(urlConn.getContentLength()!=-1){
				int code=urlConn.getResponseCode();
				if(code==200) //http状态返回码 为200，表示请求成功
				{
				//请求成功
				InputStream is=urlConn.getInputStream();
				String text=readInputStream(is);
				
				is.close();
				urlConn.disconnect();
				return text;
				}else 
				{
					return null;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

}
		
							
public static String readInputStream(InputStream is)
{
	try {
		ByteArrayOutputStream baos=new ByteArrayOutputStream();//声明缓冲区
		int len=0;
		byte []buffer =new byte[1024];// 定义读取默认长度
		if((len=is.read(buffer))!=-1)
		{
			baos.write(buffer,0,len);// 把缓冲区中输出到内存中
		}
		is.close();
		baos.close();
		byte[]result=baos.toByteArray(); // 返回这个输出流的字节数组
		String temp=new String(result);
		return temp;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return("服务器信息获取失败");
	}
}
	
	
}
