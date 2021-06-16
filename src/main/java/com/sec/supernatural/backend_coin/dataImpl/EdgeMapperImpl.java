package com.sec.supernatural.backend_coin.dataImpl;

import com.sec.supernatural.backend_coin.data.EdgeMapper;

import com.sec.supernatural.backend_coin.po.Edge;
import com.sec.supernatural.backend_coin.po.Node;
import org.neo4j.driver.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.neo4j.driver.Values.parameters;

@Repository
public class EdgeMapperImpl implements EdgeMapper {
    private final Driver driver;
    private static String neo4jEdgeLabel="edgeTest";
    @Autowired
    public EdgeMapperImpl(Driver driver){
        this.driver=driver;
    }

//    @Override
//    public void insert(Edge edge){
//        Session session = driver.session();
//
////        String buildQuery = "MATCH (a), (b) WHERE" +
////                " a.picId = $picId AND a.label = $aLabel " +
////                "AND b.picId = $picId AND b.label = $bLabel" +
////                " CREATE (a) - [r:"+neo4jEdgeLabel+"] -> (b) " +
////                "SET "+edge.getPropertiesAsString("r");
//
//               // "SET r.label = $label, r.picId = $picId", r.type=$type;
//               // "SET r.label = \"西稻\", r.picId = 44, r.type=\“名字\”;
//
//        String buildQuery = "MATCH (a), (b) WHERE" +
//                " a.picId = "+edge.getPicId()+" AND a.label = \""+edge.getSource()+
//                "\" AND b.picId = "+edge.getPicId()+" AND b.label = \""+edge.getTarget()+
//                "\" CREATE (a) - [r:"+neo4jEdgeLabel+"] -> (b) " +
//                "SET "+edge.getPropertiesAsString("r");
//
//        System.out.println("insert edge query: "+buildQuery);
//        System.out.println("aLabel: "+edge.getSource());
//        System.out.println("bLabel: "+edge.getTarget());
//
//        try {
//            session.writeTransaction(new TransactionWork<Void>() {
//                @Override
//                public Void execute(Transaction tx) {
////                    tx.run(buildQuery, parameters("aLabel", edge.getSource(),
////                            "bLabel", edge.getTarget(),"picId",edge.getPicId()));
//                    tx.run(buildQuery,parameters());
//                    return null;
//                }
//            });
//        }
//        finally {
//            session.close();
//        }
//    }

    //used
    @Override
    public List<Edge> getNeighborEdgesByLabel(String label, String picId) {
        Session session=driver.session();
        String getQuery="MATCH (a)-[r]-(b) WHERE a.picId=$picId AND a.label=$label RETURN r";
        try{
            return session.readTransaction(new TransactionWork<List<Edge>>() {
                @Override
                public List<Edge> execute(Transaction transaction) {
                    Result result=transaction.run(getQuery,parameters("picId",picId,"label",label));
                    List<Edge> lst=new ArrayList<>();
                    while(result.hasNext()){
                        //Record record=result.next();
//                        lst.add(new Edge(record.get(0).asString(), record.get(1).asString()
//                                , record.get(2).asString(), record.get(3).asString()));
                        org.neo4j.driver.types.Relationship edge = result.next().get(0).asRelationship();
                        String label=edge.type();
                        long idLong=edge.id();
                        String id=String.valueOf(idLong);
                        //HashMap<String,Object> properties=edge.properties();

                        Map<String,Object> properties = edge.asMap();
                        Map<String,Object> f=new HashMap<>();
                        f.put("id",id);
                        f.put("label",label);
                        for(Map.Entry<String,Object> entry:properties.entrySet()){
                            f.put(entry.getKey(),String.valueOf(entry.getValue()));
                        }
                        lst.add(new Edge(f));
                    }
                    return lst;
                }
            });
        }finally {
            session.close();
        }
    }
}
