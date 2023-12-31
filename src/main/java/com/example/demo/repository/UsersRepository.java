package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Users;

@Repository("userrepo")
public interface UsersRepository extends JpaRepository<Users, Integer> {

	@Modifying
	@Transactional
	@Query("UPDATE Users u SET u.password=:pass WHERE u.user_id=:id")
	public int updateUsersPassword(String pass, int id);
	
	@Query("SELECT u FROM Users u WHERE u.user_email=?1")
	public Users getUserByEmailId(String email);
	
	
	public Users findByUsername(String uname);
}
