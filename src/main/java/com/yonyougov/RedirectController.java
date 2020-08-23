package com.yonyougov;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 转发页面入口
 *
 * @author cn_zkn
 * @date 07/05/2018 8:39 AM
 * @since 1.0
 */
@Controller
@Slf4j
public class RedirectController {

    @Resource
    private PtpServiceDelegate redirectAbstractService;

    @GetMapping({"/", "index"})
    @ResponseBody
    public ModelAndView login(HttpServletResponse response, HttpServletRequest request) {
        return index(response, request);
    }

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping("/index")
    @ResponseBody
    public ModelAndView index(HttpServletResponse response, HttpServletRequest request) {
        ModelAndView doRedirect = (ModelAndView) redirectAbstractService.delegate(RedirectAbstractService.class, "doRedirect", request, response);
        return doRedirect;
    }
}
