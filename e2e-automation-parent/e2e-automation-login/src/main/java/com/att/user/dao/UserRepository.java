package com.att.user.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.att.user.dao.model.User;

@Transactional
public interface UserRepository extends JpaRepository<User, String> {

}
