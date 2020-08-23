package com.yonyougov;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @Author yindwe@yonyou.com
 * @Date 2020/5/27
 */
@Slf4j
@Component
public class PtpServiceDelegate {
    @Resource
    protected PtpConfigProperties ptpConfigProperties;
    @Resource
    private ApplicationContext context;

    private Map<Class<?>, List<PlataformAbstractService>> delegateServices = new HashMap<>();

    @PostConstruct
    private void init() {
        Map<String, PlataformAbstractService> beansOfType = context.getBeansOfType(PlataformAbstractService.class);
        beansOfType.forEach((k, v) -> {
            if (v instanceof RedirectAbstractService) {
                List<PlataformAbstractService> redirectAbstractServices = this.delegateServices.get(RedirectAbstractService.class);
                if(null == redirectAbstractServices){
                    redirectAbstractServices = new ArrayList<>();
                }
                redirectAbstractServices.add(v);
                this.delegateServices.put(RedirectAbstractService.class, redirectAbstractServices);
            } else {
                throw new RuntimeException("未匹配到特定平台抽象类代码，请确认plataform对应的service是否正确");
            }
        });
    }

    public Object delegate(Class<? extends PlataformAbstractService> clazz, String name, Object... args) {
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            if (declaredMethod.getName().equalsIgnoreCase(name)) {
                try {
                    return declaredMethod.invoke(this.delegateServices.get(clazz).stream().
                            filter(plataformAbstractService -> ptpConfigProperties.getPlataform().equalsIgnoreCase(plataformAbstractService.getPlataform().name()))
                            .findFirst().orElseThrow(()-> new RuntimeException("反射代理，未能获取到具体目标实现类，请确认...")), args);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    log.error("invoke method error,message:{}", e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


}
