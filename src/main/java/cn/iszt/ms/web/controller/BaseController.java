package cn.iszt.ms.web.controller;

import javax.annotation.Resource;

import cn.iszt.ms.service.DataExistsException;
import cn.iszt.ms.service.UserExistsException;
import cn.iszt.ms.web.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

public class BaseController {
	
	private static Logger logger = LoggerFactory.getLogger("BaseController");
	
	@Resource
	private MessageSource messageSource;
	
	@ExceptionHandler
	@ResponseBody
	public Result exception(Exception e) {
		
		String msg = null;
		if (e instanceof IllegalArgumentException) {
			msg = e.getMessage();
		} 
		else if (e instanceof UserExistsException) {
			msg = e.getMessage();
		} 
		else if (e instanceof DataExistsException) {
			msg = e.getMessage();
		}
		else {
			logger.error("系统出错", e);
			msg = "system.error";
		}
		
		String message = null;
		
		try {
			message = this.messageSource.getMessage(msg, null, null);
		} catch (NoSuchMessageException nsme) {
			message = msg;
		}
				
		return new Result(message);
	}
	
}
