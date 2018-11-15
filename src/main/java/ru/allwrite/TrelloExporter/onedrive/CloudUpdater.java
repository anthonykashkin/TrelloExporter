package ru.allwrite.TrelloExporter.onedrive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CloudUpdater {
    private static final Logger logger = LoggerFactory.getLogger(CloudUpdater.class);

    private final List<String> fieldNames;
    private final OutputConnector connector;

    @Autowired
    public CloudUpdater(List<String> fieldNames, OutputConnector connector) {
        this.fieldNames = fieldNames;
        this.connector = connector;
    }

    public void update(List<Map<String, String>> rowFields) {

        try (InputStream inputStream = connector.getInputStream();
             OutputStream outputStream = connector.getOutputStream()) {

            ExcelWriter excelWriter = new ExcelWriter(inputStream, outputStream);

            rowFields.forEach(s -> {
                List<String> fields = new ArrayList<>();

                for (String fieldName : fieldNames) {
                    String value = s.get(fieldName);

                    if (value == null) {
                        fields.add("");
                    } else {
                        fields.add(value);
                    }
                }

                excelWriter.write(fields);
            });

        } catch (IOException e) {
            logger.error("Can`t take stream from output connector. ", e);
        }
    }
}
