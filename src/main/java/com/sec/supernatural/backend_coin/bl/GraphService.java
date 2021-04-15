package com.sec.supernatural.backend_coin.bl;

import com.alibaba.fastjson.JSONObject;
import com.sec.supernatural.backend_coin.constant.MyResponse;
import com.sec.supernatural.backend_coin.po.Graph;
import com.sec.supernatural.backend_coin.vo.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author shenyichen
 * @date 2021/3/29
 */
public interface GraphService {
    /**
     * 将json文件转化为图谱信息，持久化，并返回给前端
     * @param mfile
     * @return
     */
    MyResponse json2Dao(MultipartFile mfile) throws IOException;

    /**
     * 根据picId返回对应的json文件的url
     * @param picIdVO
     * @return
     */
    MyResponse dao2JsonUrl(PicIdVO picIdVO);
    /**
     * 根据picId保存所有图元信息
     * @param graph
     * @return
     */
    MyResponse save(Graph graph);

    /**
     * 根据picId获取所有图元信息
     * @param picIdVO
     * @return
     */
    MyResponse getPicElements(PicIdVO picIdVO);

    /**
     * 根据节点类型，获取该类型节点的数组
     * @param picIdVO
     * @return
     */
    MyResponse getNodesByTypes(PicIdVO picIdVO);

    /**
     * 获取节点类型数组
     * @param picIdVO
     * @return
     */
    MyResponse getNodeTypes(PicIdVO picIdVO);

    /**
     * 根据picId获取类型与节点的映射
     * @param picIdVO
     * @return
     */
    MyResponse getPicTypes(PicIdVO picIdVO);

    /**
     * 上传用户自定义图片
     * @param picUnitVO
     * @return
     */
    MyResponse uploadCustomizeImg(PicUnitVO picUnitVO);

    /**
     * 根据picId获取自定义的所有图元
     * @param picIdVO
     * @return
     */
    MyResponse getPicElement(PicIdVO picIdVO);

    /**
     * 保存/更新缩略图
     * @param thumbnailVO
     * @return
     */
    MyResponse storeThumbnail(ThumbnailVO thumbnailVO);

    /**
     * 获取用户图谱缩略图
     * @param userIdVO
     * @return
     */
    MyResponse getUserPics(UserIdVO userIdVO);

    /**
     * 获取历史搜索
     * @param userIdVO
     * @return
     */
    MyResponse getHistory(UserIdVO userIdVO);

    /**
     * 传入关键词和picId，搜索该图谱中的节点、关系、节点属性，返回包含该关键词的节点和关系。
     * （如果关系包含关键词，要返回关系和两边的节点；如果两个节点都包含关键词，则它们间的
     * 关系也要返回）
     * 返回:
     * {
     *     "nodes":[
     *     {
     *         "label": ...
     *     },
     *     ...
     *     ],
     *     "edges":[
     *     {
     *         "label": xxx,
     *         "source": xxx,
     *         "target": xxx,
     *         "type": xxx
     *     },
     *     ...
     *     ],
     *     "picId": xxx
     * }
     */
    /**
     * 模糊匹配
     * @param searchVO
     * @return
     */
    MyResponse search(SearchVO searchVO);
}
