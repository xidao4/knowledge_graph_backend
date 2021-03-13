package com.sec.supernatural.backend_coin.mapper;

import com.sec.supernatural.backend_coin.po.TestPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TestMapper {
    int addMovie(TestPO testPO);
}
