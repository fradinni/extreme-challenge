package com.nfradin.ess

class Question {
	
	String identifier
	String text
	String answer
	
	Date dateCreated
	
	QuestionResult resultType
	String result = null

	int correctAnswer = 0
	int invalidAnswer = 0
	int noAnswer = 0
	
	static belongsTo = [registeredClient: RegisteredClient]

    static constraints = {
		identifier blank: false, unique: true
		text blank: false, maxSize: 1000
		answer blank: false, maxSize: 255
		result nullable: true
    }
	
	static mapping = {
		table "ess_questions"
		version false
	}
	
	/**
	 * Returns score for current question
	 * @return
	 */
	transient getScore() {
		switch(resultType) {
			case QuestionResult.CORRECT_ANSWER:
				return correctAnswer
				break
				
			case QuestionResult.BAD_ANSWER:
				return invalidAnswer
				break
				
			case QuestionResult.NO_ANSWER:
				return noAnswer
				break
		}
	}
}
