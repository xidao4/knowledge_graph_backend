package com.sec.supernatural.backend_coin.data;

import com.sec.supernatural.backend_coin.po.History;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author shenyichen
 * @date 2021/4/3
 */
@Mapper
@Repository
public interface HistoryMapper {
    void addHistory(History history);
    List<String> find10LatestKeyword(Integer userId);
}
