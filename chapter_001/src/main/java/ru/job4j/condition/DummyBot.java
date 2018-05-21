package ru.job4j.condition;

/**
 * Class Point - point-to-point distance calculation.
 * Calculates point-to-point distance in 2D.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class DummyBot {
	/**
	 * Answers the questions.
	 * @param question - client answer.
	 * @return bot answer.
	 */
	public String answer(String question) {
		String answer = "Это ставит меня в тупик. Спросите другой вопрос.";
		if ("Привет, Бот.".equals(question)) {
			answer = "Привет, умник.";
		} else if ("Пока.".equals(question)) {
			answer = "До скорой встречи.";
		}
		return answer;
	}
}
