package cn.qkmango.hm.system.service.impl;

import cn.qkmango.hm.system.domain.AliOss;
import cn.qkmango.hm.system.domain.AliOssSts;
import cn.qkmango.hm.system.service.OSSService;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
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
 * @className OSSServiceImpl
 * @author: Mango
 * @date: 2021-02-21 09:28
 */
public class OSSServiceImpl implements OSSService {

    private static AliOssSts ALIOSS_STS;                //AliOSS对象，返回给前端的
    private static long   ALIOSS_TIME_STAMP;            //AliOSS对象构造时，系统时间 （毫秒）
    private static long   ALIOSS_EXPIRY_DATE = 1200L;   //AliOSS对象构造时，传入的有效期限（1200秒）

    static {
        ALIOSS_STS = getSTS();
    }

    /**
     * 获取STS凭证、OSS配置属性的 AliOss对象
     * @return AliOssSts
     */
    public static AliOssSts getOssSTS() {
        long nowTime = new Date().getTime();
        //如果aliOSS创建时间已经超过15分钟（有效期20分钟），那么就重新创建aliOSS对象
        if (nowTime - ALIOSS_TIME_STAMP > 15*60*1000) {
            synchronized (new Object()) {
                if (nowTime - ALIOSS_TIME_STAMP > 15*60*1000) {
                    ALIOSS_STS = getSTS();
                }
            }
        }
        return ALIOSS_STS;
    }


    /**
     * 获取STS临时凭证
     * @return AliOssSts 返回OSS的STS临时凭证
     */
    private static AliOssSts getSTS() {

        AliOssSts aliOssSts = new AliOssSts();
        Properties properties = new Properties();
        InputStream in = OSSServiceImpl.class.getClassLoader().getResourceAsStream("alioss.properties");

        /**
         * 获取STS
         */
        try {

            properties.load(in);
            String stsEndpoint      = properties.getProperty("sts.endpoint");
            String endpoint         = properties.getProperty("endpoint");
            String AccessKeyId      = properties.getProperty("AccessKeyId");
            String accessKeySecret  = properties.getProperty("accessKeySecret");
            String roleArn          = properties.getProperty("roleArn");
            String roleSessionName  = properties.getProperty("roleSessionName");
            String stsPolicy           = properties.getProperty("sts.policy");
            String region           = properties.getProperty("region");
            String bucket           = properties.getProperty("bucket");

            DefaultProfile.addEndpoint("", "", "Sts", stsEndpoint);
            IClientProfile profile = DefaultProfile.getProfile("", AccessKeyId, accessKeySecret);
            // 用profile构造client
            DefaultAcsClient client = new DefaultAcsClient(profile);
            final AssumeRoleRequest request = new AssumeRoleRequest();
            request.setMethod(MethodType.POST);
            request.setRoleArn(roleArn);
            request.setRoleSessionName(roleSessionName);
            request.setPolicy(stsPolicy); // 若policy为空，则用户将获得该角色下所有权限

            //构造aliOss时的系统时间（毫秒）
            ALIOSS_TIME_STAMP = new Date().getTime();
            // request.setDurationSeconds(1200L); // 设置凭证有效时间，1200秒(20分钟)(默认1000L)
            request.setDurationSeconds(ALIOSS_EXPIRY_DATE); // 设置凭证有效时间，1200秒(20分钟)(默认1000L)
            final AssumeRoleResponse response = client.getAcsResponse(request);

            //将获取的配置传入OSS对象
            aliOssSts.setAccessKeyId(response.getCredentials().getAccessKeyId());
            aliOssSts.setAccessKeySecret(response.getCredentials().getAccessKeySecret());
            aliOssSts.setStsToken(response.getCredentials().getSecurityToken());
            aliOssSts.setRegion(region);
            aliOssSts.setBucket(bucket);
            aliOssSts.setStsEndpoint(stsEndpoint);
            aliOssSts.setEndpoint(endpoint);

        } catch (ClientException e) {
            System.out.println("Failed：");
            System.out.println("Error code: " + e.getErrCode());
            System.out.println("Error message: " + e.getErrMsg());
            System.out.println("RequestId: " + e.getRequestId());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return aliOssSts;
    }

    /**
     * 删除OSS中的Object（文件对象）
     * @param objectPath
     */
    public static void deleteObject(String objectPath) {
        AliOss instance = AliOss.getInstance();
        String endpoint = instance.getEndpoint();
        String accessKeyId = instance.getAccessKeyId();
        String accessKeySecret = instance.getAccessKeySecret();

        String bucketName = instance.getBucket();;
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.deleteObject(bucketName, objectPath);
        ossClient.shutdown();
    }

    public static void main(String[] args) {
        // deleteObject("微机原理/30022/100000.html");
    }




}
