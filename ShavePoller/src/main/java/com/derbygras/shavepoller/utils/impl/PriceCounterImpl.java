package com.derbygras.shavepoller.utils.impl;

import com.derbygras.shavepoller.utils.PriceCounter;

public class PriceCounterImpl extends GenericPatternCounter implements PriceCounter {

    public PriceCounterImpl(String priceRegex) {
        super(priceRegex);
    }

    @Override
    public int getPriceCount(String inputText) {
        return countPatternInstances(inputText);
    }

}
