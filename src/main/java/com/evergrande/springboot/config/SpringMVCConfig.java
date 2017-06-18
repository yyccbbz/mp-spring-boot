package com.evergrande.springboot.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.baomidou.kisso.SSOAuthorization;
import com.baomidou.kisso.web.WebKissoConfigurer;
import com.baomidou.kisso.web.interceptor.SSOPermissionInterceptor;
import com.baomidou.kisso.web.interceptor.SSOSpringInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.nio.charset.Charset;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: CuiCan
 * @Date: 2017-6-11
 * @Time: 21:26
 * @Description:
 */
@Configuration
public class SpringMVCConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private ApplicationContext context;

    //--------------Kisso start--------------------------
//    @Bean(initMethod = "initKisso")
//    public WebKissoConfigurer kissoInit() {
//        WebKissoConfigurer webKissoConfigurer = new WebKissoConfigurer();
//        webKissoConfigurer.setSsoPropPath("properties/sso.properties");
//        return webKissoConfigurer;
//    }
//
//    @Bean
//    public SSOPermissionInterceptor getSSOPermissionInterceptor(SSOAuthorization authorization) {
//        SSOPermissionInterceptor ssoPermissionInterceptor = new SSOPermissionInterceptor();
//        ssoPermissionInterceptor.setIllegalUrl("http://127.0.0.1:8888/test/permission/illegalAccess.html");
//        ssoPermissionInterceptor.setAuthorization(authorization);
//        return ssoPermissionInterceptor;
//    }
//    //--------------Kisso end--------------------------
//
//    /**
//     * 配置拦截器
//     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new SSOSpringInterceptor()).addPathPatterns("/**");
//        registry.addInterceptor(context.getBean(SSOPermissionInterceptor.class))
//                .addPathPatterns("/test/permission/**")
//                .addPathPatterns("/home");
//    }

    /**
     * 静态资源过滤
     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//        super.addResourceHandlers(registry);
//    }

    /**
     * 自定义视图解析器
     *
     * @return viewResolver
     */
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/pages/");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }

    // 自定义消息转化器
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        super.configureMessageConverters(converters);

        StringHttpMessageConverter httpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));

        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();

        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.PrettyFormat
        );
        fastConverter.setFastJsonConfig(fastJsonConfig);

        converters.add(httpMessageConverter);
        converters.add(fastConverter);
    }
}
