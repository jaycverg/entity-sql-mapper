package org.entitysqlmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

/**
 *
 * @author jvergara <jvergara@gocatapult.com>
 */
public class EntityBeanResultSetExtractor implements ResultSetExtractor
{
    private EntityBeanRowMapper rowMapper;
    
    public EntityBeanResultSetExtractor(Class entityClass)
    {
        rowMapper = new EntityBeanRowMapper(entityClass);
    }

    @Override
    public Object extractData(ResultSet rs) throws SQLException, DataAccessException
    {
        if (rs.next()) {
            return rowMapper.mapRow(rs, 1);
        }
        return null;
    }

}
