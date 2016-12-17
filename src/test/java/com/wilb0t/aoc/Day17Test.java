package com.wilb0t.aoc;

import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.contains;

import static com.wilb0t.aoc.Day17.Step;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class Day17Test {

  @Test
  public void testGetNextSteps() {
    assertThat(
        Day17.getNextSteps(new Step(0,0,"hijkl")),
        contains(
            new Step(0, 1, "hijklD")
        )
    );
    assertThat(
        Day17.getNextSteps(new Step(0,1,"hijklD")),
        containsInAnyOrder(
            new Step(0,0,"hijklDU"),
            new Step(1,1, "hijklDR"))
    );
    assertThat(
        Day17.getNextSteps(new Step(0,0,"hijklDU")),
        contains(
            new Step(1,0,"hijklDUR")
        )
    );
  }

  @Test
  public void testGetPath_TestInput() {
    assertThat(
        Day17.getPath("hijkl"),
        is(Optional.empty())
    );
    assertThat(
        Day17.getPath("ihgpwlah"),
        is(Optional.of("DDRRRD"))
    );
    assertThat(
        Day17.getPath("kglvqrro"),
        is(Optional.of("DDUDRLRRUDRD"))
    );
    assertThat(
        Day17.getPath("ulqzkmiv"),
        is(Optional.of("DRURDRUDDLLDLUURRDULRLDUUDDDRR"))
    );
  }

  @Test
  public void testGetPath_Part1Input() {
    assertThat(
        Day17.getPath("ioramepc"),
        is(Optional.of("RDDRULDDRR"))
    );
  }

  @Test
  public void testGetLongestPath_TestInput() {
    assertThat(
        Day17.getLongestPath("ihgpwlah").get().length(),
        is(370)
    );
    assertThat(
        Day17.getLongestPath("kglvqrro").get().length(),
        is(492)
    );
    assertThat(
        Day17.getLongestPath("ulqzkmiv").get().length(),
        is(830)
    );
  }

  @Test
  public void testGetLongestPath_Part1Input() {
    assertThat(
        Day17.getLongestPath("ioramepc").get().length(),
        is(766)
    );
  }
}
