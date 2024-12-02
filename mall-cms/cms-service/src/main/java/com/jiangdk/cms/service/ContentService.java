package com.jiangdk.cms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jiangdk.cms.pojo.entity.Content;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiangdk.cms.pojo.form.ContentForm;
import com.jiangdk.cms.pojo.query.ContentPageQuery;

import java.util.List;

public interface ContentService extends IService<Content>{

    /**
     * 分页查询
     */
    IPage<Content> pageContent(ContentPageQuery query);

    /**
     * 新增内容
     * @param contentForm
     */
    void addContent(ContentForm contentForm);

    /**
     * 更新内容
     * @param contentForm
     */
    void updateContentById(ContentForm contentForm);

    /**
     * 删除内容
     * @param contentId
     */
    void deleteContentById(Long contentId);
    /**
     * 根据分组编码获取分组内容
     * @param groupCode
     * @return
     */
    List<Content> getContentByGroupCode(String groupCode);
}
