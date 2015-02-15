package com.noSpysHere.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.noSpysHere.domain.Message;
import com.noSpysHere.service.db.UserInfoDAO;
import com.noSpysHere.util.Utils;

@Controller
public class ListController {

	protected final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private UserInfoDAO userInfoDAO;
	
	@RequestMapping(value="/message/list", method = RequestMethod.GET)
    public String handleHomeRequest(HttpServletRequest request, Model model){
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addAttribute("username", userDetail.getUsername());
			Utils.spyCodeStuff(request.getSession(), "3", userDetail.getUsername());
			List<Message> messages;
			if((int)request.getSession().getAttribute("isASpy") == 1){
				messages = userInfoDAO.getAllSpyMessages();
			}else{
				messages = userInfoDAO.getAllMessages();
			}
			model.addAttribute("messages", messages);
			return "list";
		}
		return "403";
	}
}
