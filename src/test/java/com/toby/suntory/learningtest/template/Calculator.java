package com.toby.suntory.learningtest.template;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {

    public int fileReadTemplate(String path, BufferedReaderCallback callback) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(path));
            int ret = callback.doSomeThingWithReader(br);
            return ret;
        } catch (IOException e) {
            throw e;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public <T> T lineReadTemplate(String path, LineCallback<T> callback, T initVal) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(path));
            T res = initVal;
            String line = null;
            while ((line = br.readLine()) != null) {
                res = callback.doSomethingWithLine(line, res);
            }
            return res;
        } catch (IOException e) {
            throw e;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int calcSum(String path) throws IOException {
        LineCallback<Integer> sumCallback =
                new LineCallback<>() {
                    @Override
                    public Integer doSomethingWithLine(String line, Integer value) {
                        return Integer.parseInt(line) + value;
                    }
                };
        return lineReadTemplate(path, sumCallback, 0);
    }

    public int calcMultiply(String path) throws IOException {
        LineCallback<Integer> multiplyCallback =
                new LineCallback<>() {
                    @Override
                    public Integer doSomethingWithLine(String line, Integer value) {
                        return Integer.parseInt(line) * value;
                    }
                };
        return lineReadTemplate(path, multiplyCallback, 1);
    }

    public String concatenate(String path) throws IOException {
        LineCallback<String> concatenateCallback =
                new LineCallback<>() {
                    @Override
                    public String doSomethingWithLine(String line, String value) {
                        return value + line;
                    }
                };
        return lineReadTemplate(path, concatenateCallback, "");
    }
}
