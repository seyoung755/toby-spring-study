package com.toby.suntory.learningtest.template;

import java.io.BufferedReader;
import java.io.IOException;

public interface BufferedReaderCallback {
    int doSomeThingWithReader(BufferedReader br) throws IOException;
}
