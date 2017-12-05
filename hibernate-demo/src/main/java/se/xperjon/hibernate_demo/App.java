package se.xperjon.hibernate_demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.spi.SessionFactoryBuilderFactory;

import se.xperjon.hibernate_demo.domain.Avdelning;
import se.xperjon.hibernate_demo.domain.Personal;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )  {
        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
        	    .configure( "hibernate.cfg.xml" )
        	    .build();
        
        Metadata metadata = new MetadataSources( standardRegistry )
        	    .addAnnotatedClass( Personal.class )
        	    .addAnnotatedClass( Avdelning.class )
        	    .getMetadataBuilder()
        	    .applyImplicitNamingStrategy( ImplicitNamingStrategyJpaCompliantImpl.INSTANCE )
        	    .build();
        
        SessionFactory sf = metadata.getSessionFactoryBuilder()
        	    .build();
        
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        Personal find = session.find(Personal.class,7313);
        find.setNamn("Kalle Karlsson");
        session.persist(find);
        tx.commit();
        
        
        System.out.println("found: "+find);
    }
}
