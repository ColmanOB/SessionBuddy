package utils;

import static org.junit.Assert.*;
import org.junit.Test;
import sessionbuddy.utils.StringCleaner;

/**
 * Tests the static cleanString method of the StringCleaner class to ensure it cleans input strings
 * as expected. Specifically, it needs to handle XML entities in a string and convert them to their
 * corresponding character. This is complicated somewhat in that the API at thesession.org is not
 * consistent with how XML entities are escaped.
 * 
 * @author Colman
 * @since 2018-03-13
 */
public class StringCleanerTest {

  // The following tests check that ampersands, in a number of separate formats, are handled
  // correctly.

  @Test
  public void testDecodeAmpersand_1() {
    // Test that the sequence "&amp;" is replaced by a single ampersand
    assertEquals("Text & Text", StringCleaner.cleanString("Text &amp; Text"));
  }

  @Test
  public void testDecodeAmpersand_2() {
    // Test that the sequence "&#038;" is replaced by a single ampersand
    assertEquals("Text & Text", StringCleaner.cleanString("Text &#038; Text"));
  }

  @Test
  public void testDecodeAmpersand_3() {
    // Test that the sequence "&#38;" is replaced by a single ampersand
    assertEquals("Text & Text", StringCleaner.cleanString("Text &#38; Text"));
  }

  @Test
  public void testUnescapedAmpersandIsUnchanged() {
    // Test that an unescaped ampersand on its own is passed through unchanged
    assertEquals("Text & Text", StringCleaner.cleanString("Text & Text"));
  }

  // Next, we test that apostrophes are decoded correctly

  @Test
  public void testDecodeApostrophe_1() {
    // Test that the sequence "&apos;" is replaced by an apostrophe
    assertEquals("Text ' Text", StringCleaner.cleanString("Text &apos; Text"));
  }

  @Test
  public void testDecodeApostrophe_2() {
    // Test that the sequence "&#039;" is replaced by an apostrophe
    assertEquals("Text ' Text", StringCleaner.cleanString("Text &#039; Text"));
  }

  @Test
  public void testDecodeApostrophe_3() {
    // Test that the sequence "&#39;" is replaced by an apostrophe
    assertEquals("Text ' Text", StringCleaner.cleanString("Text &#39; Text"));
  }

  @Test
  public void testDecodeApostrophe_4() {
    // Test that the sequence "&#8217;" is replaced by an apostrophe
    assertEquals("Text ' Text", StringCleaner.cleanString("Text &#8217; Text"));
  }

  @Test
  public void testUnescapedApostropheIsUnchanged() {
    // Test that an unescaped apostrophe on its own is passed through unchanged
    assertEquals("Text ' Text", StringCleaner.cleanString("Text ' Text"));
  }

  // Next, we test that double quotes are handled correctly

  @Test
  public void testDecodeDoubleQuotes_1() {
    // Test that the sequence "&quot;" is replaced by double quotes
    assertEquals("Text \" Text", StringCleaner.cleanString("Text &quot; Text"));
  }

  @Test
  public void testDecodeDoubleQuotes_2() {
    // Test that the sequence "&#034;" is replaced by double quotes
    assertEquals("Text \" Text", StringCleaner.cleanString("Text &#034; Text"));
  }

  @Test
  public void testDecodeDoubleQuotes_3() {
    // Test that the sequence "&#34;" is replaced by double quotes
    assertEquals("Text \" Text", StringCleaner.cleanString("Text &#34; Text"));
  }

  @Test
  public void testUnescapedDoubleQuotesAreUnchanged() {
    // Test that unescaped double quotes are passed through unchanged
    assertEquals("Text \" Text", StringCleaner.cleanString("Text \" Text"));
  }

  // Test that 'less than' signs are decoded correctly

  @Test
  public void testDecodeLessThan_1() {
    // Test that the sequence "&lt;" is replaced by a 'less than' sign
    assertEquals("Text < Text", StringCleaner.cleanString("Text &lt; Text"));
  }

  @Test
  public void testDecodeLessThan_2() {
    // Test that the sequence "&#060;" is replaced by a 'less than' sign
    assertEquals("Text < Text", StringCleaner.cleanString("Text &#060; Text"));
  }

  @Test
  public void testDecodeLessThan_3() {
    // Test that the sequence "&#60;" is replaced by a 'less than' sign
    assertEquals("Text < Text", StringCleaner.cleanString("Text &#60; Text"));
  }

  @Test
  public void testUnescapedLessThanIsUnchanged() {
    // Test that unescaped 'less than' symbols are passed through unchanged
    assertEquals("Text < Text", StringCleaner.cleanString("Text < Text"));
  }

  // Test that 'greater than' symbols are decoded correctly

  @Test
  public void testDecodeGreaterThan_1() {
    // Test that the sequence "&gt;" is replaced by a 'greater than' sign
    assertEquals("Text > Text", StringCleaner.cleanString("Text &gt; Text"));
  }

  @Test
  public void testDecodeGreaterThan_2() {
    // Test that the sequence "&#062;" is replaced by a 'greater than' sign
    assertEquals("Text > Text", StringCleaner.cleanString("Text &#062; Text"));
  }

  @Test
  public void testDecodeGreaterThan_3() {
    // Test that the sequence "&#62;" is replaced by a 'greater than' sign
    assertEquals("Text > Text", StringCleaner.cleanString("Text &#62; Text"));
  }

  @Test
  public void testUnescapedGreaterThanIsUnchanged() {
    // Test that unescaped 'less than' symbols are passed through unchanged
    assertEquals("Text > Text", StringCleaner.cleanString("Text > Text"));
  }
}
