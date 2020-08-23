package com.yonyougov;


import lombok.EqualsAndHashCode;

import javax.annotation.Resource;

/**
 * @Author yindwe@yonyou.com
 * @Date 2020/6/15
 */
@EqualsAndHashCode(of = "plataform")
public abstract class PlataformAbstractService {
    @Resource
    protected PtpConfigProperties ptpConfigProperties;
    protected abstract Plataform getPlataform();
    protected enum Plataform {
        APP80, APP85,ZJGA,ZFJ,WJB,PTP,NYB,SHCZJ,JXJK
    }
}
