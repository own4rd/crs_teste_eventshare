package com.wagner.event_schedule;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "Event Schedule API",
				version = "1.0",
				description = "API para gerenciar eventos, instituições e o status de ativação e desativação dos eventos."
		)
)
@SpringBootApplication
public class EventScheduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventScheduleApplication.class, args);
	}

}
