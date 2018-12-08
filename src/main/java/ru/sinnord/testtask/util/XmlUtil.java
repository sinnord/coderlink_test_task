package ru.sinnord.testtask.util;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.sinnord.testtask.model.RowItem;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class XmlUtil {

    public static List<RowItem> parseXml(String content) {
        List<RowItem> items = Arrays.asList(
                new RowItem(1, "fdd", "dds"),
                new RowItem(2, "kkk", "lde")
        );

        try {
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();
            Document document = dBuilder.parse(new ByteArrayInputStream(content.getBytes()));

            if (document.hasChildNodes()) {

            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            return new ArrayList<>();
        }

        return items;
    }

    private static void processChildNodes(NodeList nodeList, List<RowItem> items) {

    }
}
