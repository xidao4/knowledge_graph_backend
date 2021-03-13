package com.sec.supernatural.backend_coin.blImpl;

import com.sec.supernatural.backend_coin.bl.TestBl;
import com.sec.supernatural.backend_coin.mapper.TestMapper;
import com.sec.supernatural.backend_coin.po.TestPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestBlImpl implements TestBl {
    @Autowired
    private TestMapper testMapper;
    @Override
    public int addMovie(TestPO testPO) {
        return testMapper.addMovie(testPO);
    }
}
