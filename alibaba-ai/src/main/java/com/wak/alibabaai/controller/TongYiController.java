package com.wak.alibabaai.controller;

import com.wak.alibabaai.service.TongYiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuankang
 * @date 2024/6/29 11:12
 * @Description TODO
 * @Version 1.0
 */
@RestController
public class TongYiController {
    private TongYiService tongYiService;

    @Autowired
    public TongYiController(TongYiService tongYiService) {
        this.tongYiService = tongYiService;
    }

    @GetMapping("/ty/txt")
    public String txt2txt(@RequestParam(value = "message", defaultValue = "评价一下中国小笼包") String message){
        return tongYiService.txtChat(message);
    }

    @GetMapping("/ty/img")
    public String txt2Image(@RequestParam(value = "message", defaultValue = "生成一张小笼包画像") String message){
        return tongYiService.txt2image(message);
    }

    @GetMapping("/ty/speech")
    public String txt2speech(@RequestParam(value = "message", defaultValue = "先帝创业未半而中道崩殂，今天下三分，益州疲弊，此诚危急存亡之秋也。然侍卫之臣不懈于内，忠志之士忘身于外者，盖追先帝之殊遇，欲报之于陛下也。") String message){
        return tongYiService.txt2speech(message);
    }
}
