<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jstl/fmt" %>
<jsp:useBean id="indexJb" class="br.com.polypus.jb.indexJb" scope="request" />
<jsp:setProperty name="indexJb" property="pageContext" value="${pageContext}"/>
<jsp:setProperty name="indexJb" property="*"/>
${indexJb.execute}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <!-- General meta information -->
  <title>Polypus</title>
  <meta name="keywords" content="" />
  <meta name="description" content="" />
  <meta name="robots" content="index, follow" />
  <meta charset="utf-8" />
  <!-- // General meta information -->
	
  <!-- Load Javascript -->
  <script type="text/javascript" src="js/jquery.js"></script>
  <script type="text/javascript" src="js/jquery.query-2.1.7.js"></script>
  <script type="text/javascript" src="js/rainbows.js"></script>
  <!-- // Load Javascipt -->

  <!-- Load stylesheets -->
  <link type="text/css" rel="stylesheet" href="css/style.css" media="screen" />
  <!-- // Load stylesheets -->
	
<!--<script>
	$(document).ready(function(){
 
	$("#submit1").hover(
	function() {
	$(this).animate({"opacity": "0"}, "slow");
	},
	function() {
	$(this).animate({"opacity": "1"}, "slow");
	});
 	});
</script>-->

</head>
<body>
 <div class="head">
   <a href="http://www.polypusintelligence.com"> Polypus Inteligence</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</div>   
    
  <form action="index.jsp" method="post">
   
  <input type="hidden" name="acao" id="acao" value="logar"/>
  
  <div id="background">
  <div id="wrapper">
      
    <div id="wrappertop"></div>
      <div id="wrappermiddle">

<!--        <h2>Login</h2>-->

	<div id="username_input">

	<div id="username_inputleft"></div>

	<div id="username_inputmiddle">
          <input type="text" name="email" id="url" placeholder="E-mail Address">
          <img id="url_user" src="./images/mailicon.png" alt="">
	</div>

	<div id="username_inputright"></div>

	</div>

	<div id="password_input">

	<div id="password_inputleft"></div>

	<div id="password_inputmiddle">
	  <input type="password" name="senha" id="url" placeholder="Password">
	  <img id="url_password" src="./images/passicon.png" alt="">	
	</div>

	<div id="password_inputright"></div>

	</div>

	<div id="submit">
		  
	<input type="image" src="./images/submit_hover.png" id="submit1" value="Sign In">
	<input type="image" src="./images/submit.png" id="submit2" value="Sign In">
		
	</div>
			
      </div>

      <div id="wrapperbottom"></div>
		
      <div id="powered">
          <p>Powered by <a href="http://www.polypusintelligence.com/" target="_blank">Polypus Intelligence</a></p>
      </div>
  </div>
  </div>
  </form>
</body>
</html>