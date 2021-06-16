package com.sec.supernatural.backend_coin.data;

import com.sec.supernatural.backend_coin.po.Edge;
import com.sec.supernatural.backend_coin.po.Node;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EdgeMapper {
    //public void insert(Edge edge);
    public List<Edge> getNeighborEdgesByLabel(String label, String picId);

}
