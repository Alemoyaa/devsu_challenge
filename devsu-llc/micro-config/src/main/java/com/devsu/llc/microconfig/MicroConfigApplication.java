package com.devsu.llc.microconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class MicroConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroConfigApplication.class, args);
	}

}
