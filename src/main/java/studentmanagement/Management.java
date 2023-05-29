package src.main.java.studentmanagement;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.Scanner;

/**
 * 功能:基于xml的学生管理系统
 *
 * @author caojianbang
 * @date 23.3.22 9:23 PM
 */
public class Management {
    public static void main(String[] args) throws Exception {
//编程的原则是：见其名而知其意思
        Scanner scanner = new Scanner(System.in);
        StudentService studentService = new StudentService();
//显示操作界面
        System.out.println("查看所有学生成绩-view");
        System.out.println("根据id查看成绩-view if");
        System.out.println("添加一个学生-add");
        System.out.println("按照ID更改一个学生信息-change");
        System.out.println("按照ID删除一个学生-remove");
        System.out.println("保存学生信息-exit");
//获取用户希望干什么
        String operType = scanner.next();
        if (operType.equals("view")) {
            System.out.println("显示所有学生信息");
            studentService.showStuScore();
        }
    }
}

//一个service类
class StudentService {
    //    显示所有学生成绩的方法
    public void showStuScore() throws Exception {
        NodeList nodeList = getDocument().getElementsByTagName("student");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
//            输出学生编号
            System.out.println("编号\t" + element.getAttribute("sid") +"\t"+ "名字\t" + getFirstElementText(element, "name") +"\t"+ "java成绩\t" + getFirstElementText(element, "java")  +"\t"+ "vb\t" + getFirstElementText(element, "vb"));
        }
    }

    //封装一个方法用于获取document对象
    public Document getDocument() throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        return documentBuilder.parse("src/main/java/studentmanagement/student.xml");
    }

    //封装一个方法
    //getElementsByTagName可以隔层使用，只要该节点下面有该元素
    public String getFirstElementText(Element element, String name) {
        return element.getElementsByTagName(name).item(0).getTextContent();
    }
}

class Student {
    private String sid;
    private String name;
    private int my_java;
    private int my_oracle;
    private int my_vb;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMy_java() {
        return my_java;
    }

    public void setMy_java(int my_java) {
        this.my_java = my_java;
    }

    public int getMy_oracle() {
        return my_oracle;
    }

    public void setMy_oracle(int my_oracle) {
        this.my_oracle = my_oracle;
    }

    public int getMy_vb() {
        return my_vb;
    }

    public void setMy_vb(int my_vb) {
        this.my_vb = my_vb;
    }
}