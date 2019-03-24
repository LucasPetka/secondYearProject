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
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/websiteList">
        <div class="sidebar-brand-icon">
          <img class="mb-4 mx-auto mt-4" src="/img/logo.png" alt="" width="55" height="55">
        </div>
        <div class="sidebar-brand-text mx-3">NetNag</div>
      </a>

      <!-- Divider -->
      <hr class="sidebar-divider my-0">

      <!-- Nav Item - Profile -->
      <li class="nav-item">
        <a class="nav-link" href="/profile">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Profile</span></a>
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
				<c:forEach items="" var="website">
				<a class="collapse-item" href="/pageList?id="><c:out value=""/></a>
				</c:forEach>
          </div>
        </div>
      </li>

      <!-- Nav Item - Utilities Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities" aria-expanded="true" aria-controls="collapseUtilities">
          <i class="fas fa-fw fa-wrench"></i>
          <span>Membership</span>
        </a>
        <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Membership:</h6>
            <a class="collapse-item" href="/payment">Buy Membership</a>
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



         

            <div class="topbar-divider d-none d-sm-block"></div>

            <!-- Nav Item - User Information -->
            <li class="nav-item dropdown no-arrow">
              <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span class="mr-2 d-none d-lg-inline text-gray-600 small"></span>
                
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
            <h1 class="h3 mb-0 text-gray-800"> pages</h1>
           
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
                      
                      
                      
                      </div>
                    </div>
                    <div class="col-auto">
                      <i class="fas fa-calendar fa-2x text-gray-300"></i>
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
                      <i class="far fa-clock fa-2x text-gray-300"></i>
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
				<button class="btn btn-success" id="add_on" type="submit"><i class="fas fa-plus"></i> Page</button>
			</div>
	  </div>
	  <c:if test="${badlink == true}">
	<div class="alert alert-danger">
		Website Does not exist
		<script>$(document).ready(function(){$("#upload_on" ).hide();
		$("#upload").toggle(0);});</script>
	</div>
</c:if>


		<c:if test="${duplicatewebsite == true}">
			<div class="alert alert-danger">
				Cannot add duplicate page
				<script>$(document).ready(function(){$("#upload_on" ).hide();
				$("#upload").toggle(0);});</script>
			</div>
		</c:if>

		
		<c:if test="${exceedPageLimit == true}">
			<div class="alert alert-danger">
				You have reached your page limit.
				<script>$(document).ready(function(){$("#upload_on" ).hide();
				$("#upload").toggle(0);});</script>
			</div>
		</c:if>

	  <div id="upload">
		<form:form  method="POST" modelAttribute="page" action="/addPage?id=">
			  <div class="row justify-content-center">
			  <div class="col-md-4 mb-3">
			  <div class="input-group">
				<div class="input-group-prepend">
				  <span class="input-group-text" id="inputGroupPrepend">Page Name</span>
				</div>
				<input type="text" class="form-control" id="validationCustomUsername" name="name" placeholder="Contact Page" aria-describedby="inputGroupPrepend" required/>
				<form:errors path="name"/>
			  </div>
			</div>
				<!-- This field is used to pass the website owner Url into validation to check the complete url -->
	       <input type="hidden" name="ownerUrl" value=""/> 
			<div class="col-md-4 mb-3">
			  <div class="input-group">
				<div class="input-group-prepend">
				  <span class="input-group-text" id="inputGroupPrepend"></span>
				</div>
				
				<input type="text" class="form-control" id="validationCustomUsername" name="url" placeholder="contact" aria-describedby="inputGroupPrepend" required/>
				<label value=""></label>
				<form:errors path="url"/>
			  </div>
			</div>
			
			<div class="col-md-4 mb-3">
			<div class="input-group">
			  <div class="input-group-prepend">
				<label class="input-group-text" for="inputGroupSelect01">Alerted after</label>
			  </div>
			  
			  

			</div>
			</div>
			
			<input type="hidden"                        
			name=""
			value=""/>
			
			
			<div class="col-md-2 mb-3">
			<input type="submit" value="Add" name="add" class="btn btn-success"/>
			</div>
			
			<div class="col-md-1 mb-1">
			<button type="button" id="add_off" class="btn btn-outline-danger"><i class="fas fa-times"></i></button>
			</div>
			
			
			</div>
		</form:form>
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
				<th>Line</th>
			  <th>Changes</th>
			  
            </tr>
          </thead>
          <tbody>
            <c:forEach var="listValue" items="${code}">
				<tr>
				<td>1</td>
				<td>${listValue}</td>
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
  
  <!-- Delete Modal-->
  <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Delete?</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">Are you sure you want to delete this page?</div>
        <div class="modal-footer">
                  <a class="btn btn-primary" id="delete_modal_h" href="#">Yes</a>
          <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>

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
