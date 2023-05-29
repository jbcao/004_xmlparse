package src.main.java.jaxp;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * 功能:使用jaxp方式解析xml文件
 *
 * @author caojianbang
 * @date 18.3.22 8:28 PM
 */
public class Test {
    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse("src/main/java/jaxp/class.xml");
        System.out.println(document);
        list(document);
        //upd(document);
    }

    public static void read(Document doc) {
        NodeList n = doc.getElementsByTagName("学生");
        Element stu = (Element) n.item(0);
        System.out.println(stu.getAttribute("性别"));
        Element name = (Element) stu.getElementsByTagName("名字").item(0);
        System.out.println(name.getTextContent());
        System.out.println(n.getLength());

    }

    public static void list(Node node) {
        if(node.getNodeType()==node.ELEMENT_NODE){
            System.out.println("名字"+node.getNodeName());

        }
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node n = nodeList.item(i);
            list(n);
        }
    }
    public static  void add(Document doc) throws Exception{
        Element newStu = doc.createElement("学生");
        newStu.setAttribute("sex","男");
        Element name=doc.createElement("名字");
        name.setTextContent("caojbang");
        Element age = doc.createElement("年龄");
        age.setTextContent("23");
        newStu.appendChild(name);
        newStu.appendChild(age);
        doc.getDocumentElement().appendChild(newStu);
        TransformerFactory tf=TransformerFactory.newInstance();
        Transformer t=tf.newTransformer();
        t.transform(new DOMSource(doc),new StreamResult("src/class.xml"));

    }
    public static void del(Document doc) throws Exception{
//        Node node = doc.getElementsByTagName("学生").item(0);
//        node.getParentNode().removeChild(node);
        Element node = (Element)doc.getElementsByTagName("学生").item(0);
        node.removeAttribute("性别");
        TransformerFactory tf=TransformerFactory.newInstance();
        Transformer t=tf.newTransformer();
        t.transform(new DOMSource(doc),new StreamResult("src/class.xml"));
    }
    public static void upd(Document doc) throws Exception{
         Element node =(Element)doc.getElementsByTagName("学生").item(0);
         Element name = (Element) node.getElementsByTagName("名字").item(0);
         name.setTextContent("宋");
         name.setAttribute("sex","男");
         TransformerFactory tf= TransformerFactory.newInstance();
         Transformer t = tf.newTransformer();
         t.transform(new DOMSource(doc),new StreamResult("src/class.xml"));
    }
}