package com.yonyougov;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author yindwe@yonyou.com
 * @Date 2019/11/26
 */
@Service
@Slf4j
public class App85RedirectService extends RedirectAbstractService {

    @Override
    protected Plataform getPlataform() {
        return Plataform.APP85;
    }

    private final PtpConfigProperties ptpConfigProperties;

    public App85RedirectService(PtpConfigProperties ptpConfigProperties) {
        this.ptpConfigProperties = ptpConfigProperties;
    }

    @Override
    protected void afterHandler(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response) {
        modelAndView.addObject("name","app85");
    }

}
