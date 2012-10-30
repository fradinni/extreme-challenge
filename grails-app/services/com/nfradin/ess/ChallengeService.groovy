package com.nfradin.ess


import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder


class ChallengeService {
		
	private static final int WINDOW_SIZE = 3

	def QuestionService questionService
	
	int challengeDuration = 90			// Challenge duration in Minutes
	Map challengeSteps = null
	int challengeCurrentStep = 0;
	
	boolean challengeStarted = false
	List clientsList = []
	
	
	
	/**
	 * Start a new Challenge
	 * @param duration
	 * @param questionsTypes
	 * @return
	 */
	boolean startNewChallenge(int duration, Map schema) {
		if(!challengeStarted) {
			challengeDuration = duration
			challengeSteps = schema
			challengeCurrentStep = 1
			challengeStarted = true
			runAsync{
				process()
			}
		} else {
			challengeStarted = false
		}
		return challengeStarted
	}
	
	
	/**
	 * 
	 * @return
	 */
	def process() {
				
		// Get current challenge step
		List currentStep = challengeSteps[challengeCurrentStep]
		List currentStepQuestionsTypes = currentStep[0]
		int stepDuration = currentStep[1]
		
		println "Starting challenge step ${challengeCurrentStep}, duration: ${stepDuration} min."
		
		long startTimestamp = System.currentTimeMillis()
		long currentTimestamp = startTimestamp
		
		// While currentStep has not ended
		while(currentTimestamp < (startTimestamp + (stepDuration * 60 * 1000))) {
			
			// Wait 10 seconds
			println "Waiting 10sec before sending questions..."
			Thread.sleep(10*1000)
			
			// Generate a random questionIndex		
			int questionIndex = questionService.generateRandomInt(0, currentStepQuestionsTypes.size() - 1)
			
			// Send question to each client
			def clients = RegisteredClient.list()
			clients.each { RegisteredClient client ->
				runAsync {
					sendNewQuestionToClient(client, questionService.generateQuestion(currentStepQuestionsTypes[questionIndex]))
				}
			}
			
			// Update currentTimestamp
			currentTimestamp = System.currentTimeMillis()
		}
		
		// When step is ended
		challengeCurrentStep++		
		process()
	}
	
	
	/**
	 * 
	 * @param client
	 * @param question
	 * @return
	 */
	def sendNewQuestionToClient(RegisteredClient client, Question question) {
		String result = HttpUtils.sendGETRequest(client.url, [q: question.identifier + ": " + question.text])?.trim()
				
		if(result != null) {
			if(result.length() > 0) {
				if(result.equalsIgnoreCase(question.answer)) {
					question.resultType = QuestionResult.CORRECT_ANSWER
				} else {
					question.resultType = QuestionResult.BAD_ANSWER
				}
			} else {
				question.resultType = QuestionResult.BAD_ANSWER
			}
		} else {
			question.resultType = QuestionResult.NO_ANSWER
		}
		
		question.result = result
		
		question.registeredClient = client
		if(question.save())
			println "ResponseType: ${question.resultType}, Answer: ${question.result}, Correct Answer: ${question.answer}, Score: ${question.score}"
		else
			question.errors.allErrors.each {
				println it
			}
	}
}
