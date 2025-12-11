<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error - MediMitra</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/style.css">
</head>
<body>
    <%@ include file="header.jsp" %>
    
    <div class="container">
        <div class="error-container">
            <div class="error-icon">⚠</div>
            <h1>Oops! Something went wrong</h1>
            
            <% 
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage == null && exception != null) {
                errorMessage = exception.getMessage();
            }
            if (errorMessage == null) {
                errorMessage = "An unexpected error occurred. Please try again.";
            }
            %>
            
            <p class="error-message"><%=errorMessage%></p>
            
            <div class="error-actions">
                <a href="<%=request.getContextPath()%>/" class="btn btn-primary">Go to Home</a>
                <a href="javascript:history.back()" class="btn btn-secondary">Go Back</a>
            </div>
        </div>
    </div>
    
    <%@ include file="footer.jsp" %>
</body>
</html>
