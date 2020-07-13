package com.wangwei.cloud;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.MybatisMapWrapperFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.wangwei.cloud.sys.mapper")
public class CloudProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudProviderApplication.class, args);
    }
   // @Bean
   // OAuth2RestTemplate oauth2RestTemplate(@Qualifier("oauth2ClientContext") OAuth2ClientContext oauth2ClientContext, OAuth2ProtectedResourceDetails details) {
    //    return new OAuth2RestTemplate(details, oauth2ClientContext);
    //}
   @Bean
  public ConfigurationCustomizer mybatisConfigurationCustomizer(){
  return new ConfigurationCustomizer() {
    @Override    
           public void customize(org.apache.ibatis.session.Configuration configuration) {
      configuration.setObjectWrapperFactory(new MybatisMapWrapperFactory());
    }
  };
   } 
}
