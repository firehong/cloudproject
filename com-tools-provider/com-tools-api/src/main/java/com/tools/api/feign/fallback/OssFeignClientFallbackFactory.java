package com.tools.api.feign.fallback;


import com.tools.api.feign.OssFeign;
import com.tools.api.vo.UploadVO;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Component
public class OssFeignClientFallbackFactory implements FallbackFactory<OssFeign> {

    @Override
    public OssFeign create(Throwable throwable) {
        return new OssFeign() {
            @Override
            public UploadVO uploadFile(MultipartFile file, String path) {
                log.error("[oss] oss文件存储失败,path:{}", path, throwable);
                return UploadVO.fail("fail");
            }
        };
    }

}
