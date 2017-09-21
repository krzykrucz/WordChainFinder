package com.kkruczynski.wordchain.shell;

public interface ErrorMessage {

    String NON_EXISTENT_WORD = "Word %s does't exist in the dictionary";

    String WORDS_DIFFERENT_LENGTH = "Words should have the same length";

    String DICTIONARY_READ_ERROR = "Could not read dictionary from a file";

    static void throwRuntimeIf(boolean predicate, String errorMessage) {
        if (predicate) {
            throw new RuntimeException(errorMessage);
        }
    }
}
