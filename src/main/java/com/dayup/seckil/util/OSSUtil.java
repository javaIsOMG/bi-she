package com.dayup.seckil.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.dayup.seckil.entity.po.Mp4;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Classname OSSUtil
 * @Description TODO
 * @Date 2020/1/3 9:46
 * @Created by Yinghao.He
 */
@Slf4j
public class OSSUtil {
    public static final String saveFile(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        String substring = originalFilename.substring(originalFilename.length() - 3);
        String objectName ;
        long l = System.currentTimeMillis();
        if (substring.equals("jpg")){
            objectName = l + "heyinghao.jpg";
        }else{
            objectName = l + "heyinghao.mp4";
        }

        String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = "LTAI4FdRVMySKN57mr2xjwHD";
        String accessKeySecret = "iAlayheRN6I2ExsfQg9BE7pJwSPtLD";

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);


// 上传文件流。
        InputStream stream = multipartFile.getInputStream();
        //  InputStream inputStream = new FileInputStream(stream);
        ossClient.putObject("crow190105", objectName, stream);

// 关闭OSSClient。
        ossClient.shutdown();
        log.info("上传文件成功");
        return "https://crow190105.oss-cn-shenzhen.aliyuncs.com/" + objectName;

    }

    public static Mp4 saveMP4(MultipartFile multipartFile, String courseNo) throws IOException {
        Mp4 mp4 = new Mp4();
        String name = multipartFile.getOriginalFilename();
        mp4.setMp4Name(name);
        String url = saveFile(multipartFile);
        mp4.setUrl(url);
        mp4.setCourseNo(courseNo);
        return mp4;
    }
}
