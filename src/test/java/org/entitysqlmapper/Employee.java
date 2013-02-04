package org.entitysqlmapper;


import javax.persistence.Column;
import javax.persistence.Id;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jvergara
 */
public class Employee
{
    @Id
    private long id;
    
    private String firstName;
    private String lastName;
    private String address;

    
    @Override
    public String toString()
    {
        return getClass().getName() + 
                "[Id: " + id + ", First Name: " + firstName 
                + ", Last Name: " + lastName
                + ", Address: " + address + "]";
    }
    
    /**
     * @return the id
     */
    public long getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id)
    {
        this.id = id;
    }

    /**
     * @return the firstName
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * @return the address
     */
    public String getAddress()
    {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address)
    {
        this.address = address;
    }
}
