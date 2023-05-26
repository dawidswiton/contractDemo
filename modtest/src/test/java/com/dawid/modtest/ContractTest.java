package com.dawid.modtest;

import com.dawid.api.mod2.TextApi;
import com.dawid.mod1.Mod1Application;
import com.dawid.mod2.Mod2Application;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.SpringProperties;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Properties;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContractTest {

	static int portMod1 = 8085;
	static int portMod2 = 8084;

	ConfigurableApplicationContext mod1App;
	ConfigurableApplicationContext mod2App;

	@BeforeAll
	void initializeServices() throws IOException {
		final String mod1Properties = new DefaultResourceLoader()
				.getResource("classpath:mod1.properties")
				.getContentAsString(Charset.defaultCharset());

		final String mod2Properties = new DefaultResourceLoader()
				.getResource("classpath:mod2.properties")
				.getContentAsString(Charset.defaultCharset());

		mod1App = new SpringApplicationBuilder(Mod1Application.class)
				.properties(mod1Properties)
				.properties("server.port=" + portMod1)
				.run();
		mod2App = new SpringApplicationBuilder(Mod2Application.class)
				.properties(mod2Properties)
				.properties("server.port=" + portMod2)
				.run();
	}

	@Test
	void mod2ShouldGetNameFromMod1IfIsCallFromRest() {
		RestTemplate restTemplate = new RestTemplate();
		final ResponseEntity<String> response = restTemplate.exchange("http://localhost:8084/text", HttpMethod.GET, null, String.class);

		Assertions.assertThat(response.getBody())
				.isEqualTo("My name is Dawid Switon-Maniakowski");
	}

	@Test
	void mod2ShouldGetNameFromMod1IfIsCallFromBean() {
		final TextApi textApiBean = mod2App.getBean(TextApi.class);

		final String response = textApiBean.getText();

		Assertions.assertThat(response)
				.isEqualTo("My name is Dawid Switon-Maniakowski");
	}

}
