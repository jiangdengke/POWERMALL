package com.jiangdk.cms.controller.app;

import com.jiangdk.cms.pojo.enums.ContentGroup;
import com.jiangdk.cms.pojo.vo.AdvertVO;
import com.jiangdk.cms.pojo.vo.NavigationVO;
import com.jiangdk.cms.pojo.vo.SwiperVO;
import com.jiangdk.cms.service.ContentService;
import com.jiangdk.common.result.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 应用端/内容
 */
@RestController
@RequestMapping("/api/cms/home")
public class AppContentController {
    @Autowired
    private ContentService contentService;

    /**
     *跳转到动作搜索首页轮播图
     * @return
     */
    @GetMapping("/swiper")
    public Result<List<SwiperVO>> swiper(){
        // 通过stream对ContentList进行转换，转为SwiperVOList
        List<SwiperVO> list = contentService.getContentByGroupCode(ContentGroup.INDEX_SWIPER.getCode())
                .stream()
                .map(content -> {
                    SwiperVO swiperVO = new SwiperVO();
                    BeanUtils.copyProperties(content,swiperVO);
                    return swiperVO;
                }).collect(Collectors.toList());

        return Result.success(list);
    }
    /**
     * 首页广告
     */
    @GetMapping("/advert")
    public Result<List<AdvertVO>> advert(){
        // 通过stream对ContentList进行转换，转为SwiperVOList
        List<AdvertVO> list = contentService.getContentByGroupCode(ContentGroup.INDEX_ADVERT.getCode())
                .stream()
                .map(content -> {
                    AdvertVO advertVO = new AdvertVO();
                    BeanUtils.copyProperties(content,advertVO);
                    return advertVO;
                }).collect(Collectors.toList());

        return Result.success(list);
    }
    /**
     * 首页导航
     */
    @GetMapping("/navigation")
    public Result<List<NavigationVO>> navigation(){
        // 通过stream对ContentList进行转换，转为SwiperVOList
        List<NavigationVO> list = contentService.getContentByGroupCode(ContentGroup.INDEX_NAVIGATION.getCode())
                .stream()
                .map(content -> {
                    NavigationVO navigationVO = new NavigationVO();
                    BeanUtils.copyProperties(content,navigationVO);
                    return navigationVO;
                }).collect(Collectors.toList());

        return Result.success(list);
    }

}
