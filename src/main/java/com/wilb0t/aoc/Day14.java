package com.wilb0t.aoc;

import com.google.common.base.Throwables;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

public class Day14 {
  private final String salt;

  public Day14(String salt) {
    this.salt = salt;
  }

  protected String gethash(int idx) {
    String idxStr = salt + Integer.toString(idx);
    return DigestUtils.md5Hex(idxStr.getBytes());
  }

  protected String getStretchedHash(int idx) {
    String hash = DigestUtils.md5Hex(salt + Integer.toString(idx));
    for (int i = 0; i < 2016; i++) {
      hash = DigestUtils.md5Hex(hash);
    }
    return hash;
  }

  protected Optional<Character> hasTriple(String hash) {
    for (int idx = 0; idx <= (hash.length() - 3); idx++) {
      char c = hash.charAt(idx);
      if (hash.charAt(idx + 1) == c && hash.charAt(idx + 2) == c) {
        return Optional.of(c);
      }
    }
    return Optional.empty();
  }

  protected boolean hasQuint(char c, String hash) {
    for (int idx = 0; idx <= (hash.length() - 5); idx++) {
      if (hash.charAt(idx) == c
          && hash.charAt(idx + 1) == c
          && hash.charAt(idx + 2) == c
          && hash.charAt(idx + 3) == c
          && hash.charAt(idx + 4) == c) {
       return true;
      }
    }
    return false;
  }

  public List<Map.Entry<Integer,String>> getPads(int startIdx, int size) throws ExecutionException {
    return getPads(this::gethash, startIdx, size);
  }

  public List<Map.Entry<Integer,String>> getPads(Function<Integer,String> hashFn, int startIdx, int size) throws ExecutionException {
    LoadingCache<Integer, String> hashCache = CacheBuilder.newBuilder()
        .build(
            new CacheLoader<Integer, String>() {
              @Override
              public String load(Integer idx) throws Exception {
                return hashFn.apply(idx);
              }
            }
        );

    List<Map.Entry<Integer,String>> pads = new ArrayList<>();

    for (int idx = startIdx; pads.size() < size; idx++) {
      int lidx = idx;
      String hash = hashFn.apply(lidx);
      hasTriple(hash).ifPresent(
          c -> {
            for (int qidx = lidx + 1; qidx < lidx + 1000; qidx++) {
              try {
                String qhash = hashCache.get(qidx);
                if (hasQuint(c, qhash)) {
                  pads.add(new AbstractMap.SimpleEntry<>(lidx, hash));
                }
              } catch (Exception e) {
                throw Throwables.propagate(e);
              }
            }
          }
      );
    }

    return pads;
  }
}
