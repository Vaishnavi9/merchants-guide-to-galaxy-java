package com.app.inputOutProcessor.test;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.app.inputOutputProcessor.ConversionCriteria;

public class ConversionCriteriaTest {

	protected Character[] characterArray;
	protected Character character;

	@Before
	public void setUp() throws Exception {
		character = 'P';
		characterArray = new Character[]{'I','X','V','L'};

	}

	@Test
	public void testOutputFormatter(){
		boolean result = ConversionCriteria.checkIfLiteralPresent(characterArray, character);
		Assert.assertEquals(false, result);
	}

	@Test

	/**
	 * Test whether the subtraction logic is handled correctly.
	 */
	public void testSubtractionLogic(){
		float result = ConversionCriteria.subtractionLogic(52f, 10f, 50f);
		Assert.assertEquals(42f, result, 00.00);
	}
}
