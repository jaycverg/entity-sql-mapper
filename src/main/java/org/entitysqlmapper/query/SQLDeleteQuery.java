/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entitysqlmapper.query;

import java.util.List;
import org.entitysqlmapper.SQLFieldInfo;
import org.entitysqlmapper.SQLQuery;

/**
 *
 * @author jvergara
 */
public class SQLDeleteQuery extends SQLQuery
{
    private String sql;
    
    @Override
    public String getSql()
    {
        if( sql != null ) return sql;
        
        StringBuilder ids = new StringBuilder();
        List params = getParameters();

        for(SQLFieldInfo f : classInfo.getPrimaryFields()) {
            if( ids.length() > 0 ) ids.append(" and ");
            ids.append(f.getColumnName() + "=?");
            params.add(f.getValue(model));
        }
        
        String tblname = classInfo.getTableName();
        sql = "delete from " + tblname + " where " + ids;
        return sql;
    }

}
