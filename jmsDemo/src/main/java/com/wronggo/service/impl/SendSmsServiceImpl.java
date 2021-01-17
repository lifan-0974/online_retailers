package com.wronggo.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.wronggo.service.SendSmsService;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
@Data
public class SendSmsServiceImpl implements SendSmsService {
    private static final Logger logger = LoggerFactory.getLogger(SendSmsServiceImpl.class);
    //aliyuncs的参数
    @Value("${aliyun.accessKeyID}")
    private String accessKeyID;
    @Value("${aliyun.accessKeySecret}")
    private String accessKeySecret;
    //短信api的请求地址  固定
    @Value("${aliyun.domain}")
    private String domain;
    //指定地域名称 短信API的就是 cn-hangzhou 不能改变
    @Value("${aliyun.regionId}")
    private String regionId;
    //您的申请签名
    @Value("${aliyun.signName}")
    private String signName;
    //你的模板
    @Value("${aliyun.templateCode}")
    private String templateCode;

    /**
     * 发送短信接口
     *
     * @param phoneNum 手机号
     * @param message     消息
     * @return
     */
    @Override
    public boolean sendSms(String phoneNum, String message) {

        // 指定地域名称 短信API的就是 cn-hangzhou 不能改变  后边填写您的  accessKey 和 accessKey Secret
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyID, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);

        // 创建通用的请求对象
        CommonRequest request = new CommonRequest();
        // 指定请求方式
        request.setSysMethod(MethodType.POST);
        // 短信api的请求地址  固定
        request.setSysDomain(domain);
        //签名算法版本  固定
        request.setSysVersion("2017-05-25");
        //请求 API 的名称
        request.setSysAction("SendSms");
        //指定地域名称
        request.putQueryParameter("RegionId", regionId);
        // 要给哪个手机号发送短信  指定手机号
        request.putQueryParameter("PhoneNumbers", phoneNum);
        // 您的申请签名
        request.putQueryParameter("SignName", signName);
        // 您申请的模板 code
        request.putQueryParameter("TemplateCode", templateCode);

        Map<String, Object> params = new HashMap<>();
        //这里的key就是短信模板中的 ${xxxx}
        params.put("code", message);

        // 放入参数  需要把 map转换为json格式  使用fastJson进行转换
        request.putQueryParameter("TemplateParam", com.alibaba.fastjson.JSON.toJSONString(params));

        try {
            CommonResponse response = client.getCommonResponse(request);
            logger.info(JSON.parseObject(response.getData(), Map.class).get("Message").toString());
            return response.getHttpResponse().isSuccess();

        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }

        return false;
    }
}

