package ru.sinnord.testtask.util;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.sinnord.testtask.model.RowItem;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlUtil {

    public static List<RowItem> parseXml(String content) {
        List<RowItem> items = new ArrayList<>();

        try {
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();
            Document document = dBuilder.parse(new ByteArrayInputStream(content.getBytes()));

            if (document.hasChildNodes()) {
                processChildNodes(document.getChildNodes(), items);
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            return new ArrayList<>();
        }

        return items;
    }

    private static void processChildNodes(NodeList nodeList, List<RowItem> items) {
        for (int count = 0; count < nodeList.getLength(); count++) {

            Node tempNode = nodeList.item(count);
            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

                RowItem item = new RowItem();
                items.add(item);
                if (tempNode.getChildNodes().getLength() == 1) {
                    //no child nodes
                    item.setValue(tempNode.getTextContent());
                }

                // use "name" attribute or tagname
                if (tempNode.hasAttributes()) {
                    NamedNodeMap nodeMap = tempNode.getAttributes();

                    Node nameNode = nodeMap.getNamedItem("name");
                    if (nameNode != null) {
                        item.setName(nameNode.getNodeValue());
                    }
                } else {
                    item.setName(tempNode.getNodeName());
                }

                if (tempNode.hasChildNodes()) {
                    processChildNodes(tempNode.getChildNodes(), items);
                }
            }
        }
    }

    public static String updateContent(String content, List<RowItem> items) {
        String updatedContent = content;

        for (RowItem rowItem : items) {
            if (rowItem.getNewValue() != null) {
                updatedContent = updatedContent.replaceFirst(">" + rowItem.getValue(), ">" + rowItem.getNewValue());
            }
        }

        return updatedContent;
    }
}
