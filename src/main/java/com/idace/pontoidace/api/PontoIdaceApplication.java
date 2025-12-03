package com.idace.pontoidace.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.idace.pontoidace.api.utils.PasswordUtils;

@SpringBootApplication
public class PontoIdaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PontoIdaceApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			String senhaEncoded = PasswordUtils.gerarBCrypt("123456");
			System.out.println("Senha encoded: " + senhaEncoded);	
			
			senhaEncoded = PasswordUtils.gerarBCrypt("123456");
			System.out.println("Senha encoded novamente: " + senhaEncoded);	

			System.out.println("Senha válida: " + PasswordUtils.senhaValida("123456", senhaEncoded));	
		};
	}

}
