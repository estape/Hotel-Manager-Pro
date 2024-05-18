package com.econegigobhoood.HotelManagerPro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.econegigobhoood.HotelManagerPro.config.DBConfig;
import com.econegigobhoood.HotelManagerPro.model.Misc;
import com.econegigobhoood.HotelManagerPro.view.MainMenu;

@SpringBootApplication
public class HotelManagerProApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelManagerProApplication.class, args);

		Misc.colorBackground("blue"); // teste pra deixar um pouco diferente do padrão :D

		// Inicializando o banco de dados
		DBConfig.createTables();

		// Inicializando o menu principal
		MainMenu.callMainMenu();
	}
}
