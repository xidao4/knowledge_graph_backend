package com.sec.supernatural.backend_coin.data;

import com.sec.supernatural.backend_coin.po.Thumbnail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author shenyichen
 * @date 2021/4/1
 */
@Mapper
@Repository
public interface ThumbnailMapper {
    void addThumbnail(Thumbnail thumbnail);
    List<Thumbnail> findByUserId(Integer userId);
}
