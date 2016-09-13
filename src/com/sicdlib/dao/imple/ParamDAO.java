package com.sicdlib.dao.imple;

import com.sicdlib.dao.IBaseDAO;
import com.sicdlib.dao.IParamDAO;
import com.sicdlib.dto.ParamType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Yh on 2016/9/13.
 */
@Repository
public class ParamDAO implements IParamDAO {
    @Autowired
    IBaseDAO baseDAO;

    @Override
    public int getParamValue(String paramType, String param) {
        String hql = "select p.value " +
                "from Param p, ParamType pt " +
                "where p.name = '" + param + "' and pt.name = '" + paramType + "'";

        int value = (int) baseDAO.get(hql);

        return value;
    }
}
