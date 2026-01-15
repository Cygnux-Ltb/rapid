package io.cygnux.rapid.console.controller.backend;

import io.cygnux.rapid.infra.dto.req.AdapterParamReq;
import io.cygnux.rapid.infra.dto.req.AdapterReq;
import io.cygnux.rapid.infra.service.AdapterService;
import io.cygnux.rapid.infra.service.StrategyService;
import io.cygnux.rapid.infra.service.util.FixedOption;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static io.cygnux.rapid.console.controller.HttpParam.ADAPTER_ID;

/**
 * [UI] - 5.交易服务
 */
@Controller("tradingControllerUi")
@RequestMapping(path = "/trading/ui")
public class TradingController {

    @Resource
    private AdapterService adapterService;

    @Resource
    private StrategyService strategyService;

    @Resource
    private FixedOption fixedOption;

    @GetMapping
    public ModelAndView listTrading() {
        var view = new ModelAndView("trading_list");
        view.addObject("adaptorList", adapterService.getAllAdaptor());
        return view;
    }

    @GetMapping("/go_add")
    public ModelAndView goAddAdaptor() {
        var view = new ModelAndView("adapter_add");
        view.addObject("adaptorTypeOptions", fixedOption.getAdapterTypeOptions());
        return view;
    }

    @GetMapping("/go_edit")
    public ModelAndView goEditAdaptor(@RequestParam(ADAPTER_ID) int adapterId) {
        var view = new ModelAndView("adapter_edit");
        view.addObject("adapter", adapterService.getAdaptor(adapterId));
        view.addObject("adapterTypeOptions", fixedOption.getAdapterTypeOptions());
        return view;
    }

    @PostMapping("/update")
    public ModelAndView addStrategy(AdapterReq request) {
        var view = new ModelAndView("adapter_add");
        adapterService.putAdapter(request);
        return listTrading();
    }

    @GetMapping("/enable")
    public ModelAndView enable(@RequestParam(ADAPTER_ID) int adapterId) {
        adapterService.enableAdapter(adapterId);
        return listTrading();
    }

    @GetMapping("/disable")
    public ModelAndView disable(@RequestParam(ADAPTER_ID) int adapterId) {
        adapterService.disableAdapter(adapterId);
        return listTrading();
    }


    /**
     * @return ModelAndView
     */
    @GetMapping("/param")
    public ModelAndView adapterParams() {
        var view = new ModelAndView("adapter_param_list");
        view.addObject("params", adapterService.getAdapterParam());
        return view;
    }

    /**
     * @return ModelAndView
     */
    @GetMapping("/go_add_param")
    public ModelAndView goAddParam() {
        var view = new ModelAndView("adapter_param_add");
        view.addObject("adapterOptions", adapterService.getAdapterOptions());
        return view;
    }

    /**
     * @param adaptorId  String
     * @param paramGroup String
     * @param paramName  String
     * @return ModelAndView
     */
    @GetMapping("/go_edit_param")
    public ModelAndView goEditParam(@RequestParam(ADAPTER_ID) int adaptorId,
                                    @RequestParam("paramGroup") String paramGroup,
                                    @RequestParam("paramName") String paramName) {
        var view = new ModelAndView("adapter_param_edit");
        view.addObject("adapterOptions", adapterService.getAdapterOptions());
        view.addObject("param", adapterService.getAdapterParam(adaptorId, paramGroup, paramName));
        return view;
    }

    /**
     * @param request AdaptorParamReq
     * @return ModelAndView
     */
    @PostMapping("/update_param")
    public ModelAndView updateAdapterParam(AdapterParamReq request) {
        var param = adapterService.putAdapterParam(request);
        return adapterParams();
    }

}
