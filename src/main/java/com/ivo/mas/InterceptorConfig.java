package com.ivo.mas;

import com.ivo.mas.service.MainService;
import com.ivo.mas.system.Interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

        registry.addInterceptor(new LoginInterceptor(mainService)).addPathPatterns("/**").excludePathPatterns("/test/**","/readCardPic");
    }

}
