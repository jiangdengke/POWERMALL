package com.jiangdk.cms.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiangdk.cms.pojo.form.ContentForm;
import com.jiangdk.cms.pojo.query.ContentPageQuery;
import com.jiangdk.common.exception.BizException;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangdk.cms.pojo.entity.Content;
import com.jiangdk.cms.mapper.ContentMapper;
import com.jiangdk.cms.service.ContentService;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements ContentService{


    /**
     * 分页查询
     */
    @Override
    public IPage<Content> pageContent(ContentPageQuery query) {
        String groupCode = query.getGroupCode();
        String title = query.getTitle();
        return this.page(Page.of(query.getCurrent(),query.getSize()),
                new LambdaQueryWrapper<Content>()
                        .eq(StrUtil.isNotBlank(groupCode),Content::getGroupCode,query.getGroupCode())
                        .like(StrUtil.isNotBlank(title),Content::getTitle,query.getTitle()));
    }

    /**
     * 新增内容
     *
     * @param contentForm
     */
    @Override
    @CacheEvict(cacheNames = "contentGroup",key = "#content.groupCode")
    public void addContent(ContentForm contentForm) {
        Content content = new Content();
        BeanUtils.copyProperties(contentForm,content);
        this.save(content);
    }

    /**
     * 更新内容
     *
     * @param contentForm
     */
    @Override
    @CacheEvict(cacheNames = "contentGroup",key = "#content.groupCode")
    public void updateContentById(ContentForm contentForm) {
        // 查询要更新的数据是否存在
        Content content = this.getById(contentForm.getId());
        if (content == null){
            throw new BizException(HttpStatus.HTTP_NOT_FOUND,"内容不存在或已删除");
        }
        BeanUtils.copyProperties(contentForm,content);
        content.setUpdateTime(new Date());
        this.updateById(content);
    }

    /**
     * 删除内容
     *
     * @param contentId
     */
    @Override
    @CacheEvict(cacheNames = "contentGroup",key = "#content.groupCode")
    public void deleteContentById(Long contentId) {
        Content content = this.getById(contentId);
        if (content == null){
            throw new BizException(HttpStatus.HTTP_NOT_FOUND,"内容不存在或已删除");
        }
        this.removeById(content);
        // todo 如果内容管理的有图片，删除对应的图片
    }

    /**
     * 根据分组编码获取分组内容
     *
     * @param groupCode
     * @return
     */
    @Override
    @Cacheable(cacheNames = "contentGroup",key = "#groupCode")
    public List<Content> getContentByGroupCode(String groupCode) {

        return this.list(new LambdaQueryWrapper<Content>()
                .eq(Content::getGroupCode,groupCode));
    }
}
