<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
	</head>
<body>
	
	<h2>Your client server is registered !!!</h3>
	<br />
	<div class="well control-group success">
		<legend>Client server informations</legend>
		<b>Name:</b> 
		<label><b>${registeredClient.name}</b></label>
		<br />
		<b>URL :</b>
		<label><b>${registeredClient.url}</b></label>
		<br />
		<br />
		<legend>View Scores</legend>
		<p>
			You can watch your scores and scores of others challengers in <g:link controller="scores">Scores</g:link> section.
		</p>
	</div>
</body>
</html>