package com.urchinsys.imagetranslator.config;

import com.cloudmersive.client.invoker.ApiClient;
import com.cloudmersive.client.invoker.auth.ApiKeyAuth;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class BeanConfig {
  public static final String CLOUD_MERSIVE_API_KEY = "0f49f764-a860-4ba9-8e9f-6c3cb79fa30b";

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .build();
  }

  @Bean
  public ApiClient apiClient() {
    ApiClient apiClient = com.cloudmersive.client.invoker.Configuration.getDefaultApiClient();
    ApiKeyAuth apikey = (ApiKeyAuth) apiClient.getAuthentication("Apikey");
    apikey.setApiKey(CLOUD_MERSIVE_API_KEY);
    return apiClient;
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
