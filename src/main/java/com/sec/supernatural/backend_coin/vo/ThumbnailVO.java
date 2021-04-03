package com.sec.supernatural.backend_coin.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author shenyichen
 * @date 2021/4/3
 **/
@Data
public class ThumbnailVO {
    Integer userId;
    String picId;
    String picName;
    MultipartFile file;
}
