package com.sx.utils;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class MyParseXML {

    public static String PATH = "/home/shixin/IdeaProjects/2017Coding/src/config/struts.xml";

    public static String  getFullClassNameByActionName(String actionName) throws Exception{

        Element root = getRoot(PATH);

        Element action = getElementByName(root,actionName);

        String className = getAttrTextByClassName(action,"class");
        return className;
    }

    //获取root节点
    public static Element getRoot(String path) throws Exception{
        //1.读取xml
        // 创建saxReader对象
        SAXReader reader = new SAXReader();
        // 通过read方法读取一个文件 转换成Document对象
        Document document = reader.read(new File(path));
        //获取根节点元素对象
        Element root = document.getRootElement();
        return root;
    }

    public static Element getElementByName(Element parentElement , String name){
        Element element = null;
        for(Iterator it = parentElement.elementIterator(); it.hasNext();){
            Element temp = (Element) it.next();
            // do something
            Attribute attribute = temp.attribute("name");
            String attrText = attribute.getText();
            if(attrText.equals(name)){
                element = temp;
            }
        }
        return element;
    }

    public static String getAttrTextByClassName(Element element,String attrName){
        //取得某节点下的某属性,属性名name
        Attribute attribute=element.attribute(attrName);
        //取得属性的文字
        String text=attribute.getText();
        return text;
    }

}
