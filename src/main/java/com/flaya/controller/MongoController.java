package com.flaya.controller;

import com.flaya.domain.CyberInform;
import com.flaya.domain.param.CloudParam;
import com.flaya.repository.CloudRepository;
import com.flaya.repository.CyberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
//@EnableAutoConfiguration
//@RequestMapping("/spring_boot/rest")
public class MongoController {

    @Autowired
    private CyberRepository cyberRepository;

    @Autowired
    private CloudRepository cloudRepository;

    @RequestMapping(value = "/mongo", method = {RequestMethod.GET, RequestMethod.POST})
    public CyberInform testMongo(@RequestBody CyberInform cyberInform) {
        log.info(cyberInform.toString());
        return cyberRepository.save(cyberInform);
    }

    /**
     * 入参为数组，遍历存储
     * @param cyberInforms
     * @return
     */
    @RequestMapping(value = "/mongo_list", method = {RequestMethod.GET, RequestMethod.POST})
    public CyberInform[] testMongoList(@RequestBody CyberInform[] cyberInforms) {
        for (CyberInform cyberInform : cyberInforms) {
            log.info(cyberInform.toString());
            cyberRepository.save(cyberInform);
        }
        return cyberInforms;
    }

    /**
     * 入参为数组，转换为列表
     * @param cyberInforms
     * @return
     */
    @RequestMapping(value = "/mongo_array", method = {RequestMethod.GET, RequestMethod.POST})
    public CyberInform[] testMongoArray(@RequestBody CyberInform[] cyberInforms) {
        List<CyberInform> cyberInformList = Arrays.asList(cyberInforms);
        cyberRepository.saveAll(cyberInformList);
        return cyberInforms;
    }

    /**
     * 入参即为列表
     * @param cyberInformList
     * @return
     */
    @RequestMapping(value = "/mongo_array_new", method = {RequestMethod.GET, RequestMethod.POST})
    public List<CyberInform> testMongoArrayNew(@RequestBody List<CyberInform> cyberInformList) {
        cyberRepository.saveAll(cyberInformList);
        return cyberInformList;
    }

    /**
     * 单实例条件查询，有重复会报错：returned non unique result
     * @param streamSessionId
     * @return
     */
    @RequestMapping(value = "/mongo/{streamSessionId}", method = {RequestMethod.GET, RequestMethod.POST})
    public CyberInform testMongoFind(@PathVariable("streamSessionId") String streamSessionId) {
        return cyberRepository.findByStreamSessionId(streamSessionId);
    }

    /**
     * 列表条件查询，repository规则命名，json根节点条件查询
     * @param streamSessionId
     * @return
     */
    @RequestMapping(value = "/mongo_list/{streamSessionId}", method = {RequestMethod.GET, RequestMethod.POST})
    public List<CyberInform> testMongoFindList(@PathVariable("streamSessionId") String streamSessionId) {
        return cyberRepository.findAllByStreamSessionId(streamSessionId);
    }

    /**
     * 列表条件查询，repository规则命名，json子节点列表嵌套实体条件查询
     * @param name
     * @return
     */
    @RequestMapping(value = "/findAllByUserInfosName", method = {RequestMethod.GET, RequestMethod.POST})
    public List<CyberInform> findAllByUserInfosName(@RequestParam(value = "name", defaultValue = "") String name) {
        return cyberRepository.findAllByUserInfosName(name);
    }

    /**
     * 列表条件查询，repository规则命名，json子节点列表嵌套实体条件查询
     * @param phone
     * @return
     */
    @RequestMapping(value = "/findAllByUserInfosPhone", method = {RequestMethod.GET, RequestMethod.POST})
    public List<CyberInform> findAllByUserInfosPhone(@RequestParam(value = "phone", defaultValue = "") String phone) {
        return cyberRepository.findAllByUserInfosPhone(phone);
    }

    /**
     * 列表条件查询，自定义@Query，json子节点列表嵌套实体条件查询
     * @param name
     * @return
     */
    @RequestMapping(value = "/findByName", method = {RequestMethod.GET, RequestMethod.POST})
    public List<CyberInform> findByName(@RequestParam(value = "name", defaultValue = "") String name) {
        return cyberRepository.findByName(name);
    }

}
