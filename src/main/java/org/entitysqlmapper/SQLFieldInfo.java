/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entitysqlmapper;

import java.lang.reflect.Field;
import javax.persistence.Column;
import javax.persistence.Id;

/**
 *
 * @author jvergara
 */
public class SQLFieldInfo
{
    private Field field;
    private String columnName;
    private boolean primary;
    
    public SQLFieldInfo(Field field)
    {
        this.field = field;
        init();
    }
    
    protected void init() {
        columnName = field.getName().toLowerCase();
        if( field.isAnnotationPresent(Column.class) ) {
            Column c = field.getAnnotation(Column.class);
            if( c.name() != null && c.name().length() > 0 ) {
                columnName = c.name();
            }
        }
        if( field.isAnnotationPresent(Id.class) ) {
            primary = true;
        }
    }
    
    public void setValue(Object bean, Object value)
    {
        boolean canAccess = field.isAccessible();
        try {
            field.setAccessible(true);
            field.set(bean, value);
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            field.setAccessible(canAccess);
        }
    }
    
    public Object getValue(Object bean)
    {
        boolean canAccess = field.isAccessible();
        try {
            field.setAccessible(true);
            return field.get(bean);
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            field.setAccessible(canAccess);
        }
    }
    
    public String getName()
    {
        return field.getName();
    }

    /**
     * @return the columnName
     */
    public String getColumnName()
    {
        return columnName;
    }

    /**
     * @param columnName the columnName to set
     */
    public void setColumnName(String columnName)
    {
        this.columnName = columnName;
    }

    /**
     * @return the primary
     */
    public boolean isPrimary()
    {
        return primary;
    }
}
