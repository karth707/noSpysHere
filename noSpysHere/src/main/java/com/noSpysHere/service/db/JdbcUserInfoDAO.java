package com.noSpysHere.service.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.noSpysHere.domain.Message;
import com.noSpysHere.domain.UserInfo;
import com.noSpysHere.domain.UserRole;

public class JdbcUserInfoDAO implements UserInfoDAO{
	
	protected final Log logger = LogFactory.getLog(getClass());

	private JdbcTemplate jdbcTemplate;
	public JdbcUserInfoDAO(DataSource dataSource){
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public boolean insert(UserInfo userInfo, String knockCode){
		try{
			String sql = "INSERT INTO userInfo "
					+ "(username, password, knock) "
					+ "VALUES (?, ?, ?)";
			logger.info("Running query: " + sql);
			jdbcTemplate.update(sql, userInfo.getUsername(), userInfo.getPassword(), knockCode);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			logger.error("Database insert failed...");
		}
		return false;
	}
	
	public boolean insertUserRole(UserRole userRoles){
		try{
			String sql = "INSERT INTO user_roles " 
						+ "(username, ROLE)"
						+ "VALUES (?, ?)";
			logger.info("Running query: " + sql);
			jdbcTemplate.update(sql, userRoles.getUsername(), userRoles.getRole());
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
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
					UserInfo userInfo = new UserInfo(rs.getString("username"), rs.getString("password")
													, rs.getString("knock"));
					return userInfo;
				}
				return null;
			}
		});	
	}

	public boolean addMessage(Message message, String username) {
		try{
			String sql = "INSERT INTO messages " 
						+ "(username, title, message) "
						+ "VALUES (?, ?, ?)";
			logger.info("Running query: " + sql);
			jdbcTemplate.update(sql, username, message.getTitle(), message.getMessage());
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			logger.error("Database insert message failed...");
		}
		return false;
	}

	public boolean addSpyMessage(Message message, String username) {
		try{
			String sql = "INSERT INTO messages_spy " 
						+ "(username, title, message) "
						+ "VALUES (?, ?, ?)";
			logger.info("Running query: " + sql);
			jdbcTemplate.update(sql, username, message.getTitle(), message.getMessage());
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			logger.error("Database insert spy message failed...");
		}
		return false;
	}

	public List<Message> getAllMessages() {
		String sql = "SELECT * FROM messages";
		logger.info("Running query: " + sql);
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Message>>(){
			public List<Message> extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				List<Message> messages = new ArrayList<Message>();
				while(rs.next()){
					Message message = new Message();
					message.setMessage(rs.getString("message"));
					message.setTitle(rs.getString("title"));
					messages.add(message);
				}
				return messages;
			}
		});	
	}

	public List<Message> getAllSpyMessages() {
		String sql = "SELECT * FROM messages_spy";
		logger.info("Running query: " + sql);
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Message>>(){
			public List<Message> extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				List<Message> messages = new ArrayList<Message>();
				while(rs.next()){
					Message message = new Message();
					message.setMessage(rs.getString("message"));
					message.setTitle(rs.getString("title"));
					messages.add(message);
				}
				return messages;
			}
		});	
	}
}
