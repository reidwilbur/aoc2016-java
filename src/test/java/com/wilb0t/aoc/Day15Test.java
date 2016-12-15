package com.wilb0t.aoc;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import com.wilb0t.aoc.Day15.Disc;

import java.util.List;
import java.util.function.Function;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Day15Test {

  List<Function<Integer,Boolean>> testInput = ImmutableList.of(
      new Disc(1, 5, 4),
      new Disc(2, 2, 1)
  );

  List<Function<Integer,Boolean>> part1Input = ImmutableList.of(
      new Disc(1, 17, 15),
      new Disc(2, 3, 2),
      new Disc(3, 19, 4),
      new Disc(4, 13, 2),
      new Disc(5, 7, 2),
      new Disc(6, 5, 0)
  );

  List<Function<Integer,Boolean>> part2Input = ImmutableList.<Function<Integer,Boolean>>builder()
      .addAll(part1Input)
      .add(new Disc(7, 11, 0))
      .build();

  @Test
  public void testGetFirstAlignedStem_TestInput() {
    assertThat(Day15.getFirstAlignedTimeStep(testInput, 0), is(5));
  }

  @Test
  public void testGetFirstAlignedStem_Part1Input() {
    assertThat(Day15.getFirstAlignedTimeStep(part1Input, 0), is(400589));
  }

  @Test
  public void testGetFirstAlignedStem_Part2Input() {
    assertThat(Day15.getFirstAlignedTimeStep(part2Input, 0), is(3045959));
  }
}
