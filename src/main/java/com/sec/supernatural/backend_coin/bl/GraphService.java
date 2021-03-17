package com.sec.supernatural.backend_coin.bl;

import com.sec.supernatural.backend_coin.vo.GraphVO;
import org.json.JSONException;
import org.springframework.web.multipart.MultipartFile;

public interface GraphService {
    /**
     * 返回picId
     * @param file
     * @return
     */
    int json2Dao(MultipartFile file);

    /**
     * 根据picId返回graph数据
     * @param picId
     * @return
     */
    GraphVO getAll(int picId);

    /**
     * 根据picId返回json文件
     * @param picId
     * @return
     */
    MultipartFile dao2Json(int picId);
}
