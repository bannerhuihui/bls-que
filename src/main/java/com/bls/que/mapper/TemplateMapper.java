package com.bls.que.mapper;

import com.bls.que.pojo.Template;

import java.util.List;

public interface TemplateMapper {

    int insertSelective(Template record);

    Template selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Template record);

    List<Template> selectByTemplate(Template template);
}