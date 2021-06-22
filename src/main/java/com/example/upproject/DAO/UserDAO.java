package com.example.upproject.DAO;

import com.example.upproject.Entities.Role;
import com.example.upproject.Entities.User;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component
public class UserDAO extends AbstractHibernateDAO<User>{
    public UserDAO(){
        setClazz(User.class );
    }
    @Override
    public void deleteById(long id) {
        Session session = getCurrentSession();
        this.makeTransaction();
        User user = findOne( id);
        for(Role role: user.getRoles()){
            user.removeRole(role);
        }
        session.delete(user);
        session.getTransaction().commit();
    }
}
