package com.sun.demo;

import com.github.qcloudsms.SmsMultiSender;
import com.github.qcloudsms.SmsMultiSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.sun.demo.base.utils.SmsTencentUntil;
import com.sun.demo.base.utils.StringUtils;
import com.sun.demo.service.HelloService;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QcloudSmsTest {

    @Autowired
    public SmsTencentUntil smsTencentUntil;

    @Test
    public void contextLoads() {

        // 需要发送短信的手机号码
        String[] phoneNumbers = {"15972004233"};

        // 指定模板ID单发短信

        smsTencentUntil.sendVcode(StringUtils.getIntRandom(6), phoneNumbers);


        // 发送语音验证码
       /* try {
            SmsVoiceVerifyCodeSender vvcsender = new SmsVoiceVerifyCodeSender(appid,appkey);
            SmsVoiceVerifyCodeSenderResult result = vvcsender.send("86", phoneNumbers[0],
                "5678", 2, "");
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
        }*/

        // 发送语音通知
       /* try {
            SmsVoicePromptSender vpsender = new SmsVoicePromptSender(appid, appkey);
            SmsVoicePromptSenderResult result = vpsender.send("86", phoneNumbers[0],
                2, 2, "5678", "");
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
        }*/

        // 拉取短信回执以及回复
       /* try {
            // Note: 短信拉取功能需要联系腾讯云短信技术支持(QQ:3012203387)开通权限
            int maxNum = 10;  // 单次拉取最大量
            SmsStatusPuller spuller = new SmsStatusPuller(appid, appkey);

            // 拉取短信回执
            SmsStatusPullCallbackResult callbackResult = spuller.pullCallback(maxNum);
            System.out.println(callbackResult);

            // 拉取回复
            SmsStatusPullReplyResult replyResult = spuller.pullReply(maxNum);
            System.out.println(replyResult);
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

        // 拉取单个手机短信状态
        try {
            int beginTime = 1511125600;  // 开始时间(unix timestamp)
            int endTime = 1511841600;    // 结束时间(unix timestamp)
            int maxNum = 10;             // 单次拉取最大量
            SmsMobileStatusPuller mspuller = new SmsMobileStatusPuller(appid, appkey);

            // 拉取短信回执
            SmsStatusPullCallbackResult callbackResult = mspuller.pullCallback("86",
                phoneNumbers[0], beginTime, endTime, maxNum);
            System.out.println(callbackResult);

            // 拉取回复
            SmsStatusPullReplyResult replyResult = mspuller.pullReply("86",
                phoneNumbers[0], beginTime, endTime, maxNum);
            System.out.println(replyResult);
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }*/
    }
}
