package com.hbwh.xj.myblog.util.typehandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hbwh.xj.myblog.util.tool.JsonUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.io.IOException;
import java.sql.*;

@MappedJdbcTypes(value={JdbcType.VARCHAR})
@MappedTypes(value={Object.class})
public class JsonTypeHandler extends BaseTypeHandler<Object> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        try {
            ps.setString(i, JsonUtils.stringify(parameter));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getNullableResult(ResultSet rs, String columnName) throws SQLException {
        try {
            return JsonUtils.parse(rs.getString(columnName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        try {
            return JsonUtils.parse(rs.getString(columnIndex));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        try {
            return JsonUtils.parse(cs.getString(columnIndex));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
