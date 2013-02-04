/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entitysqlmapper;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author jvergara
 */
public class SQLClassDef
{
    private Map<Class, SQLClassInfo> classInfos;
    
    public SQLClassDef()
    {
        classInfos = new ConcurrentHashMap();
    }
    
    public SQLClassInfo getInfo(Class src)
    {
        SQLClassInfo info = classInfos.get(src);
        if( info == null ) {
            info = new SQLClassInfo(src);
            classInfos.put(src, info);
        }
        return info;
    }
}
