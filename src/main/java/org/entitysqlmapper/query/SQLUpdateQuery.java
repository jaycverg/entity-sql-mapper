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
public class SQLUpdateQuery extends SQLQuery
{
    private String sql;
    
    @Override
    public String getSql()
    {
        if( sql != null ) return sql;
        
        StringBuilder columns = new StringBuilder();
        StringBuilder ids = new StringBuilder();
        List params = getParameters();
        
        for(SQLFieldInfo f : classInfo.getFields()) {
            if( columns.length() > 0 ) columns.append(", ");            
            columns.append(f.getColumnName() + "=?");
            params.add(f.getValue(model));
        }
        
        for(SQLFieldInfo f : classInfo.getPrimaryFields()) {
            if( ids.length() > 0 ) ids.append(" and ");
            ids.append(f.getColumnName() + "=?");
            params.add(f.getValue(model));
        }
        
        String tblname = classInfo.getTableName();
        sql = "update " + tblname + " set " +columns+ " where " + ids;
        return sql;
    }

}
