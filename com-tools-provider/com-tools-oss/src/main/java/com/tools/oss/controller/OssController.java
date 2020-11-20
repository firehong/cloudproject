package com.tools.oss.controller;


import com.tools.api.vo.UploadVO;
import com.tools.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author Macro
 * @Date 2020/8/21 15:06
 * @Description  oss 存储服务
 */
@RestController
@RequestMapping("/oss")
public class OssController {

    @Autowired
    private OssService ossService;

    /**
     * 文件流上传
     * @Param
     * @Return
     */
    @PostMapping(value = "uploadFile",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UploadVO uploadFile(@RequestParam MultipartFile file, @RequestParam String path) {
        return ossService.uploadFile(file, path);
    }

}
