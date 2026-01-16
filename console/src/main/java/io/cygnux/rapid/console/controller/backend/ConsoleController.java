package io.cygnux.rapid.console.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * [UI] - 1.控制台
 */
@Controller("consoleControllerUi")
@RequestMapping(path = "/console/ui")
public class ConsoleController {

    /**
     * 控制台主页
     *
     * @return ModelAndView
     */
    @GetMapping
    public ModelAndView param() {
        var view = new ModelAndView();
        view.setViewName("_console");
        return view;
    }

}
