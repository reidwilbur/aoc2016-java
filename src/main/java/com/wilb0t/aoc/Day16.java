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

  public static String genCheckSum(String bits) {
    int csize = 2;
    while ((bits.length() / csize) % 2 == 0) {
      csize *= 2;
    }

    StringBuilder cksum = new StringBuilder();
    int chunks = bits.length() / csize;
    for (int chunk = 0; chunk < chunks; chunk++) {
      int onecnt = 0;
      for (int idx = (chunk*csize); idx < ((chunk+1)*csize); idx++) {
        if (bits.charAt(idx) == '1') {
          onecnt += 1;
        }
      }
      cksum.append((onecnt % 2 == 0) ? '1' : '0');
    }
    return cksum.toString();
  }

  public static String genBitsAndCheckSum(String initBits, int dataLen) {
    String data = genBits(initBits, dataLen);

    return genCheckSum(data);
  }
}

