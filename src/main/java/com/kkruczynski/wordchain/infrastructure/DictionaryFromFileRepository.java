package com.kkruczynski.wordchain.infrastructure;

import com.google.common.base.Charsets;
import com.kkruczynski.wordchain.domain.Dictionary;
import com.kkruczynski.wordchain.domain.DictionaryRepository;
import com.kkruczynski.wordchain.domain.Word;
import com.kkruczynski.wordchain.shell.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static com.google.common.io.Resources.getResource;

@Repository
public class DictionaryFromFileRepository implements DictionaryRepository {

    private final String dictionaryPath;

    @Autowired
    public DictionaryFromFileRepository(@Value("${dictionary.path}") String dictionaryPath) {
        this.dictionaryPath = dictionaryPath;
    }

    @Override
    public Dictionary getDictionaryByWordLength(int length) {
        final Dictionary.DictionaryBuilder dictionaryBuilder = Dictionary.builder();
        try (Stream<String> lines = Files.lines(Paths.get(getResource(dictionaryPath).toURI()), Charsets.ISO_8859_1)) {
            lines.map(Word::new)
                    .filter(word -> word.length() == length)
                    .forEach(dictionaryBuilder::addWord);
            return dictionaryBuilder.build();
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(ErrorMessage.DICTIONARY_READ_ERROR);
        }
    }
}
