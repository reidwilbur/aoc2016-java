package com.wilb0t.aoc;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

public class Day15 {
  public static class Disc implements Function<Integer,Boolean> {

    private final int vpos;
    private final int size;
    private final int startpos;

    public Disc(int vpos, int size, int startPos) {
      this.vpos = vpos;
      this.size = size;
      this.startpos = startPos;
    }

    @Override
    public Boolean apply(Integer timeStep) {
      return ((timeStep + vpos) + startpos) % size == 0;
    }
  }

  public static int getFirstAlignedTimeStep(List<Function<Integer,Boolean>> discs, int startTime) {
    return IntStream.iterate(startTime, i -> i + 1)
        .filter(
            timeStep ->
                discs.stream().map(d -> d.apply(timeStep)).allMatch(r -> r)
        )
        .findFirst()
        .getAsInt();
  }
}
