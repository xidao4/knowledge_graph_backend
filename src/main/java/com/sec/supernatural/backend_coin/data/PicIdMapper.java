package com.sec.supernatural.backend_coin.data;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PicIdMapper {
    void updatePicId();
    int getPicId();
}
