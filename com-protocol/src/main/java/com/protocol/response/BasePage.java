package com.protocol.response;


import lombok.Data;
import java.io.Serializable;
import java.util.List;


@Data
public class BasePage<T> implements Serializable {

    private Long total;

    private List<T> list;

    /**
     * 分页对象转换
     * @param total  影响的总条数
     * @param list  返回的数据集合
     * @param <T>   List元素的类名
     * @return   分页对象
     */
    public static <T> BasePage<T> toPage(Long total,List<T> list) {
        BasePage<T> result = new BasePage<T>();
        result.setTotal(total);
        result.setList(list);
        return result;
    }

}
