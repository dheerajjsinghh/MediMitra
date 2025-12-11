<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.medimitra.model.CartItem" %>
<%@ page import="java.math.BigDecimal" %>
<!DOCTYPE html>
<html>
<head>
    <title>Shopping Cart - MediMitra</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/style.css">
</head>
<body>
    <%@ include file="header.jsp" %>
    
    <div class="container">
        <h1>Shopping Cart</h1>
        
        <% 
        List<CartItem> cartItems = (List<CartItem>) request.getAttribute("cartItems");
        BigDecimal totalAmount = (BigDecimal) request.getAttribute("totalAmount");
        
        if (cartItems != null && !cartItems.isEmpty()) {
        %>
            <div class="cart-container">
                <table class="cart-table">
                    <thead>
                        <tr>
                            <th>Medicine</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Subtotal</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (CartItem item : cartItems) { %>
                        <tr>
                            <td>
                                <strong><%=item.getMedicineName()%></strong>
                                <% if (item.isRequiresPrescription()) { %>
                                    <span class="prescription-badge-small">Rx Required</span>
                                <% } %>
                            </td>
                            <td>₹<%=item.getMedicinePrice()%></td>
                            <td>
                                <form action="cart" method="post" class="qty-form">
                                    <input type="hidden" name="action" value="update">
                                    <input type="hidden" name="cartId" value="<%=item.getId()%>">
                                    <input type="number" name="quantity" value="<%=item.getQuantity()%>" 
                                           min="1" max="<%=item.getAvailableStock()%>" class="qty-input-small">
                                    <button type="submit" class="btn-small">Update</button>
                                </form>
                            </td>
                            <td>₹<%=item.getSubtotal()%></td>
                            <td>
                                <form action="cart" method="post" class="remove-form">
                                    <input type="hidden" name="action" value="remove">
                                    <input type="hidden" name="cartId" value="<%=item.getId()%>">
                                    <button type="submit" class="btn btn-danger btn-small">Remove</button>
                                </form>
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
                
                <div class="cart-summary">
                    <h2>Cart Summary</h2>
                    <div class="summary-row">
                        <span>Subtotal:</span>
                        <span>₹<%=totalAmount%></span>
                    </div>
                    <div class="summary-row total">
                        <span><strong>Total:</strong></span>
                        <span><strong>₹<%=totalAmount%></strong></span>
                    </div>
                    <a href="checkout" class="btn btn-primary btn-large">Proceed to Checkout</a>
                    <a href="medicines" class="btn btn-secondary btn-large">Continue Shopping</a>
                </div>
            </div>
        <% } else { %>
            <div class="empty-cart">
                <p>Your cart is empty</p>
                <a href="medicines" class="btn btn-primary">Shop Now</a>
            </div>
        <% } %>
    </div>
    
    <%@ include file="footer.jsp" %>
</body>
</html>
