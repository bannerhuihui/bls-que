package com.bls.que.mapper;

import com.bls.que.pojo.Template;

import java.util.List;

public interface TemplateMapper {

    Template selectByPrimaryKey(Integer id);

    List<Template> selectByType(String type);

}