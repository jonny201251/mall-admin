注解说明：
1.controller的方法如果返回json数据，需要加上@ResponseResult，返回值类型为String，必须使用GlobalResponseResult包装
2.controller的方法需要跳转到页面，访问路径后加上‘.html’

线上的域名和文件路径：
    haiyingmall.paas.casicloud.com
    /usr/local/tomcat/webapps/mallFile

部署步骤：
    1.后台调整
        考虑config中的WebMvcConfig
        修改GlobalConstants中的IMAGE_PATH、HOST_PATH
        去掉BackController中的entry的@GetMapping的注释
        logback-spring.xml
            修改日志级别：fileErrorApp
            修改目录：    d:/zybFile
        ureport.properties
            ureport.debug=false
            ureport.fileStoreDir=D:/mallFile/ureportfiles
        注释掉application.yml的log-impl、修改数据库的IP
    2.前端整合
        2.1将dist下的index.html拷贝到templates
            在index.html中添加命名空间<html xmlns:th="http://www.thymeleaf.org">
        2.2将css、js、ico文件拷贝到static->back，注意，将dist->static中的所有文件也拷贝到static->back
        2.3双击gradle -> Tasks -> build -> build
            (注意：bootWar、War等双击打出的War无效)
        2.4拷贝build -> libs 下的zyb.war即可
    3.数据库
        修改商品的详情图片的地址，sku、spu_detail
    4.全局搜索 localhost
