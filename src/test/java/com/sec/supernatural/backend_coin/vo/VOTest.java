package com.sec.supernatural.backend_coin.vo;

import com.sec.supernatural.backend_coin.po.PicUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author shenyichen
 * @date 2021/5/7
 **/
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class VOTest {
    @Test
    public void voTest(){
        // BindUrlToPicVO
        BindUrlToPicVO bindUrlToPicVO = new BindUrlToPicVO();
        bindUrlToPicVO.hashCode();
        bindUrlToPicVO.toString();
        // EdgeVO
        EdgeVO edgeVO = new EdgeVO();
        edgeVO.hashCode();
        edgeVO.toString();
        edgeVO.setId("1");
        edgeVO.setLabel("1");
        edgeVO.setSource("1");
        edgeVO.setTarget("2");
        edgeVO.setType("1");
        // GraphVO
        GraphVO graphVO = new GraphVO();
        graphVO.hashCode();
        graphVO.toString();
        // LoginVO
        LoginVO loginVO = new LoginVO();
        loginVO.hashCode();
        loginVO.toString();
        // NodesByTypesVO
        NodesByTypesVO nodesByTypesVO = new NodesByTypesVO();
        nodesByTypesVO.hashCode();
        nodesByTypesVO.toString();
        // NodeVO
        NodeVO nodeVO = new NodeVO();
        nodeVO.hashCode();
        nodeVO.toString();
        nodeVO.setId("1");
        nodeVO.setLabel("1");
        nodeVO.setType("1");
        // PicElementVO
        PicElementVO picElementVO = new PicElementVO();
        picElementVO.hashCode();
        picElementVO.toString();
        picElementVO.setCustomizeEntityName("1");
        picElementVO.setCustomizeImgUrl("1");
        // PicIdVO
        PicIdVO picIdVO = new PicIdVO();
        picIdVO.hashCode();
        picIdVO.toString();
        // PicTypesVO
        PicTypesVO picTypesVO = new PicTypesVO();
        picTypesVO.hashCode();
        picTypesVO.toString();
        // PicUnitVO
        PicUnitVO picUnitVO = new PicUnitVO();
        picUnitVO.hashCode();
        picUnitVO.toString();
        // SearchResultVO
        SearchResultVO searchResultVO = new SearchResultVO();
        searchResultVO.hashCode();
        searchResultVO.toString();
        // SearchVO
        SearchVO searchVO = new SearchVO();
        searchVO.hashCode();
        searchVO.toString();
        // ThumbnailVO
        ThumbnailVO thumbnailVO = new ThumbnailVO();
        thumbnailVO.hashCode();
        thumbnailVO.toString();
        // UserIdVO
        UserIdVO userIdVO = new UserIdVO();
        userIdVO.hashCode();
        userIdVO.toString();
        // UserPicVO
        UserPicVO userPicVO = new UserPicVO();
        userPicVO.hashCode();
        userPicVO.toString();
        // UserVO
        UserVO userVO = new UserVO();
        userVO.hashCode();
        userVO.toString();
    }
}
