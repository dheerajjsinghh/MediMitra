<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Order Success - MediMitra</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/style.css">
</head>
<body>
    <%@ include file="header.jsp" %>
    
    <div class="container">
        <div class="success-container">
            <div class="success-icon">✓</div>
            <h1>Order Placed Successfully!</h1>
            <p class="success-message">Thank you for your order. Your order has been received and is being processed.</p>
            
            <% if (request.getAttribute("orderNumber") != null) { %>
                <div class="order-number">
                    <p><strong>Order Number:</strong> <%=request.getAttribute("orderNumber")%></p>
                </div>
            <% } %>
            
            <div class="success-actions">
                <a href="orders" class="btn btn-primary">View My Orders</a>
                <a href="medicines" class="btn btn-secondary">Continue Shopping</a>
            </div>
        </div>
    </div>
    
    <%@ include file="footer.jsp" %>
</body>
</html>
