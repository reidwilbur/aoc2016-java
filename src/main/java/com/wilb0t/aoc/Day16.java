package com.wilb0t.aoc;

public class Day16 {

  public static String genBits(String initBits, int bitsLen) {
    StringBuilder data = new StringBuilder(bitsLen);
    data.append(initBits);

    while (data.length() < bitsLen) {
      int curLen = data.length();
      data.append('0');
      for (int idx = curLen - 1; (idx >= 0) && (data.length() < bitsLen); idx--) {
        data.append((data.charAt(idx) == '1') ? '0' : '1');
      }
    }

    return data.toString();
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
    String data = genBits(initBits, dataLen);

    String hash = genHash(data);
    while (hash.length() % 2 == 0) {
      hash = genHash(hash);
    }

    return hash;
  }
}
