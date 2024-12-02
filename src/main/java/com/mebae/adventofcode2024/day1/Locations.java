package com.mebae.adventofcode2024.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Locations {
  private final List<Integer> group1List;
  private final List<Integer> group2List;
  private int totalDistance = -1;
  private int similarityScore = -1;

  private Locations(List<Integer> group1List, List<Integer> group2List) {
    this.group1List = group1List;
    this.group2List = group2List;
  }

  public static Locations of(String fileName) {
    Objects.requireNonNull(fileName);
    var locations1 = new ArrayList<Integer>();
    var locations2 = new ArrayList<Integer>();

    readLocationsFile(fileName, locations1, locations2);

    return new Locations(sortByAsc(locations1), sortByAsc(locations2));
  }

  private static void readLocationsFile(String fileName, ArrayList<Integer> locations1, ArrayList<Integer> locations2) {
    try (Stream<String> lines = Files.lines(Path.of(fileName))) {
      lines.forEach(line -> {
        var locations = line.split("\\s+");
        locations1.add(Integer.parseInt(locations[0]));
        locations2.add(Integer.parseInt(locations[1]));
      });
    } catch (IOException e) {
      throw new IllegalArgumentException("An error occurred while reading the file: " + e.getMessage());
    }
  }

  private static List<Integer> sortByAsc(List<Integer> list) {
    return list.stream().sorted().toList();
  }

  public int computeTotalDistance() {
    if (totalDistance == -1) {
      totalDistance = 0;
      for (var i = 0; i < group1List.size(); i++) {
        totalDistance += Math.abs(group1List.get(i) - group2List.get(i));
      }
    }
    return totalDistance;
  }

  public int computeSimilarityScore() {
    if (similarityScore == -1) {
      similarityScore = 0;
      var locations2OccurrencesMap = group2List.stream().collect(Collectors.toMap(location -> location, location -> location, Integer::sum));
      for (var location : group1List) {
        similarityScore += locations2OccurrencesMap.getOrDefault(location, 0);
      }
    }
    return similarityScore;
  }
}
