package ru.allwrite.TrelloExporter.onedrive;

import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.OutputStream;

@Component
public interface OutputConnector {

    InputStream getInputStream();

    OutputStream getOutputStream();

}
