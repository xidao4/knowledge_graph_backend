package com.sec.supernatural.backend_coin.controller;

import com.sec.supernatural.backend_coin.bl.GraphService;
import com.sec.supernatural.backend_coin.constant.MyResponse;
import com.sec.supernatural.backend_coin.constant.ResponseCode;
import com.sec.supernatural.backend_coin.vo.ChangeRelationVO;
import com.sec.supernatural.backend_coin.vo.GraphVO;
import com.sec.supernatural.backend_coin.vo.EntityVO;
import com.sec.supernatural.backend_coin.vo.RelationVO;
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
    public MyResponse download(@RequestBody String picId){
        MultipartFile mFile = graphService.dao2Json(Integer.parseInt(picId));
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
    public MyResponse changeEntity(@RequestParam String picId,
                                   @RequestParam String oldName,
                                   @RequestParam String newName){
        return graphService.changeEntity(Integer.valueOf(picId),oldName,newName);
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
