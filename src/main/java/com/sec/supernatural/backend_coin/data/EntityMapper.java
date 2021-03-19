package com.sec.supernatural.backend_coin.data;

import com.sec.supernatural.backend_coin.po.Entity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EntityMapper {
    void addEntity(Entity entity);
    void deleteEntity(@Param("picId") int picId,@Param("name") String name);
    void changeEntity(@Param("picId") int picId,@Param("oldName") String oldName,@Param("newName") String newName);
    List<Entity> getEntity(@Param("picId") int picId, @Param("name") String name);
    List<Entity> getEntitiesByPicId(@Param("picId") int picId);

}
