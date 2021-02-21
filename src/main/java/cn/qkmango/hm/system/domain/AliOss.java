package cn.qkmango.hm.system.domain;

/**
 * @version 1.0
 * <p>AliOss</p>
 * <p>包含前端上传文件到OSS时所需要的参数和</p>
 * @className AliOss
 * @author: Mango
 * @date: 2021-02-21 08:54
 */
public class AliOss {

    private String region;
    private String accessKeyId;
    private String accessKeySecret;
    private String stsToken;
    private String bucket;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getStsToken() {
        return stsToken;
    }

    public void setStsToken(String stsToken) {
        this.stsToken = stsToken;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    @Override
    public String toString() {
        return "AliOss{" +
                "region='" + region + '\'' +
                ", accessKeyId='" + accessKeyId + '\'' +
                ", accessKeySecret='" + accessKeySecret + '\'' +
                ", stsToken='" + stsToken + '\'' +
                ", bucket='" + bucket + '\'' +
                '}';
    }
}
