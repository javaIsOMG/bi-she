package com.dayup.seckil.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Classname PageVo
 * @Description TODO
 * @Date 2020/1/6 15:40
 * @Created by Yinghao.He
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageVo<T> {
    private int page;
    private int size;
    private int total;
    private List<T> data;
}
