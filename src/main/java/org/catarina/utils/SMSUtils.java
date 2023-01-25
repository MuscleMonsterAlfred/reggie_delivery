package org.catarina.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import java.util.*;
import com.aliyuncs.dysmsapi.model.v20170525.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @program: reggie_delivery
 * @ClassName SMSUtils
 * @description: 短信发送工具类
 * @author: alfred-chenzhonghao
 * @create: 2023-01-25 05:45
 * @Version 1.0
 **/

@Slf4j
public class SMSUtils {
    /**
     * 发送短信
     * @param signName  签名
     * @param templateCode 模板
     * @param phoneNumbers  手机号
     * @param param  参数
     */
    public static void sendMessage(String signName, String templateCode,String phoneNumbers,String param){

        //这边只需要传入申请的aceessKeyId,secret就行
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "<your-access-key-id>", "<your-access-key-secret>");
        /** use STS Token **/
//         DefaultProfile profile = DefaultProfile.getProfile(
//         "<your-region-id>",           // The region ID
//         "<your-access-key-id>",       // The AccessKey ID of the RAM account
//         "<your-access-key-secret>",   // The AccessKey Secret of the RAM account
//         "<your-sts-token>");          // STS Token


        IAcsClient client = new DefaultAcsClient(profile);

        SendSmsRequest request = new SendSmsRequest();
        request.setSysRegionId("cn-hangzhou");
        request.setPhoneNumbers(phoneNumbers);//给每个手机号发短信
        request.setSignName(signName);//签名，审核通过的
        request.setTemplateCode(templateCode);//模版code
        request.setTemplateParam("{\"code\":\""+param+"\"}");//短信中的验证码

        try {
            SendSmsResponse response = client.getAcsResponse(request);
            System.out.println(new Gson().toJson(response));
            System.out.println("短信发送成功");
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }

    }

}
