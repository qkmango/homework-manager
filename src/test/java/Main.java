import cn.qkmango.hm.exception.HomeworkException;
import cn.qkmango.hm.system.domain.AliOss;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        HashMap<String, Object> map = new HashMap<>();

        Object put = map.put("name", "zs");

        System.out.println(put);
    }


    public static int delete() throws HomeworkException {

        try {
            throw new HomeworkException("失败");
        } catch (HomeworkException e) {
            e.printStackTrace();
            throw e.getCause();
        } finally {
            return 1;
        }



    }


}
