package io.github.scaamanho.sdr.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;
import com.google.common.base.Predicate;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
				.apiInfo(apiInfo()).select().paths(postPaths()).build();
	}

	private Predicate<String> postPaths() {
		return or(regex("/api/posts.*"), regex("/rdr.*"),
				regex("/rdr-api.*"),regex("/actuator.*"));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Rester's Dummy Run API")
				.description("Rester's Dummy Run API for developers")
				.termsOfServiceUrl("http://github.com/scaamanho")
				.license("(c) 2020 Santiago Caamaño")
				.licenseUrl("scaamanho@gmail.com").version("1.0").build();
	}

}
