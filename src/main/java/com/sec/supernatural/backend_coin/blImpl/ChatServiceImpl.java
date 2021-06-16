package com.sec.supernatural.backend_coin.blImpl;

import com.sec.supernatural.backend_coin.bl.ChatService;
import com.sec.supernatural.backend_coin.bl.StorageService;
import com.sec.supernatural.backend_coin.constant.MyResponse;
import com.sec.supernatural.backend_coin.data.NodeMapper;
import com.sec.supernatural.backend_coin.po.Node;
import com.sec.supernatural.backend_coin.vo.application.response.ChatAnswer;
import com.sec.supernatural.backend_coin.vo.application.response.ChatScene;
import com.sec.supernatural.backend_coin.vo.application.request.SemanticSearchVO;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
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
public class ChatServiceImpl implements ChatService {
    @Autowired
    NodeMapper nodeMapper;
    @Autowired
    StorageService storageService;

    @Override
    public MyResponse getAnswer(String roleId, String question) throws Exception{
        // 创建Httpclient对象
//        CloseableHttpClient httpClient= HttpClients.createDefault();
//        HttpPost httpPost= HttpClientUtils.create(question);
//        CloseableHttpResponse response = null;
//        try{
//            //执行请求
//            response=httpClient.execute(httpPost);
//            //判断状态码是否是200
//            String answer;
//            if(response.getStatusLine().getStatusCode()==200){
//                answer= EntityUtils.toString(response.getEntity(),"UTF-8");
//            }
//            else{
//                switch (roleId){
//                    case "0":
//                        answer="我也还不知道呢~";
//                        break;
//                    case "1":
//                        answer="好姐姐，千万绕我这一遭，原是我搞忘了。";
//                        break;
//                    case "2":
//                        answer="我不知，想来那是件难事，岂能人人都能知晓的。";
//                        break;
//                    case "3":
//                        answer="想来也不是件易事，你放心，赶明儿我替你问问老太太太太。";
//                        break;
//                    case "4":
//                        answer="此事原不该问我，改日问老太太太太便是了。";
//                        break;
//                    default:
//                        answer="";
//                }
//            }
//            System.out.println(answer);
//            return MyResponse.ok(answer);
//        }finally{
//            if(response!=null){
//                response.close();
//            }
//            httpClient.close();
//        }
        String url="http://localhost:5000/chat/getAnswer";
        MultiValueMap<String,String> header=new LinkedMultiValueMap<>();
        header.put(HttpHeaders.CONTENT_TYPE, Arrays.asList(MediaType.APPLICATION_JSON_VALUE));
        HttpEntity<SemanticSearchVO> request=new HttpEntity<>(new SemanticSearchVO(question),header);
        RestTemplate restTemplate=new RestTemplate();
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        ResponseEntity<String> exchangeRet=restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        System.out.println("exchangeRet: " + exchangeRet);
        String responseStr=exchangeRet.getBody();
        System.out.println("responseStr=exchangeRet.getBody(): "+responseStr);
        assert responseStr != null;
//        int begin=responseStr.indexOf("{");
//        int end=responseStr.lastIndexOf("}")+1;
//        responseStr=responseStr.substring(begin,end);
//        System.out.println("responseStr: "+responseStr);
        //{"answer":"\u5367\u864e\u85cf\u9f99\u7531\u7b49\u6f14\u5458\u4e3b\u6f14\uff01","code":0}
        String[] tmp=responseStr.split(",");
        String[] tmp2=tmp[0].split(":");
        String answer=tmp2[1];
        answer=answer.substring(1,answer.length()-1);
        System.out.println("answer: "+answer);
        answer= StringEscapeUtils.unescapeJava(answer);
        System.out.println("answer: "+answer);
        ChatAnswer chatAnswer=new ChatAnswer(answer);
        return MyResponse.ok(chatAnswer);
    }

    @Override
    public MyResponse uploadScene(MultipartFile mfile){
        //TODO:httpclient调用CV服务器的方法，返回一个String
        String scene="";
        List<Node> nodes=nodeMapper.getNeighborsByLabel(scene,"0");
        List<String> contentList=new ArrayList<>();
        contentList.add(scene);
        for(Node node:nodes){
            contentList.add((String)node.getProperties().get("label"));
        }
        //上传图床 返回图片的url
        String url=storageService.storeImage(mfile);
        ChatScene chatScene=new ChatScene();
        chatScene.setUrl(url);
        chatScene.setContentList(contentList);
        return MyResponse.ok(chatScene);
    }
}
