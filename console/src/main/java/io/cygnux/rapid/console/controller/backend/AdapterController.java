package io.cygnux.rapid.console.controller.backend;

import io.cygnux.rapid.infra.service.AdapterService;
import io.cygnux.rapid.infra.service.util.FixedOption;
import io.cygnux.rapid.infra.dto.req.AdapterParamReq;
import io.cygnux.rapid.infra.dto.req.AdapterReq;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static io.cygnux.rapid.console.controller.HttpParam.ADAPTER_ID;

/**
 * [UI] - 2.适配器服务
 */
@Controller("adapterControllerUi")
@RequestMapping(path = "/adapter/ui")
public class AdapterController {

    @Resource
    private AdapterService adapterService;

    @Resource
    private FixedOption fixedOption;

    /**
     * [Adaptor]列表
     *
     * @return ModelAndView
     */
    @GetMapping
    public ModelAndView listAdaptor() {
        var view = new ModelAndView("adapter_list");
        view.addObject("adapterList", adapterService.getAllAdaptor());
        return view;
    }

    /**
     * 添加[Adaptor]中转链接
     *
     * @return ModelAndView
     */
    @GetMapping("/go_add")
    public ModelAndView goAddAdapter() {
        var view = new ModelAndView("adapter_add");
        view.addObject("adapterTypeOptions", fixedOption.getAdapterTypeOptions());
        return view;
    }

    /**
     * 编辑[Adaptor]中转链接
     *
     * @param adapterId int
     * @return ModelAndView
     */
    @GetMapping("/go_edit")
    public ModelAndView goEditAdapter(@RequestParam(ADAPTER_ID) int adapterId) {
        var view = new ModelAndView("adapter_edit");
        view.addObject("adapter", adapterService.getAdaptor(adapterId));
        view.addObject("adapterTypeOptions", fixedOption.getAdapterTypeOptions());
        return view;
    }

    /**
     * 修改[Adaptor]
     *
     * @param request AdaptorRequest
     * @return ModelAndView
     */
    @PostMapping("/update")
    public ModelAndView updateAdaptor(AdapterReq request) {
        adapterService.putAdapter(request);
        return listAdaptor();
    }


    /**
     * 启用[Adaptor]
     *
     * @param adaptorId int
     * @return ModelAndView
     */
    @GetMapping("/enable")
    public ModelAndView enableAdaptor(@RequestParam(ADAPTER_ID) int adaptorId) {
        adapterService.enableAdapter(adaptorId);
        return listAdaptor();
    }


    /**
     * 禁用[Adaptor]
     *
     * @param adaptorId int
     * @return ModelAndView
     */
    @GetMapping("/disable")
    public ModelAndView disableAdaptor(@RequestParam(ADAPTER_ID) int adaptorId) {
        adapterService.disableAdapter(adaptorId);
        return listAdaptor();
    }


    /**
     * [Adaptor参数]列表
     *
     * @return ModelAndView
     */
    @GetMapping("/param")
    public ModelAndView listAdapterParam() {
        var view = new ModelAndView("adapter_param_list");
        view.addObject("params", adapterService.getAdapterParam());
        return view;
    }


    /**
     * 添加[Adapter参数]中转链接
     *
     * @return ModelAndView
     */
    @GetMapping("/go_add_param")
    public ModelAndView goAddAdaptorParam() {
        var view = new ModelAndView("adapter_param_add");
        view.addObject("adapterOptions", adapterService.getAdapterOptions());
        return view;
    }


    /**
     * 编辑[Adapter参数]中转链接
     *
     * @param adapterId  int
     * @param paramGroup String
     * @param paramName  String
     * @return ModelAndView
     */
    @GetMapping("/go_edit_param")
    public ModelAndView goEditAdapterParam(@RequestParam(ADAPTER_ID) int adapterId,
                                           @RequestParam("paramGroup") String paramGroup,
                                           @RequestParam("paramName") String paramName) {
        var view = new ModelAndView("adapter_param_edit");
        view.addObject("adapterOptions", adapterService.getAdapterOptions());
        view.addObject("param", adapterService.getAdapterParam(adapterId, paramGroup, paramName));
        return view;
    }

    /**
     * 修改[Adaptor参数]
     *
     * @param request AdaptorParamReq
     * @return ModelAndView
     */
    @PostMapping("/update_param")
    public ModelAndView updateAdapterParam(AdapterParamReq request) {
        adapterService.putAdapterParam(request);
        return listAdapterParam();
    }

}
