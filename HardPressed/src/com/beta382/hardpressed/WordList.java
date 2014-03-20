package com.beta382.hardpressed;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

public class WordList {

    private String[] m_words;
    private Comparator<String> m_currentComparator;
    private static LengthComparator s_lengthComparator = new LengthComparator();

    public WordList(List<String> list) {
        // Convert incoming List into an array of String
        m_words = list.toArray(new String[list.size()]);
        m_currentComparator = null;
    }

    public String[] contents() {
        return m_words;
    }

    public int length() {
        return m_words.length;
    }

    public void sortByLength() {
        m_currentComparator = s_lengthComparator;
        Arrays.sort(m_words, m_currentComparator);
    }

    public void sortByAlpha() {
        m_currentComparator = null;
        Arrays.sort(m_words);
    }

    public void reverseSort() {
        Arrays.sort(m_words, Collections.reverseOrder(m_currentComparator));
    }

    public String toString() {
        StringBuffer output = new StringBuffer();

        for (String word : m_words) {
            output.append(word + "\n");
        }
        return output.toString();
    }

    public WordList wordsContainingOnly(String letters) {
        Vector<String> newList = new Vector<String>();

//        long startTime = System.currentTimeMillis(); // Stats

        for (String word : m_words) {
            if (wordContainsOnly(word, letters)) {
                newList.add(word);
            }
        }

//        System.out.println("Processed " + m_words.length + " words in " +
//                (System.currentTimeMillis()-startTime) + " milliseconds."); // Stats

        return new WordList(newList);
    }

    public WordList wordsContainingMinimally(String letters) {
        Vector<String> newList = new Vector<String>();

//        long startTime = System.currentTimeMillis(); // Stats

        for (String word : m_words) {
            if (wordContainsMinimally(word, letters)) {
                newList.add(word);
            }
        }

//        System.out.println("Processed " + m_words.length + " words in " +
//                (System.currentTimeMillis()-startTime) + " milliseconds."); // Stats

        return new WordList(newList);
    }

    public WordList subList(int start, int number) {
        Vector<String> subList = new Vector<String>();

        for (int i = start; i < (start + number); i++) {
            subList.add(m_words[i]);
        }

        return new WordList(subList);
    }

    private static boolean wordContains(String word, String letters) {

        // If there are no contained letters, then containment is always true
        if (letters.length() == 0) {
            return true;
        }

        // If there are more letters in 'letters' than 'word', then containment is impossible
        if (letters.length() > word.length()) {
            return false;
        }

        BitSet verifiedIndicies = new BitSet(word.length());

        // Lower case everything because case doesn't matter
        word = word.toLowerCase();
        letters = letters.toLowerCase();

        for (int i = 0; i < letters.length(); i++) {
            int currentSearchIndex = 0; // Used to respond to cases where search letters are repeated

            while (true) {
                int letterIndex = word.indexOf(letters.charAt(i),
                        currentSearchIndex);

                if (letterIndex >= 0) { // If the word contains the letter...
                    // ... and that letter is the same one we saw before...
                    if (verifiedIndicies.get(letterIndex)) {
                        // ...start over with the next search beginning after where the repeat is.
                        currentSearchIndex = letterIndex + 1;
                    } else { // We haven't seen this letter before
                        verifiedIndicies.set(letterIndex);
                        break;
                    }
                } else { // If we don't have the letter
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean wordContainsOnly(String word, String letters) {
        return wordContains(letters, word);
    }

    public static boolean wordContainsMinimally(String word, String letters) {
        return wordContains(word, letters);
    }

    static class LengthComparator implements Comparator<String> {
        public int compare(String s1, String s2) {
            return s1.length() - s2.length();
        }
    }

    // ---- Test Methods ----//

    public static boolean wordContainsTest1(String source, String toMatch) {

        // If not looking for anything, then containment is always true
        if (toMatch.length() == 0) {
            return true;
        }

        // If there are more chars in 'toMatch' than 'source', then containment is impossible
        if (toMatch.length() > source.length()) {
            return false;
        }

        BitSet matches = new BitSet(source.length()); // initialized to false

        // Lower case everything because case doesn't matter
        source = source.toLowerCase();
        toMatch = toMatch.toLowerCase();

        for (int i = 0; i < toMatch.length(); i++) {
            final char c = toMatch.charAt(i);

            // Look for an un-matched 'c' in 'source'.
            boolean foundIt = false;
            for (int j = 0; j < source.length(); j++) {
                if (c == source.charAt(j) && !matches.get(j)) {
                    // found a not-yet-matched match in 'source', mark it as matched now
                    matches.set(j);
                    foundIt = true;
                    break;
                }
            }
            if (!foundIt) {
                // char 'c' in 'toMatch' isn't found in 'source', so return false;
                return false;
            }
        }

        return true;
    }
    
    public WordList wordsContainingOnlyTest1(String letters) {
        Vector<String> newList = new Vector<String>();

        for (String word : m_words) {
            if (wordContainsTest1(letters, word)) {
                newList.add(word);
            }
        }

        return new WordList(newList);
    }
}
