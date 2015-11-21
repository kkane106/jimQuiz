package quiz.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import quiz.data.QuizDao;
import quiz.entities.Answer;
import quiz.entities.Question;

@Controller
@SessionAttributes({"counter", "userResponses"})
public class QuizController {
	@Autowired
	private QuizDao quizDao;

	@ModelAttribute("counter")
	public int counter() {
		int counter = 0;
		return counter;
	}
	
	@ModelAttribute("userResponses")
	public List<String> userResponses() {
		List<String> userResponses = new ArrayList<String>();
		return userResponses;
	}
	
	//get list of questions and answers

	@RequestMapping("getQandA.do")
	public ModelAndView getQandA(Model model, @ModelAttribute("counter") int counter, @ModelAttribute("userResponses") List<String> userResponses,
			@RequestParam(name = "answer", required = false) String answ) {
		// length or size to .set() limit on counter to avoid exception
//		System.out.println("the stored value is: " + answ);

		if (answ != null) {
			userResponses.add(answ);

		}

		else {}

		List<Question> listOfQuestions = quizDao.getListOfQuestionObjects().getQuestions();
//		for (Question question : listOfQuestions) {
//			System.out.println(question);
//		}
		if (counter < listOfQuestions.size()) {

			Question question = listOfQuestions.get(counter);
			String questionText = question.getText();
			List<Answer> listOfAnswers = question.getAnswer();
			List<String> listOfAnswersText = new ArrayList<String>();
			for (Answer answer : listOfAnswers) {

				listOfAnswersText.add(answer.getText());
//				System.out.println(answer.getText());
			}

			model.addAttribute("question", questionText);
			model.addAttribute("answers", listOfAnswersText);
			model.addAttribute("counter", ++counter);
			model.addAttribute("size", listOfQuestions.size());
			return new ModelAndView("getQandA.jsp");
		}

		else {
			System.out.println("Ending test");
			Double score = quizDao.getScore(userResponses, counter);
			counter=0;
			userResponses.clear();
			/*for (int i = 0; i < userResponses.size(); i++) {
				userResponses.remove(i);
			}*/
			System.out.println(counter);
			for (String string : userResponses) {
				System.out.println(string);
			}
			
//			model.addAttribute("userResponses", userResponses);
			model.addAttribute("counter", counter);
			return new ModelAndView("endTest.jsp", "score", score);
		}

	}

	@RequestMapping("endTest.do")
	public ModelAndView endTest(@ModelAttribute("userResponses") List<String> userResponses,
			@RequestParam(name = "answer", required = false) String answ, Model model, @ModelAttribute("counter") int counter) {
		
//		if (answ != null) {
//			userResponses.add(answ);
//
//		}
		Double score = quizDao.getScore(userResponses, counter);
		counter=0;
//		for (String string : userResponses) {
//			System.out.println(string);
//		}
//		for (int i=0; i<userResponses.size(); i++) {
//			userResponses.remove(i);
//		}
//		System.out.println(counter);
//		for (String string : userResponses) {
//			System.out.println(string);
//		}
//		model.addAttribute("userResponses", userResponses);
		model.addAttribute("counter", counter);
		return new ModelAndView("endTest.jsp", "score", score);

	}
	
	@RequestMapping(value="addNewQuestion.do")
	public ModelAndView addNewQuestion(@RequestParam("answer") String[] answers, 
			@RequestParam("question") String question, @RequestParam("correct") String correctAnswer){
		
		Question ques = new Question();
		ques.setText(question);
		
		List<Answer> newAnswerArray = new ArrayList<>();
		for (String answer : answers) {
			Answer a = new Answer();
			a.setText(answer);
			a.setIsCorrect('N');
			a.setQuestion(ques);
			newAnswerArray.add(a);
		}
		newAnswerArray.get(Integer.parseInt(correctAnswer)).setIsCorrect('Y');;
		ques.setAnswer(newAnswerArray);
		quizDao.createQuestion(ques);
		
		
		return new ModelAndView("showQuestion.jsp", "answer", newAnswerArray);
		
	}
	
	/*@RequestMapping("getQuizQuestions.do")
	public ModelAndView getQuizQuestions() {

		return new ModelAndView("getListOfQuestions.jsp", "theQuestion", quizDao.getListOfQuestions());
	}*/

	/*
	 * @RequestMapping("getQuizName.do") public ModelAndView getQuizName() {
	 * return new ModelAndView("quizName.jsp", "theName",
	 * quizDao.getQuizName(1)); }
	 */
	// @RequestMapping("getQuizNames.do")
	// public ModelAndView getQuizNames() {
	//
	//
	// return new ModelAndView("getListOfQuizes.jsp",
	// "theName", quizDao.getListOfNames());
	// }

	/*
	 * @RequestMapping("getQuizQuestions.do") public ModelAndView
	 * getQuizQuestions() {
	 * 
	 * 
	 * return new ModelAndView("getListOfQuizes.jsp", "theName",
	 * quizDao.getQuizQuestions(1)); //may need to get count and pass as
	 * parameter }
	 */

	/*
	 * @RequestMapping("getQuizAnswers.do") public ModelAndView getQuizAnswer()
	 * {
	 * 
	 * return new ModelAndView("getListOfAnswers.jsp", "theAnswer",
	 * quizDao.getListOfAnswers()); }
	 * 
	 * @RequestMapping(value = "changeQuizName.do", // not dao interaction, just
	 * // returns another // form...could just do a // hyperlink to the other //
	 * form method = RequestMethod.GET) public ModelAndView changeQuizNameGet()
	 * { return new ModelAndView("quizNameForm.jsp"); }
	 * 
	 * @RequestMapping(value = "changeQuizName.do", method = RequestMethod.POST)
	 * public ModelAndView changeQuizNamePost(@RequestParam(name = "quizName")
	 * String name) { quizDao.changeName(1, name); return new
	 * ModelAndView("index.html"); }
	 */

}
