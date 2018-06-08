package com.example.huar.myapplication.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:Huar
 * @Date: 2018/5/24.
 */

public class LogingToServer {

    private String url = "http://10.3.44.24:8080/AppinfoSystem/login";

    public String doGet(String name,String psd){
        return doPost(name,psd);
    }

    public String doPost(String name,String psd){
        String result = "";

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        HttpEntity entity;
        HttpResponse response;

        //设置头
        String agent = "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/534.3 (KHTML, like Gecko) Chrome/6.0.472.63 Safari/534.3";
        httpPost.setHeader("User-Agent", agent);

        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("userId", name));
        pairs.add(new BasicNameValuePair("userPassword", psd));


        for (NameValuePair nameValuePair : pairs) {
            System.out.println(nameValuePair.getName() + "--------" + nameValuePair.getValue());
        }


        try {
            httpPost.setEntity(new UrlEncodedFormEntity(pairs,"UTF-8"));
            response = httpClient.execute(httpPost);
            entity = response.getEntity();
            if(entity != null){
                InputStream is = entity.getContent();
                BufferedReader in = new BufferedReader(new InputStreamReader(is));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while((line = in.readLine()) != null){
                    buffer.append(line);
                }
                is.close();
                result = buffer.toString();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}