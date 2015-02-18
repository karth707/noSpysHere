package com.noSpysHere.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.noSpysHere.domain.Message;
import com.noSpysHere.service.db.UserInfoDAO;
import com.noSpysHere.util.Utils;

@Controller
public class MessageController {

	@Autowired
	private UserInfoDAO userInfoDAO;
	
	@RequestMapping(value="/message/add", method = RequestMethod.GET)
	public String viewRegistration(Map<String, Object> model, HttpServletRequest request) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			Utils.spyCodeStuff(request.getSession(), "2", userDetail.getUsername());
			Message messageForm = new Message();    
	        model.put("messageForm", messageForm);
	        return "message";
		}
		return "403";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processMessage(@ModelAttribute("messageForm") Message message,
            Model model, HttpServletRequest request) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addAttribute("username", userDetail.getUsername());
			model.addAttribute("empty", "");
			if(!isAlphaNumeric(message.getMessage()) || !isAlphaNumeric(message.getTitle())){
				model.addAttribute("info", "Invalid charecters in text");
				return "message";
			}
			if((int)request.getSession().getAttribute("isASpy") == 1){
				userInfoDAO.addSpyMessage(message, userDetail.getUsername());
			}else{
				userInfoDAO.addMessage(message, userDetail.getUsername());
			}
			model.addAttribute("info", "Message added successfully");
			return "message";
		}
		return "403";
	}
	
	private boolean isAlphaNumeric(String s){

		if(s.equals("") || s=="" || s==null){
			return false;
		}
		String pattern= "^[.,a-zA-Z0-9 ]*$";
	        if(s.matches(pattern)){
	            return true;
	        }
	        return false;   
	}
}
