package com.mebae.adventofcode2024.day2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ReportListTests {
  private static Reports reportList;

  @BeforeAll
  static void setUpBeforeClass() {
    reportList = Reports.of("src/test/resources/day2example.txt");
  }

  @Test
  void countSafeReportsTest() {
    assertEquals(2, reportList.countSafeReports());
  }

  @Test
  void countProblemDampenerSafeReportsTest() {
    assertEquals(4, reportList.countProblemDampenerSafeReports());
  }
}
