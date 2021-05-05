package com.vCare4u.daoServices;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vCare4u.Entity.Expert;

public interface ExpertDao extends JpaRepository<Expert, Long>{
	
}
