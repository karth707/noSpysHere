package com.noSpysHere.service.db;

import java.util.List;

import com.noSpysHere.domain.Message;
import com.noSpysHere.domain.UserInfo;
import com.noSpysHere.domain.UserRole;

public interface UserInfoDAO {
	public boolean insert(UserInfo userInfo);
	public UserInfo findById(String username);
	public boolean insertUserRole(UserRole userRoles);
	public boolean addMessage(Message message, String username);
	public boolean addSpyMessage(Message message, String username);
	public List<Message> getAllMessages();
}
