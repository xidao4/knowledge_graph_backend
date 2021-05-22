package com.sec.supernatural.backend_coin.dataImpl;

import com.sec.supernatural.backend_coin.data.EdgeMapper;

import com.sec.supernatural.backend_coin.po.Edge;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.TransactionWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static org.neo4j.driver.Values.parameters;

@Repository
public class EdgeMapperImpl implements EdgeMapper {
    private final Driver driver;
    private static String neo4jEdgeLabel="edgeTest";
    @Autowired
    public EdgeMapperImpl(Driver driver){
        this.driver=driver;
    }
    @Override
    public void insert(Edge edge){
        Session session = driver.session();

//        String buildQuery = "MATCH (a), (b) WHERE" +
//                " a.picId = $picId AND a.label = $aLabel " +
//                "AND b.picId = $picId AND b.label = $bLabel" +
//                " CREATE (a) - [r:"+neo4jEdgeLabel+"] -> (b) " +
//                "SET "+edge.getPropertiesAsString("r");

               // "SET r.label = $label, r.picId = $picId", r.type=$type;
               // "SET r.label = \"西稻\", r.picId = 44, r.type=\“名字\”;

        String buildQuery = "MATCH (a), (b) WHERE" +
                " a.picId = "+edge.getPicId()+" AND a.label = \""+edge.getSource()+
                "\" AND b.picId = "+edge.getPicId()+" AND b.label = \""+edge.getTarget()+
                "\" CREATE (a) - [r:"+neo4jEdgeLabel+"] -> (b) " +
                "SET "+edge.getPropertiesAsString("r");

        System.out.println("insert edge query: "+buildQuery);
        System.out.println("aLabel: "+edge.getSource());
        System.out.println("bLabel: "+edge.getTarget());

        try {
            session.writeTransaction(new TransactionWork<Void>() {
                @Override
                public Void execute(Transaction tx) {
//                    tx.run(buildQuery, parameters("aLabel", edge.getSource(),
//                            "bLabel", edge.getTarget(),"picId",edge.getPicId()));
                    tx.run(buildQuery,parameters());
                    return null;
                }
            });
        }
        finally {
            session.close();
        }
    }
}
