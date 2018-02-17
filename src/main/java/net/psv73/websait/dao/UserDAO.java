package net.psv73.websait.dao;

import net.psv73.websait.model.User;
import net.psv73.websait.util.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class UserDAO {

    public static User getUserByLoginPass(String login, String pass) {
        User user = null;

        Session session = HibernateUtils.getSession();
        Transaction tx = session.beginTransaction();

        try {
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("name", login));
            criteria.add(Restrictions.eq("password", pass));

            user = (User) criteria.uniqueResult();

            // HQL
/*
            Query query = session.createQuery("from User where name = :userName and " +
                    " password = :password");

            query.setParameter("userName", login);
            query.setParameter("password", pass);

            user = (User) query.uniqueResult();
*/

            tx.commit();
        } catch (Throwable ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }

        return user;
    }

    public static boolean addUser(User user) {

        boolean rez = true;

/*        try (Session session = HibernateUtils.getSession()) {
            session.beginTransaction();

            session.save(user);

            session.getTransaction().commit();
        } catch (Throwable e) {
            rez = false;
            e.printStackTrace();
        }*/

        return rez;
    }
}