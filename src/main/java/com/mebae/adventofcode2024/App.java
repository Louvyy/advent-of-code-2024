package com.mebae.adventofcode2024;

import com.mebae.adventofcode2024.day1.Locations;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
  public static void main(String[] args) {
//    SpringApplication.run(App.class, args);

    var locations = Locations.of("src/main/resources/day1.txt");
    System.out.println("Total distance : " + locations.computeTotalDistance());
    System.out.println("Similarity score : " + locations.computeSimilarityScore());
  }
}
