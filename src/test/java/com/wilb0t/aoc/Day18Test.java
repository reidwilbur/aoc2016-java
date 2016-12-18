package com.wilb0t.aoc;

import org.junit.Test;

import java.io.IOException;

import static com.wilb0t.aoc.Day18.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class Day18Test {

  String input = ".^^..^...^..^^.^^^.^^^.^^^^^^.^.^^^^.^^.^^^^^^.^...^......^...^^^..^^^.....^^^^^^^^^....^^...^^^^..^";

  @Test
  public void testNextRow() {
    assertThat(nextRow("..^^."), is(".^^^^"));
    assertThat(nextRow(".^^^^"), is("^^..^"));
  }

  @Test
  public void testGetSafeTileCount_TestInput() {
    assertThat(getSafeTileCount(".^^.^.^^^^", 10), is(38));
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
