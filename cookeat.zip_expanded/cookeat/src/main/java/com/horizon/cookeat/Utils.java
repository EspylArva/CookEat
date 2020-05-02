package com.horizon.cookeat;

import org.apache.commons.logging.Log;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.logging.log4j2.Log4J2LoggingSystem;

public class Utils {

	public static final Logger log = LoggerFactory.getLogger(Utils.class);
	
	public static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	

	
}
