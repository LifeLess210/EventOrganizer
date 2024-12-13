package org.example.Utils;

import java.util.Optional;

public class Utils {
    public static <T> Optional<T> isEmpty(T object) {
        return Optional.ofNullable(object);
    }
}
