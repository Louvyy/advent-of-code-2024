package com.mebae.adventofcode2024.day1;

import com.mebae.adventofcode2024.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.mebae.adventofcode2024.utils.ListUtils.sortByAsc;
import static com.mebae.adventofcode2024.utils.constants.RegexConstants.WHITESPACE_SPLITTER;

public class Locations {
  private final List<Integer> group1List;
  private final List<Integer> group2List;

  private Integer totalDistance = null;
  private Integer similarityScore = null;

  private Locations(List<Integer> group1List, List<Integer> group2List) {
    this.group1List = group1List;
    this.group2List = group2List;
  }

  public static Locations of(String fileName) {
    Objects.requireNonNull(fileName);

    var locations1 = new ArrayList<Integer>();
    var locations2 = new ArrayList<Integer>();

    Consumer<String> fillLocations = line -> {
      var locations = line.split(WHITESPACE_SPLITTER);
      locations1.add(Integer.parseInt(locations[0]));
      locations2.add(Integer.parseInt(locations[1]));
    };

    FileUtils.computeForEachLine(fileName, fillLocations);

    return new Locations(sortByAsc(locations1), sortByAsc(locations2));
  }

  public int computeTotalDistance() {
    if (totalDistance == null) {
      totalDistance = 0;
      for (var i = 0; i < group1List.size(); i++) {
        totalDistance += Math.abs(group1List.get(i) - group2List.get(i));
      }
    }
    return totalDistance;
  }

  public int computeSimilarityScore() {
    if (similarityScore == null) {
      similarityScore = 0;
      var locations2OccurrencesMap = group2List.stream()
        .collect(Collectors.toMap(location -> location, location -> location, Integer::sum));
      for (var location : group1List) {
        similarityScore += locations2OccurrencesMap.getOrDefault(location, 0);
      }
    }
    return similarityScore;
  }
}
