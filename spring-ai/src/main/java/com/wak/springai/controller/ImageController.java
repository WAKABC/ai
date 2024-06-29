package com.wak.springai.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.FileUtil;
import jakarta.annotation.Resource;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * @author wuankang
 * @date 2024/5/30 16:52
 * @Description TODO openai文生图
 * @Version 1.0
 */
@RestController
public class ImageController {
    @Resource
    private OpenAiImageModel imageModel;

    @GetMapping(value = "/ai/image")
    public Object generateImage(@RequestParam(value = "msg", defaultValue = "苹果") String msg) {
        ImageResponse response = imageModel.call(new ImagePrompt(msg));
        byte[] imageData = Base64.decode(response.getResult().getOutput().getB64Json().getBytes());
        FileUtil.writeBytes(imageData, FileUtil.getWebRoot() + "\\src\\main\\resources\\image.png");
        return response.getResult().getOutput();
    }

    @GetMapping(value = "/ai/image/options")
    public Object generateImageOptions(@RequestParam(value = "msg", defaultValue = "苹果") String msg) {
        ImageResponse response = imageModel.call(new ImagePrompt(msg, OpenAiImageOptions
                .builder()
                .withHeight(1024)
                .withWidth(1024)
                //图片个数
                .withN(1)
                //图片质量:高清
                .withQuality("hd")
                .build()));
        return response.getResult().getOutput();
    }
}
