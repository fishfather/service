package com.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
        createStream();

        streamToOtherDataStructure();

        peekStream();

        java8Optional("test");

        Map<String, Integer > map;
//        map.computeIfAbsent()
    }

    private static void java8Optional(String text) {
        // Java 8
        Optional.ofNullable(text).ifPresent(System.out::println);
        // Pre-Java 8
        if (text != null) {
            System.out.println(text);
        }
    }

    private static void peekStream() {
        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
    }

    private static void streamToOtherDataStructure() {
        Stream<String> stream = Stream.of("a", "b", "c");
        // 1. Array
        String[] strArray1 = stream.toArray(String[]::new);
        // 2. Collection
        stream = Stream.of("a", "b", "c");
        List<String> list1 = stream.collect(Collectors.toList());

        stream = Stream.of("a", "b", "c");
        List<String> list2 = stream.collect(Collectors.toCollection(ArrayList::new));

        stream = Stream.of("a", "b", "c");
        Set set1 = stream.collect(Collectors.toSet());

        stream = Stream.of("a", "b", "c");
        Stack stack1 = stream.collect(Collectors.toCollection(Stack::new));

        // 3. String
        stream = Stream.of("a", "b", "c");
        String str = stream.collect(Collectors.joining()).toString();

    }

    private static void createStream() {
        // 1. Individual values
        Stream stream = Stream.of("a", "b", "c");
        // 2. Arrays
        String [] strArray = new String[] {"a", "b", "c"};
        stream = Stream.of(strArray);
        stream = Arrays.stream(strArray);
        // 3. Collections
        List<String> list = Arrays.asList(strArray);
        stream = list.stream();

        stream.forEach(System.out::print);
        System.out.println();

        IntStream.of(new int[]{1, 2, 3}).forEach(System.out::print);
        System.out.println();

        IntStream.range(1, 3).forEach(System.out::print);
        System.out.println();

        IntStream.rangeClosed(1, 3).forEach(System.out::print);
        separator();
    }

    private static void separator() {
        System.out.println();
        System.out.println("-----------------------");
    }


}
