package ru.sinnord.testtask.repository;

import ru.sinnord.testtask.model.RowItem;
import ru.sinnord.testtask.util.XmlUtil;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Dumb singleton realization
 */
public class InMemoryFileRepository implements FileRepository {

    private static final InMemoryFileRepository INSTANCE = new InMemoryFileRepository();

    private String fileName;

    private String fileContent;

    private Map<Integer, RowItem> items;

    private InMemoryFileRepository() {
    }

    public static InMemoryFileRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public void saveFile(String fileName, String content) {
        this.fileName = fileName;
        this.fileContent = content;
        items.clear();

        XmlUtil.parseXml(content).forEach(this::saveItem);
    }

    @Override
    public void saveItem(RowItem item) {
        items.put(item.getNumber(), item);
    }

    @Override
    public String getContent() {
        return fileContent;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public List<RowItem> getAllItems() {
        return items.values().stream()
                .sorted(Comparator.comparing(RowItem::getNumber))
                .collect(Collectors.toList());
    }

    @Override
    public RowItem getItemByNumber(Integer number) {
        return items.getOrDefault(number, null);
    }
}
