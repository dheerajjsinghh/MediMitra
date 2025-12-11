// Form validation

// Validate login form
function validateLoginForm(form) {
    const email = form.email.value.trim();
    const password = form.password.value.trim();
    
    if (!email) {
        showError('Email is required');
        return false;
    }
    
    if (!isValidEmail(email)) {
        showError('Please enter a valid email address');
        return false;
    }
    
    if (!password) {
        showError('Password is required');
        return false;
    }
    
    return true;
}

// Validate registration form
function validateRegisterForm(form) {
    const username = form.username.value.trim();
    const email = form.email.value.trim();
    const fullName = form.fullName.value.trim();
    const phone = form.phone.value.trim();
    const password = form.password.value.trim();
    
    if (!username || username.length < 3) {
        showError('Username must be at least 3 characters long');
        return false;
    }
    
    if (!email || !isValidEmail(email)) {
        showError('Please enter a valid email address');
        return false;
    }
    
    if (!fullName) {
        showError('Full name is required');
        return false;
    }
    
    if (!phone || !isValidPhone(phone)) {
        showError('Please enter a valid phone number');
        return false;
    }
    
    if (!password || password.length < 6) {
        showError('Password must be at least 6 characters long');
        return false;
    }
    
    return true;
}

// Email validation
function isValidEmail(email) {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
}

// Phone validation (10 digits)
function isValidPhone(phone) {
    const phoneRegex = /^\d{10}$/;
    return phoneRegex.test(phone.replace(/\D/g, ''));
}

// Show error message
function showError(message) {
    const errorDiv = document.createElement('div');
    errorDiv.className = 'error';
    errorDiv.textContent = message;
    errorDiv.style.position = 'fixed';
    errorDiv.style.top = '20px';
    errorDiv.style.right = '20px';
    errorDiv.style.zIndex = '1000';
    errorDiv.style.padding = '15px';
    errorDiv.style.borderRadius = '4px';
    errorDiv.style.boxShadow = '0 2px 10px rgba(0,0,0,0.2)';
    
    document.body.appendChild(errorDiv);
    
    setTimeout(() => {
        errorDiv.remove();
    }, 4000);
}

// Attach validation to forms
document.addEventListener('DOMContentLoaded', function() {
    const loginForm = document.querySelector('form[action*="login"]');
    if (loginForm) {
        loginForm.addEventListener('submit', function(e) {
            if (!validateLoginForm(this)) {
                e.preventDefault();
            }
        });
    }
    
    const registerForm = document.querySelector('form[action*="register"]');
    if (registerForm) {
        registerForm.addEventListener('submit', function(e) {
            if (!validateRegisterForm(this)) {
                e.preventDefault();
            }
        });
    }
});
