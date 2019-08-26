package com.wildcodeschool.myProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@SpringBootApplication
public class MyProjectApplication {

	public static void main(String[] args) {

		SpringApplication.run(MyProjectApplication.class, args);
	}

	//@RequestMapping("/hello/{name}")
	//@ResponseBody
	//public String hello(@PathVariable String name, @RequestParam String title) {
	//	return "Hello" + name + "," + title;
	//}

	//@RequestMapping("/myDoctor/{id}")
	//@ResponseBody

	//public Doctor myDoctor(@PathVariable int id) {
	//	return new Doctor("David Tennant", "tenth");
	//	return new ExtendedDoctor("David Tennant", "tenth", "I don't want to go");
	//}

	private int noDoctor;
	private  String nameDoctor;
	private int numberEpisodes;
	private int ageAtStar;

	@RequestMapping("/doctor/{id}")
	@ResponseBody

	public Doctor doctor(@PathVariable("id") int id, @RequestParam(value ="details", required = false) String details) {

		if ((id <= 0) | (id > 13)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Impossible de récupérer l'incarnation " + id);
		}
		if ((id > 0) && (id <= 8)) {
			throw new ResponseStatusException(HttpStatus.MOVED_TEMPORARILY);
		}
		// valeur correcte de l'id

		switch (id) {
			case 9:
				nameDoctor = "Christopher Eccleston";
				numberEpisodes = 13;
				ageAtStar = 41;
				break;
			case 10:
				nameDoctor = "David Tennant";
				numberEpisodes = 47;
				ageAtStar = 38;
				break;
			case 11:
				nameDoctor = "Matt Smith";
				numberEpisodes = 44;
				ageAtStar = 31;
				break;
			case 12:
				nameDoctor = "Peter Capaldi";
				numberEpisodes = 40;
				ageAtStar = 59;
				break;
			case 13:
				nameDoctor = "Jodie Whittaker";
				numberEpisodes = 11;
				ageAtStar = 35;
				break;
		}
		noDoctor = id;
		if (details == null) {

		//	Doctor doctor = new Doctor(noDoctor, nameDoctor);
			return new Doctor(noDoctor, nameDoctor);
		}
			else {
		//	Doctor doctor = new ExtendedDoctor(noDoctor, nameDoctor, numberEpisodes, ageAtStar);
			 return new ExtendedDoctor(noDoctor, nameDoctor, numberEpisodes, ageAtStar);
		}


	}

		class Doctor {

			private int number;
			private String name;


			public Doctor(int number,String name) {
				this.number = number;
				this.name = name;
			}

			public String getName() {
				return name;
			}

			public int getNumber() {
				return number;
			}
		}

		class ExtendedDoctor extends Doctor {

			private int numberOfEpisodes;
			private int ageAtStart;

			public ExtendedDoctor(int number, String name,  int numberOfEpisodes, int ageAtStart) {
				super(number, name);
				this.numberOfEpisodes = numberOfEpisodes;
				this.ageAtStart = ageAtStart;
			}

			public int getNumberOfEpisodes() {
				return numberOfEpisodes;
			}

			public int getAgeAtStart() {
				return ageAtStart;
			}
		}


}
