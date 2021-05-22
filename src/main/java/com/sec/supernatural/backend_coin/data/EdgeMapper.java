package com.sec.supernatural.backend_coin.data;

import com.sec.supernatural.backend_coin.po.Edge;
import org.springframework.stereotype.Repository;

@Repository
public interface EdgeMapper {
    public void insert(Edge edge);
}
