package com.tools.api.feign;


import com.tools.api.feign.fallback.OssFeignClientFallbackFactory;
import com.tools.api.vo.UploadVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author Macro
 * @Date 2020/6/28 13:20
 * @Description 用户相关接口
 */
@FeignClient(value = "ossService", fallbackFactory = OssFeignClientFallbackFactory.class)
public interface OssFeign {

    /**
     * oss文件流上传
     * @param
     * @return
     */
    @PostMapping(value = "/oss/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    UploadVO uploadFile(@RequestPart(name = "file") MultipartFile file, @RequestParam String path);



}