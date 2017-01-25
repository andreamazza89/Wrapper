package com.andreamazzarella;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

public class Wrapper {

    private final int columnWidth;

    public Wrapper(int columnWidth) {
        this.columnWidth = columnWidth;
    }

    public String wrap(String input) {
        String output = "";
        String[] words = input.split("\\s");

        List<String> lines = Arrays.stream(words)
                                   .collect(Wrapper::accumulator, addWordToCurrentOrNextLine(), combineLines());

        for (String line : lines) {
            output += line + "\n";
        }

        return output.trim();
    }

    private BiConsumer<List<String>, List<String>> combineLines() {
        return (some_lines, other_lines) -> some_lines.addAll(other_lines);
    }

    private BiConsumer<List<String>, String> addWordToCurrentOrNextLine() {
        return (accumulator, word) -> {
            String last_chunk = accumulator.get(accumulator.size() - 1);
            if (doesNotExceedColumnWidth(word, last_chunk)) {
                accumulator.set(accumulator.size() - 1, last_chunk + " " + word);
            } else {
                accumulator.add(word);
            }
        };
    }

    private boolean doesNotExceedColumnWidth(String word, String last_chunk) {
        return last_chunk.length() + word.length() <= columnWidth;
    }

    private static List<String> accumulator() {
        List<String> accumulator = new ArrayList<>();
        accumulator.add("");
        return accumulator;
    }
}