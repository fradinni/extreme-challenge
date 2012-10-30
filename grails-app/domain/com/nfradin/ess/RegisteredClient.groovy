package com.nfradin.ess

import grails.validation.Validateable

@Validateable
class RegisteredClient {
	
	String name			// Client name
	String url			// Client server URL
	String ipAddress	// Client IP
	
	static hasMany = [questions: Question]
	
    static constraints = {
		name blank: false, unique: true
		url blank: false
    }
	
	static mapping = {
		table "ess_registered_users"
		version false
	}
	
	transient int getScore() {
		int score = 0
		questions?.each {
			score += it.score
		}
		
		return score
	}
	
}
