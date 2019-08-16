package com.chengw.utils.messageUtils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;


/**
 * @author chengw
 */
public class MessageUtils {


        public static void main(String[] args) {
            DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "<accessKey>", "<accessValue>");
            IAcsClient client = new DefaultAcsClient(profile);

            CommonRequest request = new CommonRequest();
            request.setMethod(MethodType.POST);
            request.setDomain("dysmsapi.aliyuncs.com");
            request.setVersion("2017-05-25");
            request.setAction("SendSms");
            request.putQueryParameter("RegionId", "cn-hangzhou");
            request.putQueryParameter("PhoneNumbers", "undefined");
            request.putQueryParameter("SignName", "undefined");

            /**
             * 用户修改密码模板
             * **/
            request.putQueryParameter("TemplateCode","SMS_172135885");
            request.putQueryParameter("TemplateParam", "{\"code\":\"654321\"}");
            try {
                CommonResponse response = client.getCommonResponse(request);
                System.out.println(response.getData());
            } catch (ServerException e) {
                e.printStackTrace();
            } catch (ClientException e) {
                e.printStackTrace();
            }
        }




}
