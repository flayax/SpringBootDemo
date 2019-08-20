package com.flaya.controller;

import com.flaya.aspect.HttpAspect;
import com.flaya.domain.GirlInfo;
import com.flaya.domain.Result;
import com.flaya.repository.GirlRepository;
import com.flaya.service.GirlService;
import com.flaya.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/spring_boot/girl")
public class GirlController {

    private static Logger logger = LoggerFactory.getLogger(GirlController.class);

    @Autowired
    private GirlRepository girlRepository;

    @Autowired
    private GirlService girlService;

    /**
     * 查询所有列表
     *
     * @return
     */
    @GetMapping("/girls")
    public List<GirlInfo> girlList() {
        logger.info("girlList");
        return girlRepository.findAll();
    }

    /**
     * 新增
     * @param girlInfo
     * @return
     */
    @GetMapping("/add")
    public Result<GirlInfo> girlAdd(@Valid GirlInfo girlInfo, BindingResult bindingResult) {
        // 表单验证未通过
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(-3, bindingResult.getFieldError().getDefaultMessage());
        }
//        girlInfo.setCupSize(girlInfo.getCupSize());
//        girlInfo.setAge(girlInfo.getAge());
        return ResultUtil.success(girlRepository.save(girlInfo));
    }

//    /**
//     * 新增
//     *
//     * @param cupSize
//     * @param age
//     * @return
//     */
//    @GetMapping("/add")
//    public GirlInfo girlAdd(@RequestParam("cupSize") String cupSize, @RequestParam("age") Integer age) {
//        GirlInfo girlInfo = new GirlInfo();
//        girlInfo.setCupSize(cupSize);
//        girlInfo.setAge(age);
//        return girlRepository.save(girlInfo);
//    }

    /**
     * 查询
     *
     * @param id
     * @return
     */
    @GetMapping("/find")
    public GirlInfo girlFind(@RequestParam("id") Integer id) {
        return girlRepository.findById(id).orElse(null);
    }

    @PostMapping("/find/age")
    public List<GirlInfo> findByAge(@RequestParam("age") Integer age) {
        return girlRepository.findByAge(age);
    }

    /**
     * 更新
     * @param id
     * @param cupSize
     * @param age
     * @return
     */
    @PostMapping("/update")
    public GirlInfo girlUpdate(@RequestParam("id") Integer id, @RequestParam("cupSize") String cupSize, @RequestParam("age") int age, @RequestParam("money") double money) {
        GirlInfo girlInfo = girlFind(id);
        girlInfo.setCupSize(cupSize);
        girlInfo.setAge(age);
        girlInfo.setMoney(money);
        return girlRepository.save(girlInfo);
    }

    /**
     * 删除
     * @param id
     */
    @DeleteMapping("/delete")
    public void girlDelete(@RequestParam("id") Integer id) {
        girlRepository.deleteById(id);
    }

    /**
     * 连续插入两条记录，事务验证
     */
    @GetMapping("/insert/two")
    public void insertTwo() {
        girlService.insertTwo();
    }

    @GetMapping("/judge/age/{id}")
    public void getAge(@PathVariable("id") Integer id) throws Exception {
        girlService.getAge(id);
    }

    @GetMapping("/get/{id}")
    public GirlInfo getById(@PathVariable("id") Integer id) {
        return girlService.getById(id);
    }

    @GetMapping("/get/new/{id}")
    public GirlInfo getByIdNew(@PathVariable("id") Integer id) {
        return girlService.getByIdNew(id);
    }

    @GetMapping("/get/new1/{id}")
    public GirlInfo getByIdNew1(@PathVariable("id") Integer id) {
        return girlService.getByIdNew1(id);
    }
}
