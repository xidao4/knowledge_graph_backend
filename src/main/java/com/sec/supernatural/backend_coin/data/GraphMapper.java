package com.sec.supernatural.backend_coin.data;

import com.sec.supernatural.backend_coin.po.Link;
import com.sec.supernatural.backend_coin.po.Node;
import com.sec.supernatural.backend_coin.vo.ChangeRelationVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GraphMapper {
    void addEntity(Node node);
    void deleteEntity(@Param("picId") int picId,@Param("name") String name);
    void changeEntity(@Param("picId") int picId,@Param("oldName") String oldName,@Param("newName") String newName);
    List<Node> getEntity(@Param("picId") int picId,@Param("name") String name);
    List<Node> getEntitiesByPicId(@Param("picId") int picId);

    void addRelation(Link link);
    void deleteRelation(Link link);
    List<Link> getRelationsByNodesAndName(@Param("picId") int picId,
                            @Param("source") String source,
                            @Param("target") String target,
                            @Param("name") String name);
    List<Link> getRelationsByNode(Node node);
    List<Link> getRelationsBySource(Node node);
    void changeRelationName(@Param("picId") int picId,
                            @Param("source") String source,@Param("target") String target,
                            @Param("name") String name,@Param("newName") String newName);
    void changeRelationType(@Param("picId") int picId,
                            @Param("source") String source,@Param("target") String target,
                            @Param("name") String name,@Param("newType") String newType);
    void changeRelationNameAndType(ChangeRelationVO changeRelationVO);
}
