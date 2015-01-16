/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.home.javaee7.ch05.sample4;

import org.junit.After;
import org.junit.Before;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.Random;

/**
 *
 * @author vitaliy
 */
public abstract class AbstractPersistentTest {

  // ======================================
  // =             Attributes             =
  // ======================================

  protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("chapter05TestPU");
  protected EntityManager em;
  protected EntityTransaction tx;

  // ======================================
  // =          Lifecycle Methods         =
  // ======================================

  @Before
  public void initEntityManager() throws Exception {
    em = emf.createEntityManager();
    tx = em.getTransaction();
  }

  @After
  public void closeEntityManager() throws SQLException {
    if (em != null) em.close();
  }

  protected Long getRandomId() {
    return Math.abs(new Random().nextLong());
  }
}