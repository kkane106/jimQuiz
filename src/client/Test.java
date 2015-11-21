package client;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import quiz.entities.Question;
import quiz.entities.Quiz;

public class Test {
	public static void main(String[] args) {
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("QuizPersistenceUnit");
		EntityManager em = emf.createEntityManager();
		
		Quiz q = em.find(Quiz.class, 1);
		System.out.println(q.getName());
		
		List<Question> questions = q.getQuestions();
		
		for (Question question : questions) {
			System.out.println(question.getText());
		}
		em.getTransaction().begin();
		q.setName("The Capitals");
		em.getTransaction().commit();
		
	}

	
	
	
}
