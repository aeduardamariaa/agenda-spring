package com.agenda;

import org.hibernate.Transaction;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import com.agenda.model.Event;
import com.agenda.model.UserData;

public class EventTest {
    @Test
    void list_events() {
    
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        UserData user = new UserData();

        user.setFirstName("Testo");
        user.setLastName("Comum");
        user.setUsername("teste");
        user.setPassword("123456");
        user.setRandPass(false);

        session.save(user);

        transaction.commit();

        // --------------------------------------------
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction1 = session.beginTransaction();
        
        Query query = session.createQuery(
            "from Event where IdUser = :user"
        );

        query.setParameter("user", user.getId());

        List<Event> events = query.list();

        transaction1.commit();

        // --------------------------------------------

        if (events.size() == 0) {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction transaction2 = session.beginTransaction();
            Event newEvent = new Event();
            Date date = new Date(2002, 9,7);

            newEvent.setDate(date);
            newEvent.setName("Aniversário do Trevisan");
            newEvent.setDescription("Club Bosch");
            newEvent.setUser(user);
            newEvent.setDone(true);
            session.save(newEvent);
            transaction2.commit();
        }


        session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction3 = session.beginTransaction();
        query = session.createQuery(
            "from Event where IdUser = :user"
        );
        query.setParameter("user", user.getId());
        events = query.list();
        transaction3.commit();

        assertTrue(events.get(0).getName().equals("Aniversário do Trevisan"));
    }
}
