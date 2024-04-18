package com.vodafone.v2x.android.hellov2xworld.utils;

import java.util.Map;
import java.util.Objects;

public class JavaMapUtils {
    /**
     Returns the key for the given value in the specified map.
     @param <T> the type of the keys in the map
     @param <E> the type of the values in the map
     @param map the map to search for the key
     @param value the value for which to find the key
     @return the key for the given value, or {@code null} if not found
     */
    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}
