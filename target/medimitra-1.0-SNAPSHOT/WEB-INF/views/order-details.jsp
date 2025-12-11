<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.medimitra.model.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Order Details - MediMitra</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/style.css">
</head>
<body>
    <%@ include file="header.jsp" %>
    
    <div class="container">
        <% 
        Order order = (Order) request.getAttribute("order");
        List<OrderItem> orderItems = (List<OrderItem>) request.getAttribute("orderItems");
        
        if (order != null) {
        %>
            <div class="order-details-header">
                <h1>Order Details</h1>
                <a href="orders" class="btn btn-secondary">Back to Orders</a>
            </div>
            
            <div class="order-info-grid">
                <div class="order-info-card">
                    <h3>Order Information</h3>
                    <p><strong>Order Number:</strong> <%=order.getOrderNumber()%></p>
                    <p><strong>Order Date:</strong> <%=order.getCreatedAt()%></p>
                    <p><strong>Status:</strong> <span class="status-badge status-<%=order.getStatus().toLowerCase()%>"><%=order.getStatus()%></span></p>
                </div>
                
                <div class="order-info-card">
                    <h3>Payment Information</h3>
                    <p><strong>Payment Method:</strong> <%=order.getPaymentMethod()%></p>
                    <p><strong>Payment Status:</strong> <%=order.getPaymentStatus()%></p>
                    <p><strong>Total Amount:</strong> ₹<%=order.getTotalAmount()%></p>
                </div>
            </div>
            
            <div class="order-items-section">
                <h2>Order Items</h2>
                <table class="order-items-table">
                    <thead>
                        <tr>
                            <th>Medicine Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Subtotal</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (OrderItem item : orderItems) { %>
                        <tr>
                            <td><%=item.getMedicineName()%></td>
                            <td>₹<%=item.getPrice()%></td>
                            <td><%=item.getQuantity()%></td>
                            <td>₹<%=item.getSubtotal()%></td>
                        </tr>
                        <% } %>
                    </tbody>
                    <tfoot>
                        <tr class="total-row">
                            <td colspan="3"><strong>Total</strong></td>
                            <td><strong>₹<%=order.getTotalAmount()%></strong></td>
                        </tr>
                    </tfoot>
                </table>
            </div>
        <% } else { %>
            <div class="error-message">
                <p>Order not found</p>
                <a href="orders" class="btn btn-primary">View All Orders</a>
            </div>
        <% } %>
    </div>
    
    <%@ include file="footer.jsp" %>
</body>
</html>
