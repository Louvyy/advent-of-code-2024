package com.mebae.adventofcode2024.day2;

import com.mebae.adventofcode2024.utils.FileUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.mebae.adventofcode2024.utils.NumberUtils.isStrictlyOrdered;
import static com.mebae.adventofcode2024.utils.constants.RegexConstants.WHITESPACE_SPLITTER;

public class Reports {

  private final List<Report> reports;

  private Reports(List<Report> reports) {
    this.reports = reports;
  }

  public static Reports of(String fileName) {
    Objects.requireNonNull(fileName);

    var reports = new ArrayList<Report>();

    Consumer<String> fillReports = line -> {
      var levels = line.split(WHITESPACE_SPLITTER);
      reports.add(new Report(Arrays.stream(levels).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList())));
    };

    FileUtils.computeForEachLine(fileName, fillReports);

    return new Reports(reports);
  }

  /**
   * A report is safe if :
   * - The levels are either all strictly increasing or all strictly decreasing.
   * - Any two adjacent levels differ by at least one and at most three.
   *
   * @return the number of safe reports
   */
  public long countSafe() {
    return reports.stream().map(Report::isSafe).filter(isSafe -> isSafe).count();
  }

  /**
   * A report is Problem Dampener safe if removing a single level from an unsafe report would make it safe.
   *
   * @return the number of Problem Dampener safe reports
   */
  public long countProblemDampenerSafe() {
    return reports.stream().map(Report::isProblemDampenerSafe).filter(isSafe -> isSafe).count();
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
     *
     * @return true if the report is safe
     */
    private static boolean isSafe(List<Integer> levels) {
      if (levels.size() < 2) {
        return true;
      }
      var isIncreasingList = levels.get(0) < levels.get(1);
      return IntStream.range(0, levels.size() - 1).allMatch(i -> {
        var currentLevel = levels.get(i);
        var nextLevel = levels.get(i + 1);
        return isStrictlyOrdered(isIncreasingList, currentLevel, nextLevel)
          && Math.abs(nextLevel - currentLevel) >= 1
          && Math.abs(nextLevel - currentLevel) <= 3;
      });
    }

    private boolean isSafe() {
      return isSafe(levels);
    }

    /**
     * A report is Problem Dampener safe if removing a single level from an unsafe report would make it safe.
     *
     * @return true if the report is Problem Dampener safe
     */
    private boolean isProblemDampenerSafe() {
      if (isSafe()) {
        return true;
      }

      return IntStream.range(0, levels.size()).anyMatch(i -> {
        var dampenerSafeLevels = new ArrayList<>(levels);
        dampenerSafeLevels.remove(i);
        return isSafe(dampenerSafeLevels);
      });
    }
  }
}
