/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.javaee7.ch5.sample4;

import com.home.javaee7.ch05.sample4.AbstractPersistentTest;
import com.home.javaee7.ch05.sample4.Book;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author vitaliy
 */
public class BookIT extends AbstractPersistentTest {

    @Test
    public void shouldCreateABook() throws Exception {

        Book book = new Book("The Hitchhiker's Guide to the Galaxy", 12.5F, 
                "The Hitchhiker's Guide to the Galaxy is a science fiction comedy series created by Douglas Adams.", 
                "1-84023-742-2", 354, false);
        tx.begin();
        em.persist(book);
        tx.commit();
        assertNotNull("ID should not be null", book.getId());
    }

    @Test
    @Ignore("updatable = false does not work")
    public void titleShouldNotBeUpdatable() throws Exception {

        Book book = new Book("The Hitchhiker's Guide to the Galaxy", 12.5F, 
                "The Hitchhiker's Guide to the Galaxy is a science fiction comedy series created by Douglas Adams.", 
                "1-84023-742-2", 354, false);
        tx.begin();
        em.persist(book);
        tx.commit();
        assertNotNull("ID should not be null", book.getId());
        assertEquals("Title should be The Hitchhiker's Guide to the Galaxy", 
                "The Hitchhiker's Guide to the Galaxy", book.getTitle());

        tx.begin();
        book = em.find(Book.class, book.getId());
        assertEquals("Title should be The Hitchhiker's Guide to the Galaxy", 
                "The Hitchhiker's Guide to the Galaxy", book.getTitle());
        book.setTitle("H2G2");
        assertEquals("Title should be H2G2", "H2G2", book.getTitle());
        tx.commit();

        tx.begin();
        book = em.find(Book.class, book.getId());
        assertEquals("Title should be The Hitchhiker's Guide to the Galaxy", 
                "The Hitchhiker's Guide to the Galaxy", book.getTitle());
        tx.commit();
    }
}
