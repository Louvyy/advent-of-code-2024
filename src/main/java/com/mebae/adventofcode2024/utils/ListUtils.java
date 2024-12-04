package com.mebae.adventofcode2024.utils;

import java.util.List;

public final class ListUtils {
  public static <T> List<T> sortByAsc(List<T> list) {
    return list.stream().sorted().toList();
  }
}
