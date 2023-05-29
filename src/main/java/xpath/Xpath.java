package src.main.java.xpath;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.List;

/**
 * 功能:dom4j配合xpath案例
 *
 * @author caojianbang
 * @date 26.3.22 2:29 PM
 */
public class Xpath {
    public static void main(String[] args) throws Exception {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("src/main/java/xpath/test.xml");
        List list=document.selectNodes("//BBB[@*]");
        System.out.println(list.size());
        System.out.println(((Element)list.get(0)).getText());
    }
}
