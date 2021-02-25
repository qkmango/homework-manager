package cn.qkmango.hm.system.service.impl;

import cn.qkmango.hm.system.domain.QiniuConf;
import cn.qkmango.hm.system.service.QiniuService;
import com.qiniu.util.Auth;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Description: //TODO
 * <p>类简介</p>
 * <p>类详细介绍</p>
 * @className QiniuServiceImpl
 * @author: Mango
 * @date: 2021-02-25 10:03
 */
public class QiniuServiceImpl implements QiniuService {

    //Auth对象，用来生成token
    private static final Auth auth = Auth.create(
            QiniuConf.getInstance().getAccessKey(),
            QiniuConf.getInstance().getSecretKey());
    //有效时长（秒）
    private static final long expires = 1200;

    //创建token时间（毫秒）
    private static long createDateTime;

    private static String token;

    static {
        createToken();
    }

    /**
     * 获取七牛上传凭证 token
     * @return token
     */
    public static String getQiniuToken() {
        return createToken();
    }

    public static Map<String, String> getQiniuTokenAndConf() {

        String token = getQiniuToken();
        String link = QiniuConf.getInstance().getLink();
        String bucket = QiniuConf.getInstance().getBucket();

        HashMap<String, String> map = new HashMap<>();
        map.put("token",token);
        map.put("link",link);
        map.put("bucket",bucket);

        return map;
    }

    /**
     * 创建token
     */
    private static String createToken() {

        long nowDateTime = new Date().getTime();
        //创建时间+有效期=有效期时间，与现在的时间进行对比，如果小于现在的时间，说明已经过期
        if (createDateTime + expires*1000 < nowDateTime) {
            synchronized (auth) {
                if (createDateTime + expires*1000 < nowDateTime) {
                    createDateTime = new Date().getTime();
                    token = auth.uploadToken(QiniuConf.getInstance().getBucket(), null, expires, null, true);
                }
            }
        }

        return token;
    }

    public static void main(String[] args) {
        System.out.println(getQiniuToken());
    }

}
