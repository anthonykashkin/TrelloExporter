package ru.allwrite.TrelloExporter.trello;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class IndexStorage {
    private List<String> exportedCards = new CopyOnWriteArrayList<>();

    public boolean isExists(String id){
        return exportedCards.stream()
                .anyMatch(id::equals);
    }

    public void add(String id) {
        exportedCards.add(id);
    }
}
