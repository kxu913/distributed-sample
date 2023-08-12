package com.kevin.sample.mapper.handler;

import com.kevin.sample.constants.AlertStatus;
import com.kevin.sample.constants.KeyValueEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(value = {EnumTypeHandler.class})
public class EnumTypeHandler<T extends KeyValueEnum> extends BaseTypeHandler<T> {
    private final T[] enums;

    public EnumTypeHandler(Class<T> type){
        this.enums = type.getEnumConstants();
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, KeyValueEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i,parameter.getValue());
    }

    @Override
    public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return getEnum(rs.getString(columnName));
    }

    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return getEnum(rs.getString(columnIndex));
    }

    @Override
    public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getEnum(cs.getString(columnIndex));
    }

    private T getEnum(String key){
        for (T keyValue : enums) {
            if (keyValue.getKey().equalsIgnoreCase(key)){
                return keyValue;
            }
        }
        return null;
    }


}
