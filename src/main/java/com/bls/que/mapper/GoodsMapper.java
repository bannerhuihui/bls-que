package com.bls.que.mapper;


import com.bls.que.pojo.Goods;

import java.util.List;

public interface GoodsMapper {

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods record);

    void updateAllGoodsUnusable();

    Goods selectOrderType();

    List<Goods> selectAllGoods(String nameSplit);

}