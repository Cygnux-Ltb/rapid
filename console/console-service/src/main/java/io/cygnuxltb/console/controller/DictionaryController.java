package io.cygnuxltb.console.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 辞典, 包含名词解释
 */
@RestController("/dict")
public class DictionaryController {

    /**
     * @param key 关键字
     * @return 名词
     */
    @GetMapping("mapping")
    public String mapping(String key) {
        return "";
    }
}
