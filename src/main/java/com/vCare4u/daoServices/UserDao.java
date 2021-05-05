package com.vCare4u.daoServices;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vCare4u.Entity.User;

public interface UserDao extends JpaRepository<User, Long>{
	
}
