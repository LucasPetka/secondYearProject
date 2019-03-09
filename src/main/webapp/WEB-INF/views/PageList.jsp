<!DOCTYPE HTML>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>NetNag Dashoard</title>

  <!-- Custom fonts for this template-->
  <link href="vendor-dash/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="css-dash/sb-admin-2.min.css" rel="stylesheet">
  
    <script src="//code.jquery.com/jquery-1.12.4.js"></script>
	<script src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  

</head>

<body id="page-top">

  <!-- Page Wrapper -->
  <div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

      <!-- Sidebar - Brand -->
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
        <div class="sidebar-brand-icon">
          <img class="mb-4 mx-auto mt-4" src="/img/logo.png" alt="" width="55" height="55">
        </div>
        <div class="sidebar-brand-text mx-3">NetNag</div>
      </a>

      <!-- Divider -->
      <hr class="sidebar-divider my-0">

      <!-- Nav Item - Dashboard -->
      <li class="nav-item">
        <a class="nav-link" href="index.html">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Dashboard</span></a>
      </li>

      <!-- Divider -->
      <hr class="sidebar-divider">

      <!-- Heading -->
      <div class="sidebar-heading">
        Interface
      </div>
	  
	  <!-- Nav Item - Charts -->
      <li class="nav-item">
        <a class="nav-link" href="/emailList">
          <i class="fas fa-fw fa-chart-area"></i>
          <span>My Email Addresses</span></a>
      </li>

      <!-- Nav Item - Pages Collapse Menu -->
      <li class="nav-item active">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
          <i class="fas fa-fw fa-cog"></i>
          <span>My websites</span>
        </a>
        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header"><a href="/websiteList"> Websites </a> </h6>
				<c:forEach items="${websites}" var="website">
				<a class="collapse-item" href="/pageList?id=${website.id}"><c:out value="${website.name}"/></a>
				</c:forEach>
          </div>
        </div>
      </li>

      <!-- Nav Item - Utilities Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities" aria-expanded="true" aria-controls="collapseUtilities">
          <i class="fas fa-fw fa-wrench"></i>
          <span>Tokens</span>
        </a>
        <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Tokens:</h6>
            <a class="collapse-item" href="utilities-color.html">Buy Tokens</a>
            <a class="collapse-item" href="utilities-border.html">Create Debit</a>
          </div>
        </div>
      </li>

      <!-- Divider -->
      <hr class="sidebar-divider d-none d-md-block">

      <!-- Sidebar Toggler (Sidebar) -->
      <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
      </div>

    </ul>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- Topbar -->
        <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

          <!-- Sidebar Toggle (Topbar) -->
          <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
            <i class="fa fa-bars"></i>
          </button>


          <!-- Topbar Navbar -->
          <ul class="navbar-nav ml-auto">

            <!-- Nav Item - Search Dropdown (Visible Only XS) -->
            <li class="nav-item dropdown no-arrow d-sm-none">
              <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-search fa-fw"></i>
              </a>
              <!-- Dropdown - Messages -->
              <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in" aria-labelledby="searchDropdown">
                <form class="form-inline mr-auto w-100 navbar-search">
                  <div class="input-group">
                    <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
                    <div class="input-group-append">
                      <button class="btn btn-primary" type="button">
                        <i class="fas fa-search fa-sm"></i>
                      </button>
                    </div>
                  </div>
                </form>
              </div>
            </li>

            <!-- Nav Item - Alerts -->
            <li class="nav-item dropdown no-arrow mx-1">
              <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-bell fa-fw"></i>
                <!-- Counter - Alerts -->
                <span class="badge badge-danger badge-counter">3+</span>
              </a>
              <!-- Dropdown - Alerts -->
              <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="alertsDropdown">
                <h6 class="dropdown-header">
                  Alerts Center
                </h6>
                <a class="dropdown-item d-flex align-items-center" href="#">
                  <div class="mr-3">
                    <div class="icon-circle bg-primary">
                      <i class="fas fa-file-alt text-white"></i>
                    </div>
                  </div>
                  <div>
                    <div class="small text-gray-500">December 12, 2019</div>
                    <span class="font-weight-bold">A new monthly report is ready to download!</span>
                  </div>
                </a>
                <a class="dropdown-item d-flex align-items-center" href="#">
                  <div class="mr-3">
                    <div class="icon-circle bg-success">
                      <i class="fas fa-donate text-white"></i>
                    </div>
                  </div>
                  <div>
                    <div class="small text-gray-500">December 7, 2019</div>
                    $290.29 has been deposited into your account!
                  </div>
                </a>
                <a class="dropdown-item d-flex align-items-center" href="#">
                  <div class="mr-3">
                    <div class="icon-circle bg-warning">
                      <i class="fas fa-exclamation-triangle text-white"></i>
                    </div>
                  </div>
                  <div>
                    <div class="small text-gray-500">December 2, 2019</div>
                    Spending Alert: We've noticed unusually high spending for your account.
                  </div>
                </a>
                <a class="dropdown-item text-center small text-gray-500" href="#">Show All Alerts</a>
              </div>
            </li>

         

            <div class="topbar-divider d-none d-sm-block"></div>

            <!-- Nav Item - User Information -->
            <li class="nav-item dropdown no-arrow">
              <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span class="mr-2 d-none d-lg-inline text-gray-600 small">${logfirstName}</span>
                <img class="img-profile rounded-circle" src="https://source.unsplash.com/QAB-WJcbgJk/60x60">
              </a>
              <!-- Dropdown - User Information -->
              <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                <a class="dropdown-item" href="/profile">
                  <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                  Profile
                </a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="/logout" data-toggle="modal" data-target="#logoutModal">
                  <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                  Logout
                </a>
              </div>
            </li>

          </ul>

        </nav>
        <!-- End of Topbar -->
		
		
		
		

        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800">My pages</h1>
           
          </div>

          <!-- Content Row -->
          <div class="row">

            <!-- Earnings (Monthly) Card Example -->
            <div class="col-xl-3 col-md-6 mb-4">
              <div class="card border-left-primary shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">Pages tracking</div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800">
                      
                       ${page_count}
                      
                      </div>
                    </div>
                    <div class="col-auto">
                      <i class="fas fa-calendar fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <!-- Earnings (Monthly) Card Example -->
            <div class="col-xl-3 col-md-6 mb-4">
              <div class="card border-left-success shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-success text-uppercase mb-1">Websites</div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800">
                      
                      ${web_count}
                      
                      </div>
                    </div>
                    <div class="col-auto">
                      <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            

            <!-- Pending Requests Card Example -->
            <div class="col-xl-3 col-md-6 mb-4">
              <div class="card border-left-warning shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">Last update</div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800">12min ago</div>
                    </div>
                    <div class="col-auto">
                      <i class="fas fa-comments fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <!-- Content Row -->
		  
		  <script>
		$(document).ready(function(){
			$("#add_on").click(function(){
			$("#upload_on" ).hide();
			$("#upload").toggle( "slide" );
			});
			
			$("#add_off").click(function(){
			$("#upload_on" ).toggle( "slide" );
			$("#upload").hide();
			});	
			
			$("#open_webs").hover(function(){
			$("#webs").finish().slideToggle();
			});

		});
		</script>
		
		<div id="upload_on">
			<div class="col-md-2 mb-3">
				<button class="btn btn-success" id="add_on" type="submit"><i class="fas fa-plus"></i> Add Page</button>
			</div>
	  </div>
	  <c:if test="${badlink == true}">
	<div class="alert alert-danger">
		Website Does not exist
		<script>$(document).ready(function(){$("#upload_on" ).hide();
		$("#upload").toggle(0);});</script>
	</div>
</c:if>

	  <div id="upload">
		<form  method="POST" modelAttribute="page" action=/addPage?id=${websiteId}>
			  <div class="row justify-content-center">
			  <div class="col-md-3 mb-3">
			  <div class="input-group">
				<div class="input-group-prepend">
				  <span class="input-group-text" id="inputGroupPrepend">Page Name</span>
				</div>
				<input type="text" class="form-control" id="validationCustomUsername" name="name" placeholder="Contact Page" aria-describedby="inputGroupPrepend" required/>
				<form:errors path="name"/>
			  </div>
			</div>
				<!-- This field is used to pass the website owner Url into validation to check the complete url -->
	       <input type="hidden" name="ownerUrl" value="${websiteUrl}"/> 
			<div class="col-md-3 mb-3">
			  <div class="input-group">
				<div class="input-group-prepend">
				  <span class="input-group-text" id="inputGroupPrepend">${websiteUrl}</span>
				</div>
				
				<input type="text" class="form-control" id="validationCustomUsername" name="url" placeholder="contact" aria-describedby="inputGroupPrepend" required/>
				<label value="${websiteUrl}"></label>
				<form:errors path="url"/>
			  </div>
			</div>
			
			<div class="col-md-3 mb-3">
			<div class="input-group">
			  <div class="input-group-prepend">
				<label class="input-group-text" for="inputGroupSelect01">Check every</label>
			  </div>
			  <select class="custom-select" id="inputGroupSelect01" name="frequency" required/>
				<option value="2"> 2 min </option>
				<option value="5"> 5 min </option>
				<option value="15"> 15 min </option>
				<option value="60">60 min </option>
				<option value="3"> 3 hours</option>
			  </select>
			      <form:errors path="frequency"/>
			</div>
			</div>
			
			<input type="hidden"                        
			name="${_csrf.parameterName}"
			value="${_csrf.token}"/>
			
			
			<div class="col-md-2 mb-3">
			<input type="submit" value="Add" name="add" class="btn btn-success"/>
			</div>
			
			<div class="col-md-1 mb-1">
			<button type="button" id="add_off" class="btn btn-outline-danger"><i class="fas fa-times"></i></button>
			</div>
			
			
			</div>
		</form>
	</div>
		

		
		
          <div class="row">

            <!-- Area Chart -->
            <div class="col-xl-11 col-lg-7">
              <div class="card shadow mb-4">
                <!-- Card Header - Dropdown -->
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                  <h6 class="m-0 font-weight-bold text-primary">Pages</h6>
                </div>
                <!-- Card Body -->
                <div class="card-body">
                  
				<div class="table-responsive">
        <table class="table table-striped table-sm">
          <thead>
            <tr>

			  <th>Name</th>
			  <th>Link</th>
			  <th>Last Change</th>
			  <th>Checking every</th>
			  <th></th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${pages}" var="page">
				<tr>

					<td><c:out value="${page.name}"/></td>
					<td><c:out value="${page.url}"/></td>
					<td><c:out value="${page.lastUpdated}"/></td>
					<td><c:out value="${page.frequency}"/></td>
					
					<td>
					<a class="btn main_b" href="/view_changes?id=${page.id}" role="button"> Check changes </a> 
					<a class="btn btn-dark" href="/editPage?id=${page.id}&websiteid=${websiteId}" role="button" data-toggle="modal" data-target="#editModal"> <i class="fas fa-wrench"></i> </a>
					<a class="btn btn-danger" href="/removePage?id=${page.id}&websiteid=${websiteId}" role="button"> <i class="fas fa-trash-alt"></i> </a>
					</td>
				</tr>
			</c:forEach>
          </tbody>
        </table>
      </div>
				  
				  
				  
				  
				  
				  
				  
                </div>
              </div>
            </div>

            
          </div>

          <!-- Content Row -->
          <div class="row">

            

            <div class="col-lg-6 mb-4">

              <!-- Illustrations -->
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">Tips</h6>
                </div>
                <div class="card-body">
                  <div class="text-center">
                    <img class="img-fluid px-3 px-sm-4 mt-1 mb-4" style="width: 25rem;" src="img/undraw_posting_photo.svg" alt="">
                  </div>
                   <p>
                  To change your page name you can click the edit icon next to your page. <br>
                  To delete your page from NetNag click the trash icon.</p>
                  
                </div>
              </div>

             

            </div>
          </div>

        </div>
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->

      <!-- Footer -->
      <footer class="sticky-footer bg-white">
        <div class="container my-auto">
          <div class="copyright text-center my-auto">
            <span>Copyright &copy; NetNag 2019</span>
          </div>
        </div>
      </footer>
      <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->

  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>

  <!-- Logout Modal-->
  <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
        <div class="modal-footer">
          <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
          <a class="btn btn-primary" href="/logout">Logout</a>
        </div>
      </div>
    </div>
  </div>
  
  
  <!-- Edit Modal-->
  <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Edit page</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        
        
        <div class="modal-body">
        
	        <form  method="POST" id="formId" modelAttribute="page" action="#">
			<div class="form-group">
			<label path="name" class="mt-2" for="formGroupExampleInput">Page Name</label>
	        <input name="name" type="text" class="form-control" id="formGroupExampleInput" placeholder="Name"/>
	        </div>
	        
	        <div class="form-group">
			    <label for="exampleFormControlSelect1">Email Address to be Nagged</label>
			    <select class="form-control" id="exampleFormControlSelect1">
			      <option>john@john.com</option>
			      <option>john@john.com</option>
			      <option>john@john.com</option>
			      <option>john@john.com</option>
			    </select>
			  </div>
	             
	        <input type="hidden"                        
				name="${_csrf.parameterName}"
				value="${_csrf.token}"/>
		
		<script>
		//$(document).on("click", "#ids", function () {
		//	var href = $(this).attr('href');
		//	$('#formId').attr('action', href);
		    // As pointed out in comments, 
		     // it is superfluous to have to manually call the modal.
		     // $('#addBookDialog').modal('show');
		//});
		</script>
			
			

        </div>
        
        <div class="modal-footer">
        
        	<input type="submit" value="Update" name="add" class="btn btn-primary"/>
          <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
         
          
          </form>
          
          
        </div>
      </div>
    </div>
  </div>

  <!-- Bootstrap core JavaScript-->
  <script src="vendor-dash/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="vendor-dash/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="js-dash/sb-admin-2.min.js"></script>

  <!-- Page level plugins -->
  <script src="vendor-dash/chart.js/Chart.min.js"></script>

  <!-- Page level custom scripts -->
  <script src="js-dash/demo/chart-area-demo.js"></script>
  <script src="js-dash/demo/chart-pie-demo.js"></script>

</body>

</html>
