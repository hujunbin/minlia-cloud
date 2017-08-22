package com.minlia.cloud.autoconfiguration;

import com.google.gson.Gson;
import com.minlia.cloud.config.AsyncConfiguration;
import com.minlia.cloud.config.DozerConfiguration;
import com.minlia.cloud.holder.ContextHolder;
import java.util.ArrayList;
import java.util.Collection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan(basePackages = "com.minlia.*")
@Configuration
@ConditionalOnClass(MinliaCloudAutoConfiguration.class)
@Slf4j
public class MinliaCloudAutoConfiguration {


    public MinliaCloudAutoConfiguration() {
        log.debug("MinliaCloudAutoConfiguration Starting Autoconfiguration...");
    }


    @Bean
    @ConditionalOnMissingBean(ContextHolder.class)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ContextHolder contextHolder() {
        ContextHolder contextHolder = new ContextHolder();
        return contextHolder;
    }



//    @EnableWebMvc
//    @Configuration
//    @Import(WebMvcConfiguration.class)
//    @ConditionalOnMissingBean(WebMvcConfiguration.class)
//    public static class EnableMinliaWebMvcConfig {
////        @Bean
////        AuditLogInterceptor localInterceptor() {
////            return new AuditLogInterceptor();
////        }
//    }


    @Configuration
    @EnableAsync
    @EnableScheduling
    @EnableAspectJAutoProxy(proxyTargetClass = true)
    @Import(AsyncConfiguration.class)
    @ConditionalOnMissingBean(AsyncConfiguration.class)
    public static class EnableMinliaAsyncConfig {
    }


    @Configuration
    @Import(DozerConfiguration.class)
    @ConditionalOnMissingBean(DozerConfiguration.class)
    public static class EnableMinliaDozerConfiguration {
    }





    @Configuration
    @Import(WebMvcConfiguration.class)
//    @ConditionalOnMissingBean(WebMvcConfiguration.class)
    public static class StarLionWebMvcConfig {
//        @Bean
//        AuditLogInterceptor localInterceptor() {
//            return new AuditLogInterceptor();
//        }

//        @Configuration
//        public class CustomConfiguration {
//            @Bean
//            public HttpMessageConverters customConverters() {
//                Collection<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
//                GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter();
//                messageConverters.add(gsonHttpMessageConverter);
//                return new HttpMessageConverters(true, messageConverters);
//            }
//        }


    }




//    @Configuration
//    @ConditionalOnClass(Gson.class)
//    @ConditionalOnMissingClass(value = "com.fasterxml.jackson.core.JsonGenerator")
//    @ConditionalOnBean(Gson.class)
//    protected static class GsonHttpMessageConverterConfiguration {
//
//        @Bean
//        @ConditionalOnMissingBean
//        public GsonHttpMessageConverter gsonHttpMessageConverter(Gson gson) {
//            GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
//            converter.setGson(gson);
//            return converter;
//        }
//
//    }

}
