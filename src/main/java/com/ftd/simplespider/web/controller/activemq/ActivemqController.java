package com.ftd.simplespider.web.controller.activemq;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftd.activemq.producer.queue.QueueSender;
import com.ftd.activemq.producer.topic.TopicSender;

/**
 * 
 * @author liang
 * @description controller测试
 */
@Controller
@RequestMapping("/activemq")
public class ActivemqController {
	
	@Resource 
	QueueSender queueSender;
	@Resource 
	TopicSender topicSender;
	
	/**
	 * 发送消息到队列
	 * Queue队列：仅有一个订阅者会收到消息，消息一旦被处理就不会存在队列中
	 * @param message
	 * @return String
	 */
	@RequestMapping("queueSender")
	public String queueSender(HttpServletRequest request, HttpServletResponse response){
		String message = request.getParameter("message");
		if(StringUtils.isEmpty(message)){
			request.setAttribute("result", "队列发送内容:消息为空");
		}else{
			String opt="";
			try {
				queueSender.send("test.queue", message);
				opt = "suc";
			} catch (Exception e) {
				opt = e.getCause().toString();
			}
			request.setAttribute("result", "队列发送内容:"+opt);
		}
		return "/template/activemq/info";
	}
	
	/**
	 * 发送消息到主题
	 * Topic主题 ：放入一个消息，所有订阅者都会收到 
	 * 这个是主题目的地是一对多的
	 * @param message
	 * @return String
	 */
	@RequestMapping("topicSender")
	public String topicSender(HttpServletRequest request, HttpServletResponse response){
		String message = request.getParameter("message");
		if(StringUtils.isEmpty(message)){
			request.setAttribute("result", "订阅发送内容:消息为空");
		}else{
			String opt = "";
			try {
				topicSender.send("test.topic", message);
				opt = "suc";
			} catch (Exception e) {
				opt = e.getCause().toString();
			}
			request.setAttribute("result", "订阅发送内容:"+opt);
		}
		return "/template/activemq/info";
	}
	
}
