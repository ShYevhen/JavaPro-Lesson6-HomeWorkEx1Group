package net.ukr.shyevhen;

import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Main {
	private static String[] names = { "Vitalik", "Artem", "Andrey", "Aleksandr", "Denys", "Konstantin", "Yevhen",
			"Vladislav", "Nikolay", "Anna", "Mila", "Sveta", "Dasha", "Nastya", "Liza", "Ira", "Masha" };
	private static String[] surnames = { "Adamson", "Anderson", "Archibald", "Howard", "Little", "Bishop", "Mackenzie",
			"Brooks", "Carter", "Conors", "Nicholson", "Donovan", "Osborne", "Palmer", "Erickson", "Salomon", "Gate",
			"Walter" };

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAGroup");
		EntityManager em = emf.createEntityManager();
		Random rn = new Random();
		try {
			createGroup(em, rn);
			System.out.println("------------Getting groups from DB--------------");
			Query query = em.createNamedQuery("Groups.getAll", Group.class);
			List<Group> dbGroups = (List<Group>) query.getResultList();
			for (Group group : dbGroups) {
				int count = group.getStudents().size();
				System.out.println("In " + group.getName() + " " + count + " students");
				System.out.println("Students list:");
				for (Student student : group.getStudents()) {
					System.out.println(student);
				}
			}
		} finally {
			em.close();
			emf.close();
		}
	}

	private static void createGroup(EntityManager em, Random rn) {
		System.out.println("-------------Create groups and students-------------");
		Group gpOne = new Group("First course");
		Group gpTwo = new Group("Second course");
		Group gpThree = new Group("Third course");
		Group[] gps = { gpOne, gpTwo, gpThree };
		Student st;
		for (int i = 1; i <= 30; i += 1) {
			st = new Student(i, names[rn.nextInt(names.length)], surnames[rn.nextInt(surnames.length)],
					rn.nextInt(5) + 14);
			gps[rn.nextInt(100) % gps.length].addStudent(st);
		}
		em.getTransaction().begin();
		try {
			for (Group group : gps) {
				em.persist(group);
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			return;
		}
	}

}
