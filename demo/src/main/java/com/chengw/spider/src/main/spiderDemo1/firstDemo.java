package main.spiderDemo1;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class firstDemo {
    public static void main(String[] args) {

        //创建一个新的请求
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //获取网址
        HttpGet httpGet = new HttpGet("http://www.datalearner.com/blog");

        CloseableHttpResponse response = null;

        try {
            response = httpClient.execute(httpGet);

            HttpEntity httpEntity = response.getEntity();

            System.out.println(EntityUtils.toString(httpEntity));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
