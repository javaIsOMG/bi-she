package com.dayup.seckil.util;

import com.dayup.seckil.entity.vo.PageVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Classname PageUtil
 * @Description TODO
 * @Date 2020/1/6 15:41
 * @Created by Yinghao.He
 */
public class PageUtil {
    public static <T> PageVo StartPage(Page<T> page,int size,int pageSize){
        long totalPages = page.getTotalElements();
        int total= (int) totalPages;
        List<T> content = page.getContent();
        PageVo<T> pageVo = new PageVo<>();
        pageVo.setPage(pageSize);
        pageVo.setSize(size);
        pageVo.setData(content);
        pageVo.setTotal(total);
        return pageVo;
    }
}
