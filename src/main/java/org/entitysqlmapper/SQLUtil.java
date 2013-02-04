/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entitysqlmapper;

import org.entitysqlmapper.query.SQLPlainQuery;
import org.entitysqlmapper.query.SQLUpdateQuery;
import org.entitysqlmapper.query.SQLInsertQuery;
import org.entitysqlmapper.query.SQLDeleteQuery;

/**
 *
 * @author jvergara
 */
public class SQLUtil
{
    private static SQLUtil instance;
    
    public static synchronized SQLUtil getInstance()
    {
        return ( instance != null ) 
                ? instance
                : (instance = new SQLUtil());
    }
    
    
    private SQLClassDef classDef;
    
    public SQLUtil()
    {
        classDef = new SQLClassDef();
    }
    
    public synchronized SQLClassInfo getClassInfo(Class type)
    {
        return classDef.getInfo(type);
    }
    
    public SQLQuery createInsertQuery(Object model)
    {
        SQLQuery qry = new SQLInsertQuery();
        qry.setClassInfo(classDef.getInfo(model.getClass()));
        qry.setModel(model);
        return qry;
    }
    
    public SQLQuery createUpdateQuery(Object model)
    {
        SQLQuery qry = new SQLUpdateQuery();
        qry.setClassInfo(classDef.getInfo(model.getClass()));
        qry.setModel(model);
        return qry;
    }
    
    public SQLQuery createDeleteQuery(Object model)
    {
        SQLQuery qry = new SQLDeleteQuery();
        qry.setClassInfo(classDef.getInfo(model.getClass()));
        qry.setModel(model);
        return qry;
    }
    
    public <T>SQLQuery createQuery(Class<T> result, String sql, Object ... params)
    {
        SQLQuery qry = new SQLPlainQuery(sql, params);
        qry.setClassInfo(classDef.getInfo(result));
        return qry;
    }
}
