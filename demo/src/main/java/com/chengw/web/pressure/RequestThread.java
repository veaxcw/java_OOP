package com.chengw.web.pressure;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClients;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * @author chengwei
 */
public class RequestThread implements Runnable {



    private Integer count;

    private String url;

    private String method;

    private JSONObject result;


    public RequestThread(String url, String method,Integer count) {
        this.url = url;
        this.method = method;
        this.count = count;
    }

    private RequestThread() {
    }

    private void executeRequest() throws IOException {

        HttpClient httpClient = HttpClients.createDefault();

        HttpUriRequest uriRequest = null;

        if (Objects.equals(this.method,Constant.GET)) {
            uriRequest = new HttpGet(this.url);
        }
        HttpResponse response = httpClient.execute(uriRequest);

        InputStream result = response.getEntity().getContent();

        this.result = parseResult(result);
        System.out.println(result);

    }

    @Override
    public void run() {
        try {
            executeRequest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JSONObject parseResult(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder var = new StringBuilder();

        String line;
        while((line = reader.readLine()) != null) {
            var.append(line);
        }
        return JSON.parseObject(var.toString());
    }

    public JSONObject getResult () {
        return this.result;
    }
}
