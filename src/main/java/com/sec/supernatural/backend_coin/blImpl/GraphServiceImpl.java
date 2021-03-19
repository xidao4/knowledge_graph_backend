package com.sec.supernatural.backend_coin.blImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sec.supernatural.backend_coin.bl.GraphService;
import com.sec.supernatural.backend_coin.constant.MyResponse;
import com.sec.supernatural.backend_coin.constant.ResponseCode;
import com.sec.supernatural.backend_coin.data.EntityMapper;
import com.sec.supernatural.backend_coin.data.PicIdMapper;
import com.sec.supernatural.backend_coin.data.RelationMapper;
import com.sec.supernatural.backend_coin.po.Entity;
import com.sec.supernatural.backend_coin.po.Relation;
import com.sec.supernatural.backend_coin.vo.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


@Service
public class GraphServiceImpl implements GraphService {
    @Autowired
    EntityMapper entityMapper;
    @Autowired
    RelationMapper relationMapper;
    @Autowired
    PicIdMapper picIdMapper;

    /**
     * 此方法返回picId
     * json文件格式：
     * {
     *     "nodes":[
     *         {
     *             "name": "node_name_1"
     *         },
     *         ...
     *     ],
     *     "links":[
     *         {
     *             "name": "link_name_1",
     *             "source": "source_node_name",
     *             "target": "target_node_name",
     *             "type": "type_name"
     *         },
     *         ...
     *     ]
     * }
     * @param mfile
     * @return
     */
    @Override
    public int json2Dao(MultipartFile mfile) {
        try{
            // mFile to file
            String fullFileName = StringUtils.cleanPath(mfile.getOriginalFilename());//测试时要替换成file.getName
            File file = File.createTempFile(fullFileName.substring(0,fullFileName.lastIndexOf('.')), fullFileName.substring(fullFileName.lastIndexOf('.')-1));
            file.deleteOnExit();
            FileUtils.copyInputStreamToFile(mfile.getInputStream(), file);
            // file to str
            String str = FileUtils.readFileToString(file, "utf-8");
            // str to JSONObject
            JSONObject jsonObject = new JSONObject(str);
            // get data
            JSONArray nodesArray = jsonObject.getJSONArray("nodes");
            List<Entity> entities = new ArrayList<>();
            JSONArray linksArray = jsonObject.getJSONArray("links");
            List<Relation> relations = new ArrayList<>();
            int picId = picIdMapper.getPicId();
            for(int i=0;i<nodesArray.length();i++){
                System.out.println(i);
                JSONObject node = nodesArray.getJSONObject(i);
                entities.add(new Entity(picId,node.getString("name")));
//                System.out.println(node.toString());
            }
            for(int i=0;i<linksArray.length();i++){
                JSONObject link = linksArray.getJSONObject(i);
                relations.add(new Relation(picId,link.getString("name"),link.getString("source"),link.getString("target"),link.getString("type")));
            }
            // 存储
            for(int i=0;i<entities.size();i++){
                entityMapper.addEntity(entities.get(i));
            }
            for(int i=0;i<relations.size();i++){
                relationMapper.addRelation(relations.get(i));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 返回整个图谱
     * @param picId
     * @return
     */
    @Override
    public GraphVO getAll(int picId) {
        GraphVO graphVO = new GraphVO();
        graphVO.setPicId(picId+"");
        List<Entity> entities = entityMapper.getEntitiesByPicId(picId);
        List<NodeVO> nodes = new ArrayList<>();
        for(int i=0;i<entities.size();i++){
            nodes.add(new NodeVO(entities.get(i)));
        }
        graphVO.setNodes(nodes);
        List<Relation> relations=relationMapper.getRelationsByPicId(picId);
        List<LinkVO> links = new ArrayList<>();
        for(int i=0;i<relations.size();i++){
            links.add(new LinkVO(relations.get(i)));
        }
        graphVO.setLinks(links);
        return graphVO;
    }

    @Override
    public MultipartFile dao2Json(int picId) {
        // dao to JSONObject
        GraphVO graphVO = getAll(picId);
        List<NodeVO> nodes = graphVO.getNodes();
        List<LinkVO> links = graphVO.getLinks();
        JSONObject jsonObject = new JSONObject();
        JSONArray nodesArray = new JSONArray();
        JSONArray linksArray = new JSONArray();
        try{
            for(int i = 0; i< nodes.size(); i++){
                ObjectMapper mapper = new ObjectMapper();
                nodesArray.put(new JSONObject(mapper.writeValueAsString(nodes.get(i))));
            }
            for(int i = 0; i< links.size(); i++){
                ObjectMapper mapper = new ObjectMapper();
                linksArray.put(new JSONObject(mapper.writeValueAsString(links.get(i))));
            }
            jsonObject.put("nodes",nodesArray);
            jsonObject.put("links",linksArray);
        }catch (Exception e){
            e.printStackTrace();
        }
        // JSONObject to mFile
        try{
            // JSONObject to str
            String str = jsonObject.toString();
            File file = File.createTempFile("graph", ".json");
            file.deleteOnExit();
//            System.out.println(str);
            // str写入file
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            ps.println(str);
            ps.close();
            // file to mFile
            FileItem fileItem = this.getMultipartFile(file,"templFileItem");
            MultipartFile mFile = new CommonsMultipartFile(fileItem);
            // update picId
            picIdMapper.updatePicId();
            return mFile;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private File mFile2File(MultipartFile mfile) {
        File file = null;
        if(mfile.equals("") || mfile.getSize() <= 0){
            mfile = null;
        }else {
            try{
                InputStream is = mfile.getInputStream();
                String fullFileName = StringUtils.cleanPath(mfile.getOriginalFilename());
                System.out.println(fullFileName);
                file = File.createTempFile(fullFileName.substring(0,fullFileName.lastIndexOf('.')), fullFileName.substring(fullFileName.lastIndexOf('.')-1));
                System.out.println("文件路径: "+file.getAbsolutePath());
                file.deleteOnExit();
                OutputStream os = new FileOutputStream(file);
                int bytesRead = 0;
                byte[] buffer = new byte[8192];
                while ((bytesRead = is.read(buffer, 0, 8192)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
                os.close();
                is.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return file;
    }

    private FileItem getMultipartFile(File file, String fieldName){
        FileItemFactory factory = new DiskFileItemFactory(16, null);
        FileItem item = factory.createItem(fieldName, "text/plain", true, file.getName());
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        try {
            FileInputStream fis = new FileInputStream(file);
            OutputStream os = item.getOutputStream();
            while ((bytesRead = fis.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return item;
    }




    @Override
    public MyResponse addEntity(EntityVO entityVO) {
        Entity entity =new Entity(Integer.parseInt(entityVO.getPicId()), entityVO.getName());
        try{
            entityMapper.addEntity(entity);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return MyResponse.exception("插入失败");
        }
        return new MyResponse();
    }

    @Override
    public MyResponse deleteEntity(EntityVO entityVO) {
        try{
            entityMapper.deleteEntity(Integer.parseInt(entityVO.getPicId()), entityVO.getName());
            Entity entity =new Entity(Integer.parseInt(entityVO.getPicId()), entityVO.getName());
            List<Relation> relations = relationMapper.getRelationsByNode(entity);
            for(Relation relation : relations){
                entityMapper.deleteEntity(relation.getPicId(), relation.getName());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new MyResponse(ResponseCode.CATCH_EXCEPTION);
        }
        return new MyResponse();
    }

    @Override
    public MyResponse changeEntity(Integer picId,String oldName,String newName) {

        try{
            if(entityMapper.getEntity(picId,newName).size()!=0)
                return MyResponse.exception("节点新名字不能与原有节点同名");
            entityMapper.changeEntity(picId,oldName,newName);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new MyResponse(ResponseCode.CATCH_EXCEPTION);
        }
        return new MyResponse();
    }

    @Override
    public MyResponse addRelation(RelationVO vo) {
        //Link: String name, String source, String target, String type,int picId
        //RelationVO: name,node1,node2,type,picId;
        if(vo.getNode1()==null&&vo.getNode2()==null) return MyResponse.exception("插入的关系既没有source，也没有target");
        try{
            Relation relation =new Relation(Integer.parseInt(vo.getPicId()),vo.getName(),vo.getNode1(),vo.getNode2(),vo.getType());
            List<Relation> relations = relationMapper.getRelationsByNodesAndName(Integer.parseInt(vo.getPicId()),vo.getNode1(),vo.getNode2(),vo.getName());
            if(relations.size()!=0) return MyResponse.exception("不能插入相同name的边");

            relationMapper.addRelation(relation);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return MyResponse.exception("插入失败");
        }
        return new MyResponse();
    }

    @Override
    public MyResponse deleteRelation(RelationVO vo) {

        try{
            Relation relation =new Relation(Integer.parseInt(vo.getPicId()),vo.getName(),vo.getNode1(),vo.getNode2(),vo.getType());
            relationMapper.deleteRelation(relation);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new MyResponse(ResponseCode.CATCH_EXCEPTION);
        }
        return new MyResponse();
    }

    @Override
    public MyResponse changeRelation(ChangeRelationVO vo) {
        try{
            String newName=vo.getNewName();
            String newType=vo.getNewType();
            if(newName==null&&newType==null) return MyResponse.error("未输入将关系修改为什么样子");
            if(newName!=null){
                if(relationMapper.getRelationsByNodesAndName(Integer.parseInt(vo.getPicId()),vo.getNode1(),vo.getNode2(),newName).size()!=0)
                    return MyResponse.error("新关系不能与原关系重名");
            }
            if(newType==null) relationMapper.changeRelationName(Integer.parseInt(vo.getPicId()),vo.getNode1(),vo.getNode2(),vo.getName(),vo.getNewName());
            else if(newName==null) relationMapper.changeRelationType(Integer.parseInt(vo.getPicId()),vo.getNode1(),vo.getNode2(),vo.getName(),vo.getNewType());
            else relationMapper.changeRelationNameAndType(vo);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new MyResponse(ResponseCode.CATCH_EXCEPTION);
        }
        return new MyResponse();
    }
}
