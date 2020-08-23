package com.yonyougov;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @Author yindwe@yonyou.com
 * @Date 2020/5/27
 */
@ConfigurationProperties(prefix = "ptp-config")
@Data
public class PtpConfigProperties {
    //配置关联平台(指定哪些service被初始化)
    private String plataform = "app85";

    public String getPlataform() {
        Properties properties = new Properties();
        // 使用ClassLoader加载properties配置文件生成对应的输入流
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("application.properties");
        // 使用properties对象加载输入流
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取key对应的value值
        return properties.getProperty("ptp-config.plataform");
    }

    //是否扫描基础组件，并初始化
    private boolean enableUpdateBaseComp = false;
    //是否与平台绑定
    private boolean withCrux = false;
    //扫描包
    private String[] scanBaseComp;
    private String password;
    private String version;
    private String upgradeRoute;
    private boolean enabledAutoSql = false;

    private Map<String, PtpUrl> ptpUrl = new HashMap<>();

    @Data
    @EqualsAndHashCode(of = "plataform")
    @Accessors(chain = true)
    public static class PtpUrl {
        @ApiModelProperty(value = "平台")
        private String plataform;
        @ApiModelProperty(value = "菜单url")
        private String menuUrl;
        @ApiModelProperty(value = "门户统一菜单url")
        private String ptpMenuUrl;
        @ApiModelProperty(value = "门户退出url")
        private String logoutUrl;
        @ApiModelProperty(value = "角色url")
        private String roleUrl;
        @ApiModelProperty(value = "公共url")
        private String commonUrl;
        @ApiModelProperty(value = "消息中心url")
        private String msgUrl;
        @ApiModelProperty(value = "查询所有角色url")
        private String selectAllRole;
        @ApiModelProperty(value = "按照用户id查询当前角色")
        private String selectRoleByUserId;
        @ApiModelProperty(value = "获取应用上下文")
        private String appContextAllValue;
        @ApiModelProperty(value = "刷新应用上下文")
        private String appContextApply;
        @ApiModelProperty(value = "获取当前用户信息")
        private String currentUser;
        @ApiModelProperty(value = "验证当前用户")
        private String validateUser;
        @ApiModelProperty(value = "更改密码")
        private String changepassword;
        @ApiModelProperty(value = "财务云账套")
        private String cwyBookAccount;
        @ApiModelProperty(value = "财政云账套")
        private String czyBookAccount;
        @ApiModelProperty(value = "获取当前用户使用皮肤")
        private String currentUserSkin;
    }


    public PtpUrl getDelegate(){
        return this.getPtpUrl().get(this.plataform);
    }

}
