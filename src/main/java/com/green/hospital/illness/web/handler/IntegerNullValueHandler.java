package com.green.hospital.illness.web.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IntegerNullValueHandler implements TypeHandler<Integer> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Integer s, JdbcType jdbcType) throws SQLException {
        if(null == s && jdbcType == JdbcType.INTEGER){
            preparedStatement.setInt(i, 0);
        }else{
            preparedStatement.setInt(i, s);
        }
    }

    @Override
    public Integer getResult(ResultSet resultSet, String s) throws SQLException {
        return resultSet.getInt(s);
    }


    @Override
    public Integer getResult(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getInt(i);
    }

    @Override
    public Integer getResult(CallableStatement callableStatement, int i) throws SQLException {
        return callableStatement.getInt(i);
    }
}
