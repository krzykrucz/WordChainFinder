package com.kkruczynski.wordchain.domain;

import com.google.common.base.Objects;
import org.apache.commons.text.similarity.LevenshteinDistance;

public class Word {

    private static final LevenshteinDistance LEVENSHTEIN_DISTANCE = new LevenshteinDistance();

    private final String word;

    public Word(String string) {
        this.word = string;
    }

    public int distanceTo(Word anotherWord) {
        return LEVENSHTEIN_DISTANCE.apply(this.word, anotherWord.toString());
    }

    public int length() {
        return this.word.length();
    }

    @Override
    public String toString() {
        return this.word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word1 = (Word) o;
        return Objects.equal(word, word1.word);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(word);
    }
}
