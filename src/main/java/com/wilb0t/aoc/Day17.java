package com.wilb0t.aoc;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;

import static java.util.Comparator.comparing;

public class Day17 {

  public static class Step {
    public final int x;
    public final int y;

    public final String code;

    public Step(int x, int y, String code) {
      this.x = x;
      this.y = y;
      this.code = code;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Step step = (Step) o;
      return x == step.x &&
          y == step.y &&
          Objects.equals(code, step.code);
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y, code);
    }

    @Override
    public String toString() {
      return "Step{" +
          "x=" + x +
          ", y=" + y +
          ", code='" + code + '\'' +
          '}';
    }
  }

  public static boolean isOpen(char c) {
    return c == 'b'
        || c == 'c'
        || c == 'd'
        || c == 'e'
        || c == 'f';
  }

  public static Collection<Step> getNextSteps(Step s) {
    String hash = DigestUtils.md5Hex(s.code);
    List<Step> newSteps = new ArrayList<>();
    if (isOpen(hash.charAt(0)) && s.y > 0) {
      newSteps.add(new Step(s.x, s.y-1, s.code + 'U'));
    }
    if (isOpen(hash.charAt(1)) && s.y < 3) {
      newSteps.add(new Step(s.x, s.y+1, s.code + 'D'));
    }
    if (isOpen(hash.charAt(2)) && s.x > 0) {
      newSteps.add(new Step(s.x - 1, s.y, s.code + 'L'));
    }
    if (isOpen(hash.charAt(3)) && s.x < 3) {
      newSteps.add(new Step(s.x + 1, s.y, s.code + 'R'));
    }
    return newSteps;
  }

  private static boolean isFinalStep(Step s) {
    return s.x == 3 && s.y == 3;
  }

  public static Optional<String> getPath(String initCode) {
    Queue<Step> steps = new ArrayDeque<>();
    steps.add(new Step(0,0, initCode));

    while (!steps.isEmpty()) {
      Step s = steps.remove();
      if (isFinalStep(s)) {
        return Optional.of(s.code.substring(initCode.length()));
      } else {
        steps.addAll(getNextSteps(s));
      }
    }

    return Optional.empty();
  }

  public static Optional<String> getLongestPath(String initCode) {
    Queue<Step> steps = new ArrayDeque<>();
    steps.add(new Step(0,0, initCode));

    List<String> paths = new ArrayList<>();

    while (!steps.isEmpty()) {
      Step s = steps.remove();
      if (isFinalStep(s)) {
        paths.add(s.code.substring(initCode.length()));
      } else {
        steps.addAll(getNextSteps(s));
      }
    }

    return paths.stream()
        .sorted(comparing(String::length).reversed())
        .findFirst();
  }
}
