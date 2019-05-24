package com.example.fr.baimajidi;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * Created by FR on 2017/4/14.
 */
public class GetPostUtil {
    /**
     * 向指定URL发送GET方法的请求
     * @param url 发送请求的URL
     * @param params 请求参数，请求参数应该是name1=value1&name2=value2的形式。
     * @return URL所代表远程资源的响应
     */
    public static String sendGet(String url, String params)
    {
        String result = "";
        BufferedReader in = null;

        try
        {
            String urlName = url + "?" + params;
            URL realUrl = new URL(urlName);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();

            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            /*
             * 建立实际的连接
             * 如果需要发送POST请求，则需要获取URLConnection的OutputStream
             * 然后再向网络中输出请求参数
             */
            conn.connect();

            // 获取所有响应头字段
            Map<String, List<String>> map = conn.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet())
            {
                System.out.println(key + "--->" + map.get(key));
            }

            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null)
            {
                result += "\n" + line;
            }
        }
        catch (Exception e)
        {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally
        {
            try
            {
                if (in != null)
                {
                    in.close();
                }
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定URL发送POST方法的请求
     * @param url 发送请求的URL
     * @param params 请求参数，请求参数应该是name1=value1&name2=value2的形式。
     * @return URL所代表远程资源的响应
     */
    public static String sendPost(String url, String params)
    {
        PrintWriter out = null;
        InputStream in = null;
        String result = "";

        try
        {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();

            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");

            //向网络中输出请求参数
            conn.setDoOutput(true);
            conn.setDoInput(true);

            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());

            // 发送请求参数
            out.print(params);



            // flush输出流的缓冲
            out.flush();


            in= conn.getInputStream();
            byte[] bytes = new byte[1024];
            int len = 0;
            try{
                if(in.available()>0){
                    while((len=in.read(bytes))!=-1){
                        String str = new String(bytes,0,len);
                        Log.i("main",str);
                    }
                }
            }catch(Exception e){

            }


            // 定义BufferedReader输入流来读取URL的响应
            //in = new BufferedReader(
            //new InputStreamReader(conn.getInputStream()));
            //String line;
            //while ((line = in.readLine()) != null) {
            //Log.i("main","访问");
            //}
        }
        catch (Exception e)
        {
            System.out.println("发送POST请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally
        {
            try
            {
                if (out != null)
                {
                    out.close();
                }
                if (in != null)
                {
                    in.close();
                }
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        return result;
    }
}
