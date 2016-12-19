package com.wilb0t.aoc;

import org.junit.Test;

import java.io.IOException;

import static com.wilb0t.aoc.Day18.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class Day18Test {

  BitString input = toRow(".^^..^...^..^^.^^^.^^^.^^^^^^.^.^^^^.^^.^^^^^^.^...^......^...^^^..^^^.....^^^^^^^^^....^^...^^^^..^");

  @Test
  public void testNextRow() {
    assertThat(nextRow(toRow("..^^.")), is(toRow(".^^^^")));
    assertThat(nextRow(toRow(".^^^^")), is(toRow("^^..^")));
  }

  @Test
  public void testGetSafeTileCount_TestInput() {
    assertThat(getSafeTileCount(toRow(".^^.^.^^^^"), 10), is(38));
  }

  @Test
  public void testGetSafeTileCount_Part1Input() {
    assertThat(getSafeTileCount(input, 40), is(2005));
  }

  @Test
  public void testGetSafeTileCount_Part2Input() throws IOException {
    assertThat(getSafeTileCount(input, 400000), is(20008491));
  }
}
