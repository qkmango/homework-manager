package cn.qkmango.hm.system.domain;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @version 1.0
 * <p>类简介</p>
 * <p>类详细介绍</p>
 * @className QiniuConf
 * @author: Mango
 * @date: 2021-02-25 09:46
 */
public class QiniuConf {

    private String accessKey;
    private String secretKey;
    private String bucket;
    private String link;

    private QiniuConf() {
        Properties properties = new Properties();
        InputStream in = QiniuConf.class.getClassLoader().getResourceAsStream("qiniu.properties");
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        accessKey = properties.getProperty("accessKey");
        secretKey = properties.getProperty("secretKey");
        bucket = properties.getProperty("bucket");
        link = properties.getProperty("link");
    }

    private static QiniuConf instance = new QiniuConf();


    public static QiniuConf getInstance() {
        return instance;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getBucket() {
        return bucket;
    }

    public String getLink() {
        return link;
    }

    @Override
    public String toString() {
        return "QiniuConf{" +
                "accessKey='" + accessKey + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", bucket='" + bucket + '\'' +
                '}';
    }

    public static void main(String[] args) {
        System.out.println(getInstance());
    }
}
