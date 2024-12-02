package com.mebae.adventofcode2024;

import com.mebae.adventofcode2024.day1.Locations;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LocationsTests {
  private static Locations locations;

  @BeforeAll
  static void setUpBeforeClass() {
    locations = Locations.of("src/test/resources/day1example.txt");
  }

  @Test
  void computeTotalDistanceTest() {
    assertEquals(11, locations.computeTotalDistance());
  }

  @Test
  void computeSimilarityScoreTest() {
    assertEquals(31, locations.computeSimilarityScore());
  }
}
