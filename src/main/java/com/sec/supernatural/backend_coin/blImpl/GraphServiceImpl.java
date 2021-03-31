package com.sec.supernatural.backend_coin.blImpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sec.supernatural.backend_coin.bl.GraphService;
import com.sec.supernatural.backend_coin.constant.MyResponse;
import com.sec.supernatural.backend_coin.data.MongoDBMapper;
import com.sec.supernatural.backend_coin.po.Graph;
import com.sec.supernatural.backend_coin.vo.*;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author shenyichen
 * @date 2021/3/29
 */
@Service
public class GraphServiceImpl implements GraphService {

    @Autowired
    MongoDBMapper mongoDBMapper;

    @Override
    public MyResponse json2Dao(MultipartFile mfile) throws IOException {
        // mFile to file
        String fullFileName = StringUtils.cleanPath(mfile.getOriginalFilename());//测试时要替换成file.getName
        File file = File.createTempFile(fullFileName.substring(0,fullFileName.lastIndexOf('.')), fullFileName.substring(fullFileName.lastIndexOf('.')-1));
        file.deleteOnExit();
        FileUtils.copyInputStreamToFile(mfile.getInputStream(), file);
        // file to str
        String str = FileUtils.readFileToString(file, "utf-8");
        // str to JSONObject
        JSONObject jsonObject = JSONObject.parseObject(str);
        // JSONObject to Graph
        JSONArray nodes = jsonObject.getJSONArray("nodes");
        JSONArray edges = jsonObject.getJSONArray("edges");
        Graph graph = new Graph();
        graph.setFnodes(nodes);
        graph.setFedges(edges);
        graph.setSnodes(nodes);
        graph.setSedges(edges);
        // save data
        save(graph);
        // build response
        return MyResponse.ok(graph);
    }

    @Override
    public MyResponse dao2JsonUrl(PicIdVO picIdVO) {

        return null;
    }

    @Override
    public MyResponse save(Graph graph) {
        mongoDBMapper.saveGraph(graph);
        return MyResponse.ok("success");
    }

    @Override
    public MyResponse getPicElements(PicIdVO picIdVO) {
        return null;
    }

    @Override
    public MyResponse getPicTypes(PicIdVO picIdVO) {
        return null;
    }

    @Override
    public MyResponse getUserPics(UserIdVO userIdVO) {
        return null;
    }

    @Override
    public MyResponse getHistory(UserIdVO userIdVO) {
        return null;
    }

    @Override
    public MyResponse search(SearchVO searchVO) {
        return null;
    }
}
