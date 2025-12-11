<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.medimitra.model.*" %>
<%@ page import="java.math.BigDecimal" %>
<!DOCTYPE html>
<html>
<head>
    <title>Checkout - MediMitra</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/style.css">
</head>
<body>
    <%@ include file="header.jsp" %>
    
    <div class="container">
        <h1>Checkout</h1>
        
        <% 
        List<CartItem> cartItems = (List<CartItem>) request.getAttribute("cartItems");
        List<Address> addresses = (List<Address>) request.getAttribute("addresses");
        BigDecimal totalAmount = (BigDecimal) request.getAttribute("totalAmount");
        %>
        
        <div class="checkout-container">
            <div class="checkout-main">
                <form action="checkout" method="post" class="checkout-form">
                    <div class="checkout-section">
                        <h2>Delivery Address</h2>
                        <% if (addresses != null && !addresses.isEmpty()) { %>
                            <div class="address-list">
                                <% for (Address addr : addresses) { %>
                                <label class="address-option">
                                    <input type="radio" name="addressId" value="<%=addr.getId()%>" 
                                           <%= addr.isDefault() ? "checked" : "" %> required>
                                    <div class="address-card">
                                        <p><strong><%=addr.getFullAddress()%></strong></p>
                                    </div>
                                </label>
                                <% } %>
                            </div>
                        <% } else { %>
                            <p>No saved addresses. Please add an address first.</p>
                        <% } %>
                    </div>
                    
                    <div class="checkout-section">
                        <h2>Payment Method</h2>
                        <div class="payment-options">
                            <label>
                                <input type="radio" name="paymentMethod" value="CREDIT_CARD" checked required>
                                Credit/Debit Card
                            </label>
                            <label>
                                <input type="radio" name="paymentMethod" value="UPI" required>
                                UPI
                            </label>
                            <label>
                                <input type="radio" name="paymentMethod" value="COD" required>
                                Cash on Delivery
                            </label>
                        </div>
                    </div>
                    
                    <div class="checkout-section">
                        <h2>Order Items</h2>
                        <div class="order-items">
                            <% for (CartItem item : cartItems) { %>
                            <div class="order-item">
                                <span><%=item.getMedicineName()%> (x<%=item.getQuantity()%>)</span>
                                <span>₹<%=item.getSubtotal()%></span>
                            </div>
                            <% } %>
                        </div>
                    </div>
                    
                    <button type="submit" class="btn btn-primary btn-large">Place Order</button>
                </form>
            </div>
            
            <div class="checkout-sidebar">
                <div class="order-summary">
                    <h2>Order Summary</h2>
                    <div class="summary-row">
                        <span>Subtotal:</span>
                        <span>₹<%=totalAmount%></span>
                    </div>
                    <div class="summary-row">
                        <span>Delivery:</span>
                        <span>FREE</span>
                    </div>
                    <div class="summary-row total">
                        <span><strong>Total:</strong></span>
                        <span><strong>₹<%=totalAmount%></strong></span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <%@ include file="footer.jsp" %>
</body>
</html>
