package com.chattycathy.server.model;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Provides convenient access to sessionFactory
 */
@Slf4j
public class HibernateUtil {
    /**
     * Hides public constructor for entirely static class
     */
    private HibernateUtil() {}

    private static StandardServiceRegistry registry;

    private static SessionFactory sessionFactory;

    /**
     * creates and maintains sessionFactory singleton.
     * Singleton is used as only one instance of sessionFactory should exist
     * @return sessionFactory which can be used to get instances of session.
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                // Create registry
                registry = new StandardServiceRegistryBuilder().configure().build();

                // Create MetadataSources
                MetadataSources sources = new MetadataSources(registry);

                // Create Metadata
                Metadata metadata = sources.getMetadataBuilder().build();

                // Create SessionFactory
                sessionFactory = metadata.getSessionFactoryBuilder().build();

            } catch (Exception e) {
                log.error("could not build SessionFactory due to: {}", e.getMessage());
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
