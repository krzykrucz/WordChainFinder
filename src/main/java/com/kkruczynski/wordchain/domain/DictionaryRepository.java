package com.kkruczynski.wordchain.domain;

public interface DictionaryRepository {

    Dictionary getDictionaryByWordLength(int length);
}
