package com.wak.springai.controller;

import cn.hutool.core.io.FileUtil;
import jakarta.annotation.Resource;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

/**
 * @author wuankang
 * @date 2024/5/30 18:52
 * @Description TODO 文生音
 * @Version 1.0
 */
@RestController
public class TtsController {
    @Resource
    private OpenAiAudioSpeechModel audioSpeechModel;

    @GetMapping(value = "/ai/tts")
    public String tts(){
        String textContent = "先帝创业未半而中道崩殂，今天下三分，益州疲弊，此诚危急存亡之秋也。" +
                "然侍卫之臣不懈于内，忠志之士忘身于外者，盖追先帝之殊遇，欲报之于陛下也。";
        byte[] response = audioSpeechModel.call(textContent);
        //文件保存在resource目录下
        FileUtil.writeBytes(response, "src/main/resources/出师表.mp3");
        return "文件写入成功！存储路径：";
    }
}
