package test.com.sx.utils;


import com.sx.struts.LoginAction;
import com.sx.struts.Struts;
import com.sx.utils.MyGetSet;
import com.sx.utils.MyParseXML;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
* MyParseXML Tester.
* 
* @author <Authors name> 
* @since <pre>九月 3, 2017</pre> 
* @version 1.0 
*/ 
public class MyParseXMLTest {

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: parse(URL url) 
* 
*/ 
@Test
public void testParse() throws Exception { 
//    MyParseXML.getFullClassNameByActionName("login");
//    File directory = new File("");//参数为空
//    String courseFile = directory.getCanonicalPath() ;
    String className =  MyParseXML.getFullClassNameByActionName("logout");
    System.out.println(className);
}
@Test
public void testMethods(){
    Method method = null;
    try {
        method = LoginAction.class.getMethod("say",String.class);
        String value = "bbbbbbbbb123";
        method.invoke(LoginAction.class.newInstance(),value );
    } catch (Exception e) {
        e.printStackTrace();
    }
//    String methodName = null;
//    methodName = MyGetSet.getSetMethodName("name");
//    System.out.println("methodName:"+methodName);
}
@Test
public void StrutsTest(){
    Map<String,String> parameters = new HashMap<String, String>();
    parameters.put("name","shixin");
    parameters.put("password","codingding");
    try {
        Struts.runAction("login",parameters);
    } catch (Exception e) {
        e.printStackTrace();
    }
}
@Test
public void Test2(){
    try {
        for(PropertyDescriptor propertyDescriptor :
                Introspector.getBeanInfo(LoginAction.class).getPropertyDescriptors()){

            // propertyEditor.getReadMethod() exposes the getter
            // btw, this may be null if you have a write-only property
            String methodName = propertyDescriptor.getReadMethod().getName();
            methodName =  MyGetSet.getNameByGetMethod(methodName);
            System.out.println("readMethod:"+methodName);
        }
    } catch (IntrospectionException e) {
        e.printStackTrace();
    }
}
@Test
public void Test3(){
    t("getClass");
}
    public static void t(String methodName){
        System.out.println(methodName);

//        System.out.println(methodName.substring(3,methodName.length()-3));
    }



} 
