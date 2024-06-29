package com.wak.alibabaai.service;

import cn.hutool.core.io.FileUtil;
import com.alibaba.cloud.ai.tongyi.audio.api.SpeechClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.image.ImageClient;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;

/**
 * @author wuankang
 * @date 2024/6/29 11:05
 * @Description TODO
 * @Version 1.0
 */
@Service
@Slf4j
public class TongYiSimpleServiceImpl implements TongYiService {
    private final ChatClient chatClient;
    private final ImageClient imageClient;
    private final SpeechClient speechClient;

    @Autowired
    public TongYiSimpleServiceImpl(ChatClient chatClient, ImageClient imageClient, SpeechClient speechClient) {
        this.chatClient = chatClient;
        this.imageClient = imageClient;
        this.speechClient = speechClient;
    }

    /**
     * 文生文
     *
     * @param message 消息
     * @return {@code String }
     */
    @Override
    public String txtChat(String message) {
        return chatClient.call(message);
    }

    /**
     * 文生图
     *
     * @param message 消息
     * @return {@code ImageResponse }
     */
    @Override
    public String txt2image(String message) {
        ImagePrompt imagePrompt = new ImagePrompt(message);
        return imageClient.call(imagePrompt).getResult().getOutput().getUrl();
    }

    /**
     * 文生音
     *
     * @param message 消息
     * @return {@code String }
     */
    @Override
    public String txt2speech(String message) {
        ByteBuffer buffer = speechClient.call(message);
        FileUtil.writeBytes(buffer.array(), FileUtil.getWebRoot() + "\\src\\main\\resources\\三国英雄传.mp3");
        return "文件下载完成，文件位置：" + FileUtil.getWebRoot() + "\\src\\main\\resources\\三国英雄传.mp3";
    }
}
