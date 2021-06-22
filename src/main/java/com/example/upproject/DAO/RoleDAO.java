package com.example.upproject.DAO;

import com.example.upproject.Entities.Role;
import com.example.upproject.Entities.User;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component
public class RoleDAO extends AbstractHibernateDAO<Role>{
    public RoleDAO(){
        setClazz(Role.class );
    }
    @Override
    public void deleteById(long id) {
        Session session = getCurrentSession();
        this.makeTransaction();
        Role role = findOne( id);
        for(User user:role.getUsers()){
            user.getRoles().remove(role);
            session.merge(user);
        }
        session.delete(role);
        session.getTransaction().commit();
    }
}
