package com.urchinsys.imagetranslator.config;

import com.cloudmersive.client.invoker.ApiClient;
import com.cloudmersive.client.invoker.auth.ApiKeyAuth;
import com.urchinsys.imagetranslator.converter.WordsApiResponseToWordDefinitionDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
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
  @Value("${cloud_mersive_api_key}")
  public String CLOUD_MERSIVE_API_KEY;

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
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.addConverter(new WordsApiResponseToWordDefinitionDto());
    return modelMapper;
  }
}
