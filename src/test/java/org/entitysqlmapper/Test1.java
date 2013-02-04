package org.entitysqlmapper;


import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import org.junit.Test;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author jvergara
 */
public class Test1
{

    public Test1()
    {
    }

    @Test
    public void test1() throws ClassNotFoundException
    {
        Class.forName("org.postgresql.Driver");        
        Connection con = null;
        
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost/testdb", "postgres", "Change1234");
            SQLUtil sql = SQLUtil.getInstance();

            List<Employee> list = sql.createQuery(Employee.class, "select * from employee")
                           .getResultList(con);
            
            for(Employee e : list) {
                System.out.println( e );
            }
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                con.close();
            }
            catch(Exception ignored){}
        }
    }
}
