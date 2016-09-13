package com.sicdlib.service.imple;

import com.sicdlib.dao.IParamDAO;
import com.sicdlib.service.IParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Yh on 2016/9/13.
 */
@Service("paramService")
public class ParamService implements IParamService{
    @Autowired
    IParamDAO paramDAO;

    @Override
    public int getParamValue(String paramType, String param) {
        int value = paramDAO.getParamValue(paramType, param);
        return value;
    }
}
