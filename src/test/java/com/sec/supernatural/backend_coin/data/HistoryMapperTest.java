package com.sec.supernatural.backend_coin.data;

import com.sec.supernatural.backend_coin.po.History;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author shenyichen
 * @date 2021/4/6
 **/
@SpringBootTest
public class HistoryMapperTest {
    @Autowired
    HistoryMapper historyMapper;

    @Test
//    @Transactional
    void addHistoryTest(){
        History history = new History();
        history.setUserId(1);
        history.setKeyword("test");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        history.setCreateTime(sdf.format(date));
        historyMapper.addHistory(history);
    }

    @Test
    @Transactional
    void find10LatestKeywordTest(){
        List<String> keywords = historyMapper.find10LatestKeyword(1);
        System.out.println("size: "+keywords.size());
        for(int i=0;i<keywords.size();i++){
            System.out.println(keywords.get(i));
        }
    }
}
