package org.entitysqlmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author jvergara <jvergara@gocatapult.com>
 */
public class EntityBeanRowMapper implements RowMapper
{

    private Class entityClass;

    public EntityBeanRowMapper(Class entityClass)
    {
        this.entityClass = entityClass;
    }

    @Override
    public Object mapRow(ResultSet rs, int rowIdx) throws SQLException
    {
        try {
            SQLClassInfo classInfo = SQLUtil.getInstance().getClassInfo(entityClass);
            ResultSetMetaData meta = rs.getMetaData();
            Object bean = classInfo.createInstance();
            for (int i = 1; i <= meta.getColumnCount(); ++i) {
                String colname = meta.getColumnName(i);
                Object value = rs.getObject(i);
                if (bean instanceof Map) {
                    ((Map) bean).put(colname, value);
                }
                else {
                    SQLFieldInfo fieldInfo = classInfo.getFieldByColumnName(colname);
                    if (fieldInfo != null) {
                        fieldInfo.setValue(bean, value);
                    }
                }
            }
            return bean;
        }
        catch (Exception e) {
            throw new SQLException(e);
        }
    }
}
