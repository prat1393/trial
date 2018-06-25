package com.att.login.services;

import com.att.user.dao.model.User;

public interface UserAccessService {
	public User getUserByAttId(String attId);
}
