package org.example.intelligentapiwithspringai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BudgetingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudgetingApplication.class, args);
	}

	// Cria o bean do ChatClient usando o builder do Spring AI
	@Bean
	public ChatClient chatClient(ChatClient.Builder builder) {
		return builder.build();
	}
}