package com.nfradin.ess

import java.text.DateFormat
import java.text.SimpleDateFormat;

import groovy.time.TimeCategory;



/**
 * 
 * @author Nicolas FRADIN
 *
 */
class QuestionService {

	// Questions types
	enum QuestionType {
		TYPE_OP_DEC_SUM,
		TYPE_OP_HEX_SUM,
		TYPE_OP_DEC_ARRAY_PRODUCT,
		TYPE_DECIMAL_OF_PI,
		TYPE_PRIME_NUMBER,
		TYPE_LUCAS_NUMBER,
		TYPE_COLOR_IN_FRENCH,
		TYPE_DAYOFWEEK_FOR_DATE,
	}

	// Random numbers generator
	Random rand = new Random()


	/**
	 * 
	 * @return
	 */
	def generateQuestion(QuestionType type) {
		Question question = null
		switch(type) {
			case QuestionType.TYPE_OP_DEC_SUM :
				question = generate_OP_DEC_SUM_Question()
				break

			case QuestionType.TYPE_OP_HEX_SUM :
				question = generate_OP_HEX_SUM_Question()
				break

			case QuestionType.TYPE_OP_DEC_ARRAY_PRODUCT :
				question = generate_OP_DEC_ARRAY_PRODUCT_Question()
				break

			case QuestionType.TYPE_DECIMAL_OF_PI :
				question = generate_DECIMAL_OF_PI_Question()
				break

			case QuestionType.TYPE_PRIME_NUMBER :
				question = generate_PRIME_NUMBER_Question()
				break

			case QuestionType.TYPE_LUCAS_NUMBER :
				question = generate_LUCAS_NUMBER_Question()
				break
				
			case QuestionType.TYPE_COLOR_IN_FRENCH :
				question = generate_COLOR_IN_FRENCH_Question()
				break
				
			case QuestionType.TYPE_DAYOFWEEK_FOR_DATE :
				question = generate_DAYOFWEEK_FOR_DATE_Question()
				break
		}
		return question
	}
	
	/**
	 * Generate an identifier for a question
	 * @param questionText
	 * @return
	 */
	def generateQuestionIdentifier(String questionText) {
		return ((String)generateRandomInt(1, 10000) + questionText).getBytes().encodeBase64().toString().substring(0, 8)
	}


	/**
	 * Generates a question of type : Sum of decimal numbers
	 * @return Question question
	 */
	def generate_OP_DEC_SUM_Question() {
		Question question = new Question()

		// Generate Random numbers
		int randomNum1 = generateRandomInt(1, 120)
		int randomNum2 = generateRandomInt(1, 120)

		// Fill question fields
		question.text = "What is the sum of ${randomNum1} and ${randomNum2} ?"
		question.answer = "${randomNum1 + randomNum2}"
		question.identifier = generateQuestionIdentifier(question.text)

		// Set points for this question
		question.correctAnswer = 10
		question.invalidAnswer = -5
		question.noAnswer = -2

		return question
	}


	/**
	 * Generates a question of type : Sum of hexadecimal numbers
	 * @return Question question
	 */
	def generate_OP_HEX_SUM_Question() {
		Question question = new Question()

		// Generate Random numbers
		int randomNum1 = generateRandomInt(1, 100)
		int randomNum2 = generateRandomInt(1, 100)

		// Fill question fields
		question.text = "What is the decimal result of sum between 0x${Integer.toHexString(randomNum1)} and 0x${Integer.toHexString(randomNum2)} ?"
		question.answer = "${randomNum1 + randomNum2}"
		question.identifier = generateQuestionIdentifier(question.text)

		// Set points for this question
		question.correctAnswer = 15
		question.invalidAnswer = -8
		question.noAnswer = -5

		return question
	}


	/**
	 * Generates a question of type : Product of decimal numbers in array
	 * @return Question question
	 */
	def generate_OP_DEC_ARRAY_PRODUCT_Question() {
		Question question = new Question()

		// Generate Random numbers
		def numbers = []
		BigInteger result = new BigInteger(1);
		(1..6).each {
			long rdm = generateRandomInt(10000000, 99999999)
			numbers.add(rdm)
			result *= rdm
		}

		// Fill question fields
		question.text = "What is the product of following numbers ${numbers.toString()} ?"
		question.answer = "${result}"
		question.identifier = generateQuestionIdentifier(question.text)

		// Set points for this question
		question.correctAnswer = 20
		question.invalidAnswer = -10
		question.noAnswer = -5

		return question
	}


	/**
	 *
	 * @return
	 */
	def generate_DECIMAL_OF_PI_Question() {
		String piStr = Math.PI.toString()
		String piDecimalsStr = piStr.substring(piStr.indexOf('.')+1)
		int nbDecimals = piDecimalsStr.length()

		Question question = new Question()

		// Generate Random number
		int randomNum1 = generateRandomInt(5, nbDecimals-1)

		// Fill question fields
		question.text = "What is the ${randomNum1}th decimal of Pi number ?"
		question.answer = piDecimalsStr[randomNum1-1]
		question.identifier = generateQuestionIdentifier(question.text)

		// Set points for this question
		question.correctAnswer = 20
		question.invalidAnswer = -15
		question.noAnswer = -10

		return question
	}


	/**
	 * Generates a question of type : Sum of decimal numbers
	 * @return Question question
	 */
	def generate_PRIME_NUMBER_Question() {
		def primeNumbers = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97]

		Question question = new Question()

		// Generate Random number
		int randomNum1 = generateRandomInt(4, primeNumbers.size()-1)

		// Fill question fields
		question.text = "What is the ${randomNum1}th prime number ?"
		question.answer = primeNumbers[randomNum1-1]
		question.identifier = generateQuestionIdentifier(question.text)

		// Set points for this question
		question.correctAnswer = 30
		question.invalidAnswer = -20
		question.noAnswer = -15

		return question
	}


	/**
	 * Generates a question of type : Sum of decimal numbers
	 * @return Question question
	 */
	def generate_LUCAS_NUMBER_Question() {

		Question question = new Question()

		// Generate Random number
		int randomNum1 = generateRandomInt(1, 20)
		int lucasNumber = getLucasNumber(randomNum1)

		// Fill question fields
		question.text = "What is the ${randomNum1}th Lucas number ?"
		question.answer = "${lucasNumber}"
		question.identifier = generateQuestionIdentifier(question.text)

		// Set points for this question
		question.correctAnswer = 50
		question.invalidAnswer = -30
		question.noAnswer = -20

		return question
	}


	/**
	 * Generates a question of type : Sum of decimal numbers
	 * @return Question question
	 */
	def generate_COLOR_IN_FRENCH_Question() {

		Map colors = [
					"blue": "bleu",
					"red":	"rouge",
					"green": "vert",
					"black": "noir",
					"white": "blanc",
				]

		Question question = new Question()

		// Generate Random number
		int randomNum1 = generateRandomInt(0, 4)

		String enColor = colors.keySet().toList()[randomNum1]
		String frColor = colors[enColor]

		// Fill question fields
		question.text = "What is the color '${enColor}' in french ?"
		question.answer = "${frColor}"
		question.identifier = generateQuestionIdentifier(question.text)

		// Set points for this question
		question.correctAnswer = 40
		question.invalidAnswer = -30
		question.noAnswer = -20

		return question
	}
	
	
	/**
	* Generates a question of type : Sum of decimal numbers
	* @return Question question
	*/
   def generate_DAYOFWEEK_FOR_DATE_Question() {
	   
	   // Generate fake date in the future
	   Date date = new Date()
	   date.clearTime()
	   
	   int yearFactor = generateRandomInt(1, 5)
	   	   
	   use(TimeCategory) {
		   date = date + yearFactor.years
	   }
	   
	   SimpleDateFormat df = new SimpleDateFormat("EEEE", Locale.US)

	   Question question = new Question()

	   // Fill question fields
	   question.text = "What is the day of week corresponding to date ${String.format('%tY/%<tm/%<td', date)}  ?"
	   question.answer = "${df.format(date)}"
	   question.identifier = generateQuestionIdentifier(question.text)

	   // Set points for this question
	   question.correctAnswer = 50
	   question.invalidAnswer = -30
	   question.noAnswer = -20

	   return question
   }



	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	def getLucasNumber(int pos) {
		int luc = 0
		if (pos < 2) {
			if(pos==0) {
				luc=1
			} else {
				luc = 3
			}
		}
		else
		{
			luc = getLucasNumber(pos-1)+getLucasNumber(pos-2)
		}

		return luc
	}

	def generateRandomInt(int min, int max) {
		rand.nextInt(max - min + 1) + min;
	}

	def generateRandomLong(long min, long max) {
		Math.abs(rand.nextLong())
	}
}
