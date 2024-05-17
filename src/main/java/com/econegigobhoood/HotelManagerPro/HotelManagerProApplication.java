package com.econegigobhoood.HotelManagerPro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.econegigobhoood.HotelManagerPro.config.DBConfig;

@SpringBootApplication
public class HotelManagerProApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelManagerProApplication.class, args);

		// Inicializando o banco de dados
		DBConfig.createTables();
	}
}
