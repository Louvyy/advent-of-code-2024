package com.mebae.adventofcode2024.day3;

import java.util.Objects;
import java.util.regex.Pattern;

import static com.mebae.adventofcode2024.utils.FileUtils.readString;

public class Memory {
  private final String corruptedMemory;
  private Integer mulResult = null;
  private Integer mulWithInstructionsResult = null;

  /**
   * Regex pattern to match a mul() operation with two numeric arguments
   */
  private static final String MUL_OPERATION_REGEX = "mul\\((\\d+),(\\d+)\\)";

  /**
   * Regex pattern to match either a mul() operation or conditional statements (do() and don't())
   */
  private static final String MUL_AND_INSTRUCTION_REGEX = MUL_OPERATION_REGEX + "|do\\(\\)|don't\\(\\)";

  private static final String ENABLE_INSTRUCTION = "do()";
  private static final String DISABLE_INSTRUCTION = "don't()";

  private Memory(String corruptedMemory) {
    this.corruptedMemory = corruptedMemory;
  }

  public static Memory of(String fileName) {
    Objects.requireNonNull(fileName);
    return new Memory(readString(fileName));
  }

  public int computeMulResult() {
    if (mulResult == null) {
      var pattern = Pattern.compile(MUL_OPERATION_REGEX);
      var matcher = pattern.matcher(corruptedMemory);
      mulResult = 0;
      while (matcher.find()) {
        mulResult += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
      }
    }
    return mulResult;
  }

  public int computeMulWithInstructionsResult() {
    if (mulWithInstructionsResult == null) {
      var pattern = Pattern.compile(MUL_AND_INSTRUCTION_REGEX);
      var matcher = pattern.matcher(corruptedMemory);
      mulWithInstructionsResult = 0;
      var enabled = true;
      while (matcher.find()) {
        var match = matcher.group();
        switch (match) {
          case ENABLE_INSTRUCTION -> enabled = true;
          case DISABLE_INSTRUCTION -> enabled = false;
          default -> {
            if (enabled) {
              mulWithInstructionsResult += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
            }
          }
        }
      }
    }
    return mulWithInstructionsResult;
  }
}
