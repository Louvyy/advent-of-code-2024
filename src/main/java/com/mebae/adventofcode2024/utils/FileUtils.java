package com.mebae.adventofcode2024.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;

public final class FileUtils {
  public static String readString(String fileName) {
    Objects.requireNonNull(fileName);
    try {
      return Files.readString(Path.of(fileName));
    } catch (IOException e) {
      throw new IllegalArgumentException("An error occurred while reading the file: " + e.getMessage());
    }
  }

  public static void computeForEachLine(String fileName, Consumer<String> consumer) {
    Objects.requireNonNull(fileName);
    Objects.requireNonNull(consumer);
    try (Stream<String> lines = Files.lines(Path.of(fileName))) {
      lines.forEach(consumer);
    } catch (IOException e) {
      throw new IllegalArgumentException("An error occurred while reading the file: " + e.getMessage());
    }
  }
}
