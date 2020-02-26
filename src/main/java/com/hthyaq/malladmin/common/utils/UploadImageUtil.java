package com.hthyaq.malladmin.common.utils;

import cn.hutool.core.util.IdUtil;
import com.hthyaq.malladmin.common.constants.GlobalConstants;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class UploadImageUtil {
    //type为brand（品牌）、item（商品）
    public static String save(MultipartFile imageFile, String type) throws IOException {
        String originalFilename = imageFile.getOriginalFilename();
        String[] tmp = originalFilename.split("\\.");
        String fileName = tmp[0];
        //fileName兼容处理
        int lastIndex = fileName.lastIndexOf("\\");
        if (lastIndex > 0) {
            fileName = fileName.substring(lastIndex + 1);
        }
        String suffix = tmp[1];
        InputStream inputStream = imageFile.getInputStream();
        String uuid = IdUtil.fastUUID();
        //图片保存到本地硬盘
        String diskPath = GlobalConstants.IMAGE_PATH + "/" + type + "/" + fileName + "-" + uuid + "." + suffix;
        IOUtils.copy(inputStream, new FileOutputStream(diskPath));
        //图片的数据库路径
        String dbPath = "/image/" + type + "/" + fileName + "-" + uuid + "." + suffix;
        return dbPath;
    }
}
