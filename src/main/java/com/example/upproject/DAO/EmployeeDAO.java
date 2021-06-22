package com.example.upproject.DAO;

import com.example.upproject.Entities.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDAO extends AbstractHibernateDAO< Employee >{
    public EmployeeDAO(){
        setClazz(Employee.class );
    }

}
