package com.sec.supernatural.backend_coin.blImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sec.supernatural.backend_coin.bl.SearchService;
import com.sec.supernatural.backend_coin.constant.MyResponse;
import com.sec.supernatural.backend_coin.data.EdgeMapper;
import com.sec.supernatural.backend_coin.data.NodeMapper;
import com.sec.supernatural.backend_coin.po.Edge;
import com.sec.supernatural.backend_coin.po.Node;
import com.sec.supernatural.backend_coin.vo.application.request.SemanticSearchVO;
import com.sec.supernatural.backend_coin.vo.application.response.EdgeDisplay;
import com.sec.supernatural.backend_coin.vo.application.response.NodeDetail;
import com.sec.supernatural.backend_coin.vo.application.response.NodeInfo;
import com.sec.supernatural.backend_coin.vo.application.response.NodeDisplay;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    NodeMapper nodeMapper;
    @Autowired
    EdgeMapper edgeMapper;

    @Override
    public MyResponse getAnswer(String question) throws Exception{
        //String url="http://localhost:5000/search/getAnswer";
        String url="http://120.27.240.225:5000/search/getAnswer";
        MultiValueMap<String,String> header=new LinkedMultiValueMap<>();
        header.put(HttpHeaders.CONTENT_TYPE, Arrays.asList(MediaType.APPLICATION_JSON_VALUE));
        SemanticSearchVO semanticSearchVO=new SemanticSearchVO();
        semanticSearchVO.setQuestion(question);
        HttpEntity<SemanticSearchVO> request=new HttpEntity<>(semanticSearchVO,header);
        RestTemplate restTemplate=new RestTemplate();
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        ResponseEntity<HashMap> exchangeRet=restTemplate.exchange(url, HttpMethod.POST, request, HashMap.class);
        System.out.println("exchangeRet: " + exchangeRet);
        HashMap<String,Object> responseStr=(HashMap<String,Object>)exchangeRet.getBody();
        System.out.println("responseStr[]=exchangeRet.getBody(): "+responseStr);
        assert responseStr != null;
        //{"answer":["\u5b57\u7b26\u4e321","\u5b57\u7b26\u4e322","\u5b57\u7b26\u4e323"],"code":0}
        List<String> arr=(ArrayList<String>)responseStr.get("answer");



//        int begin=responseStr.indexOf("[");
//        int end=responseStr.lastIndexOf("]");
//        responseStr=responseStr.substring(begin+1,end);
//        System.out.println("responseStr: "+responseStr);
//        String[] arr=responseStr.split(",");
        List<NodeInfo> contentList=new ArrayList<>();
        for(String str:arr){
            System.out.println(str);//"\u5b57\u7b26\u4e321"
            //str=str.substring(2,str.length()-1);
            String label= StringEscapeUtils.unescapeJava(str);
            System.out.println(label);
            NodeInfo nodeInfo=new NodeInfo();
            nodeInfo.setTitle(label);
            Node node=nodeMapper.findByName(label,"0");
            if(node!=null){
                nodeInfo.setInfo((String)node.getProperties().get("info"));
                nodeInfo.setCategories((String)node.getProperties().get("categories"));
                nodeInfo.setId((String)node.getProperties().get("id"));
                contentList.add(nodeInfo);
            }
        }
        if(arr.size()==1){
            List<Node> nodes=nodeMapper.getNeighborsByLabel(arr.get(0),"0");
            if(nodes.size()!=0)
                Node2NodeInfo(contentList, nodes);
        }

        if(contentList.size()==0){
            NodeInfo nodeInfo=new NodeInfo();
            nodeInfo.setTitle("测试");
            nodeInfo.setInfo("测试");
            nodeInfo.setCategories("测试");
            contentList.add(nodeInfo);
        }
        return MyResponse.ok(contentList);
    }

    @Override
    public MyResponse uploadScene(MultipartFile mfile) {
        //TODO:httpclient调用CV服务器的方法，返回一个String
        String scene="";

        List<NodeInfo> contentList=new ArrayList<>();
        List<Node> nodes=new ArrayList<>();
        nodes.add(nodeMapper.findByName(scene,"0"));
        List<Node> tmp= nodeMapper.getNeighborsByLabel(scene,"0");
        nodes.addAll(tmp);
        Node2NodeInfo(contentList, nodes);
        return MyResponse.ok(contentList);
    }

    private void Node2NodeInfo(List<NodeInfo> contentList, List<Node> nodes) {
        for(Node node:nodes){
            NodeInfo nodeInfo=new NodeInfo();
            nodeInfo.setTitle((String)node.getProperties().get("label"));
            nodeInfo.setInfo((String)node.getProperties().get("info"));
            nodeInfo.setCategories((String)node.getProperties().get("categories"));
            contentList.add(nodeInfo);
        }
    }

    @Override
    public MyResponse detail(String label){
        Node node=nodeMapper.findByName(label,"0");
        NodeDetail nodeDetail=new NodeDetail();
        nodeDetail.setCategories((String)node.getProperties().get("categories"));
        nodeDetail.setInfo((String)node.getProperties().get("info"));
        nodeDetail.setEnding((String)node.getProperties().get("ending"));
        nodeDetail.setReason((String)node.getProperties().get("reason"));
        nodeDetail.setImg((String)node.getProperties().get("img"));
        nodeDetail.setTitle((String)node.getProperties().get("label"));

        List<Node> nodes=nodeMapper.getNeighborsByLabel((String)node.getProperties().get("label"),"0");
        List<NodeDisplay> node3s=new ArrayList<>();
        for(Node n:nodes){
            NodeDisplay n3=new NodeDisplay();
            n3.setId((String)n.getProperties().get("id"));
            n3.setLabel((String)n.getProperties().get("label"));
            n3.setNodeType((String)n.getProperties().get("categories"));
            node3s.add(n3);
        }
        nodeDetail.setNodes(node3s);

        List<Edge> edges=edgeMapper.getNeighborEdgesByLabel((String)node.getProperties().get("label"),"0");
        List<EdgeDisplay> edgeDisplays=new ArrayList<>();
        for(Edge e:edges){
            EdgeDisplay edgeDisplay=new EdgeDisplay();
            edgeDisplay.setId((String)e.getProperties().get("id"));
            edgeDisplay.setLabel((String)e.getProperties().get("label"));
            edgeDisplay.setSource((String)e.getProperties().get("from"));
            edgeDisplay.setTarget((String)e.getProperties().get("to"));
            edgeDisplay.setType((String)e.getProperties().get("type"));
            edgeDisplays.add(edgeDisplay);
        }
        nodeDetail.setEdges(edgeDisplays);

        return MyResponse.ok(nodeDetail);
    }
}
