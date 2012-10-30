package com.nfradin.ess

import java.util.regex.Matcher
import java.util.regex.Pattern

class TestController {

    def index = { 
		String question = request.getParameter("q")
		println "Question: ${question.substring(question.indexOf(':')+2)}"

		if(question.contains("What is the sum of")) {
			def numbers = extractNumbersFromString(question)
			if(numbers.size() == 2) {
				render (Integer.parseInt(numbers[0]) + Integer.parseInt(numbers[1])).toString()
				return
			} else {
				println "${numbers.size()} number(s) found !"
			}
		} 
		else if(question.contains("What is the decimal result of sum between")) {
			
			return
		}
		else {
			render "nok"
			return
		}

		return
	}
	
	
	/**
	 * Extract numbers from string
	 * @param text
	 * @return a list of numbers found in string
	 */
	def extractNumbersFromString(String text) {
		List numbers = []

		Pattern p = Pattern.compile("(\\s(\\d)*\\s)+");
		Matcher m = p.matcher(text);
		while(m.find())
		{
			numbers.add m.group().trim()
		}
		
		return numbers
	}
}
