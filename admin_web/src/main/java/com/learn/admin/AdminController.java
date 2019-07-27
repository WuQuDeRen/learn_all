package com.learn.admin;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @desc  本模块会被打成 jar 包，并在 web 模块中引入本模块并配置号包扫描即可
 * @author ji_fei
 * @date  2019-07-14 20:49
 */

@RestController
public class AdminController {


    @RequestMapping("/admin/c")
    public String test() {
        System.out.println("-----");
        return "ok";
    }
}
