package com.renjie120.httpunit.jeesite;

import java.io.IOException;

import org.xml.sax.SAXException;

import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

public class LoginTest {
	public static void main(String[] args) {
		System.out.println("使用Post方式向服务器发送数据，然后获取网页内容：");
		// 建立一个WebConversation实例
		WebConversation wc = new WebConversation();
		// 向指定的URL发出请求
		WebRequest req = new PostMethodWebRequest("http://localhost:8181/online/a/login");
		// 给请求加上参数
		req.setParameter("username", "thinkgem");
		req.setParameter("password", "admin");
		// 获取响应对象
		WebResponse resp;
		try {
			resp = wc.getResponse(req);

			// 用getText方法获取相应的全部内容
			// 用System.out.println将获取的内容打印在控制台上
			System.out.println(resp.getText());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
//		HttpUnitOptions.setScriptingEnabled(false);  
//        WebConversation wc = new WebConversation();  
//        WebRequest request = new PostMethodWebRequest("http://localhost:8181/online/a/login");  
//        WebResponse response = null;  
//        try {  
//            response = wc.getResponse(request);  
//            //获得Html中的form表单，HttpUnit将他封装成WebForm对象  
//            WebForm form = response.getForms()[0];  
//            //WebForm对象提供getParameterValue的方法将根据表单中的name获得对应的value,而不用管该元素的类型。  
//            //对表单进行处理操作  
//            form.setParameter("username", "thinkgem");
//            form.setParameter("password", "admin");
//            //提交表单 获得新的response  
//            response = form.submit();  
//            System.out.println(response.getText());  
//            System.out.println("----------------------------");  
//            // 获得页面链接对象    
//            WebLink link = response.getLinkWith("新闻中心");    
//            //模拟用户单击事件    
//            link.click();    
//            // 获得当前的响应对象    
//            WebResponse nextLink = wc.getCurrentPage();    
//            // 用getText方法获取相应的全部内容    
//            // 用System.out.println将获取的内容打印在控制台上    
//            System.out.println(nextLink.getText());    
//              
//        } catch (IOException e) {  
//            e.printStackTrace();  
//        } catch (SAXException e) {  
//            e.printStackTrace();  
//        }  
//    }  

	}
}
