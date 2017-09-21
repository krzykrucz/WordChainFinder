package com.kkruczynski.wordchain.domain

import spock.lang.Specification

class WordTest extends Specification {

    def "should calculate distance from another word"() {
        expect:
        new Word(word1).distanceTo(new Word(word2)) == distance

        where:
        word1 | word2 || distance
        'cat' | 'dog' || 3
        'cat' | 'cat' || 0
    }
}
