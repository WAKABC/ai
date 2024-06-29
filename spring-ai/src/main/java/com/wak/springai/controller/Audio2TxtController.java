package com.wak.springai.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuankang
 * @date 2024/5/30 18:39
 * @Description TODO音生文
 * @Version 1.0
 */
@RestController
public class Audio2TxtController {

    @Resource
    private OpenAiAudioTranscriptionModel audioTranscriptionModel;

    @GetMapping(value = "/ai/audio")
    public Object audioToText(@RequestParam(value = "language", defaultValue = "en") String language) {
        ClassPathResource resource;
        if (language.equalsIgnoreCase("zh")) {
            resource = new ClassPathResource("三国英杰.mp4");
        } else {
            resource = new ClassPathResource("listen.mp3");
        }
        String response = audioTranscriptionModel.call(resource);
        System.out.println("response = " + response);
        return response;
    }
}
