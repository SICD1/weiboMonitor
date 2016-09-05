package com.sicdlib.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sicdlib.entity.Corpus_WordList;

import edu.xjtsoft.base.service.DefaultEntityManager;

@Service
@Transactional
public class Corpus_WordListService extends DefaultEntityManager<Corpus_WordList> {
	
}
