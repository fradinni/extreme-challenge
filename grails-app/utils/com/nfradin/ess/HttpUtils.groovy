/**
 * 
 */
package com.nfradin.ess

/**
 * @author Nicolas FRADIN
 *
 */
class HttpUtils {
	
	/**
	 * 
	 * @param url
	 * @param parameters
	 * @return
	 */
	static sendGETRequest(String url, Map parameters = null) {
		StringBuffer buffer = new StringBuffer()
		
		def query = url
		if(parameters) {
			parameters.eachWithIndex { param, value, index ->
				if(index == 0) query += "?"
				else query += "&"
				query += "${param}="+URLEncoder.encode("${value}","UTF-8")
			}
		}
				
		try {
			URL yahoo = new URL(query)
			URLConnection yc = yahoo.openConnection()
			yc.setConnectTimeout(4000)
			
			BufferedReader input = new BufferedReader(new InputStreamReader(yc.getInputStream()))
			String inputLine;
	
			
			while ((inputLine = input.readLine()) != null)
				buffer.append inputLine;
				
			input.close();
		} catch(Exception e) {
			return null
		}
		
		def result = new String(buffer.getValue())
		if(result == "null") return null
		else return result 
	}

}
