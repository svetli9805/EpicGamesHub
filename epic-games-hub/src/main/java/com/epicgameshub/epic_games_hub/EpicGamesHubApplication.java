package com.epicgameshub.epic_games_hub;

import com.epicgameshub.epic_games_hub.role.Role;
import com.epicgameshub.epic_games_hub.role.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class EpicGamesHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(EpicGamesHubApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(RoleRepository roleRepository) {
		return args -> {
			if (roleRepository.findByName("USER").isEmpty()) {
				roleRepository.save(
						Role.builder().name("USER").build()
				);
			}
		};
	}

}
