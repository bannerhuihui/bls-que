package com.bls.que.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @projectName: bls-que
 * @package: com.bls.que.vo
 * @className: LayPage
 * @author: huihui
 * @description: TODO
 * @date: 2024/5/17 11:03
 * @version: 1.0
 */
@Data
public class LayPage <T>  implements Serializable{

    private static final long serialVersionUID = -8246186608079471843L;

    private Integer code;

    private String msg;

    private Integer count;

    private List<T> data;
}
