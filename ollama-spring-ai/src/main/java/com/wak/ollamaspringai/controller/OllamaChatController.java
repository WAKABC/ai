package com.wak.ollamaspringai.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuankang
 * @date 2024/6/28 19:18
 * @Description TODO
 * @Version 1.0
 */
@RestController
public class OllamaChatController {
    @Resource
    private OllamaChatClient client;

    /**
     * 直接调用
     *
     * @param message 问题
     * @return {@code String }
     */
    @GetMapping("/ai/ollama")
    public String generate(@RequestParam(value = "message",defaultValue = "简要概括中国历史文化") String message) {
        return client.call(message);
    }
}
