package com.wilb0t.aoc;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class Day16Test {

  @Test
  public void testProcess() {
    assertThat(Day16.process("1"), is("100"));
    assertThat(Day16.process("0"), is("001"));
    assertThat(Day16.process("11111"), is("11111000000"));
    assertThat(Day16.process("111100001010"), is("1111000010100101011110000"));
  }

  @Test
  public void testGenHash() {
    assertThat(Day16.genHash("110010110100"), is("110101"));
    assertThat(Day16.genHash("110101"), is("100"));
  }

  @Test
  public void testGenCheckSum_TestData() {
    assertThat(Day16.getCheckSum("10000", 20), is("01100"));
  }

  @Test
  public void testGenCheckSum_Part1() {
    assertThat(Day16.getCheckSum("11011110011011101", 272), is("00000100100001100"));
  }

  @Test
  public void testGenCheckSum_Part2() {
    assertThat(Day16.getCheckSum("11011110011011101", 35651584), is("00011010100010010"));
  }
}
