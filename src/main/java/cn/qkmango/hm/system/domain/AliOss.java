package cn.qkmango.hm.system.domain;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @version 1.0
 * <p>AliOss</p>
 * <p>类中有AliOss所需要的参数，此类的accessKeyId、accessKeySecret均为 阿里云OSS RAM访问控制中的 hm 用户的，
 * 所以accessKeyId、accessKeySecret是永久的，切勿将此类配置传入前端</p>
 * @className AliOss
 * @author: Mango
 * @date: 2021-02-24 19:31
 */
public class AliOss {

    private String accessKeyId;
    private String accessKeySecret;
    private String endpoint;
    private String bucket;

    private AliOss(){
        Properties properties = new Properties();
        InputStream in = AliOss.class.getClassLoader().getResourceAsStream("alioss.properties");
        try {
            properties.load(in);
            accessKeyId      = properties.getProperty("AccessKeyId");
            accessKeySecret  = properties.getProperty("accessKeySecret");
            endpoint         = properties.getProperty("endpoint");
            bucket           = properties.getProperty("bucket");
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

    }

    private static AliOss instance = new AliOss();

    public static AliOss getInstance() {
        return instance;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getBucket() {
        return bucket;
    }

    @Override
    public String toString() {
        return "AliOss{" +
                "accessKeyId='" + accessKeyId + '\'' +
                ", accessKeySecret='" + accessKeySecret + '\'' +
                ", endpoint='" + endpoint + '\'' +
                ", bucket='" + bucket + '\'' +
                '}';
    }
}
