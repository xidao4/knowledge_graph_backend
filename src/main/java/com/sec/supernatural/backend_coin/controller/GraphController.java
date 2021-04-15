package com.sec.supernatural.backend_coin.controller;

import com.alibaba.fastjson.JSONObject;
import com.sec.supernatural.backend_coin.bl.GraphService;
import com.sec.supernatural.backend_coin.constant.MyResponse;
import com.sec.supernatural.backend_coin.po.Graph;
import com.sec.supernatural.backend_coin.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author shenyichen
 * @date 2021/3/31
 */
@RestController
@RequestMapping("/api/graph")
public class GraphController {

    @Autowired
    GraphService graphService;

    @PostMapping("/getAll")
    public MyResponse getAll(@RequestBody MultipartFile mfile) {
        try{
            return graphService.json2Dao(mfile);
        } catch (IOException e) {
            e.printStackTrace();
            return MyResponse.error("Exception in backend !");
        }
    }

    @PostMapping("/download")
    public MyResponse download(@RequestBody PicIdVO picIdVO){
        return graphService.dao2JsonUrl(picIdVO);
    }

    @PostMapping("/save")
    public MyResponse save(@RequestBody Graph graph) {
        return graphService.save(graph);
    }

    @PostMapping("/getPicElements")
    public MyResponse getPicElements(@RequestBody PicIdVO picIdVO) {
        return graphService.getPicElements(picIdVO);
    }

    @PostMapping("/getNodesByTypes")
    public MyResponse getNodesByTypes(@RequestBody PicIdVO picIdVO){
        return graphService.getNodesByTypes(picIdVO);
    }

    @PostMapping("/getNodeTypes")
    public MyResponse getNodeTypes(@RequestBody PicIdVO picIdVO){
        return graphService.getNodeTypes(picIdVO);
    }

    @PostMapping("/getPicTypes")
    public MyResponse getPicTypes(@RequestBody PicIdVO picIdVO) {
        return graphService.getPicTypes(picIdVO);
    }

    @PostMapping("/uploadCustomizeImg/{picId}/{userId}/{picName}")
    public MyResponse uploadCustomizeImg(@PathVariable String picId,
                                 @PathVariable Integer userId,
                                 @PathVariable String picName,
                                 @RequestBody MultipartFile mFile){
        PicUnitVO picUnitVO = new PicUnitVO(userId,picId,picName,mFile);
        return graphService.uploadCustomizeImg(picUnitVO);
    }

    @PostMapping("/getPicCustomizeElements")
    public MyResponse getPicCustomizeElements(@RequestBody PicIdVO picIdVO){
        return graphService.getPicCustomizeElements(picIdVO);
    }

    @PostMapping("/bindUrlToPic")
    public MyResponse bindUrlToPic(@RequestBody BindUrlToPicVO bindUrlToPicVO){
        return graphService.bindUrlToPic(bindUrlToPicVO);
    }

    @PostMapping("/thumbnail/{picId}/{userId}/{picName}")
    public MyResponse thumbnail(@PathVariable String picId,
                                @PathVariable Integer userId,
                                @PathVariable String picName,
                                @RequestBody MultipartFile mFile){
        ThumbnailVO thumbnailVO = new ThumbnailVO(userId,picId,picName,mFile);
        return graphService.storeThumbnail(thumbnailVO);
    }

    @PostMapping("/getUserPics")
    public MyResponse getUserPics(@RequestBody UserIdVO userIdVO) {
        return graphService.getUserPics(userIdVO);
    }

    @PostMapping("/getHistory")
    public MyResponse getHistory(@RequestBody UserIdVO userIdVO) {
        return graphService.getHistory(userIdVO);
    }

    @PostMapping("/search")
    public MyResponse search(@RequestBody SearchVO searchVO) {
        return graphService.search(searchVO);
    }

}
