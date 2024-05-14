package com.bls.que.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: my-code
 * @description:
 * @author: Mr.Yuan
 * @create: 2022-05-19 10:25
 **/
@Data
public class PageEntity<T> implements Serializable {

    private static final long serialVersionUID = 5426565670871177764L;

    private List<T> dataList;// 对象集合
    private int totalPage = 0;// 总页数
    private int currentPage;// 当前页
    private int maxRow = 10;// 每页显示的最大记录数
    private int total = 0; // 数据总条数

    public PageEntity(List<T> dataList, Integer currentPage, Integer maxRow, Integer totalCount) {
        if (currentPage == null) {
            currentPage = 0;
        }
        if (maxRow == null) {
            maxRow = 10;
        }
        if (totalCount == null) {
            totalCount = 0;
        }
        this.dataList = dataList;
        this.totalPage = totalCount % maxRow == 0 ? totalCount / maxRow : totalCount / maxRow + 1;
        this.currentPage = currentPage;
        this.maxRow = maxRow;
        this.total = totalCount;
    }

    private PageEntity() {}

}
