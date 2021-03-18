package com.sec.supernatural.backend_coin.blImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sec.supernatural.backend_coin.bl.GraphService;
import com.sec.supernatural.backend_coin.mapper.GraphMapper;
import com.sec.supernatural.backend_coin.po.Link;
import com.sec.supernatural.backend_coin.po.Node;
import com.sec.supernatural.backend_coin.vo.GraphVO;
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
    GraphMapper graphMapper;

    /**
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
            String fullFileName = StringUtils.cleanPath(mfile.getOriginalFilename());
            File file = File.createTempFile(fullFileName.substring(0,fullFileName.lastIndexOf('.')), fullFileName.substring(fullFileName.lastIndexOf('.')-1));
            file.deleteOnExit();
            FileUtils.copyInputStreamToFile(mfile.getInputStream(), file);
            // file to str
            String str = FileUtils.readFileToString(file, "utf-8");
            // str to JSONObject
            JSONObject jsonObject = new JSONObject(str);
            // get data
            JSONArray nodesArray = jsonObject.getJSONArray("nodes");
            List<Node> nodes = new ArrayList<>();
            JSONArray linksArray = jsonObject.getJSONArray("links");
            List<Link> links = new ArrayList<>();
            for(int i=0;i<nodesArray.length();i++){
                JSONObject node = nodesArray.getJSONObject(i);
                nodes.add(new Node(node.getString("name")));
            }
            for(int i=0;i<linksArray.length();i++){
                JSONObject link = linksArray.getJSONObject(i);
                links.add(new Link(link.getString("name"),link.getString("source"),link.getString("target"),link.getString("type")));
            }
            //TODO: 存储
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public GraphVO getAll(int picId) {
        return null;
    }

    @Override
    public MultipartFile dao2Json(int picId) {
        // dao to JSONObject
        GraphVO graphVO = getAll(picId);
        List<Node> nodes = graphVO.getNodes();
        List<Link> links = graphVO.getLinks();
        JSONObject jsonObject = new JSONObject();
        JSONArray nodesArray = new JSONArray();
        JSONArray linksArray = new JSONArray();
        try{
            for(int i=0;i<nodes.size();i++){
                ObjectMapper mapper = new ObjectMapper();
                nodesArray.put(new JSONObject(mapper.writeValueAsString(nodes.get(i))));
            }
            for(int i=0;i<links.size();i++){
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
            // str写入file
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            ps.println(str);
            ps.close();
            // file to mFile
            FileItem fileItem = this.getMultipartFile(file,"templFileItem");
            MultipartFile mFile = new CommonsMultipartFile(fileItem);
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
}
