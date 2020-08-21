package com.tools.oss.sdk;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PolicyConditions;
import com.szl.common.core.exception.MyException;
import com.szl.common.core.exception.SystemError;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 阿里oos文件上传
 */
@Component
public class Alioos{

    @Value("${com.oss.endpoint}")
    private String endpoint;
    @Value("${com.oss.accessKeyId}")
    private String accessKeyId;
    @Value("${com.oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${com.oss.bucketName}")
    private String bucketName;

    /**
     * 文件上传
     *
     * @param fileName 文件名
     * @param mfile    MultipartFile
     * @return String
     */
    public String AlioosUpload(String fileName, MultipartFile mfile) {
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        //设置contentType
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(mfile.getSize());
        String contentType = mfile.getContentType();
        objectMetadata.setContentType(contentType);
        InputStream inputStream;
        try {
            inputStream = mfile.getInputStream();
            ossClient.putObject(bucketName, fileName, inputStream, objectMetadata);
            inputStream.close();
            Date expiration = new Date(new Date().getTime() + 3600L * 1000 * 24 * 365 * 10);
            URL url = ossClient.generatePresignedUrl(bucketName, fileName, expiration);
            // 关闭client
            String fileUrl = url.toString().substring(4, url.toString().indexOf("?"));
            fileUrl = new StringBuilder().append("https").append(fileUrl).toString();
            ossClient.shutdown();
            return fileUrl;
        } catch (Exception e) {
            ossClient.shutdown();
            throw new MyException(SystemError.SERVER_ERROR.getCode(),e.getMessage());
        }
    }


    /**
     * 提供页面直传需要的签名信息
     * @param dir  用户上传文件时指定的前缀
     * @return Map<String, String>
     * @throws UnsupportedEncodingException
     */
    public Map<String, String> getOssPolicy(String dir) throws UnsupportedEncodingException {
        long expireTime = 30;
        long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
        Date expiration = new Date(expireEndTime);
        PolicyConditions policyConds = new PolicyConditions();
        policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
        policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        String postPolicy = client.generatePostPolicy(expiration, policyConds);
        byte[] binaryData = postPolicy.getBytes("utf-8");
        String encodedPolicy = BinaryUtil.toBase64String(binaryData);
        String postSignature = client.calculatePostSignature(postPolicy);

        Map<String, String> respMap = new LinkedHashMap<>();
        respMap.put("accessid", accessKeyId);
        respMap.put("policy", encodedPolicy);
        respMap.put("signature", postSignature);
        respMap.put("dir", dir);
        respMap.put("expire", String.valueOf(expireEndTime / 1000));
        return respMap;
    }

}
