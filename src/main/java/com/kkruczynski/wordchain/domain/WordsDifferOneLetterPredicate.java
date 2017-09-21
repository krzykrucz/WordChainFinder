package com.kkruczynski.wordchain.domain;

import java.util.function.Predicate;

public class WordsDifferOneLetterPredicate implements Predicate<Word> {

    private final Word word;

    WordsDifferOneLetterPredicate(Word word) {
        this.word = word;
    }

    @Override
    public boolean test(Word anotherWord) {
        return word.distanceTo(anotherWord) == 1;
    }
}
