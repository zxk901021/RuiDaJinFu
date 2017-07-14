package com.daiqile.test.http;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by ZHY_9 on 2017/7/5.
 */

public class HttpPostUtil {

    public static String requesPost(String userId) throws Throwable{
        String path = "http://test2.zjrdjr.com/port/getUserBankList.php";
        // 请求的参数转换为byte数组
        String params = "dcode=" + URLEncoder.encode("7d5372bbcd6cc79f1bd71211f092622e", "UTF-8")
                + "&user_id=" + URLEncoder.encode(userId, "UTF-8");
        byte[] postData = params.getBytes();
        // 新建一个URL对象
        URL url = new URL(path);
        // 打开一个HttpURLConnection连接
        HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
        // 设置连接超时时间
        urlConn.setConnectTimeout(5 * 1000);
        // Post请求必须设置允许输出
        urlConn.setDoOutput(true);
        // Post请求不能使用缓存
        urlConn.setUseCaches(false);
        // 设置为Post请求
        urlConn.setRequestMethod("POST");
        urlConn.setInstanceFollowRedirects(true);
        // 配置请求Content-Type
        urlConn.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
        // 开始连接
        urlConn.connect();
        // 发送请求参数
        DataOutputStream dos = new DataOutputStream(urlConn.getOutputStream());
        dos.write(postData);
        dos.flush();
        dos.close();
        // 判断请求是否成功
        if (urlConn.getResponseCode() == 200) {
            // 获取返回的数据
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            InputStream is = urlConn.getInputStream();
            byte[] buffer = new byte[1024];
            int rc = 0;
            while ((rc = is.read(buffer, 0, 1024)) > 0){
                bao.write(buffer, 0, rc);
            }
            byte[] data = bao.toByteArray();
            System.out.println(new String(data, "UTF-8"));
            return new String(data, "UTF-8");
        } else {
            return "";
        }
    }

    public static String requestUser(String userId) throws Throwable{
        String path = "http://test2.zjrdjr.com/port/getUser.php";
        // 请求的参数转换为byte数组
        String params = "dcode=" + URLEncoder.encode("7d5372bbcd6cc79f1bd71211f092622e", "UTF-8")
                + "&user_id=" + URLEncoder.encode(userId, "UTF-8");
        byte[] postData = params.getBytes();
        // 新建一个URL对象
        URL url = new URL(path);
        // 打开一个HttpURLConnection连接
        HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
        // 设置连接超时时间
        urlConn.setConnectTimeout(5 * 1000);
        // Post请求必须设置允许输出
        urlConn.setDoOutput(true);
        // Post请求不能使用缓存
        urlConn.setUseCaches(false);
        // 设置为Post请求
        urlConn.setRequestMethod("POST");
        urlConn.setInstanceFollowRedirects(true);
        // 配置请求Content-Type
        urlConn.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
        // 开始连接
        urlConn.connect();
        // 发送请求参数
        DataOutputStream dos = new DataOutputStream(urlConn.getOutputStream());
        dos.write(postData);
        dos.flush();
        dos.close();
        // 判断请求是否成功
        if (urlConn.getResponseCode() == 200) {
            // 获取返回的数据
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            InputStream is = urlConn.getInputStream();
            byte[] buffer = new byte[1024];
            int rc = 0;
            while ((rc = is.read(buffer, 0, 1024)) > 0){
                bao.write(buffer, 0, rc);
            }
            byte[] data = bao.toByteArray();
            System.out.println(new String(data, "UTF-8"));
            return new String(data, "UTF-8");
        } else {
            return "";
        }
    }

    public static String requestVerify(String userId, String account) throws Throwable{
        String path = "http://test2.zjrdjr.com/port/userBank.php?type=code";
        // 请求的参数转换为byte数组
        String params = "dcode=" + URLEncoder.encode("7d5372bbcd6cc79f1bd71211f092622e", "UTF-8")
                + "&user_id=" + URLEncoder.encode(userId, "UTF-8")
                + "&account=" + URLEncoder.encode(account, "UTF-8");
        byte[] postData = params.getBytes();
        // 新建一个URL对象
        URL url = new URL(path);
        // 打开一个HttpURLConnection连接
        HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
        // 设置连接超时时间
        urlConn.setConnectTimeout(5 * 1000);
        // Post请求必须设置允许输出
        urlConn.setDoOutput(true);
        // Post请求不能使用缓存
        urlConn.setUseCaches(false);
        // 设置为Post请求
        urlConn.setRequestMethod("POST");
        urlConn.setInstanceFollowRedirects(true);
        // 配置请求Content-Type
        urlConn.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
        // 开始连接
        urlConn.connect();
        // 发送请求参数
        DataOutputStream dos = new DataOutputStream(urlConn.getOutputStream());
        dos.write(postData);
        dos.flush();
        dos.close();
        // 判断请求是否成功
        if (urlConn.getResponseCode() == 200) {
            // 获取返回的数据
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            InputStream is = urlConn.getInputStream();
            byte[] buffer = new byte[1024];
            int rc = 0;
            while ((rc = is.read(buffer, 0, 1024)) > 0){
                bao.write(buffer, 0, rc);
            }
            byte[] data = bao.toByteArray();
            System.out.println(new String(data, "UTF-8"));
            return new String(data, "UTF-8");
        } else {
            return "";
        }
    }
}
