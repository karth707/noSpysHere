package com.noSpysHere.service.db;

import com.noSpysHere.domain.UserInfo;
import com.noSpysHere.domain.UserRole;

public interface UserInfoDAO {
	public boolean insert(UserInfo userInfo);
	public UserInfo findById(String username);
	public boolean insertUserRole(UserRole userRoles);
}
