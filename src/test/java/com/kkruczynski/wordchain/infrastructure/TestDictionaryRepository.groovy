package com.kkruczynski.wordchain.infrastructure;

class TestDictionaryRepository extends DictionaryFromFileRepository {

    TestDictionaryRepository() {
        super("wordlist.txt");
    }

}
