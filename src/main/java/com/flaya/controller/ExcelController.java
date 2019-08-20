package com.flaya.controller;

//import com.zhb.service.ImportService;

import com.flaya.domain.GameInfo;
import com.flaya.repository.GameRepository;
import com.flaya.service.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @Autowired
    private GameRepository gameRepository;

    @PostMapping(value = "/upload")
    @ResponseBody
    public String uploadExcel(@RequestParam("file") MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        try {
            List<List<XSSFCell>> list = excelService.getBankListByExcel(inputStream, file.getOriginalFilename());
            for (int i = 0; i < list.size(); i++) {
                List<String> cells = new ArrayList<String>();
                List<XSSFCell> lo = list.get(i);
                for (XSSFCell cell : lo) {
                    String value = cell.getStringCellValue();
                    System.out.println(value);
                    cells.add(value);
                }
                GameInfo gameInfo = new GameInfo(cells);
                gameRepository.save(gameInfo);
            }
            return "上传成功";
        } catch (Exception e) {
            log.error("uploadExcel Error", e);
            return "上传失败";
        } finally {
            inputStream.close();
        }
    }


    @PostMapping(value = "/game_ip")
    @ResponseBody
    public String gameIpExcel(@RequestParam("file") MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        try {
            List<List<XSSFCell>> rowList = excelService.getBankListByExcel(inputStream, file.getOriginalFilename());
            for (List<XSSFCell> cellList : rowList) {
//                List<XSSFCell> cellList = list.get(i);
                for (int i = 0; i < cellList.size(); i ++) {
                    XSSFCell cell = cellList.get(i);
                    String value = cell.getStringCellValue();
                    if(i == 0) {
                        log.info("game==={}", value);
                    } else {
                        log.info("ip==={}", value);
                    }

                }
            }
            return "上传成功";
        } catch (Exception e) {
            log.error("gameIpExcel Error", e);
            return "上传失败";
        } finally {
            inputStream.close();
        }
    }

//    @PostMapping(value = "/upload")
//    @ResponseBody
//    public String uploadExcel(HttpServletRequest request) throws Exception {
//        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//
//        MultipartFile file = multipartRequest.getFile("filename");
//        if (file.isEmpty()) {
//            return "文件不能为空";
//        }
//        InputStream inputStream = file.getInputStream();
//        List<List<Object>> list = excelService.getBankListByExcel(inputStream, file.getOriginalFilename());
//        inputStream.close();
//
//        for (int i = 0; i < list.size(); i++) {
//            List<Object> lo = list.get(i);
//            //TODO 随意发挥
//            System.out.println(lo);
//
//        }
//        return "上传成功";
//    }

}



