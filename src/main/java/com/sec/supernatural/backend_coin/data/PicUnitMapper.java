package com.sec.supernatural.backend_coin.data;

import com.sec.supernatural.backend_coin.po.PicUnit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author shenyichen
 * @date 2021/4/14
 **/
@Mapper
@Repository
public interface PicUnitMapper {
    void addPicUnit(PicUnit picUnit);
    List<PicUnit> findByPicId(String picId);
    void updateUnitName(@Param("picId") String picId, @Param("url") String url, @Param("unitName") String unitName);
}
