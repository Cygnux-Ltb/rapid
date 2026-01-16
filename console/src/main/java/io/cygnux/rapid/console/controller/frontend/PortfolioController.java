package io.cygnux.rapid.console.controller.frontend;

import io.cygnux.rapid.infra.dto.req.PortfolioReq;
import io.cygnux.rapid.infra.dto.resp.PortfolioInstrumentsRsp;
import io.cygnux.rapid.infra.dto.resp.PortfoliosRsp;
import io.cygnux.rapid.console.controller.ResponseStatus;
import io.cygnux.rapid.infra.service.PortfolioService;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static io.cygnux.rapid.console.controller.HttpParam.PORTFOLIO_CODE;
import static io.cygnux.rapid.console.controller.HttpParam.USERID;
import static io.cygnux.rapid.console.controller.ResponseStatus.BAD_REQUEST;
import static io.cygnux.rapid.console.controller.ResponseStatus.OK;
import static io.cygnux.rapid.infra.service.PortfolioService.PortfolioConst.PORTFOLIO_FIRST_CODE;
import static io.cygnux.rapid.infra.service.PortfolioService.PortfolioConst.PORTFOLIO_SECOND_CODE;

/**
 * [REST] - 投资组合(股票池/目标池)服务
 */
@Tag(name = "投资组合(股票池/目标池)服务")
@RestController("portfolioControllerV1")
@RequestMapping(path = "/portfolio/v1", produces = "application/json;charset=utf-8")
public class PortfolioController {

    private static final Logger log = Log4j2LoggerFactory.getLogger(PortfolioController.class);

    @Resource
    private PortfolioService portfolioService;

    /**
     * 添加投资标的(目标池页面中'添加分组'和'添加股票'调用)
     * (1).创建(或修改)目标池时需要提供 'userid', 'portfolioCode', 'portfolioName'
     * (2).添加投资标的时需要提供 'userid', 'portfolioCode', 'instrumentCode'
     * (3).当提供全部4个参数时, 将创建(或修改)目标池, 并将投资标的加入目标池中
     *
     * @param req PortfolioReq
     * @return ResponseStatus
     */
    @PostMapping(path = "/new", consumes = "application/json;charset=utf-8")
    public ResponseStatus putPortfolio(@RequestBody PortfolioReq req) {
        try {
            if (portfolioService.putPortfolio(req.getUserid(), req.getPortfolioCode(),
                    req.getPortfolioName(), req.getInstrumentCode()))
                return OK;
            return BAD_REQUEST;
        } catch (Exception e) {
            log.error("URI -> [../portfolio/v1/new] exception occurred : {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 删除指定用户投资组合
     * (1).删除投资组合(股票池), 需要提供 'userid', 'portfolioCode'
     * (2).删除投资组合(股票池)中的标的, 需要提供 'userid', 'portfolioCode', 'instruments', 其中 'instruments' 为数组类型
     *
     * @param userid        (int 必须项) 用户ID
     * @param portfolioCode (String 必须项) 投资组合(股票池/目标池)代码
     * @param instruments   (String[] 非必须项) 投资标的代码(可提供多个投资标的代码, 使用','分割)
     * @return ResponseStatus
     */
    @DeleteMapping(path = "/delete")
    public ResponseStatus deletePortfolio(@RequestParam(USERID) int userid,
                                          @RequestParam(PORTFOLIO_CODE) String portfolioCode,
                                          @RequestParam("instruments") String instruments) {
        try {
            if (portfolioService.deletePortfolioOrInstruments(userid, portfolioCode, instruments))
                return OK;
            return BAD_REQUEST;
        } catch (Exception e) {
            log.error("URI -> [../portfolio/v1/delete] exception occurred : {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 获取用户投资组合(股票池/目标池)列表
     *
     * @param userid (int) 用户ID
     * @return PortfoliosRsp
     */
    @GetMapping("/list")
    public PortfoliosRsp getPortfolio(@RequestParam(USERID) int userid) {
        return portfolioService.getPortfolios(userid);
    }

    /**
     * 获取用户投资组合(股票池/目标池)详细信息
     *
     * @param userid        (int)    用户ID
     * @param portfolioCode (String) 投资组合(股票池/目标池)名称
     * @return PortfolioInstrumentsRsp
     */
    @GetMapping("/detail")
    public PortfolioInstrumentsRsp getPortfolio(@RequestParam(USERID) int userid,
                                                @RequestParam(PORTFOLIO_CODE) String portfolioCode) {
        return portfolioService.getPortfolioInstruments(userid, portfolioCode);
    }

    /**
     * 获取用户第一投资组合 (首页左侧优先股票池)
     *
     * @param userId (int) 用户ID
     * @return PortfolioInstrumentsRsp
     */
    @GetMapping("/first")
    public PortfolioInstrumentsRsp getFirstPortfolio(@RequestParam(USERID) int userId) {
        return portfolioService.getPortfolioInstruments(userId, PORTFOLIO_FIRST_CODE);
    }

    /**
     * 获取用户第二投资组合 (首页右侧次优股票池)
     *
     * @param userId (int) 用户ID
     * @return PortfolioInstrumentsRsp
     */
    @GetMapping("/second")
    public PortfolioInstrumentsRsp getSecondPortfolio(@RequestParam(USERID) int userId) {
        return portfolioService.getPortfolioInstruments(userId, PORTFOLIO_SECOND_CODE);
    }

}
