package com.sicdlib.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sicdlib.entity.UserType;

import edu.xjtsoft.base.service.DefaultEntityManager;

@Service
@Transactional
public class UserTypeService extends DefaultEntityManager<UserType> {
	
}
