package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        List<String> result = new ArrayList<>();
        if (source == null || source.isEmpty()) {
            return result;
        }

        // Sort delimiters by length in descending order to handle longer delimiters first
        List<String> sortedDelimiters = new ArrayList<>(delimiters);
        sortedDelimiters.sort((a, b) -> Integer.compare(b.length(), a.length()));

        int start = 0;
        while (start < source.length()) {
            int nextDelimiterIndex = -1;
            String nextDelimiter = null;

            // Find the earliest occurring delimiter in the string
            for (String delimiter : sortedDelimiters) {
                int index = source.indexOf(delimiter, start);
                if (index != -1 && (nextDelimiterIndex == -1 || index < nextDelimiterIndex)) {
                    nextDelimiterIndex = index;
                    nextDelimiter = delimiter;
                }
            }

            // If no more delimiters are found, add the remaining string and break
            if (nextDelimiterIndex == -1) {
                result.add(source.substring(start));
                break;
            }

            // Add the substring before the delimiter
            if (nextDelimiterIndex > start) {
                result.add(source.substring(start, nextDelimiterIndex));
            }

            // Move the start index past the delimiter
            start = nextDelimiterIndex + nextDelimiter.length();
        }

        return result;
    }
}
