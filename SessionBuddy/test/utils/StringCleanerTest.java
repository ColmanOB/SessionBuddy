package utils;

import static org.junit.Assert.*;

import org.junit.Test;

import sessionbuddy.utils.StringCleaner;

/**
 * Tests the StringCleaner class to ensure it cleans input strings as expected.
 * Specifically, it needs to handle XML entities in a string and convert them to their corresponding character.
 * This is complicated somewhat in that the API at thesession.org is not consistent with how XML entities are escaped.
 * 
 * @author Colman
 * @since 2017-10-09
 */
public class StringCleanerTest 
	{
	
	// The following tests check that ampersands, in a number of separate formats, are handled correctly.
	
	@Test
	public void testDecodeAmpersand_1() 
		{
		// Test that the sequence "&amp;" is replaced by a single ampersand
		assertEquals("Text & Text", StringCleaner.cleanString("Text &amp; Text"));
		}
	
	@Test
	public void testDecodeAmpersand_2() 
		{
		// Test that the sequence "&#038;" is replaced by a single ampersand
		assertEquals("Text & Text", StringCleaner.cleanString("Text &#038; Text"));
		}

	@Test
	public void testDecodeAmpersand_3() 
		{
		// Test that the sequence "&#38;" is replaced by a single ampersand
		assertEquals("Text & Text", StringCleaner.cleanString("Text &#38; Text"));
		}
	
	@Test
	public void testUnescapedAmpersandInRespose() 
		{
		// Test that an unescaped ampersand on its own is passed through unchanged
		assertEquals("Text & Text", StringCleaner.cleanString("Text & Text"));
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
	
	}
