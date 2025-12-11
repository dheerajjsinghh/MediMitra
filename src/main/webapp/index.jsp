<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>MediMitra - Your Health Partner</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/style.css">
</head>
<body>
    <div class="site-header">
        <div class="header-content">
            <div class="logo">
                <a href="<%=request.getContextPath()%>/">MediMitra</a>
            </div>
            <nav class="main-nav">
                <a href="<%=request.getContextPath()%>/medicines">Browse Medicines</a>
                <% if (session.getAttribute("username") != null) { %>
                    <a href="<%=request.getContextPath()%>/cart">Cart</a>
                    <a href="<%=request.getContextPath()%>/orders">My Orders</a>
                    <span class="user-info">Welcome, <%= session.getAttribute("username") %>!</span>
                    <a href="<%=request.getContextPath()%>/logout">Logout</a>
                <% } else { %>
                    <a href="<%=request.getContextPath()%>/login">Login</a>
                    <a href="<%=request.getContextPath()%>/register">Register</a>
                <% } %>
            </nav>
        </div>
    </div>
    
    <div class="container" style="text-align: center; padding: 80px 20px;">
        <h1 style="font-size: 3em; margin-bottom: 20px;">Welcome to MediMitra</h1>
        <p style="font-size: 1.3em; color: #666; margin-bottom: 40px;">Your trusted online pharmacy for all your healthcare needs</p>
        
        <div style="display: flex; gap: 20px; justify-content: center; flex-wrap: wrap;">
            <a href="<%=request.getContextPath()%>/medicines" class="btn btn-primary btn-large" style="width: auto;">
                Browse Medicines
            </a>
            <% if (session.getAttribute("username") == null) { %>
                <a href="<%=request.getContextPath()%>/register" class="btn btn-secondary btn-large" style="width: auto;">
                    Create Account
                </a>
            <% } %>
        </div>
        
        <div style="margin-top: 60px; display: grid; grid-template-columns: repeat(auto-fit, minmax(250px, 1fr)); gap: 30px;">
            <div style="background: white; padding: 30px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1);">
                <h3 style="color: #667eea; margin-bottom: 15px;">💊 Wide Range</h3>
                <p>Browse through our extensive collection of medicines and healthcare products</p>
            </div>
            <div style="background: white; padding: 30px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1);">
                <h3 style="color: #667eea; margin-bottom: 15px;">🚚 Fast Delivery</h3>
                <p>Get your medicines delivered quickly and safely to your doorstep</p>
            </div>
            <div style="background: white; padding: 30px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1);">
                <h3 style="color: #667eea; margin-bottom: 15px;">🔒 Secure</h3>
                <p>Safe and secure transactions with multiple payment options</p>
            </div>
        </div>
    </div>
    
    <div class="site-footer">
        <div class="footer-content">
            <div class="footer-section">
                <h3>MediMitra</h3>
                <p>Your trusted medical e-commerce platform</p>
            </div>
            <div class="footer-section">
                <h4>Quick Links</h4>
                <ul>
                    <li><a href="<%=request.getContextPath()%>/medicines">Shop Medicines</a></li>
                    <li><a href="<%=request.getContextPath()%>/orders">Track Orders</a></li>
                </ul>
            </div>
            <div class="footer-section">
                <h4>Contact</h4>
                <p>Email: support@medimitra.com</p>
                <p>Phone: 1800-123-4567</p>
            </div>
        </div>
        <div class="footer-bottom">
            <p>&copy; 2025 MediMitra. All rights reserved.</p>
        </div>
    </div>
    
    <script src="<%=request.getContextPath()%>/assets/js/main.js"></script>
</body>
</html>
