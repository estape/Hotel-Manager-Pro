package com.econegigobhoood.HotelManagerPro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.econegigobhoood.HotelManagerPro.view.MainMenu;

@SpringBootApplication
public class HotelManagerProApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelManagerProApplication.class, args);

		MainMenu.callMainMenu();
	}
}
