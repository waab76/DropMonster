package com.derbygras.shavepoller.utils.impl;

import static com.derbygras.shavepoller.testutils.TestData.loadTestData;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GenericPatternCounterImplTest {

    // Code Under Test
    GenericPatternCounter cut;

    // Test objects
    String inputText;
    int count;

    // SetUp and TearDown
    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {

    }

    // Test Cases
    @Test
    public void whenCountPatternInstancesIsCalled_returnsCorrectCount_forPricePattern_andNoPrices() {
        givenUniversalPriceCounter();
        givenInputWithNoPrices();

        whenCountPatternInstancesIsCalled();

        thenCountIs(0);
    }

    @Test
    public void whenCountPatternInstancesIsCalled_returnsCorrectCount_forPricePattern_andBufPage() {
        givenUniversalPriceCounter();
        givenBuffleheadPage();

        whenCountPatternInstancesIsCalled();

        thenCountIs(3);
    }

    @Test
    public void whenCountPatternInstancesIsCalled_returnsCorrectCount_forBufSoldOutPattern_andBufPage() {
        givenBuffleheadSoldOutCounter();
        givenBuffleheadPage();

        whenCountPatternInstancesIsCalled();

        thenCountIs(3);
    }

    @Test
    public void whenCountPatternInstancesIsCalled_returnsCorrectCount_forPricePattern_andDGPage() {
        givenUniversalPriceCounter();
        givenDeclarationPage();

        whenCountPatternInstancesIsCalled();

        thenCountIs(15);
    }

    @Test
    public void whenCountPatternInstancesIsCalled_returnsCorrectCount_forDGSoldOutPattern_andDGPage() {
        givenDeclarationSoldOutCounter();
        givenDeclarationPage();

        whenCountPatternInstancesIsCalled();

        thenCountIs(14);
    }

    @Test
    public void whenCountPatternInstancesIsCalled_returnsCorrectCount_forBufSoldOutPattern_andDGPage() {
        givenBuffleheadSoldOutCounter();
        givenDeclarationPage();

        whenCountPatternInstancesIsCalled();

        thenCountIs(0);
    }

    @Test
    public void whenCountPatternInstancesIsCalled_returnsCorrectCount_forDGSoldOutPattern_andBufPage() {
        givenDeclarationSoldOutCounter();
        givenBuffleheadPage();

        whenCountPatternInstancesIsCalled();

        thenCountIs(0);
    }

    // GIVENs
    private void givenUniversalPriceCounter() {
        cut = new GenericPatternCounter("\\d?\\d\\d\\.\\d\\d[< uU]");
    }

    private void givenBuffleheadSoldOutCounter() {
        cut = new GenericPatternCounter("Out of stock");
    }

    private void givenBuffleheadPage() {
        inputText = loadTestData("BuffleheadSoap.html");
    }

    private void givenInputWithNoPrices() {
        inputText = "No Prices $500.00.0 20.00a 100.a0";
    }

    private void givenDeclarationSoldOutCounter() {
        cut = new GenericPatternCounter("em>Sold Out");
    }

    private void givenDeclarationPage() {
        inputText = loadTestData("DeclarationBrushes.html");
    }

    // WHENs
    private void whenCountPatternInstancesIsCalled() {
        count = cut.countPatternInstances(inputText);
    }

    // THENs
    private void thenCountIs(int expectedCount) {
        assertThat(count, is(expectedCount));
    }

}
