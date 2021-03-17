package com.sec.supernatural.backend_coin.blImpl;

import com.sec.supernatural.backend_coin.bl.GraphService;
import com.sec.supernatural.backend_coin.mapper.GraphMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GraphServiceImpl implements GraphService {
    @Autowired
    GraphMapper graphMapper;
}
