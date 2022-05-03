package com.toby.suntory.learningtest.template;

public interface LineCallback<T> {
    T doSomethingWithLine(String line, T value);
}
