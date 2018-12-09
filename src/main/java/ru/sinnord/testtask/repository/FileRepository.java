package ru.sinnord.testtask.repository;

import ru.sinnord.testtask.model.RowItem;

import java.util.List;

public interface FileRepository {

    void saveFile(String fileName, String content);

    void saveItem(RowItem item);

    String getContent();

    String getFileName();

    List<RowItem> getAllItems();

    RowItem getItem(Integer id);
}
