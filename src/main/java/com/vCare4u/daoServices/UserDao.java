package com.vCare4u.daoServices;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vCare4u.Entity.User;

public interface UserDao extends JpaRepository<User, BigInteger>{
	public User findByEmail(String email);
	public User findByEmailAndPassword(String email,String password);
}
