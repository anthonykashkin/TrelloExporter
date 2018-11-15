package ru.allwrite.TrelloExporter.trello;

import com.julienvey.trello.domain.Card;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
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

    public List<Map<String, String>> execute() {
        //TODO transform to map
        //TODO add xml config for fields
        return getCards()
                .stream()
                .map(s -> {
                    Map<String, String> map = new HashMap<>();
                    map.put("name", s.getName());
                    map.put("desc", s.getDesc());
                    return map;
                })
                .collect(Collectors.toList());
    }
}
