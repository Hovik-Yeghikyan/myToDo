<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <script src="/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<span>
    <%
        if (session.getAttribute("msg") != null) { %>
        <h3><%=session.getAttribute("msg")%></h3>
   <%
           session.removeAttribute("msg");
       }
   %>
</span>
<section class="vh-100" style="background-color: #9A616D;">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col col-xl-10">
                <div class="card" style="border-radius: 1rem;">
                    <div class="row g-0">
                        <div class="col-md-6 col-lg-5 d-none d-md-block">
                            <img src="/img/img.png"
                                 alt="login form" class="img-fluid" style="border-radius: 1rem 0 0 1rem;"/>
                        </div>
                        <div class="col-md-6 col-lg-7 d-flex align-items-center">
                            <div class="card-body p-4 p-lg-5 text-black">

                                <form action="/register" method="post" enctype="multipart/form-data">


                                    <h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Sign up your
                                        account</h5>

                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <input type="text" id="name" class="form-control form-control-lg"
                                               name="name"/>
                                        <label class="form-label" for="name">Name</label>
                                    </div>
                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <input type="text" id="surname" class="form-control form-control-lg"
                                               name="surname"/>
                                        <label class="form-label" for="surname">Surname</label>
                                    </div>
                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <input type="email" id="form2Example17" class="form-control form-control-lg"
                                               name="email"/>
                                        <label class="form-label" for="form2Example17">Email address</label>
                                    </div>

                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <input type="password" id="form2Example27" class="form-control form-control-lg"
                                               name="password"/>
                                        <label class="form-label" for="form2Example27">Password</label>
                                    </div>
                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <input type="file" id="image" class="form-control form-control-lg"
                                               name="img"/>
                                        <label class="form-label" for="image">Image</label>
                                    </div>

                                    <div class="pt-1 mb-4">
                                        <button data-mdb-button-init data-mdb-ripple-init
                                                class="btn btn-dark btn-lg btn-block" type="submit">Register
                                        </button>
                                    </div>

                                    <p class="mb-5 pb-lg-2" style="color: #393f81;">Login <a
                                            href="/login"
                                            style="color: #393f81;">Login</a></p>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
