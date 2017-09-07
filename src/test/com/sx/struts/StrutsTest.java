package test.com.sx.struts; 

import com.sx.struts.Struts;
import com.sx.struts.view.View;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.HashMap;
import java.util.Map;

/** 
* Struts Tester. 
* 
* @author <Authors name> 
* @since <pre>九月 4, 2017</pre> 
* @version 1.0 
*/ 
public class StrutsTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: runAction(String actionName, Map<String, String> parameters) 
* 
*/ 
@Test
public void testRunAction() throws Exception { 
//TODO: Test goes here... 
}
@Test
public void testLoginActionSuccess() throws Exception {
    String actionName = "login";
    Map<String,String> params = new HashMap<String, String>();
    params.put("name","test1");
    params.put("password","1234");
    View view = Struts.runAction(actionName,params);

//    Assert.assertEquals("/jsp/homepage.jsp",view.getJsp());
    System.out.println(view.getParameters().get("message"));
    System.out.println("view jsp:"+view.getJsp());


}


} 
