package com.sec.supernatural.backend_coin.data;

import com.sec.supernatural.backend_coin.po.Graph;
import com.sec.supernatural.backend_coin.po.Node;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NodeMapper {

    public void insert(Node node);

    public void deleteNodeByName(String label,String picId);
    public void updateNode(Node newNode,String oldLabel,String picId);
    public Node findByName(String label,String picId);
    public Node retrieveNodesByPicId(String picId);

    public List<Node> getNeighborsByLabel(String label,String picId);

}
