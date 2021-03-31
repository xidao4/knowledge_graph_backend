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
    public ResponseEntity<byte[]> download(@RequestBody PicIdVO picIdVO){
        MultipartFile mFile = graphService.dao2Json(Integer.parseInt(picIdVO.getPicId()));
        ResponseEntity<byte[]> response = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.add("content-disposition", "inline;filename=" + mFile.getOriginalFilename());
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        try {
            response = new ResponseEntity<byte[]>(mFile.getBytes(), headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }


    @PostMapping("/save")
    public MyResponse save(@RequestBody Graph graph) {
        return graphService.save(graph);
    }

    @PostMapping("/getPicElements")
    public MyResponse getPicElements(@RequestBody PicIdVO picIdVO) {
        return graphService.getPicElements(picIdVO);
    }

    @PostMapping("/getPicTypes")
    public MyResponse getPicTypes(@RequestBody PicIdVO picIdVO) {
        return graphService.getPicTypes(picIdVO);
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
