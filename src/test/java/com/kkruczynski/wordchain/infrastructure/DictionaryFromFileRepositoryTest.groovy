package com.kkruczynski.wordchain.infrastructure

import com.kkruczynski.wordchain.domain.DictionaryRepository
import spock.lang.Specification

class DictionaryFromFileRepositoryTest extends Specification {

    DictionaryRepository dictionaryRepository = new TestDictionaryRepository()

    def "should return non empty dictionary of words length 3"() {
        expect:
        dictionaryRepository.getDictionaryByWordLength(3).allWords.size() > 0
    }

    def "should return non empty dictionary of words length 0"() {
        expect:
        dictionaryRepository.getDictionaryByWordLength(0).allWords.size() == 0
    }
}
