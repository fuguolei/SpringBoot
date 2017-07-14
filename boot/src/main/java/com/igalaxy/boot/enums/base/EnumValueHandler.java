package com.igalaxy.boot.enums.base;


import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.lang.reflect.ParameterizedType;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by fuguolei
 */
public class EnumValueHandler <E extends ValueEnum> extends BaseTypeHandler<E> {


    private Class<ValueEnum> type;

    @SuppressWarnings("unchecked")
    public EnumValueHandler() {
        ParameterizedType parameterizedType = (ParameterizedType)this.getClass().getGenericSuperclass();
        type= (Class<ValueEnum>)(parameterizedType.getActualTypeArguments()[0]);
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, ValueEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getValue());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int i = rs.getInt(columnName);
        if (rs.wasNull()) {
            return null;
        } else {
            return getValuedEnum(i);
        }
    }

    @Override
    public E getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int i = cs.getInt(columnIndex);
        if (cs.wasNull()) {
            return null;
        } else {
            return getValuedEnum(i);
        }
    }

    @SuppressWarnings("unchecked")
    private E getValuedEnum(int value) {
        ValueEnum[] objs = type.getEnumConstants();
        for (ValueEnum em : objs) {
            if (em.getValue() == value) {
                return (E) em;
            }
        }
        throw new IllegalArgumentException("Cannot convert " + value + " to " + type.getSimpleName() + " by value.");
    }
}
