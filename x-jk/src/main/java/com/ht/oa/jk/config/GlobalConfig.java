package com.ht.oa.jk.config;

import com.ht.oa.jk.utils.common.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component
public class GlobalConfig {

    @Resource
    private Environment env;

    @Resource
    private void configureThymeleafStaticVars(ThymeleafViewResolver viewResolver) {
        if (viewResolver != null) {
            Map<String, Object> vars = new HashMap<>();
            String isProxy = env.getProperty("common.isProxy");
            if (!StringUtils.isBlank(isProxy) && Integer.parseInt(isProxy) == 1) {
                String proxyPort = env.getProperty("common.proxyPort");
                String schema = env.getProperty("common.schema");
                String domain = env.getProperty("common.domain");
                if (!StringUtils.isBlank(proxyPort)) {
                    if (Integer.parseInt(proxyPort) == 80 || Integer.parseInt(proxyPort) == 443) {
                        vars.put("jsDataPath", schema + "://" + domain + "/");
                        vars.put("jsResPath", schema + "://" + domain + "/");
                    }
                } else {
                    vars.put("jsDataPath", schema + "://" + domain + ":" + proxyPort + "/");
                    vars.put("jsResPath", schema + "://" + domain + ":" + proxyPort + "/");
                }
            } else {
                vars.put("jsDataPath", env.getProperty("common.jsDataPath"));
                vars.put("jsResPath", env.getProperty("common.jsResPath"));
            }
            vars.put("jsCdnPath", env.getProperty("common.jsCdnPath"));
            vars.put("dc", env.getProperty("common.dc"));
            viewResolver.setStaticVariables(vars);
        }
    }

}
