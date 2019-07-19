package com.chengw.utils.uploadUtil;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author chengw
 */
public class UploadUtils {


    private String boundary = "----aryg4pBUG8gGY9qgAAs";


    /**
     * @param farServer
     * @param file
     * @return 返回transUrl
     */
    public <T>T upload2FileServer(String farServer, File file,Class<T> tClass) {

        HttpURLConnection connection = getConnection(farServer);

        if(connection != null){
            try {
                OutputStream outputStream = new DataOutputStream(connection.getOutputStream());
                StringBuilder sb = new StringBuilder();
                sb.append("--"+boundary);
                sb.append("\r\n");
                sb.append("Content-Disposition: form-data;name=\"transUrl\";filename=\""+ file.getName() + "\"\r\n");
                sb.append("Content-Type:application/octet-stream\r\n\r\n");

                outputStream.write(sb.toString().getBytes());

                DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));

                byte[] data = new byte[1024];
                int len;

                while((len = dataInputStream.read(data)) != -1){
                    outputStream.write(data,0,len);
                }

                outputStream.write("/r/n".getBytes());
                byte[] bytes = ("\r\n--" + boundary + "--\r\n").getBytes();

                outputStream.write(bytes);

                T result = getTransUrlFromResponse(connection.getInputStream(),tClass);

                return result;

            } catch (IOException e) {
                e.printStackTrace();

            }finally {

            }
        }

        return null;
    }

    /**
     * 用httpClient 重构上传代码
     * @param fileServer
     * @param file
     * @return 返回transUrl
     */
    public  <T>T  upload2Server(String fileServer, File file,Class<T> tClass) throws IOException {

        HttpClient client = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(fileServer);

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addBinaryBody("transUrl",file,ContentType.APPLICATION_OCTET_STREAM,file.getName());
        HttpEntity multipart = builder.build();
        httpPost.setEntity(multipart);

        HttpResponse response = client.execute(httpPost);

        InputStream content = response.getEntity().getContent();

        T result = getTransUrlFromResponse(content,tClass);

        return result;


    }

    private HttpURLConnection getConnection(String urlPath){

        HttpURLConnection connection = null;

        try {
            URL url = new URL(urlPath);

            connection = (HttpURLConnection) url.openConnection();

            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Connection","keep-alive");
            connection.setRequestProperty("Charset","UTF-8");
            connection.setRequestProperty("User-Agent","Mozilla/5.0(Macintosh;IntelMacOSX10_7_0)"
                    +"AppleWebKit/535.11(KHTML,likeGecko)Chrome/17.0.963.56Safari/535.11");
            connection.setRequestProperty("content-type","multipart/form-data;boundary=" + boundary);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return connection;
    }

    /**
     * 获取文件服务器返回值
     * @param inputStream
     * @return
     * @throws IOException
     */
    private <T> T getTransUrlFromResponse(InputStream inputStream,Class<T> clazz) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer ans = new StringBuffer();

        String line;
        while((line = reader.readLine()) != null){
            ans.append(line);
        }


        CommonResponse jsonObject = JSON.parseObject(ans.toString(), CommonResponse.class);

        T result = JSON.parseObject(JSON.toJSONString(jsonObject.getResult()), clazz);

        return result;
    }



}
