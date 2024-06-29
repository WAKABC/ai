package com.wak.alibabaai.service;

import org.springframework.ai.image.ImageResponse;

/**
 * @author wuankang
 * @date 2024/6/29 11:07
 * @Description TODO 统一千问大模型功能接口
 * @Version 1.0
 */
public interface TongYiService {

    /**
     * 文生文
     *
     * @param message 消息
     * @return {@code String }
     */
    public String txtChat(String message);


    /**
     * 文升图
     *
     * @param message 消息
     * @return {@code ImageResponse }
     */
    public String txt2image(String message);

    /**
     * 文生音
     *
     * @param message 消息
     * @return {@code String }
     */
    public String txt2speech(String message);
}
