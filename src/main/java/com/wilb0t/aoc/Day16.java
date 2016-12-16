package com.wilb0t.aoc;

public class Day16 {

  public static String process(String bits) {
    StringBuilder nbits = new StringBuilder((bits.length()*2)+1);
    nbits.append(bits);
    nbits.append('0');
    for (int idx = bits.length() - 1; idx >= 0; idx--) {
      nbits.append((bits.charAt(idx) == '1') ? '0' : '1');
    }
    return nbits.toString();
  }

  public static String genHash(String bits) {
    StringBuilder hbits = new StringBuilder(bits.length()/2);
    for (int idx = 0; idx*2 + 1 < bits.length(); idx++) {
      if (bits.charAt(idx*2) == bits.charAt(idx*2 + 1)) {
        hbits.append(1);
      } else {
        hbits.append(0);
      }
    }
    return hbits.toString();
  }

  public static String getCheckSum(String initBits, int dataLen) {
    String data = initBits;
    while (data.length() < dataLen) {
      data = process(data);
    }
    data = data.substring(0, dataLen);

    String hash = genHash(data);
    while (hash.length() % 2 == 0) {
      hash = genHash(hash);
    }

    return hash;
  }
}
