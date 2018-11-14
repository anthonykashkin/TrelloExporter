package ru.allwrite.TrelloExporter.trello;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Card;
import com.julienvey.trello.impl.TrelloImpl;
import com.julienvey.trello.impl.http.ApacheHttpClient;
import org.springframework.beans.factory.annotation.Value;
import java.util.List;

public class TrelloConnector {
    @Value("${trello.key")
    private String trelloKey;
    @Value("${trello.access_token")
    private String trelloAccessToken;
    @Value("${trello.list_name")
    private String listName;

    private Trello trelloApi;

    public TrelloConnector() {
        trelloApi = new TrelloImpl(trelloKey, trelloAccessToken, new ApacheHttpClient());
    }

    public List<Card> getList() {
        if (trelloApi != null) {
            return trelloApi.getListCards(listName);
        } else {
            return null;
        }
    }

    public String getTrelloKey() {
        return trelloKey;
    }

    public void setTrelloKey(String trelloKey) {
        this.trelloKey = trelloKey;
    }

    public String getTrelloAccessToken() {
        return trelloAccessToken;
    }

    public void setTrelloAccessToken(String trelloAccessToken) {
        this.trelloAccessToken = trelloAccessToken;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public Trello getTrelloApi() {
        return trelloApi;
    }

    public void setTrelloApi(Trello trelloApi) {
        this.trelloApi = trelloApi;
    }
}
