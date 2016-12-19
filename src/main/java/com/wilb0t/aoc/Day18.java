package com.wilb0t.aoc;

import java.util.Arrays;
import java.util.Objects;

public class Day18 {

  public static class BitString {
    private final int size;

    Long[] words = new Long[]{0L,0L};

    public BitString(int nbits) {
      this.size = nbits;
    }

    public void set(int i) {
      int widx = i / Long.SIZE;
      int bidx = i % Long.SIZE;

      words[widx] |= (1L << bidx);
    }

    public void clear(int i) {
      int widx = i / Long.SIZE;
      int bidx = i % Long.SIZE;

      words[widx] &= ~(1L << bidx);
    }

    public boolean get(int i) {
      int widx = i / Long.SIZE;
      int bidx = i % Long.SIZE;

      return (words[widx] & (1L << bidx)) != 0;
    }

    public int bitCount() {
      return Long.bitCount(words[0]) + Long.bitCount(words[1]);
    }

    public int size() {
      return this.size;
    }

    @Override
    public String toString() {
      return "AocBitSet["
          + Long.toBinaryString(words[1])
          + ", "
          + Long.toBinaryString(words[0])
          + "]";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      BitString bitString = (BitString) o;
      return size == bitString.size &&
          Arrays.equals(words, bitString.words);
    }

    @Override
    public int hashCode() {
      return Objects.hash(size, words);
    }
  }

  public static BitString toRow(String rowStr) {
    BitString bs = new BitString(rowStr.length());
    for(int i = 0; i < bs.size(); i++) {
      if (rowStr.charAt(rowStr.length() - 1 - i) == '^') {
        bs.set(i);
      }
    }
    return bs;
  }

  public static BitString nextRow(BitString row) {
    BitString nextRow = new BitString(row.size());
    for (int i = 0; i < row.size(); i++) {
      int b;
      if (i == 0) {
        b = ((row.get(i    )) ? 0b010 : 0b0)
          | ((row.get(i + 1)) ? 0b001 : 0b0);
      } else if (i == row.size() - 1) {
        b = ((row.get(i - 1)) ? 0b100 : 0b0)
          | ((row.get(i    )) ? 0b010 : 0b0);
      } else {
        b = ((row.get(i - 1)) ? 0b100 : 0b0)
          | ((row.get(i    )) ? 0b010 : 0b0)
          | ((row.get(i + 1)) ? 0b001 : 0b0);
      }
      if ((b == 6 || b == 4 || b == 3 || b == 1)) {
        nextRow.set(i);
      }
    }
    return nextRow;
  }

  public static int getSafeTileCount(BitString initRow, int numRows) {
    BitString row = initRow;
    int count = 0;
    for (int i = 0; i < numRows; i++) {
      count += (row.size() - row.bitCount());
      row = nextRow(row);
    }
    return count;
  }
}
