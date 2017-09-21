package utils;

import static org.junit.Assert.*;

import org.junit.Test;

import sessionbuddy.utils.StringCleaner;

/**
 * Tests the StringCleaner class to ensure it cleans input strings as expected
 * 
 * @author Colman
 * @since 2017-09-16
 */
public class StringCleanerTest 
	{
	StringCleaner cleaner = new StringCleaner();
	
	@Test
	public void testDecodeAmpersand_1() 
		{
		// Test that the sequence "&amp;" is replaced by a single ampersand
		assertEquals("Tr&blah", StringCleaner.cleanString("Tr&amp;blah"));
		}
	
	@Test
	public void testDecodeAmpersand2() 
		{
		// Test that the sequence "&#038;" is replaced by a single ampersand
		assertEquals("Tr&blah", StringCleaner.cleanString("Tr&#038;blah"));
		}

	@Test
	public void testDecodeAmpersand3() 
		{
		// Test that the sequence "&#38;" is replaced by a single ampersand
		assertEquals("Tr&blah", StringCleaner.cleanString("Tr&#38;blah"));
		}

	@Test
	public void testDecodeAmpersandAndApostrophe() 
		{
		// Test that the sequence "&#38;" is replaced by a single ampersand
		assertEquals("Tr&bla'h", StringCleaner.cleanString("Tr&#38;bla&apos;h"));
		}
	
	@Test
	public void testAmpersandAndSpaceInRespose() 
		{
		// Test that the sequence "&#38;" is replaced by a single ampersand
		assertEquals("Tr& blah", StringCleaner.cleanString("Tr& blah"));
		}
	
	@Test
	public void testAmpersandInRespose() 
		{
		// Test that an ampersand on its own is passed through unchanged
		assertEquals("Tr&blah", StringCleaner.cleanString("Tr&blah"));
		}
	
	}
