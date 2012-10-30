package com.nfradin.ess


class ChallengeJob {
    
	def concurrent = false
	
	def ChallengeService challengeService
	
    def execute() {
        println "ChallengeJob started..."
		
    }	
	
	def sendNewQuestionToClients() {
		
		RegisteredClient.list().each { RegisteredClient client ->
			
		}
		
	}

}
