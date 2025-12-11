// Cart functionality

// Add to cart with AJAX
function addToCartAjax(medicineId, quantity) {
    const formData = new FormData();
    formData.append('action', 'add');
    formData.append('medicineId', medicineId);
    formData.append('quantity', quantity);
    
    fetch(getContextPath() + '/cart', {
        method: 'POST',
        body: formData
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            showMessage('Item added to cart successfully!');
            updateCartCount();
        } else {
            showMessage(data.message || 'Failed to add item to cart', 'error');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        showMessage('Error adding item to cart', 'error');
    });
}

// Update cart item quantity
function updateCartItem(cartId, quantity) {
    const formData = new FormData();
    formData.append('action', 'update');
    formData.append('cartId', cartId);
    formData.append('quantity', quantity);
    
    fetch(getContextPath() + '/cart', {
        method: 'POST',
        body: formData
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            showMessage('Cart updated successfully!');
            location.reload();
        } else {
            showMessage(data.message || 'Failed to update cart', 'error');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        showMessage('Error updating cart', 'error');
    });
}

// Remove item from cart
function removeFromCart(cartId) {
    if (!confirm('Are you sure you want to remove this item?')) {
        return;
    }
    
    const formData = new FormData();
    formData.append('action', 'remove');
    formData.append('cartId', cartId);
    
    fetch(getContextPath() + '/cart', {
        method: 'POST',
        body: formData
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            showMessage('Item removed from cart');
            location.reload();
        } else {
            showMessage(data.message || 'Failed to remove item', 'error');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        showMessage('Error removing item', 'error');
    });
}

// Get context path helper
function getContextPath() {
    return window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
}

// Show message helper
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

// Update cart count helper
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
