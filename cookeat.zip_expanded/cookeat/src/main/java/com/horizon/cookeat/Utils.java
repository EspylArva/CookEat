package com.horizon.cookeat;

import javax.persistence.EntityManagerFactory;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class Utils {

	private final Logger log = Logger.getLogger(this.getClass());
	public static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
}
