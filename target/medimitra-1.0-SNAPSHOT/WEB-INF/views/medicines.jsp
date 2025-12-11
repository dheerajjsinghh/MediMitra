<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.medimitra.model.Medicine" %>
<!DOCTYPE html>
<html>
<head>
    <title>Medicines - MediMitra</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/style.css">
</head>
<body>
    <%@ include file="header.jsp" %>
    
    <div class="container">
        <div class="page-header">
            <h1>Our Medicines</h1>
            <div class="header-actions">
                <form action="medicines" method="get" class="search-form">
                    <input type="text" name="search" placeholder="Search medicines..." 
                           value="<%= request.getAttribute("searchKeyword") != null ? request.getAttribute("searchKeyword") : "" %>">
                    <button type="submit" class="btn btn-secondary">Search</button>
                </form>
            </div>
        </div>
        
        <div class="filter-section">
            <h3>Categories:</h3>
            <div class="category-filters">
                <a href="medicines" class="category-btn <%= request.getAttribute("selectedCategory") == null ? "active" : "" %>">All</a>
                <a href="medicines?category=Pain Relief" class="category-btn">Pain Relief</a>
                <a href="medicines?category=Antibiotics" class="category-btn">Antibiotics</a>
                <a href="medicines?category=Allergy" class="category-btn">Allergy</a>
                <a href="medicines?category=Vitamins" class="category-btn">Vitamins</a>
                <a href="medicines?category=Diabetes" class="category-btn">Diabetes</a>
            </div>
        </div>
        
        <div class="medicines-grid">
            <% 
            List<Medicine> medicines = (List<Medicine>) request.getAttribute("medicines");
            if (medicines != null && !medicines.isEmpty()) {
                for (Medicine medicine : medicines) {
            %>
                <div class="medicine-card">
                    <div class="medicine-image">
                        <img src="<%=request.getContextPath()%>/assets/images/<%=medicine.getImageUrl()%>" 
                             alt="<%=medicine.getName()%>" onerror="this.src='<%=request.getContextPath()%>/assets/images/placeholder.jpg'">
                    </div>
                    <div class="medicine-info">
                        <h3><%=medicine.getName()%></h3>
                        <p class="manufacturer"><%=medicine.getManufacturer()%></p>
                        <p class="description"><%=medicine.getDescription()%></p>
                        <div class="medicine-meta">
                            <span class="price">₹<%=medicine.getPrice()%></span>
                            <span class="stock">Stock: <%=medicine.getStock()%></span>
                        </div>
                        <% if (medicine.isRequiresPrescription()) { %>
                            <span class="prescription-badge">Prescription Required</span>
                        <% } %>
                        <form action="cart" method="post" class="add-to-cart-form">
                            <input type="hidden" name="action" value="add">
                            <input type="hidden" name="medicineId" value="<%=medicine.getId()%>">
                            <input type="number" name="quantity" value="1" min="1" max="<%=medicine.getStock()%>" class="qty-input">
                            <button type="submit" class="btn btn-primary">Add to Cart</button>
                        </form>
                    </div>
                </div>
            <% 
                }
            } else {
            %>
                <p class="no-results">No medicines found.</p>
            <% } %>
        </div>
    </div>
    
    <%@ include file="footer.jsp" %>
</body>
</html>
