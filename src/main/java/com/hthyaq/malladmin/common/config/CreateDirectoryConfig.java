package com.hthyaq.malladmin.common.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.io.File;

/*
    容器启动后立即执行
    创建项目需要的目录
        D:/mallFile/ureportfiles
        D:/mallFile/excel
        D:/mallFile/image
 */
//@Component
public class CreateDirectoryConfig implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        String directory = "D:/mallFile";
        String[] strArr = {"/ureportfiles", "/excel", "/image/brand", "/image/item"};
        for (String tmp : strArr) {
            File dir1 = new File(directory + tmp);
            if (!dir1.exists()) {
                dir1.mkdirs();
            }
        }
    }
}
