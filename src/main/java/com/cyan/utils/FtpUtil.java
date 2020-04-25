package com.cyan.utils;

import lombok.Data;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.*;

@Data
@Component // 注册bean到容器中
@ConfigurationProperties(prefix = "ftp")
public class FtpUtil {

    private Logger logger = LoggerFactory.getLogger(FtpUtil.class);

    //FTP服务器ip地址
    private String host;
    //FTP服务器端口
    private Integer port;
    //FTP登录账号
    private String username;
    //FTP登录密码
    private String password;
    //FTP服务器文件存放路径
    private String filePath;

    /**
     * 指定文件名，上传文件
     *
     * @param filename 上传文件名
     * @param input    输入流
     * @return 成功返回true，否则返回false
     */
    public boolean uploadFile(String filename, InputStream input) throws IOException {

        FTPClient ftpClient = new FTPClient();
        try {
            int reply;
            logger.info("连接FTP服务器");
            ftpClient.connect(host, port);

            logger.info("登录");
            ftpClient.login(username, password);

            reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                logger.info("FTP连接失败");
                ftpClient.disconnect();
                return false;
            }

            ftpClient.setControlEncoding("utf-8");// 解决上传文件时文件名乱码
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);// 设置上传文件的类型为二进制类型

            logger.info("切换到上传目录");
            if (!ftpClient.changeWorkingDirectory(filePath)) {
                logger.info("目录不存在===>创建目录");
                if (!ftpClient.makeDirectory(filePath)) {
                    logger.error("目录创建失败");
                    return false;
                } else {
                    logger.info("进入上传目录");
                    ftpClient.changeWorkingDirectory(filePath);
                }
            }

            ftpClient.setBufferSize(1024); // 设置缓存区
            ftpClient.enterLocalPassiveMode();// 开通一个端口来传输数据

            logger.info("上传文件");
            if (!ftpClient.storeFile(filename, input)) {
                logger.info("上传失败");
                return false;
            }
            ftpClient.logout();
        } catch (IOException e) {
            logger.info("文件上传异常", e);
            e.printStackTrace();
        } finally {
            logger.info("断开FTP连接");
            ftpClient.disconnect();
        }
        return true;
    }

    /**
     * @param fileName  要下载的文件名
     * @param localPath 下载后保存到本地的路径
     * @return 成功返回true，否则返回false
     */
    public boolean downloadFile(String fileName, String localPath) throws IOException {
        FTPClient ftpClient = new FTPClient();
        try {
            int reply;
            logger.info("连接FTP服务器");
            ftpClient.connect(host, port);

            logger.info("登录");
            ftpClient.login(username, password);

            reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                logger.info("FTP连接失败");
                ftpClient.disconnect();
                return false;
            }

            logger.info("进入FTP服务器下载目录");
            if (!ftpClient.changeWorkingDirectory(filePath)) {
                logger.info("目录不存在");
                return false;
            }

            logger.info("匹配查找文件");
            FTPFile[] fs = ftpClient.listFiles();
            for (FTPFile ff : fs) {
                if (ff.getName().equals(fileName)) {
                    logger.info("开始下载");
                    File localFile = new File(localPath + "/" + ff.getName());
                    OutputStream is = new FileOutputStream(localFile);
                    ftpClient.retrieveFile(ff.getName(), is);
                    is.close();
                    logger.info("下载完成");
                } else {
                    logger.info("找不到文件");
                }
            }
            ftpClient.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            logger.info("断开FTP连接");
            ftpClient.disconnect();
        }
        return true;
    }
}
