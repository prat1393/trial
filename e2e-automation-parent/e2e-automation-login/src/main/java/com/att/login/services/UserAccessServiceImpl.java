package com.att.login.services;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.att.user.dao.UserRepository;
import com.att.user.dao.model.User;

@Service
@Repository
public class UserAccessServiceImpl implements UserAccessService {

	private UserRepository repository;

	@Inject
	public UserAccessServiceImpl(UserRepository repository) {
		super();
		this.repository = repository;
	}

	public User getUserByAttId(String attId) {
		// repository.count();
		return repository.findOne(attId);
	}

}
