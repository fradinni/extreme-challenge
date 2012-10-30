package com.nfradin.ess

import com.nfradin.ess.QuestionService.QuestionType;

class RegisterController {
	
	def questionService
	def challengeService
	
	// Total time 25min
	def schema = [
		1: [[QuestionType.TYPE_OP_DEC_SUM], 1],
		2: [[QuestionType.TYPE_OP_DEC_SUM, QuestionType.TYPE_OP_HEX_SUM], 2],
		3: [[QuestionType.TYPE_OP_DEC_SUM, QuestionType.TYPE_OP_HEX_SUM, QuestionType.TYPE_OP_DEC_ARRAY_PRODUCT], 3],
		4: [[QuestionType.TYPE_OP_DEC_ARRAY_PRODUCT, QuestionType.TYPE_COLOR_IN_FRENCH], 2],
		5: [[QuestionType.TYPE_OP_DEC_ARRAY_PRODUCT, QuestionType.TYPE_COLOR_IN_FRENCH, QuestionType.TYPE_DECIMAL_OF_PI], 3],
		6: [[QuestionType.TYPE_COLOR_IN_FRENCH, QuestionType.TYPE_DECIMAL_OF_PI, QuestionType.TYPE_DAYOFWEEK_FOR_DATE], 3],
		7: [[QuestionType.TYPE_OP_DEC_SUM, QuestionType.TYPE_DECIMAL_OF_PI, QuestionType.TYPE_DAYOFWEEK_FOR_DATE], 3],
		8: [[QuestionType.TYPE_OP_HEX_SUM,  QuestionType.TYPE_PRIME_NUMBER], 2],
		9: [[QuestionType.TYPE_PRIME_NUMBER, QuestionType.TYPE_COLOR_IN_FRENCH, QuestionType.TYPE_LUCAS_NUMBER], 3],
		10: [[QuestionType.TYPE_DECIMAL_OF_PI, QuestionType.TYPE_COLOR_IN_FRENCH, QuestionType.TYPE_LUCAS_NUMBER], 3]
	]

	/**
	 * 
	 * @return
	 */
	def index() {				
		[activepage: "register", ipAddress: request.getRemoteAddr()]	
	}
	
	
	/**
	 * 
	 * @return
	 */
    def register() {
		
		// Create new RegisteredClient
		RegisteredClient client = new RegisteredClient(params)
		client.ipAddress = request.getRemoteAddr()
		
		// Validate RegisteredClient
		if(client.validate()) {

			// Save RegisteredClient and redirect to confirm page
			client.save()
			session.isRegistered = true
			session.registeredClient = client.id
			
			challengeService.startNewChallenge(90, schema)
			
			render(view:"confirm", model:[activepage: "register", registeredClient: client])
			return
			
		} else {
			render(view: "index", model:[activepage: "register", registeredClient: client])
			return
		}
	}
	
	
	def exitChallenge() {
		if(session.isRegistered) {
			RegisteredClient client = RegisteredClient.get(session.registeredClient)
			session.isRegistered = false
			session.removeAttribute("registeredClient")
			client.delete()
		}
		
		redirect(action: "index")
	}
	
}
