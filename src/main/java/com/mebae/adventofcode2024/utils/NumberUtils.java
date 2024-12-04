package com.mebae.adventofcode2024.utils;

public class NumberUtils {
  public static boolean isStrictlyOrdered(boolean isIncreasing, int number1, int number2) {
    return isIncreasing ? number1 < number2 : number1 > number2;
  }
}
