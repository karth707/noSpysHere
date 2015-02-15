package com.noSpysHere.web;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.noSpysHere.domain.NewUser;
import com.noSpysHere.domain.UserInfo;
import com.noSpysHere.domain.UserRole;
import com.noSpysHere.service.db.UserInfoDAO;

@Controller
@RequestMapping(value = {"/user/register", "/signup/register", "/register"})
public class RegisterController {

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private UserInfoDAO userInfoDAO;
	
	@RequestMapping(method = RequestMethod.GET)
    public String viewRegistration(Map<String, Object> model) {
        NewUser userForm = new NewUser();    
        model.put("userForm", userForm);
        return "register";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processRegistration(@ModelAttribute("userForm") NewUser newUser,
            Map<String, Object> model) {

		if(!isAlphaNumeric(newUser.getUsername()) || !isAlphaNumeric(newUser.getPassword()) 
					|| !isAlphaNumeric(newUser.getPassword_confirm())){
			model.put("newUserError", "Invalid Invalid charecters in from");
			return "register";
		}
		if(userExists(newUser.getUsername())){
			model.put("newUserError", "Username Taken!");
			return "register";
		}else if(newUser.getUsername()==""){
			model.put("newUserError", "invalid username!");
			return "register";
		}else if(newUser.getPassword().length()<4){
			model.put("newUserError", "password must be atleast 4 characters!");
			return "register";
		}else if(!newUser.getPassword().equals(newUser.getPassword_confirm())){
			System.out.println(newUser.getPassword() + ":::" + newUser.getPassword_confirm());
			model.put("newUserError", "password must match!");
			return "register";
		}else{
			updateDB(newUser);
			return "login";
		}
	}

	private void updateDB(NewUser newUser) {
		UserInfo userInfo = new UserInfo();
		userInfo.setPassword(newUser.getPassword());
		userInfo.setUsername(newUser.getUsername());
		userInfoDAO.insert(userInfo);
		
		UserRole userRole = new UserRole();
		userRole.setRole("ROLE_USER");
		userRole.setUsername(newUser.getUsername());
		userInfoDAO.insertUserRole(userRole);
		
	}

	private boolean userExists(String username) {
		if(userInfoDAO.findById(username)!=null){
			return true;
		}
		return false;
	}
	
	private boolean isAlphaNumeric(String s){
	    String pattern= "^[a-zA-Z0-9@.]*$";
	        if(s.matches(pattern)){
	            return true;
	        }
	        return false;   
	}
}
