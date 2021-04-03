package com.sec.supernatural.backend_coin.blImpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sec.supernatural.backend_coin.bl.GraphService;
import com.sec.supernatural.backend_coin.bl.StorageService;
import com.sec.supernatural.backend_coin.constant.MyResponse;
import com.sec.supernatural.backend_coin.constant.ResponseCode;
import com.sec.supernatural.backend_coin.data.HistoryMapper;
import com.sec.supernatural.backend_coin.data.MongoDBMapper;
import com.sec.supernatural.backend_coin.data.ThumbnailMapper;
import com.sec.supernatural.backend_coin.po.Graph;
import com.sec.supernatural.backend_coin.po.History;
import com.sec.supernatural.backend_coin.po.Thumbnail;
import com.sec.supernatural.backend_coin.vo.*;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author shenyichen
 * @date 2021/3/29
 */
@Service
public class GraphServiceImpl implements GraphService {

    @Autowired
    StorageService storageService;
    @Autowired
    MongoDBMapper mongoDBMapper;
    @Autowired
    ThumbnailMapper thumbnailMapper;
    @Autowired
    HistoryMapper historyMapper;

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
        MyResponse myResponse = save(graph);
        // build response
        if(myResponse.getCode()== ResponseCode.OK)
            return MyResponse.ok(graph);
        else
            return myResponse;
    }

    @Override
    public MyResponse dao2JsonUrl(PicIdVO picIdVO) {
        // dao to Graph
        String picId = picIdVO.getPicId();
        Graph graph = mongoDBMapper.findGraph(picId);
        // Graph to json str
        String str = JSONObject.toJSONString(graph);
        // str写入file，返回url
        String url = storageService.storeFile(str,picId+".json");
        // build response
        if(url.length()==0 || url==null){
            return MyResponse.error("Error Generating Json File !");
        }else{
            return MyResponse.ok(url);
        }
    }

    @Override
    public MyResponse save(Graph graph) {
        mongoDBMapper.saveGraph(graph);
        if(graph.getPicId()==null || graph.getPicId()=="")
            return MyResponse.error("图元信息保存失败");
        else
            return MyResponse.ok("success");
    }

    @Override
    public MyResponse getPicElements(PicIdVO picIdVO) {
        String picId = picIdVO.getPicId();
        Graph graph = mongoDBMapper.findGraph(picId);
        if(graph==null || ! graph.getPicId().equals(picId))
            return MyResponse.error("Can Not Find Pic !");
        else
            return MyResponse.ok(graph);
    }

    @Override
    public MyResponse getPicTypes(PicIdVO picIdVO) {
        String picId = picIdVO.getPicId();
        Graph graph = mongoDBMapper.findGraph(picId);
        if(graph==null || ! graph.getPicId().equals(picId))
            return MyResponse.error("Can Not Find Pic !");
        JSONArray fnodesJson = graph.getFnodes();
        List<JSONObject> fnodesList = JSONObject.parseArray(fnodesJson.toJSONString(), JSONObject.class);
        Map<String, List<JSONObject>> fnodesMap = fnodesList.stream().collect(Collectors.groupingBy(item -> item.getString("type")));
        JSONArray snodesJson = graph.getSnodes();
        List<JSONObject> snodesList = JSONObject.parseArray(snodesJson.toJSONString(), JSONObject.class);
        Map<String, List<JSONObject>> snodesMap = snodesList.stream().collect(Collectors.groupingBy(item -> item.getString("type")));
        PicTypesVO picTypesVO = new PicTypesVO();
        picTypesVO.setFnodesMap(fnodesMap);
        picTypesVO.setSnodesMap(snodesMap);
        return MyResponse.ok(picTypesVO);
    }

    @Override
    public MyResponse storeThumbnail(ThumbnailVO thumbnailVO) {
        String url = storageService.storeImage(thumbnailVO.getFile());
        Thumbnail thumbnail = new Thumbnail();
        BeanUtils.copyProperties(thumbnailVO,thumbnail);
        thumbnail.setUrl(url);
        thumbnailMapper.addThumbnail(thumbnail);
        return MyResponse.ok("success");
    }

    @Override
    public MyResponse getUserPics(UserIdVO userIdVO) {
        List<Thumbnail> thumbnails = thumbnailMapper.findByUserId(userIdVO.getUserId());
        List<UserPicVO> userPicVOS = new ArrayList<>();
        for(Thumbnail t: thumbnails){
            UserPicVO userPicVO = new UserPicVO();
            BeanUtils.copyProperties(t,userPicVO);
            userPicVOS.add(userPicVO);
        }
        return MyResponse.ok(userPicVOS);
    }

    @Override
    public MyResponse getHistory(UserIdVO userIdVO) {
        Integer userId = userIdVO.getUserId();
        List<String> histories = historyMapper.find10LatestKeyword(userId);
        return MyResponse.ok(histories);
    }

    @Override
    public MyResponse search(SearchVO searchVO) {
        String picId = searchVO.getPicId();
        String keyword = searchVO.getKeyWord();
        Graph graph = mongoDBMapper.findGraph(picId);
        if(graph==null || ! graph.getPicId().equals(picId))
            return MyResponse.error("Can Not Find Pic !");
        List<String> nodes;
        Set<String> nodesSet = new HashSet<>();
        List<EdgeVO> edges = new ArrayList<>();
        JSONArray fedgesJson = graph.getFedges();
        List<JSONObject> fedgesList = JSONObject.parseArray(fedgesJson.toJSONString(), JSONObject.class);
        for(JSONObject edge: fedgesList){
            String label = edge.getString("label");
            String source = edge.getString("source");
            String target = edge.getString("target");
            String type = edge.getString("type");
            EdgeVO edgeVO = new EdgeVO();
            if(fuzyMatching(label,keyword)){
                edges.add(new EdgeVO(label,source,target,type));
                nodesSet.add(source);
                nodesSet.add(target);
            }else if(fuzyMatching(source,keyword)){
                nodesSet.add(source);
            }else if(fuzyMatching(target,keyword)){
                nodesSet.add(target);
            }
        }
        nodes = new ArrayList<>(nodesSet);
        SearchResultVO searchResultVO = new SearchResultVO();
        searchResultVO.setPicId(picId);
        searchResultVO.setNodes(nodes);
        searchResultVO.setEdges(edges);
        return MyResponse.ok(searchResultVO);
    }

    private boolean fuzyMatching(String str, String template){
        return str.contains(template);
    }
}
