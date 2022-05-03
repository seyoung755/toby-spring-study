package com.toby.suntory.learningtest.template;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class CalcSumTest {
    Calculator calculator;
    String path;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
        path = getClass().getResource("/numbers.txt").getPath();
    }

    @Test
    void sumOfNumbers() throws IOException {
        assertThat(calculator.calcSum(path)).isEqualTo(10);
    }

    @Test
    void multiplyOfNumbers() throws IOException {
        assertThat(calculator.calcMultiply(path)).isEqualTo(24);
    }

    @Test
    void concatenateStrings() throws IOException {
        assertThat(calculator.concatenate(path)).isEqualTo("1234");
    }
}
