package com.andreamazzarella;

import org.junit.Test;
import static org.junit.Assert.*;

public class WrapperTest {

    @Test
    public void doesNotWrapEmptyInput() {
        Wrapper testWrapper = new Wrapper(0);
        assertEquals("", testWrapper.wrap(""));
    }

    @Test
    public void doseNotWrapWhithInputIsShorterThanColumn() {
        Wrapper testWrapper = new Wrapper(10);
        assertEquals("Ciao", testWrapper.wrap("Ciao"));
    }

    @Test
    public void wrapsWithInputLongerThanColumn() {
        Wrapper testWrapper = new Wrapper(6);
        assertEquals("Hey,\nthere!", testWrapper.wrap("Hey, there!"));
    }

    @Test
    public void wrapsThreeWords() {
        Wrapper testWrapper = new Wrapper(6);
        assertEquals("word\nword\nword", testWrapper.wrap("word word word"));
    }

    @Test
    public void wrapsLineWithMultipleWords() {
        Wrapper testWrapper = new Wrapper(10);
        assertEquals("hey word\nword word", testWrapper.wrap("hey word word word"));
    }
}
