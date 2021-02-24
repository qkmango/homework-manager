import cn.qkmango.hm.system.domain.AliOss;
import cn.qkmango.hm.system.domain.AliOssSts;
import cn.qkmango.hm.system.service.impl.OSSServiceImpl;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

public class Main {

    public static void main(String[] args) {
        delete();

        // OSSServiceImpl.getOssSTS();

    }


    public static void delete() {
        AliOss instance = AliOss.getInstance();
        String endpoint = instance.getEndpoint();
        String accessKeyId = instance.getAccessKeyId();
        String accessKeySecret = instance.getAccessKeySecret();

        String bucketName = "qkmango";
        String objectName = "index.html";
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.deleteObject(bucketName, objectName);
        ossClient.shutdown();
    }


}
