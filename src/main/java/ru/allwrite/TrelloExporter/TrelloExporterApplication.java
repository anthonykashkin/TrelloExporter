package ru.allwrite.TrelloExporter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TrelloExporterApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrelloExporterApplication.class, args);
	}
}
