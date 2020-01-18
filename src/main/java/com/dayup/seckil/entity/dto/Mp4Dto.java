package com.dayup.seckil.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;

/**
 * @Classname mp4Dto
 * @Description TODO
 * @Date 2020/1/10 17:00
 * @Created by Yinghao.He
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mp4Dto<T> implements Serializable {
    private static final long serialVersionUID = -2438870861570629904L;
    private String url;
    private List<T> data;
    private String courseName;
}
