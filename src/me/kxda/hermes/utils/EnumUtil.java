package me.kxda.hermes.utils;

import java.util.Random;

public class EnumUtil {

    public static <T> T getRandom(T[] array) {
        return array[new Random().nextInt(array.length)];
    }
}

