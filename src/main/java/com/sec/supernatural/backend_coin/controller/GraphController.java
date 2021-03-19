package com.sec.supernatural.backend_coin.controller;

import com.sec.supernatural.backend_coin.bl.GraphService;
import com.sec.supernatural.backend_coin.constant.MyResponse;
import com.sec.supernatural.backend_coin.vo.ChangeRelationVO;
import com.sec.supernatural.backend_coin.vo.NodeVO;
import com.sec.supernatural.backend_coin.vo.RelationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/graph")
public class GraphController {
    @Autowired
    GraphService graphService;

    @PostMapping("/addEntity")
    public MyResponse addEntity(@RequestBody NodeVO nodeVO){
        return graphService.addEntity(nodeVO);
    }
    @PostMapping("/deleteEntity")
    public MyResponse deleteEntity(@RequestBody NodeVO nodeVO){
        return graphService.deleteEntity(nodeVO);
    }
    @PostMapping("/changeEntity")
    public MyResponse changeEntity(@RequestParam Integer picId,
                                   @RequestParam String oldName,
                                   @RequestParam String newName){
        return graphService.changeEntity(picId,oldName,newName);
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
