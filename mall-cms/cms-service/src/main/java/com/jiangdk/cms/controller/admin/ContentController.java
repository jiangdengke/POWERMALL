package com.jiangdk.cms.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiangdk.cms.pojo.entity.Content;
import com.jiangdk.cms.pojo.form.ContentForm;
import com.jiangdk.cms.pojo.query.ContentPageQuery;
import com.jiangdk.cms.service.ContentService;
import com.jiangdk.common.mybatis.page.PageResult;
import com.jiangdk.common.result.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Action;

/**
 * 管理端/内容
 */
@RestController
@RequestMapping("/api-admin/cms/content")
public class ContentController {
    @Autowired
    private ContentService contentService;

    /**
     * 内容【分页】
     * @param query
     * @return
     */
    @GetMapping
    public PageResult<Content> list(ContentPageQuery query){
        return PageResult.success(contentService.pageContent(query));
    }
    /**
     * 内容【新增】
     */
    @PostMapping
    public Result add(@RequestBody @Validated ContentForm contentForm) {
        contentService.addContent(contentForm);
        return Result.success();
    }
    /**
     * 内容【更新】
     */
    @PutMapping("/{contentId}")
    public Result update(
            @RequestBody @Validated ContentForm contentForm,
            @PathVariable Long contentId
    ) {
        contentForm.setId(contentId);
        contentService.updateContentById(contentForm);
        return Result.success();
    }
    /**
     * 内容【删除】
     */
    @DeleteMapping("/{contentId}")
    public Result delete(
            @PathVariable Long contentId
    ) {
        contentService.deleteContentById(contentId);
        return Result.success();
    }
}
