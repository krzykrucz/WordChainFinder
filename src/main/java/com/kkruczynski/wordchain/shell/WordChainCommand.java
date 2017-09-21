package com.kkruczynski.wordchain.shell;

import com.kkruczynski.wordchain.domain.ChainFinder;
import com.kkruczynski.wordchain.domain.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class WordChainCommand {

    private final ChainFinder chainFinder;

    @Autowired
    public WordChainCommand(ChainFinder chainFinder) {
        this.chainFinder = chainFinder;
    }

    @ShellMethod("Find word chain between two given words, like: 'chain word1 word2'")
    public String chain(String word1, String word2) {
        return chainFinder.findChainBetween(new Word(word1), new Word(word2))
                .toString();
    }
}