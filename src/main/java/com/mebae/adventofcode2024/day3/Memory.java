package com.mebae.adventofcode2024.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.regex.Pattern;

public class Memory {
  private final String corruptedMemory;
  private int mulResult = -1;

  private Memory(String corruptedMemory) {
    this.corruptedMemory = corruptedMemory;
  }

  public static Memory of(String fileName) {
    Objects.requireNonNull(fileName);
    return new Memory(readCorruptedMemory(fileName));
  }

  private static String readCorruptedMemory(String fileName) {
    try {
      return Files.readString(Path.of(fileName));
    } catch (IOException e) {
      throw new IllegalArgumentException("An error occurred while reading the file: " + e.getMessage());
    }
  }

  public int computeMulResult() {
    if (mulResult == -1) {
      var pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
      var matcher = pattern.matcher(corruptedMemory);
      mulResult = 0;
      while (matcher.find()) {
        mulResult += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
      }
    }
    return mulResult;
  }
}
