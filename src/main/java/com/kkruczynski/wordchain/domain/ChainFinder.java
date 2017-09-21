package com.kkruczynski.wordchain.domain;

import com.kkruczynski.wordchain.shell.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kkruczynski.wordchain.shell.ErrorMessage.throwRuntimeIf;
import static java.lang.String.format;

@Service
public class ChainFinder {

    private final DictionaryRepository dictionaryRepository;

    @Autowired
    public ChainFinder(DictionaryRepository dictionaryRepository) {
        this.dictionaryRepository = dictionaryRepository;
    }

    public List<Word> findChainBetween(Word firstWord, Word secondWord) {
        final Dictionary dictionary = dictionaryRepository.getDictionaryByWordLength(firstWord.length());
        throwRuntimeIf(!dictionary.getAllWords().contains(firstWord), format(ErrorMessage.NON_EXISTENT_WORD, firstWord));
        throwRuntimeIf(!dictionary.getAllWords().contains(secondWord), format(ErrorMessage.NON_EXISTENT_WORD, secondWord));
        throwRuntimeIf(firstWord.length() != secondWord.length(), ErrorMessage.WORDS_DIFFERENT_LENGTH);
        return dictionary.getChainBetween(firstWord, secondWord);
    }

}
