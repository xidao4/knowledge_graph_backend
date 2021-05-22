package com.sec.supernatural.backend_coin.dataImpl;

import com.sec.supernatural.backend_coin.util.StringTools;
import org.neo4j.driver.*;
import com.sec.supernatural.backend_coin.data.NodeMapper;
import com.sec.supernatural.backend_coin.po.Graph;
import com.sec.supernatural.backend_coin.po.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

import static org.neo4j.driver.Values.ofOffsetTime;
import static org.neo4j.driver.Values.parameters;

@Repository
public class NodeMapperImpl implements NodeMapper {
    private final Driver driver;
    /**
     *     org.neo4j.driver public interface Driver
     *             extends AutoCloseable
     *   Maven: org.neo4j.driver:neo4j-java-driver:4.2.0
     */
    private final String neo4jNodeLabel="node";

    @Autowired
    public NodeMapperImpl(Driver driver){
        this.driver=driver;
    }

    @Override
    public void insert(Node node) {
        Session session=driver.session();
        String insertQuery="CREATE (a:"+neo4jNodeLabel+") SET "+ node.getPropertiesAsString("a");
        System.out.println("insert node query: "+insertQuery);
        try{
            session.writeTransaction(new TransactionWork<Void>() {
                @Override
                public Void execute(Transaction tx){
                    tx.run(insertQuery,parameters());
                    return null;
                }
            });
        }finally {
            session.close();
        }
    }

    /**
     * 根据节点名删除节点及其对应关系
     * @param label
     * @param picId
     */
    @Override
    public void deleteNodeByName(String label, String picId) {
        Session session=driver.session();
        String deleteQuery1="MATCH (a) - [r] -> (b) WHERE a.picId = $picId AND a.label = $label DELETE r";
        String deleteQuery2="MATCH (a) - [r] -> (b) WHERE b.picId = $picId AND b.label = $label DELETE r";
        String deleteQuery="MATCH (a) WHERE a.picId = $picId AND a.label = $label DELETE a";
        try{
            session.writeTransaction(new TransactionWork<Void>() {
                @Override
                public Void execute(Transaction tx){
                    tx.run(deleteQuery,parameters("label",label,"picId",picId));
                    tx.run(deleteQuery1,parameters("label",label,"picId",picId));
                    tx.run(deleteQuery2,parameters("label",label,"picId",picId));
                    return null;
                }
            });
        }finally{
            session.close();
        }
    }

    @Override
    public void updateNode(Node newNode, String oldLabel, String picId) {
        Session session=driver.session();
        String updateQuery="MATCH (a) WHERE a.picId=$picId AND a.label=$oldLabel SET "+newNode.getPropertiesAsString("a");
        try{
            session.writeTransaction(new TransactionWork<Void>() {
                @Override
                public Void execute(Transaction tx){
                    tx.run(updateQuery,parameters("picId",picId,"oldLabel",oldLabel));
                    return null;
                }
            });
        }finally {
            session.close();
        }
    }


    @Override
    public Node retrieveNodesByPicId(String picId) {
        Session session=driver.session();
        String getGraphQuery="MATCH ( a : node ) WHERE a.picId=$picId RETURN a";
        try{
            session.readTransaction(new TransactionWork<Void>() {
                @Override
                public Void execute(Transaction tx){
                    Result ret=tx.run(getGraphQuery,parameters("picId",picId));
                    return null;
                }
            });
        }finally{
            session.close();
        }
        return null;
    }

    @Override
    public Node findByName(String label, String picId) {

        Session session = driver.session();
        String getQuery = "MATCH(a:node) WHERE a.picId = $picId AND a.label = $label RETURN a";

        try {
            return session.readTransaction(new TransactionWork<Node>() {
                //没写好
                @Override
                public Node execute(Transaction tx) {
                    Result result = tx.run(getQuery, parameters("picId", picId, "label", label));
                    if (result.hasNext()) {
                        org.neo4j.driver.types.Node node = result.next().get(0).asNode();
                        Map<String, Object> properties = node.asMap();
                        return new Node(properties);
                    } else {
                        return null;
                    }
                }
            });
        }finally{
            session.close();
        }
    }
}
