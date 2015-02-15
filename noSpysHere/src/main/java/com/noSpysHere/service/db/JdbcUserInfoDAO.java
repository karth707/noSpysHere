package com.noSpysHere.service.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.noSpysHere.domain.UserInfo;
import com.noSpysHere.domain.UserRole;

public class JdbcUserInfoDAO implements UserInfoDAO{
	
	protected final Log logger = LogFactory.getLog(getClass());

	private JdbcTemplate jdbcTemplate;
	public JdbcUserInfoDAO(DataSource dataSource){
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public boolean insert(UserInfo userInfo){
		try{
			String sql = "INSERT INTO userInfo "
					+ "(username, password) "
					+ "VALUES (?, ?)";
			logger.info("Running query: " + sql);
			jdbcTemplate.update(sql, userInfo.getUsername(), userInfo.getPassword());
			return true;
		}catch(Exception ex){
			logger.error("Database insert failed...");
		}
		return false;
	}
	
	public boolean insertUserRole(UserRole userRoles){
		try{
			String sql = "INSERT INTO user_roles " 
						+ "(userId, ROLE)"
						+ "VALUES (?, ?)";
			logger.info("Running query: " + sql);
			jdbcTemplate.update(sql, userRoles.getUserId(), userRoles.getRole());
			return true;
		}catch(Exception ex){
			logger.error("Database insertUserRole failed...");
		}
		return false;
	}
	
	public UserInfo findById(String username) {
		String sql = "SELECT * FROM userInfo WHERE username = "+"\"" + username + "\"";
		logger.info("Running query: " + sql);
		return jdbcTemplate.query(sql, new ResultSetExtractor<UserInfo>(){
			public UserInfo extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if(rs.next()){
					UserInfo userInfo = new UserInfo(rs.getString("userId"), rs.getString("password")
													, rs.getString("knock"));
					return userInfo;
				}
				return null;
			}
		});	
	}
}
