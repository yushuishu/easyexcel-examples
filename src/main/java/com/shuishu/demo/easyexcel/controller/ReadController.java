package com.shuishu.demo.easyexcel.controller;


import com.shuishu.demo.easyexcel.service.ReadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
@RequestMapping("read")
public class ReadController {
    @Resource
    private ReadService readService;


    @PostMapping("import/1")
    public void simpleRead(@RequestParam(name = "excel") MultipartFile excel){
        readService.simpleRead(excel);
    }

    @PostMapping("import/2")
    public void simpleRead2(@RequestParam(name = "excel") MultipartFile excel){
        readService.simpleRead2(excel);
    }

    @PostMapping("import/3")
    public void simpleRead3(@RequestParam(name = "excel") MultipartFile excel){
        readService.simpleRead3(excel);
    }

    @PostMapping("import/4")
    public void simpleRead4(@RequestParam(name = "excel") MultipartFile excel){
        readService.simpleRead4(excel);
    }

    @PostMapping("import/5")
    public void simpleRead5(@RequestParam(name = "excel") MultipartFile excel){
        readService.simpleRead5(excel);
    }

}
