<!doctype html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<!--[if lt IE 9]>
      		<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    	<![endif]-->
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Extreme Startup Challenge Server v1.0"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap.min.css')}" type="text/css">
		<script src="http://code.jquery.com/jquery-latest.js"></script>
		<g:layoutHead/>
        <r:layoutResources />
	</head>
	<body>
		<div class="navbar navbar-inverse navbar-fixed-top">
	      <div class="navbar-inner">
	        <div class="container">
	          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	          </a>
	          <g:link class="brand" controller="home">Extreme Quizz Challenge</g:link>
	          <div class="nav-collapse collapse">
	            <ul class="nav">
	              <li class="${activepage == "register" ? "active" : ""}"><g:link controller="register">Register</g:link></li>
	              <li class="${activepage == "scores" ? "active" : ""}"><g:link controller="scores">Scores</g:link></li>
	              
	              <g:if test="${session.isRegistered}">
	              	<li><div style="width: 100px;">&nbsp;</div></li>
	              	<li><g:link controller="register" action="exitChallenge"><b>Exit Challenge !</b></g:link></li>
	              </g:if>
	            </ul>
	          </div><!--/.nav-collapse -->
	        </div>
	      </div>
	    </div>
	    
	    <div class="container" style="margin-top: 70px;">
			<g:layoutBody/>
		</div>
		<g:javascript library="application"/>
        <r:layoutResources />
	</body>
</html>