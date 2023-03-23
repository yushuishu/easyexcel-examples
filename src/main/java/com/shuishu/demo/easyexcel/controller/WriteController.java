package com.shuishu.demo.easyexcel.controller;


import com.shuishu.demo.easyexcel.service.WriteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ：shuishu
 * @date   ：2022/12/22 10:26
 * @IDE    ：IntelliJ IDEA
 * @Motto  ：ABC(Always Be Coding)
 * <p></p>
 * @Description -
 */
@RestController
@RequestMapping("write")
public class WriteController {
    @Resource
    private WriteService writeService;


}
