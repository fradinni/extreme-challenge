<%@page import="com.nfradin.ess.RegisteredClient" %>
<%@page import="com.nfradin.ess.QuestionResult" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
	</head>
<body>
	<div class="container-fluid">
	  <div class="row-fluid">
	    <div class="span6 well">
	    	<legend>Your Score</legend>
	    	<label><b>Score: </b>${RegisteredClient.get(session.registeredClient)?.score}</label>
	    	<br /><br />
	    	<legend>Details</legend>
	    	<div style="height: 300px; overflow: auto; border: 1px solid gray; font-size: 10px;">
	    		<div>
	    			<ul>
	    			<g:each in="${RegisteredClient.get(session.registeredClient)?.questions?.sort(){it.dateCreated}?.reverse()}" var="question">
	    				<li>${question.identifier} : <font color="${question.resultType == QuestionResult.CORRECT_ANSWER ? 'green' : question.resultType == QuestionResult.NO_ANSWER ? 'orange' : 'red'}">${question.text}</font> <b>${question.result} -> [${question.score} point(s)]</b></li>
	    			</g:each>
	    			</ul>
	    		</div>
	    	</div>
	    </div>
	    <div class="span6 well">
	      	<legend>Challengers Scores</legend>
	      	<div id="challengersScores" style="height: 150px; overflow: auto; border: 1px solid gray; font-size: 10px;">
	      		<div id="challengersScoresContent">
	      			<ul>
	      			<g:each in="${RegisteredClient.list() - [RegisteredClient.get(session.registeredClient)]}" var="challenger">
	      				<li>${challenger.name} - ${challenger.url} - ${challenger.score} points</li>
	      			</g:each>
	      			</ul>
	      		</div>
	      	</div>
	    </div>
	  </div>
	</div>
</body>
</html>