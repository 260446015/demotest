package com.yonyougov;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author yindwe@yonyou.com
 * @Date 2019/11/26
 */
public interface IRedirectService {

    ModelAndView doRedirect(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
