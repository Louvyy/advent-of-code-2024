package com.mebae.adventofcode2024.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Reports {

  private final List<Report> reports;

  private Reports(List<Report> reports) {
    this.reports = reports;
  }

  public static Reports of(String fileName) {
    Objects.requireNonNull(fileName);
    return readReportsFile(fileName);
  }

  private static Reports readReportsFile(String fileName) {
    try (Stream<String> lines = Files.lines(Path.of(fileName))) {
      var reports = new ArrayList<Report>();
      lines.forEach(line -> {
        var levels = line.split("\\s+");
        reports.add(new Report(Arrays.stream(levels).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList())));
      });
      return new Reports(reports);
    } catch (IOException e) {
      throw new IllegalArgumentException("An error occurred while reading the file: " + e.getMessage());
    }
  }

  /**
   * A report is safe if :
   * - The levels are either all strictly increasing or all strictly decreasing.
   * - Any two adjacent levels differ by at least one and at most three.
   * @return the number of safe reports
   */
  public long countSafeReports() {
    return reports.stream()
      .map(Report::isSafeReport)
      .filter(isSafe -> isSafe)
      .count();
  }

  private static class Report {
    List<Integer> levels;

    private Report(List<Integer> levels) {
      this.levels = levels;
    }

    /**
     * A report is safe if :
     * - The levels are either all strictly increasing or all strictly decreasing.
     * - Any two adjacent levels differ by at least one and at most three.
     * @return true if the report is safe
     */
    private boolean isSafeReport() {
      if (levels.size() < 2) {
        return true;
      }
      var isIncreasingList = levels.get(0) < levels.get(1);
      return IntStream.range(0, levels.size() - 1)
        .allMatch(i -> {
          var currentLevel = levels.get(i);
          var nextLevel = levels.get(i + 1);
          return isIncreasingList
            ? nextLevel >= currentLevel + 1 && nextLevel <= currentLevel + 3
            : nextLevel <= currentLevel - 1 && nextLevel >= currentLevel - 3;
        });
    }
  }
}
