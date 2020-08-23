package com.yonyougov;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author yindwe@yonyou.com
 * @Date 2020/5/28
 */
@Slf4j
public abstract class RedirectAbstractService extends PlataformAbstractService implements IRedirectService {

    protected ModelAndView handleModel(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String reqTitleName = request.getParameter("titleName");
        modelAndView.addObject("titleName", reqTitleName);
        return modelAndView;
    }

    protected ModelAndView handleView(ModelAndView modelAndView) {
        log.info("当前输出plataform:{}",ptpConfigProperties.getPlataform());
        modelAndView.setViewName(ptpConfigProperties.getPlataform() + "/index");
        return modelAndView;
    }

    protected void afterHandler(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public ModelAndView doRedirect(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = handleView(handleModel(request));
        afterHandler(modelAndView, request, response);
        return modelAndView;
    }

}
