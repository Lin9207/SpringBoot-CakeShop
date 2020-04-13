package com.cyan.commons;

import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

public class UploadFile {

    /*保存文件*/
    public String upload(MultipartFile multipartFile) {
        if (multipartFile.getSize() > 0) {
            //获取文件名
            String fileName = getFileName(multipartFile);
            //指定本地文件夹存储图片
            String path = ClassUtils.getDefaultClassLoader().getResource("static/picture").getPath();
            try {
                //将图片保存到static文件夹里
                multipartFile.transferTo(new File(path + "/" + fileName));
                return "/picture/" + fileName;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }else {
            return null;
        }
    }

    /*获取文件,重新生成文件名*/
    public String getFileName(MultipartFile multipartFile) {

        //获取文件名
        String fileName = multipartFile.getOriginalFilename();
        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //重新生成文件名
        fileName = System.currentTimeMillis() + suffixName;

        return fileName;
    }
}