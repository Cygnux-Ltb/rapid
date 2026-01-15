package io.cygnux.rapid.console.controller.frontend;

import io.cygnux.rapid.console.controller.ResponseStatus;
import io.cygnux.rapid.infra.dto.resp.ProductRsp;
import io.cygnux.rapid.infra.dto.resp.StrategyStatus;
import io.cygnux.rapid.infra.service.ProductService;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static io.cygnux.rapid.console.controller.HttpParam.PROD_CODE;
import static io.cygnux.rapid.console.controller.HttpParam.STATUS;
import static io.cygnux.rapid.console.controller.HttpParam.STRATEGY_ID;

/**
 * [REST] - 产品服务
 */
@Tag(name = "产品服务")
@RestController("productControllerV1")
@RequestMapping(path = "/product/v1", produces = "application/json;charset=utf-8")
public class ProductController {

    private static final Logger log = Log4j2LoggerFactory.getLogger(ProductController.class);

    /**
     * 执行具体操作的executor
     */
    @Resource
    private ProductService productService;

    private final ConcurrentHashMap<Integer, StrategyStatus> StrategyStatusMap = new ConcurrentHashMap<>();

    /**
     * 获取全部产品
     *
     * @return List<ProductRsp>
     */
    @GetMapping
    public List<ProductRsp> getProduct() {
        return productService.getAll();
    }

    /**
     * 获取指定产品信息
     *
     * @param productCode String
     * @return ProductRsp
     */
    @GetMapping("/get")
    public ProductRsp getProduct(@RequestParam(PROD_CODE) String productCode) {
        return productService.get(productCode);
    }

    /**
     * 产品初始化
     *
     * @return ResponseStatus
     */
    @GetMapping("/init")
    public ResponseStatus putInitFinish(@RequestParam(STRATEGY_ID) int strategyId,
                                        @RequestParam(STATUS) int status) {
        StrategyStatus strategyStatus = StrategyStatusMap.putIfAbsent(strategyId,
                new StrategyStatus().setStrategyId(strategyId).setStatus(status));
        return ResponseStatus.OK;
    }

}
