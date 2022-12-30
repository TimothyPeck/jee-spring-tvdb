package ch.hearc.tvdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TvdbApplication {

	public static void main(String[] args) {
		System.out.println("Before SpringApplication.run");
		SpringApplication.run(TvdbApplication.class, args);
		System.out.println("After SpringApplication.run");
	}

}
