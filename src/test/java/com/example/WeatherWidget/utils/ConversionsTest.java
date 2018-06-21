package com.example.WeatherWidget.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConversionsTest {

    @Test
    public void convertDegreesToDirection() {
        assertEquals("N", Conversions.convertDegreesToDirection("0"));
        assertEquals("E", Conversions.convertDegreesToDirection("90"));
        assertEquals("N", Conversions.convertDegreesToDirection("370"));
    }

    @Test
    public void convertMphToMs() {
        assertEquals(Double.valueOf(0.0), Conversions.convertMphToMs("0"));
        assertEquals(Double.valueOf(44.7), Conversions.convertMphToMs("100"));
    }
}