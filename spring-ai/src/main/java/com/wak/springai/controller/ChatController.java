package com.wak.springai.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuankang
 * @date 2024/5/30 15:29
 * @Description TODO openai-chatgpt-3.5 文生文
 * @Version 1.0
 */
@RestController
public class ChatController {
    @Resource
    private OpenAiChatModel chatModel;

    /**
     * 直接调用
     *
     * @param message 问题
     * @return {@code String }
     */
    @GetMapping("/ai/hello")
    public String generate(@RequestParam("message") String message) {
        return chatModel.call(message);
    }

    /**
     * Prompt提示词作为参数，返回json串
     *
     * @param message 消息
     * @return {@code String }
     */
    @GetMapping("/ai/prompt")
    public String generateStream(@RequestParam("message") String message) {
        ChatResponse response = chatModel.call(new Prompt(new UserMessage(message)));
        return response.getResult().getOutput().getContent();
    }


    /**
     * 代码指定请求参数
     *
     * @param message 消息
     * @return {@code Object }
     */
    @GetMapping("/ai/option")
    public Object generateStreamOptions(@RequestParam("message") String message) {
        ChatResponse response = chatModel.call(new Prompt(message, OpenAiChatOptions
                .builder()
                //指定模型，需确定key有权限
                .withModel("gpt-4-32k")
                //温度，越小回答越准确
                .withTemperature(0.4f)
                .build()
        ));
        return response.getResult().getOutput().getContent();
    }

}
