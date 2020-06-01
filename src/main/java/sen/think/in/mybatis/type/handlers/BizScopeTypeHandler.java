package sen.think.in.mybatis.type.handlers;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@MappedJdbcTypes(JdbcType.VARCHAR)
public class BizScopeTypeHandler extends BaseTypeHandler<List<Integer>> {
    private static final String SEPARATOR = ",";

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<Integer> bizScopeTypes, JdbcType jdbcType) throws SQLException {
        ps.setString(i, StringUtils.join(SEPARATOR));
    }

    @Override
    public List<Integer> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return Arrays.stream(resultSet.getString(s).split(SEPARATOR))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    @Override
    public List<Integer> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return Arrays.stream(resultSet.getString(i).split(SEPARATOR))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    @Override
    public List<Integer> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return Arrays.stream(callableStatement.getString(i).split(SEPARATOR))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
