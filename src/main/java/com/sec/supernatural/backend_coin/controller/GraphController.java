package com.sec.supernatural.backend_coin.controller;

import com.sec.supernatural.backend_coin.bl.GraphService;
import com.sec.supernatural.backend_coin.constant.MyResponse;
import com.sec.supernatural.backend_coin.constant.ResponseCode;
import com.sec.supernatural.backend_coin.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/graph")
public class GraphController {
    @Autowired
    GraphService graphService;

    @PostMapping("/getAll")
    public MyResponse getAll(@RequestBody MultipartFile mFile){
        int picId = graphService.json2Dao(mFile);
        if(picId==-1){
            return MyResponse.error("picId不存在");
        }
        GraphVO graphVO = graphService.getAll(picId);
        return MyResponse.ok(graphVO);
    }

    @PostMapping("/download")
    public MyResponse download(@RequestBody PicIdVO picIdVO){
        MultipartFile mFile = graphService.dao2Json(Integer.parseInt(picIdVO.getPicId()));
        if(mFile==null){
            return MyResponse.error("File Null");
        }
        return MyResponse.ok(mFile);
    }


    @PostMapping("/addEntity")
    public MyResponse addEntity(@RequestBody EntityVO entityVO){
        return graphService.addEntity(entityVO);
    }
    @PostMapping("/deleteEntity")
    public MyResponse deleteEntity(@RequestBody EntityVO entityVO){
        return graphService.deleteEntity(entityVO);
    }
    @PostMapping("/changeEntity")
    public MyResponse changeEntity(@RequestBody ChangeEntityVO changeEntityVO){
        return graphService.changeEntity(Integer.valueOf(changeEntityVO.getPicId()),changeEntityVO.getOldName(),changeEntityVO.getNewName());
    }

    @PostMapping("/addRelation")
    public MyResponse addRelation(@RequestBody RelationVO relationVO){
        return graphService.addRelation(relationVO);
    }
    @PostMapping("/deleteRelation")
    public MyResponse deleteRelation(@RequestBody RelationVO relationVO){
        return graphService.deleteRelation(relationVO);
    }
    @PostMapping("/changeRelation")
    public MyResponse changeRelation(@RequestBody ChangeRelationVO changeRelationVO){
        return graphService.changeRelation(changeRelationVO);
    }
}
