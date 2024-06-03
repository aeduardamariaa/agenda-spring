package com.agenda;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

import com.agenda.model.UserData;

public class AuthentificationTest {

    @Test
    void validate() {
        Authentification auth = Authentification.tryLogin("teste", "123456");
        
        if (auth.getUser() == null) {
            Session session = HibernateUtil
                .getSessionFactory().getCurrentSession();
            
            Transaction transaction = session.beginTransaction();

            UserData user = new UserData();

            user.setFirstName("Testo");
            user.setLastName("Comum");
            user.setUsername("teste");
            user.setPassword("123456");
            user.setRandPass(false);

            session.save(user);

            transaction.commit();
        }

        auth = Authentification.tryLogin("teste", "123456");
        assertTrue(auth.getUser() != null);
    }
}
