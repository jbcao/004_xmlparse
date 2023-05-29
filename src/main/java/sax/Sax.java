package src.main.java.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * 功能:使用sax技术解析xml
 *
 * @author caojianbang
 * @date 23.3.22 4:36 PM
 */
public class Sax {
    public static void main(String[] args) throws Exception{
        SAXParserFactory spf= SAXParserFactory.newInstance();
        SAXParser sp=spf.newSAXParser();
        sp.parse("src/main/java/sax/class.xml",new MyDefaultHandler());


    }
}
class MyDefaultHandler extends DefaultHandler{
    private boolean isName=false;
    private boolean isAge=false;
    @Override
    public void startDocument() throws SAXException {
        //System.out.println("开始扫描");
        super.startDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //System.out.println("名字"+qName);
        if(qName.equals("名字")){
            isName=true;
        }
        if(qName.equals("年龄")){
            isAge=true;
        }
        super.startElement(uri, localName, qName, attributes);
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        super.characters(ch, start, length);
        String c=new String(ch,start,length);
        if(!c.trim().equals("")&&(isName||isAge)){
            System.out.println(new String(ch,start,length)+length);
            isAge=false;
            isName=false;
        }

    }
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
    }
    @Override
    public void endDocument() throws SAXException {
       // System.out.println("结束扫描");
        super.endDocument();
    }
}