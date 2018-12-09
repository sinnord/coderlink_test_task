package ru.sinnord.testtask.repository;

import ru.sinnord.testtask.model.RowItem;
import ru.sinnord.testtask.util.XmlUtil;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Dumb singleton realization
 */
public class InMemoryFileRepository implements FileRepository {

    private static final InMemoryFileRepository INSTANCE = new InMemoryFileRepository();
    private AtomicInteger counter = new AtomicInteger(0);

    private String fileName;

    private String fileContent;

    private Map<Integer, RowItem> repository = new ConcurrentHashMap<>();

    private InMemoryFileRepository() {
    }

    public static InMemoryFileRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public void saveFile(String fileName, String content) {
        this.fileName = fileName;
        this.fileContent = content;
        repository.clear();

        XmlUtil.parseXml(content).forEach(this::saveItem);
    }

    @Override
    public void saveItem(RowItem item) {

        if (item.isNew()) {
            item.setId(counter.incrementAndGet());
        }
        repository.put(item.getId(), item);
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
        return repository.values().stream()
                .sorted(Comparator.comparing(RowItem::getId))
                .collect(Collectors.toList());
    }

    @Override
    public RowItem getItem(Integer id) {
        return repository.getOrDefault(id, null);
    }
}
