package io.cygnuxltb.console.controller;

import io.cygnuxltb.console.service.PortfolioService;
import io.cygnuxltb.protocol.http.response.PortfolioDTO;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static io.mercury.common.http.MimeType.APPLICATION_JSON_UTF8;

/**
 * 投资组合接口
 */
@RestController
@RequestMapping(path = "/portfolio", produces = APPLICATION_JSON_UTF8)
public class PortfolioController {

    private static final Logger log = Log4j2LoggerFactory.getLogger(PortfolioController.class);

    @Resource
    private PortfolioService service;

    /**
     * 获取用户投资组合
     *
     * @param userId        用户ID
     * @param portfolioName 投资组合名
     * @return PortfolioDTO
     */
    @RequestMapping()
    public PortfolioDTO get(@RequestParam("userId") int userId,
                            @RequestParam("portfolio") String portfolioName) {
        return service.getPortfolio(userId, portfolioName);
    }


}
