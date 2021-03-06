<!DOCTYPE HTML>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>NetNag</title>

  <!-- Font Awesome Icons -->
  <link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Merriweather+Sans:400,700" rel="stylesheet">
  <link href='https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic' rel='stylesheet' type='text/css'>

  <!-- Plugin CSS -->
  <link href="/vendor/magnific-popup/magnific-popup.css" rel="stylesheet">

  <!-- Theme CSS - Includes Bootstrap -->
  <link href="/css/creative.min.css" rel="stylesheet">

</head>

<body id="page-top">

  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-light fixed-top py-3" id="mainNav">
    <div class="container">
      <a class="navbar-brand js-scroll-trigger" href="#page-top">
	  <img src="/img/logo.png" width="60" height="60" class="d-inline-block align-top" alt="">
	  </a>
      <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto my-2 my-lg-0">
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="#about">About</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="#services">Services</a>
          </li>
                    <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="#pricing">Pricing</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="#contact">Contact</a>
          </li>
		  <li class="nav-item">
            <a class="nav-link js-scroll-trigger log_b" target="_blank" href="login_register">Log In</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>

  <!-- Masthead -->
  <header class="masthead">
    <div class="container h-100">
      <div class="row h-100 align-items-center justify-content-center text-center">
        <div class="col-lg-10 align-self-end">
          <h1 class=" text-white font-weight-bold">NetNag</h1>
          <hr class="divider my-4">
        </div>
        <div class="col-lg-8 align-self-baseline">
          <p class="text-white-75 font-weight-light mb-5">Keep your websites updated, Increase your web presence, <br>Make your business a success! </p>
		  <br>
		  <br>
          <a class="btn btn-primary btn-xl js-scroll-trigger" href="#about">Find Out More</a>
        </div>
      </div>
    </div>
  </header>

  <!-- About Section -->
  <section class="page-section bg-primary" id="about">
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-lg-8 text-center">
          <h2 class="text-white mt-0">We've got what you need!</h2>
          <hr class="divider light my-4">
          <p class="text-white-50 mb-4">NetNag has everything you need to monitor, update and view your website statistics all in one place. We offer affordable solutions to ensure your sites stay fresh and engaging! </p>
          <a class="btn btn-light btn-xl js-scroll-trigger" href="#services">Get Started!</a>
        </div>
      </div>
    </div>
  </section>

  <!-- Services Section -->
  <section class="page-section" id="services">
    <div class="container">
      <h2 class="text-center mt-0">What we offer</h2>
      <hr class="divider my-4">
      <div class="row">
        <div class="col-lg-3 col-md-6 text-center">
          <div class="mt-5">
            <i class="fas fa-4x fa-laptop-code text-primary mb-4"></i>
            <h3 class="h4 mb-2">Website monitoring</h3>
            <p class="text-muted mb-0">24/7 tracking for all of your websites.</p>
          </div>
        </div>
        <div class="col-lg-3 col-md-6 text-center">
          <div class="mt-5">
            <i class="fas fa-4x fa-envelope text-primary mb-4"></i>
            <h3 class="h4 mb-2">Email alerts</h3>
            <p class="text-muted mb-0">Choose who to email when a page needs updating.</p>
          </div>
        </div>
        <div class="col-lg-3 col-md-6 text-center">
          <div class="mt-5">
            <i class="fas fa-4x fa-globe text-primary mb-4"></i>
            <h3 class="h4 mb-2">View statistics</h3>
            <p class="text-muted mb-0">See everything about your website in one place.</p>
          </div>
        </div>
        <div class="col-lg-3 col-md-6 text-center">
          <div class="mt-5">
            <i class="fas fa-4x fa-heart text-primary mb-4"></i>
            <h3 class="h4 mb-2">User friendly</h3>
            <p class="text-muted mb-0">No technical knowledge required.</p>
          </div>
        </div>
      </div>
    </div>
  </section>

  <!-- Portfolio Section -->
  <section id="pricing">

        <div class="container">
          <div class="col-lg-12 text-center">
   <h2 class="mb-4">Pricing plans</h2>
         <hr class="divider my-4">
   </div>
      <div class="card-deck mb-3 text-center">
      
        <div class="card mb-4 box-shadow">
          <div class="card-header">
            <h4 class="my-0 font-weight-normal">Tier 1</h4>
          </div>
          <div class="card-body">
            <h1 class="card-title pricing-card-title">�9.99 <small class="text-muted">/ mo</small></h1>
            <ul class="list-unstyled mt-3 mb-4">
                <li> 20 Pages</li>
                 <li> Checks for updates every 24 hours</li>
                 <li> Alerted every 3, 6, 9 or 12 months</li>
                 <li> Alerted when page needs updating</li>
                 <li> 24/7 Support</li>
                 <li> �9.99 / Month</li><br><br>
            </ul>
            
          </div>
        </div>
        <div class="card mb-4 box-shadow">
          <div class="card-header">
            <h4 class="my-0 font-weight-normal">Tier 2</h4>
          </div>
          <div class="card-body">
            <h1 class="card-title pricing-card-title">�19.99 <small class="text-muted">/ mo</small></h1>
            <ul class="list-unstyled mt-3 mb-4">
                <li> 50 Pages</li>
                <li> Checks for updates every 6 hours</li>
                <li> Alerted every 1 to 12 months</li>
                <li> Alerted when page is updated and needs updating</li>
                <li> 24/7 Support</li>
                <li> �19.99 / Month</li><br>
            </ul>
           
          </div>
        </div>
        <div class="card mb-4 box-shadow">
          <div class="card-header">
            <h4 class="my-0 font-weight-normal">Tier 3</h4>
          </div>
          <div class="card-body">
            <h1 class="card-title pricing-card-title">�29.99 <small class="text-muted">/ mo</small></h1>
            <ul class="list-unstyled mt-3 mb-4">
                <li> 100 Pages</li>
                <li> Checks for updates every hour</li>
                <li> Alerted every week to 12 months</li>
                <li> Alerted when page is updated and needs updating</li>
                <li> Provides changes made to pages</li>
                <li> 24/7 Support</li>
                <li> �29.99 / Month</li>
            </ul>
            
          </div>
        </div>
 
      </div>
             <div class="col-lg-12 text-center">
          <a class="btn btn-primary btn-xl btn-xl" href="/login_register">Sign up!</a>
          </div>
  </section>
  

  <!-- Contact Section -->
  <section class="page-section" id="contact">
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-lg-8 text-center">
          <h2 class="mt-0">Contact the team</h2>
          <hr class="divider my-4">
          <p class="text-muted mb-5">Ready to revive your website? Give us a call or send us an email and we will get back to you as soon as possible!</p>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-4 ml-auto text-center">
          <i class="fas fa-phone fa-3x mb-3 text-muted"></i>
          <div>Phone Number</div>
        </div>
        <div class="col-lg-4 mr-auto text-center">
          <i class="fas fa-envelope fa-3x mb-3 text-muted"></i>
          <!-- Make sure to change the email address in anchor text AND the link below! -->
          <a class="d-block" href="mailto:contact@yourwebsite.com">contact@yourwebsite.com</a>
        </div>
      </div>
    </div>
  </section>

  <!-- Footer -->
  <footer class="bg-light py-5">
    <div class="container">
      <div class="small text-center text-muted">Copyright &copy; 2019 - NetNag</div>
    </div>
  </footer>

  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Plugin JavaScript -->
  <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
  <script src="vendor/magnific-popup/jquery.magnific-popup.min.js"></script>

  <!-- Custom scripts for this template -->
  <script src="js/creative.min.js"></script>

</body>

</html>

