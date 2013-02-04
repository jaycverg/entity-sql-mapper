/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entitysqlmapper.query;

import org.entitysqlmapper.SQLQuery;


/**
 *
 * @author jvergara
 */
public class SQLPlainQuery extends SQLQuery
{
    private String sql;
        
    public SQLPlainQuery(String sql, Object ... params) {
        this.sql = sql;
        for(Object o : params) {
            getParameters().add(o);
        }
    }
    
    @Override
    public String getSql()
    {
        return sql;
    }
    
}
