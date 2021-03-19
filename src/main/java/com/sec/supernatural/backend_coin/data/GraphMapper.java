package com.sec.supernatural.backend_coin.data;

import com.sec.supernatural.backend_coin.po.Node;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface GraphMapper {
    void addEntity(Node node);
    void deleteEntity(@Param("picId") int picId,@Param("name") String name);
    void changeEntity(@Param("picId") int picId,@Param("oldName") String oldName,@Param("newName") String newName);
}
