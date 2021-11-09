package io.github.scaamanho.rds.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.util.StringUtils;

@Configuration
public class OpenApiConfiguration {

  @Bean
  public OpenAPI customOpenAPI(@Value("${application.name:Open API}") String appName,
                               @Value("${application.description:}") String appDescription,
                               @Value("${application.version:1.0.0}") String appVersion)
  {
    final String securitySchemeName = "bearerAuth";
    final String apiTitle = String.format("%s API", StringUtils.capitalize(appName));

    return new OpenAPI()
        .info(
          new Info().title(apiTitle)
          .version(appVersion)
          .description(appDescription)
          .termsOfService("https://swagger.io/terms")
          .license(new License().name("(c) 2020 Santiago Caamaño").url(""))
          .contact(new Contact().name("Santiago Caamaño").url("").email("scaamanho@gmail.com")))
        .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
        .components(
          new Components().addSecuritySchemes(
              securitySchemeName,
              new SecurityScheme().name(securitySchemeName)
                  .type(SecurityScheme.Type.HTTP)
                  .scheme("bearer")
                  .bearerFormat("JWT")));
  }
}
