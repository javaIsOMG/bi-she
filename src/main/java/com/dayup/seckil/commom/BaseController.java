package com.dayup.seckil.commom;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Classname BaseController
 * @Description TODO
 * @Date 2019/12/21 15:34
 * @Created by Yinghao.He
 */
@RequestMapping("/api")
@CrossOrigin(origins = "*",allowCredentials = "true",allowedHeaders = "*")
public class BaseController {
}
