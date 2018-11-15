package ru.allwrite.TrelloExporter.onedrive;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class ExcelWriter {
    private static final Logger logger = LoggerFactory.getLogger(ExcelWriter.class);

    @Value("${excel.sheet")
    private String sheet;

    private InputStream inputStream;
    private OutputStream outputStream;

    public ExcelWriter(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    public void write(List<String> newRow) {
        Workbook workbook = null;
        try {
            workbook = new HSSFWorkbook(inputStream);
        } catch (IOException e) {
            logger.error("Can`t get excel book from stream. ", e);
            return;
        }

        Sheet sheet = workbook.getSheet(this.sheet);

        Row row = sheet.createRow(sheet.getLastRowNum() + 1);

        for (int i = 0; i < newRow.size(); i++) {
            row.createCell(i).setCellValue(newRow.get(i));
        }

        try {
            workbook.write(outputStream);
        } catch (IOException e) {
            logger.error("Can`t write updated excel book to stream. ", e);
        }
    }
}
