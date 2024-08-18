package com.payment.paymentintegration.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Slf4j
@Configuration
public class ThymeleafTemplateConfig {
    @Bean
    public ClassLoaderTemplateResolver classLoaderTemplateResolver() {
        ClassLoaderTemplateResolver emailTemplateResolver=new ClassLoaderTemplateResolver();
        emailTemplateResolver.setPrefix("templates/");
        emailTemplateResolver.setTemplateMode(TemplateMode.HTML);
        emailTemplateResolver.setSuffix(".html");
        emailTemplateResolver.setCharacterEncoding("UTF-8");
        emailTemplateResolver.setCacheable(false);
        log.info("ClassLoaderTemplateResolver : " + emailTemplateResolver);
        return emailTemplateResolver;
    }
}
