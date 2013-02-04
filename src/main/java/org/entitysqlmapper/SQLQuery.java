/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entitysqlmapper;

import java.sql.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jvergara
 */
public abstract class SQLQuery<T>
{
    
    protected SQLClassInfo classInfo;
    protected Object model;

    private Format timeStampFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private List parameters;
    
    
    public abstract String getSql();

    public void update(Connection con) throws SQLException
    {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement(getSql());
            fillParams(stm);
            stm.executeUpdate();
        }
        catch (SQLException e) {
            throw e;
        }
        finally {
            stm.close();
        }
    }

    public ResultSet execute(Connection con) throws SQLException
    {
        PreparedStatement stm = con.prepareStatement(getSql());
        fillParams(stm);
        return stm.executeQuery();
    }
    
    public List<T> getResultList(Connection con) throws SQLException
    {
        ResultSet rs = null;
        try {
            rs = execute(con);
            List list = new ArrayList();

            ResultSetMetaData meta = rs.getMetaData();
            
            while (rs.next()) {
                Object bean = classInfo.createInstance();
                for (int i = 1; i <= meta.getColumnCount(); ++i) {
                    String colname = meta.getColumnName(i);
                    Object value = rs.getObject(i);
                    if( bean instanceof Map ) {
                        ((Map) bean).put(colname, value);
                    }
                    else {
                        SQLFieldInfo fieldInfo = classInfo.getFieldByColumnName(colname);
                        if( fieldInfo != null ) {
                            fieldInfo.setValue(bean, value);
                        }
                    }
                }
                list.add(bean);
            }
            
            return list;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            rs.close();
        }
    }

    private void fillParams(PreparedStatement stm) throws SQLException
    {
        int idx = 1;
        for (Object p : getParameters()) {
            if( p instanceof java.util.Date ) {
                p = java.sql.Timestamp.valueOf( timeStampFormat.format(p) );
            }
            stm.setObject(idx, p);
            idx++;
        }
    }

    public List getParameters()
    {
        return (parameters != null)
                ? parameters
                : (parameters = new ArrayList());
    }

    /**
     * @return the classInfo
     */
    public SQLClassInfo getClassInfo()
    {
        return classInfo;
    }

    /**
     * @param classInfo the classInfo to set
     */
    public void setClassInfo(SQLClassInfo classInfo)
    {
        this.classInfo = classInfo;
    }

    /**
     * @return the bean
     */
    public Object getModel()
    {
        return model;
    }

    /**
     * @param bean the bean to set
     */
    public void setModel(Object model)
    {
        this.model = model;
    }
}
