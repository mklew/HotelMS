<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>HotelMS</title>
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" type="text/css" href="/content/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="/content/css/bootstrap-responsive.css">
    <style type="text/css">
      /* Override some defaults */
      html, body {
        background-color: #eee;
      }
      body {
        padding-top: 40px; 
      }
      .container {
        width: 300px;
      }

      /* The white background content wrapper */
      .container > .content {
        background-color: #fff;
        padding: 20px;
        margin: 0 -20px; 
        -webkit-border-radius: 10px 10px 10px 10px;
           -moz-border-radius: 10px 10px 10px 10px;
                border-radius: 10px 10px 10px 10px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.15);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.15);
                box-shadow: 0 1px 2px rgba(0,0,0,.15);
      }

	  .login-form {
		margin-left: 65px;
	  }
	
	  legend {
		margin-right: -50px;
		font-weight: bold;
	  	color: #404040;
	  }

    </style>

</head>
<body>
  <div class="container">
    <div class="content">
      <div class="row">
        <div class="login-form">
          <h2>Login</h2>
          <form action="" method="POST">
            <fieldset>
              <div class="clearfix">
                <input type="text" placeholder="Username" name="username" value="root">
              </div>
              <div class="clearfix">
                <input type="password" placeholder="Password" name="password" value="root">
              </div>
              <button class="btn btn-primary" type="submit">Sign in</button>
            </fieldset>
          </form>
        </div>
      </div>
    </div>
  </div> <!-- /container -->
</body>
</html>