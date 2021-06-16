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

import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    NodeMapper nodeMapper;
    @Autowired
    EdgeMapper edgeMapper;

    @Override
    public MyResponse getAnswer(String question) throws Exception{
        String url="http://localhost:5000/search/getAnswer";
        MultiValueMap<String,String> header=new LinkedMultiValueMap<>();
        header.put(HttpHeaders.CONTENT_TYPE, Arrays.asList(MediaType.APPLICATION_JSON_VALUE));
        HttpEntity<SemanticSearchVO> request=new HttpEntity<>(new SemanticSearchVO(question),header);
        RestTemplate restTemplate=new RestTemplate();
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        ResponseEntity<String[]> exchangeRet=restTemplate.exchange(url, HttpMethod.POST, request, String[].class);
        System.out.println("exchangeRet: " + exchangeRet);
        String[] responseStr=exchangeRet.getBody();
        //System.out.println("responseStr[]=exchangeRet.getBody(): "+responseStr);
        assert responseStr != null;


        List<NodeInfo> contentList=new ArrayList<>();


        final ObjectMapper objectMapper=new ObjectMapper();
        ObjectNode searchVOObject =objectMapper.createObjectNode();
        searchVOObject.put("question",question);

        //HttpEntity<String> request = new HttpEntity<String>(searchVOObject.toString(),headers);
        //final RestTemplate restTemplate=new RestTemplate();
        //String[] data=restTemplate.postForObject(url,
        //        request,String[].class);
        //System.out.println(Arrays.toString(data));

        //assert data != null;
//        for(String str:data){
//            NodeInfo nodeInfo=new NodeInfo();
//            nodeInfo.setTitle(str);
//            Node node=nodeMapper.findByName(str,"0");
//            nodeInfo.setInfo((String)node.getProperties().get("info"));
//            nodeInfo.setCategories((String)node.getProperties().get("categories"));
//            contentList.add(nodeInfo);
//        }
//        if(data.length==1) {
//            List<Node> nodes=nodeMapper.getNeighborsByLabel(data[0],"0");
//            Node2NodeInfo(contentList, nodes);
//        }
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
        nodeDetail.setTitle((String)node.getProperties().get("title"));

        List<Node> nodes=nodeMapper.getNeighborsByLabel((String)node.getProperties().get("label"),"0");
        List<NodeDisplay> node3s=new ArrayList<>();
        for(Node n:nodes){
            NodeDisplay n3=new NodeDisplay();
            n3.setId((String)n.getProperties().get("id"));
            n3.setLabel((String)n.getProperties().get("label"));
            n3.setNodeType((String)n.getProperties().get("categories"));
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
