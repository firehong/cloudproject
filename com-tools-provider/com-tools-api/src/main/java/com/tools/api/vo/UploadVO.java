package com.tools.api.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UploadVO {

    private String url;
    private String msg;
    private Integer code;

    /**
     * 成功返回结果
     * @param UploadVO 获取的数据
     */
    public static UploadVO success(String url) {
        return UploadVO.builder().code(0).msg("SUCCESS").url(url).build();
    }

    /**
     * 失败返回结果
     * @param UploadVO 获取的数据
     */
    public static UploadVO fail(String msg) {
        return UploadVO.builder().code(-1).msg("msg").build();
    }

}
