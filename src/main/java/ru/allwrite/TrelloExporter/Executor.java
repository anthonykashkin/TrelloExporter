package ru.allwrite.TrelloExporter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.allwrite.TrelloExporter.onedrive.CloudUpdater;
import ru.allwrite.TrelloExporter.trello.TrelloService;

import java.util.List;
import java.util.Map;

@Component
public class Executor {
    private final TrelloService trelloService;
    private final CloudUpdater cloudUpdater;

    @Autowired
    public Executor(TrelloService trelloService, CloudUpdater cloudUpdater) {
        this.trelloService = new TrelloService();
        this.cloudUpdater = cloudUpdater;
    }

    public void execute() {
        List<Map<String, String>> rawFields = trelloService.execute();
        cloudUpdater.update(rawFields);
    }
}
