<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.medimitra.model.Order" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Orders - MediMitra</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/style.css">
</head>
<body>
    <%@ include file="header.jsp" %>
    
    <div class="container">
        <h1>My Orders</h1>
        
        <% 
        List<Order> orders = (List<Order>) request.getAttribute("orders");
        
        if (orders != null && !orders.isEmpty()) {
        %>
            <table class="orders-table">
                <thead>
                    <tr>
                        <th>Order Number</th>
                        <th>Date</th>
                        <th>Total Amount</th>
                        <th>Payment Method</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Order order : orders) { %>
                    <tr>
                        <td><%=order.getOrderNumber()%></td>
                        <td><%=order.getCreatedAt()%></td>
                        <td>₹<%=order.getTotalAmount()%></td>
                        <td><%=order.getPaymentMethod()%></td>
                        <td>
                            <span class="status-badge status-<%=order.getStatus().toLowerCase()%>">
                                <%=order.getStatus()%>
                            </span>
                        </td>
                        <td>
                            <a href="order-details?orderId=<%=order.getId()%>" class="btn btn-small">View Details</a>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        <% } else { %>
            <div class="empty-orders">
                <p>You haven't placed any orders yet</p>
                <a href="medicines" class="btn btn-primary">Start Shopping</a>
            </div>
        <% } %>
    </div>
    
    <%@ include file="footer.jsp" %>
</body>
</html>
