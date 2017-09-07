package com.sx.struts;

import com.sx.struts.view.View;
import com.sx.utils.MyGetSet;
import com.sx.utils.MyParseXML;
import org.dom4j.Element;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Struts {
    public static View runAction(String actionName, Map<String, String> parameters) throws Exception {

        Element root = MyParseXML.getRoot(MyParseXML.PATH);

        Element actionElement = MyParseXML.getElementByName(root,actionName);

        String className = MyParseXML.getAttrTextByClassName(actionElement,"class");

        //0.读取配置文件struts.xml
//        String className = null;
        //这里应该把action存一下,因为返回结果中需要从action中查询result节点的name文本值
        //获取action
//        className = MyParseXML.getFullClassNameByActionName(actionName);
        // 1.根据actionName 找到对应到class ,例如LoginAction,通过反射实例化对象.
        //根据parameters中的参数，调用对象到setter方法，例如parameters中的数据是("name"="acn")
        //那么就调用setName()
        Class actionClass = Class.forName(className);
        LoginAction loginAction = (LoginAction) actionClass.newInstance();

        //获取key,通过key来创建set方法名称.

        Set<Map.Entry<String, String>> entries = parameters.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            String key = entry.getKey();
            String value = entry.getValue();
            Method method = LoginAction.class.getMethod(MyGetSet.getSetMethodName(key), String.class);
            method.invoke(loginAction, value);
        }


        //2.通过反射调用对象的execute方法,并获得返回值,例如success.
        Method executeMethod = LoginAction.class.getMethod("execute");
        String result = (String)executeMethod.invoke(loginAction);

        //3.通过反射找到对象的所有getter(),并调用获取值,将值和属性形成一个HashMap,放入View的parameters中.
        int i = 1;
        Map parametersMap = new HashMap<String,String>();
        for(PropertyDescriptor propertyDescriptor :
                Introspector.getBeanInfo(LoginAction.class).getPropertyDescriptors()){
            if(i > 1){
                Method method = propertyDescriptor.getReadMethod();
                String methodName = method.getName();
                methodName =  MyGetSet.getNameByGetMethod(methodName);
                String value = (String)method.invoke(loginAction);
                parametersMap.put(methodName,value);
            }
            i++;

        }
        View view = new View();
        view.setParameters(parametersMap);

        //4.根据struts.xml配置文件中,<result>配置,以及execute到返回值,确定哪一个jsp路径放到View对象的jsp中.
        String jssURL = MyParseXML.getElementByName(actionElement,result).getText();
        view.setJsp(jssURL);


        return view;

    }
}
