package com.yu.yimclient.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SensitiveWordFilter {

    @Value("${sensitive.words}")
    private String sensitiveWords; // 从配置文件中读取敏感词

    public boolean hasSensitiveWord(String word) {
        String[] sensitiveArray = sensitiveWords.split(",");
        for (String sensitiveWord : sensitiveArray) {
            if (word.contains(sensitiveWord.trim())) {
                return true;
            }
        }
        return false;
    }
}
