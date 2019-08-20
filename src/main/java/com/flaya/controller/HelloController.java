package com.flaya.controller;

import com.flaya.properties.GirlProperties;
import com.flaya.domain.GirlInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

//@Controller
//@ResponseBody
@RestController // @RestController相当于@Controller + @ResponseBody
@RequestMapping("/spring_boot")
public class HelloController {

    @Value("${cupSize}")
    private String cupSize;

    @Value("${age}")
    private int age;

    @Value("${content}")
    private String content;

    @Autowired
    private GirlProperties girl;

    @Autowired
    private GirlInfo girlInfo;

    // url映射支持多个
    @RequestMapping(value = {"/hello", "/hi"}, method = RequestMethod.GET)
    public String say() {
        return "Hello Spring Boot!";
//        return "index"; // 不推荐使用模版，影响性能
    }

    // 请求类型为post
    @RequestMapping(value = "/cupSize", method = RequestMethod.POST)
    public String cupSize() {
        return cupSize;
    }

    // @RequestMapping不指定method类型，则get和post都支持
    @RequestMapping("/age")
    public int age() {
        return age;
    }

    // 实体与配置文件关联中包含其他变量
    @RequestMapping(value = "/content", method = RequestMethod.GET)
    public String content() {
        return content;
    }

    // 实体与配置文件分组关联
    @RequestMapping(value = "/girl", method = RequestMethod.GET)
    public GirlProperties girl() {
        return girl;
    }

    // 实体与配置文件分组关联
    @RequestMapping(value = "/girl_info", method = RequestMethod.GET)
    public GirlInfo girlInfo() {
        return girlInfo;
    }

    // 请求带参数,url为：http://127.0.0.1:8082/spring_boot/id/8
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public String id(@PathVariable("id") int id) {
        return "id: " + id;
    }

    // 带参数的同名多态方法，参数也支持在前，url为：http://127.0.0.1:8082/spring_boot/99/age
    @RequestMapping(value = "/{age}/age")
    public int age(@PathVariable("age") int age) {
        return age;
    }

    // 请求带参数，传统url格式：http://127.0.0.1:8082/spring_boot/name?name=flaya
    @RequestMapping(value = "/name", method = RequestMethod.GET)
    public String name(@RequestParam("name") String myName) {
        return "name: " + myName;
    }

    // 请求带参数，参数可不传，且设定默认值
//    @RequestMapping(value = "/remark", method = RequestMethod.GET)
    @GetMapping("/remark") // 注解相当于@RequestMapping(value = "/remark", method = RequestMethod.GET)
    public String remark(@RequestParam(value = "remark", required = false, defaultValue = "Spring Boot") String remark) {
        return "remark: " + remark;
    }

    @RequestMapping(value = "/test", method = {RequestMethod.GET, RequestMethod.POST})
    public void test() throws Exception {
        throw new Exception();
    }
}
