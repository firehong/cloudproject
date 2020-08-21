package com.tools.oss.service.impl;


import com.tools.api.vo.UploadVO;
import com.tools.oss.sdk.Alioos;
import com.tools.oss.service.OssService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class OssServiceImpl implements OssService {

    @Autowired
    private Alioos alioos;

    @Override
    public UploadVO uploadFile(MultipartFile file, String path) {
        try {
            if (!"".equals(file.getOriginalFilename())) {
                //文件名称
                String fileName = file.getOriginalFilename();
                //自定义的文件名称
                String trueFileName = System.currentTimeMillis() + fileName;
                //上传文件名
                String newFileName = path + "/" + trueFileName;
                //上传文件至阿里oss
                String url = alioos.AlioosUpload(path, file);
                return UploadVO.success(url);
            } else {
                return UploadVO.fail("文件不能为空");
            }
        } catch (Exception e) {
            log.error("[oss] oss文件上传失败：{}" , e.getMessage(), e);
            return UploadVO.fail(e.getMessage());
        }
    }


}
