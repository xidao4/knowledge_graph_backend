package com.sec.supernatural.backend_coin;


import com.sec.supernatural.backend_coin.util.FileUtil;
import com.sec.supernatural.backend_coin.util.GsonUtils;
import com.sec.supernatural.backend_coin.util.HttpUtil;
import com.sec.supernatural.backend_coin.util.ImageUtil;
import org.apache.http.entity.ContentType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * easydl图像分类
 */
public class TestClassify {

    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
    public static String easydlImageClassify(MultipartFile mfile) {
        // 请求url
        String url = "https://aip.baidubce.com/rpc/2.0/ai_custom/v1/classification/honglou";
        try {
            Map<String, Object> map = new HashMap<>();
            String str = ImageUtil.multipartFileToBASE64(mfile);
            map.put("image", str);
            map.put("top_num", "9");

            String param = GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = Authentificate.getAuth();

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws IOException {

        File file = new File("D:\\自分\\Studies\\资料\\大三下资料\\软工3\\backend_coin\\datastore\\1.jpg");
        if (!file.exists()) {
            System.out.println("文件不存在！");
        } else {
            System.out.println("文件存在！");
        }
        FileInputStream inputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), file.getName(),
                ContentType.APPLICATION_OCTET_STREAM.toString(), inputStream);
        String result = TestClassify.easydlImageClassify(multipartFile);
        // result:
        // {"log_id":2023306780061063579,
        // "results":[{"name":"0_baidu","score":0.9682695269584656},{"name":"0_google","score":0.02047288976609707},{"name":"3_baidu","score":0.0042624641209840775},{"name":"8_baidu","score":0.002089953515678644},{"name":"7_baidu","score":0.0019145160913467407},{"name":"6_baidu","score":0.0008043393027037382},{"name":"4_baidu","score":0.0006909500807523727},{"name":"2_baidu","score":0.0005396428168751299},{"name":"8_google","score":0.0003127690579276532}]}

    }
}