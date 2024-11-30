package com.jiangdk.pms.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jiangdk.common.mybatis.page.PageResult;
import com.jiangdk.common.result.Result;
import com.jiangdk.pms.pojo.form.SpuForm;
import com.jiangdk.pms.pojo.query.SpuPageQuery;
import com.jiangdk.pms.pojo.vo.SpuVO;
import com.jiangdk.pms.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理端/商品
 */
@RestController
@RequestMapping("/api-admin/pms/spu")
public class SpuController {
    @Autowired
    private SpuService spuService;

    /**
     * 商品【详情】
     * @param spuId
     * @return
     */
    @GetMapping("{spuId}")
    public Result<SpuVO> getById(
            @PathVariable("spuId") Long spuId
    ){
        return Result.success(spuService.getSpuById(spuId));
    }

    /**
     * 商品【分页查询】
     * @param spuPageQuery
     * @return
     */
    @GetMapping
    public PageResult<SpuVO> list(
            @Validated SpuPageQuery spuPageQuery
    ){
        IPage<SpuVO> page =  spuService.spuPage(spuPageQuery);
        return PageResult.success(page);
    }
    /**
     * 商品【新增】
     * @param spuForm
     * @return
     */
    @PostMapping
    public Result addSpu(@RequestBody SpuForm spuForm) {
        spuService.addSpu(spuForm);
        return Result.success();
    }

    /**
     * 商品【更新】
     * @param spuId
     * @param spuForm
     * @return
     */
    @PutMapping("/{spuId}")
    public Result updateSpuById(@PathVariable Long spuId, @RequestBody SpuForm spuForm) {
        spuForm.setId(spuId);
        spuService.updateSpuById(spuForm);
        return Result.success();
    }
    /**
     * 商品【删除】
     * @param spuId
     * @return
     */
    @DeleteMapping("/{spuId}")
    public Result updateSpuById(
            @PathVariable Long spuId
    ){
        spuService.deleteSpuById(spuId);
        return Result.success();
    }

}
