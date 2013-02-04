/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entitysqlmapper;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author jvergara
 */
public class SQLClassInfo
{
    private Class source;
    private String tableName;
    private Map<String, SQLFieldInfo> fieldsIndex;
    private Map<String, SQLFieldInfo> fieldsColNameIndex;
    private List<SQLFieldInfo> fields;
    private List<SQLFieldInfo> primaryFields;
    
    public SQLClassInfo(Class source)
    {
        this.source = source;
        fieldsIndex = new HashMap();
        fieldsColNameIndex = new HashMap();
        fields = new ArrayList();
        primaryFields = new ArrayList();
        init(source);
    }
    
    private void init(Class source)
    {
        tableName = source.getSimpleName().toLowerCase();
        if( source.isAnnotationPresent(Table.class) ) {
            Table t = (Table) source.getAnnotation(Table.class);
            if( t.name() != null && t.name().length() > 0 ) {
                tableName = t.name();
            }
        }
        
        doIndexFieldsRecursive(source);
    }

    private void doIndexFieldsRecursive(Class source)
    {
        for(Field f : source.getDeclaredFields()){
            if( f.isAnnotationPresent(Transient.class) ) continue;
            if( Modifier.isStatic(f.getModifiers()) )    continue;
            
            SQLFieldInfo finfo = new SQLFieldInfo(f);
            fieldsIndex.put(f.getName(), finfo);
            fieldsColNameIndex.put(finfo.getColumnName(), finfo);
            fields.add(finfo);
            
            if( finfo.isPrimary() )
                primaryFields.add(finfo);
        }
    }
    
    public Object createInstance() throws InstantiationException, IllegalAccessException 
    {
        return source.newInstance();
    }
    
    public String getName()
    {
        return source.getName();
    }
    
    public String getTableName()
    {
        return tableName;
    }
    
    public SQLFieldInfo getField(String name)
    {
        return fieldsIndex.get(name);
    }
    
    public SQLFieldInfo getFieldByColumnName(String name)
    {
        return fieldsColNameIndex.get(name);
    }
    
    public List<SQLFieldInfo> getFields()
    {
        return fields;
    }
    
    public List<SQLFieldInfo> getPrimaryFields()
    {
        return primaryFields;
    }

}
