package com.bls.que.vo;

import lombok.Data;

import java.util.List;

/**
 * @projectName: bls-que
 * @package: com.bls.que.vo
 * @className: LayData
 * @author: huihui
 * @description: TODO
 * @date: 2024/5/17 11:05
 * @version: 1.0
 */
@Data
public class LayData <T> {

    private List<T> item;

}
