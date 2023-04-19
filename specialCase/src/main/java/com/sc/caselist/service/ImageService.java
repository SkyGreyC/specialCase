package com.sc.caselist.service;

import com.sc.caselist.logic.ImageLogic;
import com.sc.caselist.service.vo.ImageVO;
import com.sc.login.service.vo.UserVO;
import com.sc.utils.CommonUtils;
import com.sc.utils.JsonModel;
import com.sc.utils.MapToBean;
import com.sc.utils.TypeEnum;
import com.sc.utils.entity.ImageEntity;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/image")
public class ImageService {

    @Autowired
    private ImageLogic imageLogic;

    /**
     * 上传单个文件
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/uploadFile",method = RequestMethod.POST)
    public JsonModel uploadFile(MultipartFile file) throws IOException {
        JsonModel model = new JsonModel();
        //获取上传文件名称
        String filename = file.getOriginalFilename();
//      String path="D:/code/specialCase/image/"+resourceId+"/";
        String path="C:/CYT/code/image/";
        //判断该路径是否存在
        File file1 = new File(path);
        if (!file1.exists()) {
            //如果这个文件夹不存在的话,就创建这个文件
            file1.mkdirs();
        }
//        if(CommonUtils.isNull(resourceType)){
//            model.msgError("上传单个文件失败，图片类型为空");
//            return model;
//        }else if(resourceType.equals(TypeEnum.RESOURCE_TYPE.CASE.toString())){
//            filename = resourceId+".png";
//        }else if(resourceType.equals(TypeEnum.RESOURCE_TYPE.LESION.toString())){
//            filename = "label.png";
//        }else{
////          path="D:/code/specialCase/image/other/";
//            path="C:/CYT/code/image/other/";
//        }
        //完成文件上传
        file.transferTo(new File(path, filename));
        ImageVO imageVO = new ImageVO();
//        imageVO.setResourceId(resourceId);
//        imageVO.setResourceType(resourceType);
        imageVO.setImageName(filename);
        imageVO.setImageSuffix(filename.substring(filename.lastIndexOf(".")));
        imageVO.setImageSize(file.getSize());
        model.msgSuccess("上传单个文件成功",imageVO);
        return model;
    }

    /**
     * 下载单个文件
     * @param imageId
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/downloadFile",method = RequestMethod.GET)
    public void downloadFile(String imageId, HttpServletResponse response){
        try {
            ImageEntity imageEntity = imageLogic.loadByKey(imageId);
            if(CommonUtils.isNull(imageEntity)){
                throw new Exception("未找到附件");
            }
            String basePath="C:/CYT/code/image/";
            if(imageEntity.getResourceType().equals(TypeEnum.RESOURCE_TYPE.HOME.toString())){
                basePath+="other/";
            }else{
                basePath+=imageEntity.getResourceId()+"/";
            }
            String name=imageEntity.getImageName();
            // 1、定义输入流，通过输入流读取文件内容
            FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));
            // 2、通过response对象，获取到输出流
            ServletOutputStream outputStream = response.getOutputStream();
            // 3、通过response对象设置响应数据格式(image/jpeg)
//            response.setContentType("image/jpeg");
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1){
                // 4、通过输入流读取文件数据，然后通过上述的输出流写回浏览器
                outputStream.write(bytes,0,len);
                // 刷新
                outputStream.flush();
            }
            // 5、关闭资源
            outputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * 查询图片信息
     * */
    @RequestMapping(value = "/findImageByResourceId",method = RequestMethod.POST)
    public JsonModel findImageByResourceId(@RequestBody Map<String, Object> map){
        try{
            JsonModel model = new JsonModel();
            String resourceId = MapUtils.getString(map,"resourceId");
            if(CommonUtils.isNull(resourceId)){
                model.msgError("查询图片信息失败，id为空");
                return model;
            }
            List<ImageEntity> data = imageLogic.findImageByResourceId(resourceId);
            model.msgSuccess("查询图片信息成功",MapToBean.toList(data, ImageVO.class));
            return model;
        }catch(Exception e){
            return new JsonModel().msgError(e.getMessage());
        }
    }

    /*
     * 保存关联关系
     * */
    @RequestMapping(value = "/saveImageInfo",method = RequestMethod.POST)
    public JsonModel saveImageInfo(@RequestBody Map<String, Object> map){
        try{
            JsonModel model = new JsonModel();
            List<ImageVO> imageVOs = MapToBean.getList(map,"imageVOs",ImageVO.class);
            UserVO userVO = MapToBean.getObject(map,"userVO",UserVO.class);
            if(imageVOs.size()==0){
                model.msgNullRecord("无绑定附件");
                return model;
            }
            int result = imageLogic.saveImageInfo(imageVOs,userVO);
            model.msgSuccess("保存关联关系,成功个数:"+result);
            return model;
        }catch(Exception e){
            return new JsonModel().msgError(e.getMessage());
        }
    }


}
