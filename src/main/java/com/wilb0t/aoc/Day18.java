package com.wilb0t.aoc;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day18 {

  public static String nextRow(String row) {
    String expRow = '.' + row + '.';
    int safecount = 0;
    return IntStream.range(1, row.length()+1)
        .map(i ->
            ((expRow.charAt(i-1) == '^') ? 0b100 : 0b0)
          | ((expRow.charAt(i  ) == '^') ? 0b010 : 0b0)
          | ((expRow.charAt(i+1) == '^') ? 0b001 : 0b0)
        )
        .mapToObj(m ->
          (m == 6 || m == 4 || m == 3 || m == 1) ? "^" : "."
        )
        .collect(Collectors.joining());
  }

  public static int safeTileCount(String row) {
    int count = 0;
    for (int i = 0; i < row.length(); i++) {
      if (row.charAt(i) == '.') {
        count += 1;
      }
    }
    return count;
  }

  public static int getSafeTileCount(String initRow, int numRows) {
    String lastRow = initRow;
    int count = safeTileCount(initRow);
    for (int i = 1; i < numRows; i++) {
      lastRow = nextRow(lastRow);
      count += safeTileCount(lastRow);
    }
    return count;
  }
}
