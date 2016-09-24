package com.sicdlib.service.imple;

import com.sicdlib.dto.Corpus_WordList;
import edu.xjtsoft.base.service.DefaultEntityManager;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by maninit on 2016/9/22.
 */
@Service
@Transactional
public class Corpus_WordListService extends DefaultEntityManager<Corpus_WordList> {
}
