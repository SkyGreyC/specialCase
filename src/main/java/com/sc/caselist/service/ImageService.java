package com.sc.caselist.service;

import com.sc.caselist.logic.ImageLogic;
import com.sc.caselist.service.vo.ImageVO;
import com.sc.utils.CommonUtils;
import com.sc.utils.JsonModel;
import com.sc.utils.TypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping(value = "/image")
public class ImageService {

    @Autowired
    private ImageLogic imageLogic;

    /**
     * 上传单个文件
     * @param upload
     * @param resourceType
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/uploadFile",method = RequestMethod.POST)
    public JsonModel uploadFile(MultipartFile upload, String resourceType) throws IOException {
        JsonModel model = new JsonModel();
        //获取上传文件名称
        String filename = upload.getOriginalFilename().substring(0,upload.getOriginalFilename().lastIndexOf("."));
        String path="D:/code/specialCase/image/"+filename+"/";
        //判断该路径是否存在
        File file = new File(path);
        if (!file.exists()) {
            //如果这个文件夹不存在的话,就创建这个文件
            file.mkdirs();
        }
        if(CommonUtils.isNull(resourceType)){
            model.msgError("上传单个文件失败，图片类型为空");
            return model;
        }else if(resourceType.equals(TypeEnum.RESOURCE_TYPE.CASE.toString())){
            //nothing
        }else if(resourceType.equals(TypeEnum.RESOURCE_TYPE.LESION.toString())){
            filename = "label";
        }else{
            path="D:/code/specialCase/image/other/";
        }
        //完成文件上传
        upload.transferTo(new File(path, upload.getOriginalFilename()));
        model.msgSuccess("上传单个文件成功");
        return model;
    }
}
