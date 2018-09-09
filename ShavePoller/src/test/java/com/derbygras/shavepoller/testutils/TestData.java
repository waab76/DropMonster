package com.derbygras.shavepoller.testutils;

import java.io.IOException;
import java.net.URL;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class TestData {

    public static String loadTestData(String htmlFile) {
        URL url = Resources.getResource(htmlFile);
        String text;
        try {
            text = Resources.toString(url, Charsets.UTF_8);
        } catch (IOException ioex) {
            text = null;
        }

        return text;
    }
}
