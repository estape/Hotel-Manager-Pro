package com.econegigobhoood.HotelManagerPro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.econegigobhoood.HotelManagerPro.view.MainMenu;

@SpringBootApplication
public class HMPApplication {

	public static void main(String[] args) {
		SpringApplication.run(HMPApplication.class, args);

		MainMenu.callMainMenu();
	}
}
