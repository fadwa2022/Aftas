package com.example.aftas;

import com.example.aftas.entity.Member;
import com.example.aftas.entity.Role;
import com.example.aftas.repository.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AftasApplication {

	public static void main(String[] args) {
		SpringApplication.run(AftasApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			MemberRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			if (userRepository.findByUsername("fadwa").isEmpty()) {
				var Member = com.example.aftas.entity.Member.builder()
						.username("fadwa")
						.password(passwordEncoder.encode("1234"))
						.name("fadwa")
						.role(Role.MANAGER)
						.build();
				userRepository.save(Member);

			}
			;
		};
	}

}
