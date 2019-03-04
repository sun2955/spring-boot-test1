package com.sun.demo.base.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import com.github.qcloudsms.SmsMultiSender;
import com.github.qcloudsms.SmsMultiSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author  szy 
 * @version 创建时间：2019-3-4 下午12:07:17
 * 
 */
@Component
public class SmsTencentUntil {

	// 短信应用SDK AppID
	@Value("${tencentSms.appid}")
	private int appid;

	// 短信应用SDK AppKey
	@Value("${tencentSms.appkey}")
	private String appkey;

	// 模板ID
	@Value("${tencentSms.templateId}")
	private int templateId;

	// 签名内容
	@Value("${tencentSms.smsSign}")
	private String smsSign;

	private static SmsMultiSender smsMultiSenderInstance;
	
	/*static{
		
	   //库名
		try {
			smsMultiSenderInstance = getInstance();
			
			System.out.println("Connect to database successfully!");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		 
	}*/
	    
	
	/*private static SmsMultiSender getInstance(){
		
		if(smsMultiSenderInstance == null){
			//smsMultiSenderInstance = new SmsMultiSender(appid, appkey);
		}
		return smsMultiSenderInstance;
	}*/
	
	
	
	/**
	 * 发送验证码
	 * @param vcode
	 * @param phoneNumbers
	 */
    public  void sendVcode(String vcode,String [] phoneNumbers){

		System.out.println("vcode="+vcode);

		smsMultiSenderInstance = new SmsMultiSender(appid, appkey);
    	  // 指定模板ID单发短信
        System.out.println("vcode="+vcode);

        try {
            String[] params = {vcode,"5"};
            SmsMultiSenderResult result =  smsMultiSenderInstance.sendWithParam("86", phoneNumbers,
					templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
            System.out.print(result);
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }
    }
	
}
