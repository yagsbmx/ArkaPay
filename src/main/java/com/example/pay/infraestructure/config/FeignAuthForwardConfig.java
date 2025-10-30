package com.example.pay.infraestructure.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
public class FeignAuthForwardConfig implements RequestInterceptor {
  @Override
  public void apply(RequestTemplate template) {
    var attrs = RequestContextHolder.getRequestAttributes();
    if (attrs instanceof ServletRequestAttributes sra) {
      var auth = sra.getRequest().getHeader("Authorization");
      if (auth != null) template.header("Authorization", auth);
    }
  }
}
