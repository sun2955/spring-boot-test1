package com.sun.demo.base.config.swagger2;

import com.google.common.collect.Sets;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
@ConfigurationProperties(prefix="swagger2")
public class Swagger2Config {
	private String userName;
	private String email;
	private String url;
	private String title;
	private String desc;
	private String version;
	private String license;
	private String licenseUrl;
	private String termsOfServiceUrl;
	private String basePackage;

	@Bean
	public Docket getDocket() {
		return new Docket(DocumentationType.SWAGGER_2).
				apiInfo(getApi()).
				select().
				apis(RequestHandlerSelectors.basePackage(basePackage)).build();
	}
	// 如何把对象放在容器
	// 1 配置<bean> 2 通过注解 Companent Service Resp 
	// 在方法上面，通过Bean 里注入容器

	private ApiInfo getApi() {
		Contact contact = new Contact(userName, url, email);
		return new ApiInfoBuilder().
				title(title).
				description(desc).
				license(license).
				licenseUrl(licenseUrl).
				termsOfServiceUrl(termsOfServiceUrl).
				version(version).
				contact(contact).build();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getLicenseUrl() {
		return licenseUrl;
	}

	public void setLicenseUrl(String licenseUrl) {
		this.licenseUrl = licenseUrl;
	}

	public String getTermsOfServiceUrl() {
		return termsOfServiceUrl;
	}

	public void setTermsOfServiceUrl(String termsOfServiceUrl) {
		this.termsOfServiceUrl = termsOfServiceUrl;
	}

	public String getBasePackage() {
		return basePackage;
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}
	
}
