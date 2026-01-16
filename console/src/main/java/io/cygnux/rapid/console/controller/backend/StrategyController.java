package io.cygnux.rapid.console.controller.backend;

import io.cygnux.rapid.infra.dto.req.StrategyParamReq;
import io.cygnux.rapid.infra.dto.req.StrategyReq;
import io.cygnux.rapid.infra.service.AlgoService;
import io.cygnux.rapid.infra.service.StrategyService;
import io.cygnux.rapid.infra.service.UserService;
import io.cygnux.rapid.infra.service.util.FixedOption;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static io.cygnux.rapid.console.controller.HttpParam.STRATEGY_ID;
import static io.cygnux.rapid.console.controller.HttpParam.USERID;

/**
 * [UI] - 4.策略服务
 */
@Controller("strategyControllerUi")
@RequestMapping(path = "/strategy/ui")
public class StrategyController {

    @Resource
    private StrategyService strategyService;

    @Resource
    private FixedOption fixedOption;

    @Resource
    private UserService userService;

    @Resource
    private AlgoService algoService;

    /**
     * @return ModelAndView
     */
    @GetMapping
    public ModelAndView listStrategy() {
        var view = new ModelAndView("strategy_list");
        view.addObject("strategyList", strategyService.getAllStrategy());
        return view;
    }

    /**
     * @return ModelAndView
     */
    @GetMapping("/go_add")
    public ModelAndView goAddStrategy() {
        var view = new ModelAndView("strategy_add");
        view.addObject("strategyTypeOptions", fixedOption.getStrategyTypeOptions());
        return view;
    }

    /**
     * @param strategyId int
     * @return ModelAndView
     */
    @GetMapping("/go_edit")
    public ModelAndView goEditStrategy(@RequestParam(STRATEGY_ID) int strategyId) {
        var view = new ModelAndView("strategy_edit");
        view.addObject("strategy", strategyService.getStrategy(strategyId));
        view.addObject("strategyTypeOptions", fixedOption.getStrategyTypeOptions());
        return view;
    }

    /**
     * @param request StrategyRequest
     * @return ModelAndView
     */
    @PostMapping("/update")
    public ModelAndView updateStrategy(StrategyReq request) {
        var view = new ModelAndView("strategy_add");
        strategyService.putStrategy(request);
        return listStrategy();
    }

    /**
     * @param strategyId int
     * @return ModelAndView
     */
    @GetMapping("/enable")
    public ModelAndView enableStrategy(@RequestParam(STRATEGY_ID) int strategyId) {
        strategyService.enableStrategy(strategyId);
        return listStrategy();
    }

    /**
     * @param strategyId int
     * @return ModelAndView
     */
    @GetMapping("/disable")
    public ModelAndView disableStrategy(@RequestParam(STRATEGY_ID) int strategyId) {
        strategyService.disableStrategy(strategyId);
        return listStrategy();
    }


    //############################### STRATEGY PARAM ###############################//

    /**
     * @return ModelAndView
     */
    @GetMapping("/param")
    public ModelAndView listStrategyParam() {
        var view = new ModelAndView("strategy_param_list");
        view.addObject("params", strategyService.getStrategyParam());
        return view;
    }

    /**
     * @return ModelAndView
     */
    @GetMapping("/go_add_param")
    public ModelAndView goAddStrategyParam() {
        var view = new ModelAndView("strategy_param_add");
        view.addObject("userOptions", userService.getUserOptions());
        view.addObject("strategyOptions", strategyService.getStrategyOptions());
        view.addObject("algoOptions", algoService.getAlgoOptions());
        view.addObject("paramTypeOptions", fixedOption.getParamTypeOptions());
        return view;
    }

    /**
     * @param userid     int
     * @param strategyId int
     * @param algoId     int
     * @param paramName  String
     * @return ModelAndView
     */
    @GetMapping("/go_edit_param")
    public ModelAndView goEditStrategyParam(@RequestParam(USERID) int userid,
                                            @RequestParam(STRATEGY_ID) int strategyId,
                                            @RequestParam("algoId") int algoId,
                                            @RequestParam("paramName") String paramName) {
        var view = new ModelAndView("strategy_param_edit");
        view.addObject("param", strategyService.getStrategyParam(userid, strategyId, algoId, paramName));
        view.addObject("paramTypeOptions", fixedOption.getParamTypeOptions());
        return view;
    }

    /**
     * @param request StrategyParamReq
     * @return ModelAndView
     */
    @PostMapping("/update_param")
    public ModelAndView updateStrategyParam(StrategyParamReq request) {
        var param = strategyService.putStrategyParam(request);
        return listStrategyParam();
    }

}
