<%@page import="com.nfradin.ess.RegisteredClient" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
	</head>
<body>
	
	
		<g:if test="${session.isRegistered == null || session.isRegistered == false}">
		<div class="well">
		<g:form action="register" method="POST">
			<legend>Register a new client</legend>
			<div class="control-group ${hasErrors(bean:registeredClient,field:'name','error')}">
				<label>Client Name</label>
	  			<g:textField name="name" placeholder="Enter name..." value="${fieldValue(bean:registeredClient, field:'name')}"/>
	  			<g:hasErrors bean="${registeredClient}" field="name">
	  				<br />
	  				<span class="help-inline"><g:message error="${registeredClient.errors.getFieldError('name')}" /></span>
	  			</g:hasErrors>
	 			</div>
	 			<br />
	 			<br />
	 			<div class="control-group ${hasErrors(bean:registeredClient,field:'url','error')}">
	  			<label>Client server URL ( your ip address is ${ipAddress} )</label>
	  			<g:textField name="url" placeholder="Enter url with pattern http://server_ip:port/ ..." style="width: 400px;" value="${fieldValue(bean:registeredClient, field:'url')}" />
	  			<g:hasErrors bean="${registeredClient}" field="url">
	  				<br />
	  				<span class="help-inline"><g:message error="${registeredClient.errors.getFieldError('url')}" /></span>
	  			</g:hasErrors>
	 			</div>
	 			<br/>
	 			<br />
	 			<g:submitButton name="register" value="Register..." class="btn btn-primary" />
		</g:form>
		</div>
		</g:if>
		<g:else>
			<g:set var="registeredClient" value="${RegisteredClient.get(session.registeredClient)}"></g:set>
			<div class="well control-group success">
				<legend>Client already registered !</legend>
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
		</g:else>
	
</body>
</html>