package com.noSpysHere.web;

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

import com.noSpysHere.service.db.UserInfoDAO;

@Controller
public class ListController {

	protected final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private UserInfoDAO userInfoDAO;
	
	@RequestMapping(value={"/", "/message/list"}, method = RequestMethod.GET)
    public String handleHomeRequest(Model model){
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addAttribute("username", userDetail.getUsername());
			return "list";
		}
		return "403";
	}
}
