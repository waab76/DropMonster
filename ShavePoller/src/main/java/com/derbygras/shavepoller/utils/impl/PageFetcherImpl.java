package com.derbygras.shavepoller.utils.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.derbygras.shavepoller.utils.PageFetcher;

public class PageFetcherImpl implements PageFetcher {
    private final String inputUrl;

    public PageFetcherImpl(String inputUrl) {
        this.inputUrl = inputUrl;
    }

    @Override
    public String fetchPage() {
        StringBuffer outputBuffer = new StringBuffer();

        try {
            URL url = new URL(inputUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String line;
            while (null != (line = br.readLine())) {
                outputBuffer.append(line);
            }

            conn.disconnect();
        } catch (IOException ioex) {
            ioex.printStackTrace();
        }

        return outputBuffer.toString();
    }

    @Override
    public String getSiteUrl() {
        return inputUrl;
    }
}
