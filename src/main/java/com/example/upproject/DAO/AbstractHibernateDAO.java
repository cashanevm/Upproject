package com.example.upproject.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractHibernateDAO< T extends Serializable>{
    private Class< T > clazz;

    @Autowired
    private SessionFactory sessionFactory;

    public void setClazz(Class< T > clazzToSet) {
        clazz = clazzToSet;
    }

    public T findOne(long id) {

        Session session = getCurrentSession();
        this.makeTransaction();
        T t = (T) session.get( clazz, id );
        return t;
    }
    public List< T > findAll() {
        return getCurrentSession().createQuery( "from " + clazz.getName() ).list();
    }

    public void save(T entity) {
        Session session = getCurrentSession();
        this.makeTransaction();
        session.persist( entity );
        session.getTransaction().commit();
    }

    public void update(T entity) {
        Session session = getCurrentSession();
        this.makeTransaction();
        session.merge( entity );
        session.getTransaction().commit();

    }

    public void delete(T entity) {
        Session session = getCurrentSession();
        this.makeTransaction();
        session.delete( entity );
        session.getTransaction().commit();
    }
    public void deleteById(long id) {
        Session session = getCurrentSession();
        this.makeTransaction();
        final T entity = findOne( id);
        delete( entity );
        session.getTransaction().commit();
    }

    protected final Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    public Transaction makeTransaction() {
        Session session = getCurrentSession();
        if(session.getTransaction().getStatus().equals(TransactionStatus.NOT_ACTIVE)){
            session.getTransaction().begin();
        }
        return session.getTransaction();
    }
}
