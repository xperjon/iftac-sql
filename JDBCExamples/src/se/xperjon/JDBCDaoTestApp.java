package se.xperjon;

import java.util.List;

import se.xperjon.dao.PersonalDao;
import se.xperjon.db.PersonalConnectionFactory;
import se.xperjon.domain.Avdelning;
import se.xperjon.domain.Personal;

public class JDBCDaoTestApp {
	public static void main(String[] args) {
			//Read
			PersonalDao personalDao = new PersonalDao(new PersonalConnectionFactory());
			Personal personal = personalDao.getPersonalById(7313);
			System.out.println("Found " + personal);
			
			//Update
			personalDao.updatePersonalNameById(personal != null ? personal.getId() : 0, "Nina Brede");
			personal = personalDao.getPersonalById(7313);
			System.out.println("Found " + personal);
			
			//Delete
			personalDao.deletePersonalById(7313);
			personal = personalDao.getPersonalById(7313);
			System.out.println("Found " + personal);
			
			//Create
			Personal p = new Personal();
			Avdelning a = new Avdelning();
			a.setId(128);
			p.setAvdelning(a);
			p.setNamn("Jon-Erik Liw");
			p.setBefattning("programmerare");
			personalDao.addPersonal(p);		
			
			//Search
			List<Personal> list = personalDao.searchByName("Jon-Erik");
			for (Personal pers : list) {
				System.out.println("Found: "+pers);
			}
	}
}
