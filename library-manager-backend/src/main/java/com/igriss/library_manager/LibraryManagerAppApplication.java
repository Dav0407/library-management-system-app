package com.igriss.library_manager;

import com.igriss.library_manager.role.Role;
import com.igriss.library_manager.role.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableAsync
public class LibraryManagerAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagerAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(RoleRepository roleRepository){
        return args -> {
            if(roleRepository.findByName("USER").isEmpty())
                roleRepository.save(Role.builder().name("USER").build());
        };
    }
}

