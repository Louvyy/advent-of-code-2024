package com.mebae.adventofcode2024.day3;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MemoryTests {
  private static Memory memory;

  @BeforeAll
  static void setUpBeforeClass() {
    memory = Memory.of("src/test/resources/day3example.txt");
  }

  @Test
  void computeMulResultTest() {
    assertEquals(161, memory.computeMulResult());
  }
}
