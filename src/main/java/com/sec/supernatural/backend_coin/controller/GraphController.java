package com.sec.supernatural.backend_coin.controller;

import com.alibaba.fastjson.JSONObject;
import com.sec.supernatural.backend_coin.bl.GraphService;
import com.sec.supernatural.backend_coin.constant.MyResponse;
import com.sec.supernatural.backend_coin.po.Graph;
import com.sec.supernatural.backend_coin.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/picElement")
    public MyResponse picElement(@RequestParam(value = "picId") String picId,
                                 @RequestParam(value = "userId") Integer userId,
                                 @RequestParam(value = "picName") String picName,
                                 @RequestBody MultipartFile mFile){
        PicUnitVO picUnitVO = new PicUnitVO(userId,picId,picName,mFile);
        return graphService.picElement(picUnitVO);
    }

    @PostMapping("/getPicElement")
    public MyResponse getPicElement(@RequestBody PicIdVO picIdVO){
        return graphService.getPicElement(picIdVO);
    }

    @PostMapping("/thumbnail")
    public MyResponse thumbnail(@RequestParam(value = "picId") String picId,
                                @RequestParam(value = "userId") Integer userId,
                                @RequestParam(value = "picName") String picName,
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
