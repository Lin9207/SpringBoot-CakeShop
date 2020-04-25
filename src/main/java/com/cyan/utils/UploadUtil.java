package com.cyan.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class UploadUtil {

    @Autowired
    private FtpUtil ftpUtil;

    /*保存文件*/
    public String upload(MultipartFile multipartFile){
        if (multipartFile.getSize() > 0) {
            // 生成文件名
            String fileName = getFileName(multipartFile);
            // 上传文件
            try {
                if (ftpUtil.uploadFile(fileName, multipartFile.getInputStream()))
                    return "/picture/" + fileName;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /*重新生成文件名*/
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