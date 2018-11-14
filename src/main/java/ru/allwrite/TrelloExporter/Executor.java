package ru.allwrite.TrelloExporter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.allwrite.TrelloExporter.onedrive.CloudUpdater;
import ru.allwrite.TrelloExporter.trello.TrelloService;

import java.util.Map;

public class Executor {
    private final TrelloService trelloService;
    private final CloudUpdater cloudUpdater;

    public Executor(TrelloService trelloService, CloudUpdater cloudUpdater) {
        this.trelloService = new TrelloService();
        this.cloudUpdater = new CloudUpdater();
    }

    public void execute() {
        Map<String, String> fieldsToValue = trelloService.execute();
        cloudUpdater.update(fieldsToValue);
    }
}
