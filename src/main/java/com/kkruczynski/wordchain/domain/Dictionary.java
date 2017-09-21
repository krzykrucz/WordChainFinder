package com.kkruczynski.wordchain.domain;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm.SingleSourcePaths;
import org.jgrapht.alg.shortestpath.BidirectionalDijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.List;
import java.util.Set;

public class Dictionary {

    private final Graph<Word, DefaultEdge> allChainsGraph;

    private Dictionary(DictionaryBuilder builder) {
        allChainsGraph = builder.allChainsGraph;
    }

    public static DictionaryBuilder builder() {
        return new DictionaryBuilder();
    }

    public List<Word> getChainBetween(Word word1, Word word2) {
        final ShortestPathAlgorithm<Word, DefaultEdge> shortestPathAlgorithm =
                new BidirectionalDijkstraShortestPath<>(allChainsGraph);
        final SingleSourcePaths<Word, DefaultEdge> shortestChainSource = shortestPathAlgorithm.getPaths(word1);
        return shortestChainSource.getPath(word2)
                .getVertexList();
    }

    public Set<Word> getAllWords() {
        return this.allChainsGraph.vertexSet();
    }

    public static class DictionaryBuilder {

        private final Graph<Word, DefaultEdge> allChainsGraph = new SimpleGraph<>(DefaultEdge.class);

        public DictionaryBuilder addWord(Word newWord) {
            allChainsGraph.addVertex(newWord);
            allChainsGraph.vertexSet()
                    .stream()
                    .filter(new WordsDifferOneLetterPredicate(newWord))
                    .forEach(adjacentWord -> allChainsGraph.addEdge(adjacentWord, newWord));
            return this;
        }

        public Dictionary build() {
            return new Dictionary(this);
        }


    }

}
