package com.coffee.coffeeweb.config;

import cn.dreampie.orm.ActiveRecordPlugin;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.coffee.coffeeweb.config.impl.DruidDataSourceProvider;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid(){
        return  new DruidDataSource();
    }
    //配置Druid的监控
    //1、配置一个管理后台的Servlet
    // 访问的路径：http://localhost:8080/druid/index.html
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String,String> initParams = new HashMap<>();
        // 添加IP白名单
        initParams.put("allow", "192.168.25.125,127.0.0.1");
        // 添加IP黑名单，当白名单和黑名单重复时，黑名单优先级更高
        initParams.put("deny", "192.168.25.123");
        // 添加控制台管理用户
        initParams.put("loginUsername", "druid");
        initParams.put("loginPassword", "123456");
        // 是否能够重置数据
        initParams.put("resetEnable", "false");
        bean.setInitParameters(initParams);
        return bean;
    }


    //2、配置一个web监控的filter
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        Map<String,String> initParams = new HashMap<>();
        // 添加过滤规则
//        initParams.put("addUrlPatterns","/*");
        // 忽略过滤格式
        initParams.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(initParams);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return  bean;
    }

    /**
     * ActiveRecord模式配置
     * @param dataSource
     * @return
     */
    @Bean(initMethod = "start", destroyMethod = "stop")
    public ActiveRecordPlugin activeRecordPlugin(DataSource dataSource) {
        DruidDataSourceProvider provider = new DruidDataSourceProvider();
        provider.setDataSource((DruidDataSource) dataSource);
        provider.setDialect("mysql");
        provider.setDsName("datasource");
        ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin(provider);
        activeRecordPlugin.addIncludePackages("cn.dreampie.resource");
        return activeRecordPlugin;
    }
}
