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
public class SQLInsertQuery extends SQLQuery
{
    private String sql;
    
    @Override
    public String getSql()
    {
        if( sql != null ) return sql;
        
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();
        List params = getParameters();
        
        for(SQLFieldInfo f : classInfo.getFields()) {
            if( columns.length() > 0 ) columns.append(", ");
            if( values.length() > 0 )  values.append(", ");
            
            columns.append(f.getColumnName());
            values.append("?");
            
            params.add( f.getValue(model) );
        }
        
        String tblname = classInfo.getTableName();
        sql = "insert into " + tblname + "(" +columns+ ") values(" +values+ ")";
        return sql;
    }

}
