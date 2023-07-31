package io.cygnuxltb.console.controller;

import io.cygnuxltb.console.controller.base.ResponseStatus;
import io.cygnuxltb.console.service.ProductService;
import io.cygnuxltb.protocol.http.response.ProductDTO;
import io.cygnuxltb.protocol.http.response.status.StrategyStatus;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static io.cygnuxltb.console.controller.base.HttpParam.PRODUCT_ID;
import static io.cygnuxltb.console.controller.base.HttpParam.STRATEGY_ID;
import static io.mercury.common.http.MimeType.APPLICATION_JSON_UTF8;

/**
 * 产品服务
 */
@RestController
@RequestMapping(path = "/product", produces = APPLICATION_JSON_UTF8)
public final class ProductController {

    private static final Logger log = Log4j2LoggerFactory.getLogger(ProductController.class);

    /**
     * 执行具体操作的executor
     */
    @Resource
    private ProductService service;

    private final ConcurrentHashMap<Integer, StrategyStatus> StrategyStatusMap = new ConcurrentHashMap<>();

    /**
     * 获取全部产品
     *
     * @return ResponseEntity<List < ProductEntity>>
     */
    @GetMapping
    public List<ProductDTO> getProduct() {
        return service.get();
    }

    /**
     * 获取指定产品信息
     *
     * @param productId int
     * @return ResponseEntity<ProductEntity>
     */
    @GetMapping("/get")
    public ProductDTO getProduct(@RequestParam(PRODUCT_ID) int productId) {
        return service.get(productId);
    }

    /**
     * 产品初始化
     *
     * @return ResponseEntity<?>
     */
    @GetMapping("/init")
    public ResponseStatus putInitFinish(@RequestParam(STRATEGY_ID) int strategyId,
                                        @RequestParam("status") int status) {
        StrategyStatus strategyStatus = StrategyStatusMap.putIfAbsent(strategyId,
                new StrategyStatus().setStrategyId(strategyId).setStatus(status));
        return ResponseStatus.OK;
    }

}
