<%@ page contentType="text/html;charset=UTF-8" %>
<header class="site-header">
    <div class="container header-content">
        <div class="logo">
            <a href="<%=request.getContextPath()%>/">MediMitra</a>
        </div>
        <nav class="main-nav">
            <a href="<%=request.getContextPath()%>/medicines">Medicines</a>
            <a href="<%=request.getContextPath()%>/orders">My Orders</a>
            <a href="<%=request.getContextPath()%>/cart" class="cart-link">
                🛒 Cart <span class="cart-count"></span>
            </a>
            <% if (session.getAttribute("username") != null) { %>
                <span class="user-info">Welcome, <%= session.getAttribute("username") %>!</span>
                <a href="<%=request.getContextPath()%>/logout">Logout</a>
            <% } else { %>
                <a href="<%=request.getContextPath()%>/login">Login</a>
            <% } %>
        </nav>
    </div>
</header>
