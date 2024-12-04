package com.mebae.adventofcode2024;

import com.mebae.adventofcode2024.day1.Locations;
import com.mebae.adventofcode2024.day2.Reports;
import com.mebae.adventofcode2024.day3.Memory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
  public static void main(String[] args) {
//    SpringApplication.run(App.class, args);

    var locations = Locations.of("src/main/resources/day1.txt");
    System.out.println("Total distance : " + locations.computeTotalDistance());
    System.out.println("Similarity score : " + locations.computeSimilarityScore());

    var reports = Reports.of("src/main/resources/day2.txt");
    System.out.println("Number of safe reports : " + reports.countSafe());
    System.out.println("Number of Problem Dampener safe reports : " + reports.countProblemDampenerSafe());

    var memory = Memory.of("src/main/resources/day3.txt");
    System.out.println("Multiplications result : " + memory.computeMulResult());
    System.out.println("Multiplications with instructions result : " + memory.computeMulWithInstructionsResult());
  }
}
