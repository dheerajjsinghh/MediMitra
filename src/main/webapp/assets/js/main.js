// MediMitra - Main JavaScript

// Update cart count on page load
document.addEventListener('DOMContentLoaded', function() {
    updateCartCount();
});

// Fetch and update cart count
function updateCartCount() {
    fetch(getContextPath() + '/cart?action=count')
        .then(response => response.json())
        .then(data => {
            const cartCountElem = document.querySelector('.cart-count');
            if (cartCountElem && data.count > 0) {
                cartCountElem.textContent = data.count;
                cartCountElem.style.display = 'inline';
            } else if (cartCountElem) {
                cartCountElem.style.display = 'none';
            }
        })
        .catch(error => console.error('Error fetching cart count:', error));
}

// Get context path
function getContextPath() {
    return window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
}

// Show success message
function showMessage(message, type = 'success') {
    const messageDiv = document.createElement('div');
    messageDiv.className = type === 'success' ? 'success' : 'error';
    messageDiv.textContent = message;
    messageDiv.style.position = 'fixed';
    messageDiv.style.top = '20px';
    messageDiv.style.right = '20px';
    messageDiv.style.zIndex = '1000';
    messageDiv.style.padding = '15px';
    messageDiv.style.borderRadius = '4px';
    messageDiv.style.boxShadow = '0 2px 10px rgba(0,0,0,0.2)';
    
    document.body.appendChild(messageDiv);
    
    setTimeout(() => {
        messageDiv.remove();
    }, 3000);
}

// Mobile menu toggle
const mobileMenuToggle = document.querySelector('.mobile-menu-toggle');
if (mobileMenuToggle) {
    mobileMenuToggle.addEventListener('click', function() {
        const mainNav = document.querySelector('.main-nav');
        mainNav.classList.toggle('active');
    });
}
