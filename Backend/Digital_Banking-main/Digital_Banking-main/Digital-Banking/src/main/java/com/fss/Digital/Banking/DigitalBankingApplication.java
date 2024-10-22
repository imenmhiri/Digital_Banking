package com.fss.Digital.Banking;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class DigitalBankingApplication    {

	public static void main(String[] args) {
		SpringApplication.run(DigitalBankingApplication.class, args);
	}
	@Bean
	// Pour hacher le mot de passe
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

}
