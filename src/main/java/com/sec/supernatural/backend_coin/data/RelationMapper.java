package com.sec.supernatural.backend_coin.data;

import com.sec.supernatural.backend_coin.po.Entity;
import com.sec.supernatural.backend_coin.po.Relation;
import com.sec.supernatural.backend_coin.vo.ChangeRelationVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RelationMapper {
    void addRelation(Relation relation);
    void deleteRelation(Relation relation);
    List<Relation> getRelationsByNodesAndName(@Param("picId") int picId,
                                              @Param("node1") String node1,
                                              @Param("node2") String node2,
                                              @Param("name") String name);
    List<Relation> getRelationsByNode(Entity entity);
    void changeRelationName(@Param("picId") int picId,
                            @Param("node1") String node1,@Param("node2") String node2,
                            @Param("name") String name,@Param("newName") String newName);
    void changeRelationType(@Param("picId") int picId,
                            @Param("node1") String node1,@Param("node2") String node2,
                            @Param("name") String name,@Param("newType") String newType);
    void changeRelationNameAndType(ChangeRelationVO changeRelationVO);
}
