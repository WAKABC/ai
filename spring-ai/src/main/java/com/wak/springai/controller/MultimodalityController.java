package com.wak.springai.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.messages.Media;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wuankang
 * @date 2024/5/30 22:37
 * @Description TODO 多模态AI
 * @Version 1.0
 */
@RestController
public class MultimodalityController {
    @Resource
    private ChatModel chatModel;

    @GetMapping(value = "/ai/multi")
    public Object audioToText(@RequestParam(value = "msg", defaultValue = "描述图片内容") String msg) {
        ClassPathResource imageSource = new ClassPathResource("image.png");
        UserMessage message = new UserMessage(msg, List.of(new Media(MimeTypeUtils.IMAGE_PNG, imageSource)));
        ChatResponse response = chatModel.call(new Prompt(List.of(message), OpenAiChatOptions
                .builder()
                .withModel(OpenAiApi.ChatModel.GPT_4_VISION_PREVIEW.getValue())
                .build()));
        return response.getResult().getOutput().getContent();
    }
}
