package ru.allwrite.TrelloExporter.trello;

import com.julienvey.trello.domain.Card;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TrelloService{
    private IndexStorage storage;
    private TrelloConnector connector;

    private List<String> fieldNames;

    public TrelloService() {
        this.storage = new IndexStorage();
        this.connector = new TrelloConnector();
    }

    private List<Card> getCards() {
        List<Card> nonExportedCard = connector.getList()
                .stream()
                .filter(s -> storage.isExists(s.getId()))
                .collect(Collectors.toList());

        nonExportedCard.forEach(card -> storage.add(card.getId()));

        return nonExportedCard;
    }

    public Map<String, String> execute() {
        //TODO transform to map
        //TODO add xml config for fields
        List<Card> cards = getCards();

    }
}
