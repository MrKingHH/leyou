package com.leyou.upload.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class UploadService {

    public static final Logger LOGGER = LoggerFactory.getLogger(UploadService.class);

    //支持的文件类型
    private static final List<String> suffixes = Arrays.asList("image/png", "image/jpeg");

    public String uploadImage(MultipartFile file) {
        LOGGER.info("开始上传图片服务");
        String contentType = file.getContentType();
        if (!suffixes.contains(contentType)) {
            LOGGER.info("上传失败， 文件类型不匹配：{}", contentType);
            return null;
        }
        try {
            BufferedImage read = ImageIO.read(file.getInputStream());
            if (read == null) {
                LOGGER.info("上传失败，文件内容不符合要求");
                return null;
            }
            File dir = new File("D:\\psbc\\upload");
            if (!dir.getParentFile().exists() && !dir.exists()) {
                dir.getParentFile().mkdir();
                dir.mkdir();
            }

            System.out.println(file.getOriginalFilename());
            file.transferTo(new File(dir, file.getOriginalFilename()));
            String url = "http://image.leyou.com/upload/" + file.getOriginalFilename();
            LOGGER.info("结束上传图片服务");
            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
