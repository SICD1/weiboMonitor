package com.sicdlib.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sicdlib.entity.User_userType;

import edu.xjtsoft.base.service.DefaultEntityManager;

@Service
@Transactional
public class User_userTypeService extends DefaultEntityManager<User_userType> {
	
}
