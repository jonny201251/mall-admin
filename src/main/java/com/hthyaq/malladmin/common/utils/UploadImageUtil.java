package com.hthyaq.malladmin.common.utils;

import cn.hutool.core.util.IdUtil;
import com.hthyaq.malladmin.common.constants.GlobalConstants;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class UploadImageUtil {
    //type为brand（品牌）、item（商品）
    public static String save(MultipartFile imageFile, String type) throws IOException {
        String originalFilename = imageFile.getOriginalFilename();
        String suffix = FilenameUtils.getExtension(originalFilename);
        InputStream inputStream = imageFile.getInputStream();
        String uuid = IdUtil.fastSimpleUUID();
        //图片保存到本地硬盘
        String diskPath = GlobalConstants.IMAGE_PATH + "/" + type + "/" + uuid + "." + suffix;
        IOUtils.copy(inputStream, new FileOutputStream(diskPath));
        //图片的数据库路径
        String dbPath = "/image/" + type + "/" + uuid + "." + suffix;
        return dbPath;
    }
}
