package com.wilb0t.aoc;

import org.junit.Before;
import org.junit.Test;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Day14Test {

  private Day14 testInst;

  @Before
  public void setup() {
    testInst = new Day14("abc");
  }

  @Test
  public void testGetHash() {
    assertThat(testInst.gethash(18), is("0034e0923cc38887a57bd7b1d4f953df"));
    assertThat(testInst.gethash(72), is("a213738a53f0dcf7b3033986a9904e11"));
    assertThat(testInst.gethash(792), is("d3037333338c0e36482facef46a5d375"));
  }

  @Test
  public void testHasTriple() {
    assertThat(testInst.hasTriple(""), is(Optional.empty()));
    assertThat(testInst.hasTriple("aa"), is(Optional.empty()));
    assertThat(testInst.hasTriple("aaa"), is(Optional.of('a')));
    assertThat(testInst.hasTriple("faaabcccd"), is(Optional.of('a')));
    assertThat(testInst.hasTriple("a213738a53f0dcf7b3033986a9904e11"), is(Optional.empty()));
  }

  @Test
  public void hasQuint() {
    assertThat(testInst.hasQuint('a', ""), is(false));
    assertThat(testInst.hasQuint('a', "aa"), is(false));
    assertThat(testInst.hasQuint('a', "aaaa"), is(false));
    assertThat(testInst.hasQuint('a', "faaaab"), is(false));
    assertThat(testInst.hasQuint('a', "faaaaab"), is(true));
    assertThat(testInst.hasQuint('a', "faaaaa"), is(true));
  }

  @Test
  public void testGetPads() throws ExecutionException {
    List<Map.Entry<Integer,String>> pads = testInst.getPads(0, 64);
    assertThat(pads.get(63), is(new AbstractMap.SimpleEntry<>(22728, "26ccc731a8706e0c4f979aeb341871f0")));
  }

  @Test
  public void testGetPadsPart1() throws ExecutionException {
    Day14 day14 = new Day14("yjdafjpo");
    List<Map.Entry<Integer,String>> pads = day14.getPads(0, 64);
    assertThat(pads.get(63), is(new AbstractMap.SimpleEntry<>(25427, "d95b61890f0df9add71f1427ddd7bf54")));
  }

  @Test
  public void testGetPadsPart2() throws ExecutionException {
    Day14 day14 = new Day14("yjdafjpo");
    List<Map.Entry<Integer,String>> pads = day14.getPads(day14::getStretchedHash,0, 64);
    assertThat(pads.get(63), is(new AbstractMap.SimpleEntry<>(22045, "e30f959d84b3d585bae8acae233358f9")));
  }
}
