package com.wilb0t.aoc;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class Day16Test {

  @Test
  public void testGenBits() {
    assertThat(Day16.genBits("1", 3), is("100"));
    assertThat(Day16.genBits("0", 3), is("001"));
    assertThat(Day16.genBits("11111", 11), is("11111000000"));
    assertThat(Day16.genBits("111100001010", 25), is("1111000010100101011110000"));
  }

  @Test
  public void testGenCheckSum() {
    assertThat(Day16.genCheckSum("110010110100"), is("100"));
    assertThat(Day16.genCheckSum("10000011110010000111"), is("01100"));
  }

  @Test
  public void testGenBitsAndCheckSum_TestData() {
    assertThat(Day16.genBitsAndCheckSum("10000", 20), is("01100"));
  }

  @Test
  public void testGenBitsAndCheckSum_Part1() {
    assertThat(Day16.genBitsAndCheckSum("11011110011011101", 272), is("00000100100001100"));
  }

  @Test
  public void testGenBitsAndCheckSum_Part2() {
    assertThat(Day16.genBitsAndCheckSum("11011110011011101", 35651584), is("00011010100010010"));
  }
}
