package com.sec.supernatural.backend_coin.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.sec.supernatural.backend_coin.bl.GraphService;
import com.sec.supernatural.backend_coin.constant.MyResponse;
import com.sec.supernatural.backend_coin.constant.ResponseCode;
import com.sec.supernatural.backend_coin.data.NodeDao;
import com.sec.supernatural.backend_coin.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/graph")
public class GraphController {
    @Autowired
    GraphService graphService;

    @Autowired
    NodeDao nodeDao;

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

//    private HttpResponseVO postMultipartFile(String url, MultipartFile file, String fileParamName,
//                                             Map<String, String> headerParams,
//                                             Map<String, String> otherParams) {
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        try {
//            String fileName = file.getOriginalFilename();
//            HttpPost httpPost = new HttpPost(url);
//            //添加header
//            if (headerParams != null) {
//                for (Map.Entry<String, String> e : headerParams.entrySet()) {
//                    httpPost.addHeader(e.getKey(), e.getValue());
//                }
//            }
//            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//            builder.setCharset(Charset.forName("utf-8"));
//            //加上此行代码解决返回中文乱码问题
//            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
//            //文件流处理
//            builder.addBinaryBody(fileParamName, file.getInputStream(), ContentType.MULTIPART_FORM_DATA, fileName);
//            if (otherParams != null) {
//                for (Map.Entry<String, String> e : otherParams.entrySet()) {
//                    //追加其他请求参数信息
//                    builder.addTextBody(e.getKey(), e.getValue());
//                }
//            }
//            HttpEntity entity = builder.build();
//            httpPost.setEntity(entity);
//            //执行提交
//            HttpResponse response = httpClient.execute(httpPost);
//            HttpEntity responseEntity = response.getEntity();
//            StatusLine statusLine = response.getStatusLine();
//            int status = statusLine.getStatusCode();
//            Header[] headers = response.getAllHeaders();
//            String body = EntityUtils.toString(responseEntity);
//            Map<String, String> headerMap = Maps.newHashMap();
//            if (ArrayUtils.isNotEmpty(headers)) {
//                for (Header header : headers) {
//                    headerMap.put(header.getName(), header.getValue());
//                }
//            }
//            return new HttpResponseVO(status, body, headerMap);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    @PostMapping("mongodb")
    public MyResponse mongodb(@RequestBody JSONObject jsonParam){
        nodeDao.saveByDocument(jsonParam);
        return MyResponse.ok("okk");
    }
}
