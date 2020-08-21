package com.tools.oss.service;


import com.tools.api.vo.UploadVO;
import org.springframework.web.multipart.MultipartFile;

public interface OssService {


    UploadVO uploadFile(MultipartFile file, String path);

}
