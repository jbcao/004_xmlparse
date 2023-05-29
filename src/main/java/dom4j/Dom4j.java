package src.main.java.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;

/**
 * 功能:使用dom4j对XML文件进行crud
 *
 * @author caojianbang
 * @date 24.3.22 4:18 PM
 */
public class Dom4j {
    public static void main(String[] args) throws Exception {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new File("src/main/java/dom4j/class.xml"));
        //list(document.getRootElement());
        addByIndex(document);
    }

    public static void addByIndex(Document document) throws Exception {
        Element newHero = DocumentHelper.createElement("学生");
        newHero.setText("林冲");
        List all = document.getRootElement().elements("学生");
        for (int i = 0; i < all.size(); i++) {
            Element stu = ((Element) all.get(i)).element("名字");
            if (stu.getText().equals("卢俊义")) {
                all.add(i + 1, newHero);
                break;
            }
        }
        OutputFormat outputFormat = OutputFormat.createPrettyPrint();
        outputFormat.setEncoding("utf-8");
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(new File("src/main/java/dom4j/class.xml")), outputFormat);
        xmlWriter.write(document);
        xmlWriter.close();
    }

    public static void update(Document document) throws Exception {
        List<Element> stus = document.getRootElement().elements("学生");
        for (Element el : stus) {
            Element age = el.element("年龄");
            age.setText(Integer.parseInt(age.getTextTrim()) + 3 + "");
            Element name = el.element("名字");
            name.addAttribute("别名", "okok");
        }
        OutputFormat outputFormat = OutputFormat.createPrettyPrint();
        outputFormat.setEncoding("utf-8");
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(new File("src/main/java/dom4j/class.xml")), outputFormat);
        xmlWriter.write(document);
        xmlWriter.close();

    }

    public static void del(Document document) throws Exception {
        Element stu = document.getRootElement().element("学生");
        //stu.getParent().remove(stu);
        stu.remove(stu.attribute("别名"));
        OutputFormat outputFormat = OutputFormat.createPrettyPrint();
        outputFormat.setEncoding("utf-8");
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(new File("src/main/java/dom4j/class.xml")), outputFormat);
        xmlWriter.write(document);
        xmlWriter.close();
    }

    public static void add(Document document) throws Exception {
        Element newStu = DocumentHelper.createElement("学生");
        Element stuName = DocumentHelper.createElement("名字");
        stuName.setText("songjiang");
        stuName.addAttribute("nickname", "jishiyu");
        Element stuAge = DocumentHelper.createElement("年龄");
        Element stuIntro = DocumentHelper.createElement("介绍");
        newStu.add(stuName);
        newStu.add(stuAge);
        newStu.add(stuIntro);
        document.getRootElement().add(newStu);
        OutputFormat outputFormat = OutputFormat.createPrettyPrint();
        outputFormat.setEncoding("utf-8");
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(new File("src/main/java/dom4j/class.xml")), outputFormat);
        xmlWriter.write(document);
        xmlWriter.close();

    }

    public static void read(Document docment) {
        Element root = docment.getRootElement();
        Element stu = root.elements("学生").get(0);
        Element stuName = stu.element("名字");
        System.out.println(stuName.getTextTrim() + stuName.attributeValue("别名"));
    }

    public static void list(Element element) {
        System.out.println(element.getName() + element.getTextTrim());
        Iterator iterator = element.elementIterator();
        while (iterator.hasNext()) {
            Element e = (Element) iterator.next();
            list(e);
        }

    }

}
