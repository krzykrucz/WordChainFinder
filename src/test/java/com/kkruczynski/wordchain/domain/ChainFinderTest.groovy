package com.kkruczynski.wordchain.domain

import com.kkruczynski.wordchain.infrastructure.TestDictionaryRepository
import spock.lang.Specification
import spock.lang.Unroll

class ChainFinderTest extends Specification {

    def dictionaryRepository = new TestDictionaryRepository()

    ChainFinder chainFinder = new ChainFinder(dictionaryRepository)

    @Unroll
    def "should find shortest chain for pair of words"() {
        expect:
        chainFinder.findChainBetween(new Word(word1), new Word(word2)) == expectedChain.collect { new Word(it) }

        where:
        word1  | word2  || expectedChain
        'lead' | 'gold' || ['lead', 'load', 'goad', 'gold']
        'ruby' | 'code' || ['ruby', 'rube', 'robe', 'rode', 'code']
    }

    def "should find same chain in both directions"() {
        given:
        def cat = new Word('cat')
        def dog = new Word('dog')

        expect:
        chainFinder.findChainBetween(cat, dog) == chainFinder.findChainBetween(dog, cat).reverse()
    }

    def "should throw exception on non-existent word"() {
        when:
        chainFinder.findChainBetween(new Word('cat'), new Word('abc'))

        then:
        thrown(RuntimeException)
    }

    def "should throw exception on different word length"() {
        when:
        chainFinder.findChainBetween(new Word('cat'), new Word('house'))

        then:
        thrown(RuntimeException)
    }
}
