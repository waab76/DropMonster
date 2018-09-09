package com.derbygras.shavepoller.utils.impl;

import com.derbygras.shavepoller.utils.SoldOutCounter;

public class SoldOutCounterImpl extends GenericPatternCounter implements SoldOutCounter {

    public SoldOutCounterImpl(String soldOutRegex) {
        super(soldOutRegex);
    }

    @Override
    public int getSoldOutCount(String inputText) {
        return countPatternInstances(inputText);
    }
}
