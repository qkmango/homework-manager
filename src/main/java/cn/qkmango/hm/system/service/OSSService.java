package cn.qkmango.hm.system.service;

import cn.qkmango.hm.system.domain.AliOss;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

/**
 * @version 1.0
 * @Description: //TODO
 * <p>类简介</p>
 * <p>类详细介绍</p>
 * @className OSSService
 * @author: Mango
 * @date: 2021-02-21 09:28
 */
public class OSSService {

    private static AliOss ALIOSS;                       //AliOSS对象，返回给前端的
    private static long   ALIOSS_TIME_STAMP;            //AliOSS对象构造时，系统时间 （毫秒）
    private static long   ALIOSS_EXPIRY_DATE = 1200L;   //AliOSS对象构造时，传入的有效期限（1200秒）

    static {
        ALIOSS = getSTS();
    }

    /**
     * 获取STS凭证、OSS配置属性的 AliOss对象
     * @return AliOss
     */
    public static AliOss getOssSTS() {
        long nowTime = new Date().getTime();
        //如果aliOSS创建时间已经超过15分钟（有效期20分钟），那么就重新创建aliOSS对象
        if (nowTime - ALIOSS_TIME_STAMP > 15*60*1000) {
            synchronized (new Object()) {
                if (nowTime - ALIOSS_TIME_STAMP > 15*60*1000) {
                    ALIOSS = getSTS();
                }
            }
        }
        return ALIOSS;
    }


    /**
     * 获取STS临时凭证
     * @return AliOss 返回OSS的STS临时凭证
     */
    private static AliOss getSTS() {

        AliOss aliOss = new AliOss();

        /**
         * 加载配置文件，获取配置
         */
        Properties properties = new Properties();
        // 使用ClassLoader加载properties配置文件生成对应的输入流
        InputStream in = OSSService.class.getClassLoader().getResourceAsStream("alioss.properties");
        // 使用properties对象加载输入流
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String endpoint         = properties.getProperty("endpoint");
        String AccessKeyId      = properties.getProperty("AccessKeyId");
        String accessKeySecret  = properties.getProperty("accessKeySecret");
        String roleArn          = properties.getProperty("roleArn");
        String roleSessionName  = properties.getProperty("roleSessionName");
        String policy           = properties.getProperty("policy");
        String region           = properties.getProperty("region");
        String bucket           = properties.getProperty("bucket");

        /**
         * 获取STS
         */
        try {
            DefaultProfile.addEndpoint("", "", "Sts", endpoint);
            IClientProfile profile = DefaultProfile.getProfile("", AccessKeyId, accessKeySecret);
            // 用profile构造client
            DefaultAcsClient client = new DefaultAcsClient(profile);
            final AssumeRoleRequest request = new AssumeRoleRequest();
            request.setMethod(MethodType.POST);
            request.setRoleArn(roleArn);
            request.setRoleSessionName(roleSessionName);
            request.setPolicy(policy); // 若policy为空，则用户将获得该角色下所有权限

            //构造aliOss时的系统时间（毫秒）
            ALIOSS_TIME_STAMP = new Date().getTime();
            // request.setDurationSeconds(1200L); // 设置凭证有效时间，1200秒(20分钟)(默认1000L)
            request.setDurationSeconds(ALIOSS_EXPIRY_DATE); // 设置凭证有效时间，1200秒(20分钟)(默认1000L)
            final AssumeRoleResponse response = client.getAcsResponse(request);

            //将获取的配置传入OSS对象
            aliOss.setAccessKeyId(response.getCredentials().getAccessKeyId());
            aliOss.setAccessKeySecret(response.getCredentials().getAccessKeySecret());
            aliOss.setStsToken(response.getCredentials().getSecurityToken());
            aliOss.setRegion(region);
            aliOss.setBucket(bucket);

        } catch (ClientException e) {
            System.out.println("Failed：");
            System.out.println("Error code: " + e.getErrCode());
            System.out.println("Error message: " + e.getErrMsg());
            System.out.println("RequestId: " + e.getRequestId());
        }

        return aliOss;
    }

    public static void main(String[] args) {
        System.out.println(ALIOSS_TIME_STAMP);
        System.out.println(ALIOSS);
    }


}
