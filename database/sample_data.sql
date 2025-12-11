-- MediMitra Sample Data
-- Sample data for testing the Medical E-Commerce Platform

USE medimitra;

-- ============================================
-- Sample Users (Password: Admin@123 for all)
-- ============================================
INSERT INTO users (name, email, password_hash, phone, role, wallet_balance, reward_points) VALUES
('Admin User', 'admin@medimitra.com', '$2a$10$XQcjV3N4o4VxT4V8vUK8V.8LJ7KP5H/K4Y5.1G2W3T4Y5Z6A7B8C9', '9876543210', 'ADMIN', 5000.00, 1000),
('John Doe', 'john.doe@example.com', '$2a$10$XQcjV3N4o4VxT4V8vUK8V.8LJ7KP5H/K4Y5.1G2W3T4Y5Z6A7B8C9', '9876543211', 'USER', 1000.00, 250),
('Jane Smith', 'jane.smith@example.com', '$2a$10$XQcjV3N4o4VxT4V8vUK8V.8LJ7KP5H/K4Y5.1G2W3T4Y5Z6A7B8C9', '9876543212', 'USER', 500.00, 100),
('Store Manager', 'store@medimitra.com', '$2a$10$XQcjV3N4o4VxT4V8vUK8V.8LJ7KP5H/K4Y5.1G2W3T4Y5Z6A7B8C9', '9876543213', 'STORE', 0.00, 0);

-- ============================================
-- Categories
-- ============================================
INSERT INTO categories (category_name, description, active) VALUES
('Pain Relief', 'Medicines for pain management and relief', TRUE),
('Antibiotics', 'Antibacterial medications', TRUE),
('Vitamins & Supplements', 'Nutritional supplements and vitamins', TRUE),
('Cold & Flu', 'Medicines for cold, cough and flu symptoms', TRUE),
('Diabetes Care', 'Medications and supplies for diabetes management', TRUE),
('Heart Care', 'Cardiovascular medications', TRUE),
('Digestive Health', 'Medicines for digestive issues', TRUE),
('Skin Care', 'Topical medications and skin treatments', TRUE),
('First Aid', 'Emergency and first aid supplies', TRUE),
('Baby Care', 'Medicines and products for infants and children', TRUE);

-- ============================================
-- Medicines
-- ============================================
INSERT INTO medicines (name, brand, category_id, salts, description, price, gst_rate, stock, expiry_date, prescription_required, active) VALUES
-- Pain Relief
('Paracetamol 500mg', 'Crocin', 1, 'Paracetamol', 'Used to relieve pain and reduce fever', 25.00, 12.00, 500, '2026-12-31', FALSE, TRUE),
('Ibuprofen 400mg', 'Brufen', 1, 'Ibuprofen', 'Anti-inflammatory pain reliever', 45.00, 12.00, 300, '2026-06-30', FALSE, TRUE),
('Diclofenac Sodium 50mg', 'Voveran', 1, 'Diclofenac Sodium', 'Non-steroidal anti-inflammatory drug', 60.00, 12.00, 200, '2026-08-15', TRUE, TRUE),

-- Antibiotics
('Amoxicillin 500mg', 'Amoxil', 2, 'Amoxicillin', 'Broad-spectrum antibiotic', 120.00, 12.00, 150, '2026-05-20', TRUE, TRUE),
('Azithromycin 500mg', 'Azee', 2, 'Azithromycin', 'Antibiotic for bacterial infections', 180.00, 12.00, 100, '2026-07-10', TRUE, TRUE),
('Ciprofloxacin 500mg', 'Ciplox', 2, 'Ciprofloxacin', 'Fluoroquinolone antibiotic', 95.00, 12.00, 120, '2026-09-25', TRUE, TRUE),

-- Vitamins & Supplements
('Vitamin D3 60000 IU', 'Uprise D3', 3, 'Cholecalciferol', 'Vitamin D supplement for bone health', 75.00, 5.00, 400, '2027-03-15', FALSE, TRUE),
('Multivitamin Tablets', 'Revital H', 3, 'Multiple Vitamins and Minerals', 'Complete daily multivitamin', 250.00, 5.00, 300, '2026-11-30', FALSE, TRUE),
('Calcium + Vitamin D', 'Shelcal 500', 3, 'Calcium Carbonate, Vitamin D3', 'Calcium supplement with vitamin D', 150.00, 5.00, 250, '2027-01-20', FALSE, TRUE),

-- Cold & Flu
('Cetirizine 10mg', 'Cetrizet', 4, 'Cetirizine Hydrochloride', 'Antihistamine for allergies', 30.00, 12.00, 350, '2026-10-10', FALSE, TRUE),
('Paracetamol + Phenylephrine', 'D-Cold Total', 4, 'Paracetamol, Phenylephrine, Cetirizine', 'Cold and flu relief', 55.00, 12.00, 280, '2026-04-30', FALSE, TRUE),
('Cough Syrup', 'Benadryl', 4, 'Diphenhydramine, Ammonium Chloride', 'Relief from cough and throat irritation', 85.00, 12.00, 200, '2026-08-20', FALSE, TRUE),

-- Diabetes Care
('Metformin 500mg', 'Glycomet', 5, 'Metformin Hydrochloride', 'Blood sugar control medication', 35.00, 12.00, 400, '2026-12-15', TRUE, TRUE),
('Glimepiride 2mg', 'Amaryl', 5, 'Glimepiride', 'Antidiabetic medication', 90.00, 12.00, 150, '2026-09-05', TRUE, TRUE),
('Insulin Glargine 100IU', 'Lantus Solostar', 5, 'Insulin Glargine', 'Long-acting insulin injection', 850.00, 5.00, 50, '2026-06-01', TRUE, TRUE),

-- Heart Care
('Atorvastatin 10mg', 'Atorlip', 6, 'Atorvastatin', 'Cholesterol lowering medication', 110.00, 12.00, 200, '2026-11-20', TRUE, TRUE),
('Aspirin 75mg', 'Ecosprin 75', 6, 'Aspirin', 'Blood thinner for heart health', 20.00, 12.00, 500, '2027-02-28', FALSE, TRUE),
('Ramipril 5mg', 'Cardace', 6, 'Ramipril', 'ACE inhibitor for blood pressure', 95.00, 12.00, 180, '2026-07-15', TRUE, TRUE),

-- Digestive Health
('Omeprazole 20mg', 'Omez', 7, 'Omeprazole', 'Reduces stomach acid production', 65.00, 12.00, 300, '2026-10-30', FALSE, TRUE),
('Pantoprazole 40mg', 'Pantop', 7, 'Pantoprazole', 'Proton pump inhibitor for acid reflux', 80.00, 12.00, 250, '2026-09-10', FALSE, TRUE),
('Probiotics Capsules', 'Econorm', 7, 'Saccharomyces Boulardii', 'Probiotic for gut health', 120.00, 5.00, 200, '2026-12-25', FALSE, TRUE),

-- Skin Care
('Clotrimazole Cream', 'Candid', 8, 'Clotrimazole', 'Antifungal cream for skin infections', 75.00, 18.00, 180, '2026-08-30', FALSE, TRUE),
('Betamethasone Cream', 'Betnovate', 8, 'Betamethasone', 'Corticosteroid for skin inflammation', 95.00, 18.00, 150, '2026-11-15', TRUE, TRUE),
('Hydroquinone Cream', 'Melalite', 8, 'Hydroquinone', 'Skin lightening cream', 180.00, 18.00, 100, '2026-07-20', FALSE, TRUE),

-- First Aid
('Antiseptic Liquid', 'Dettol', 9, 'Chloroxylenol', 'Antiseptic disinfectant', 110.00, 18.00, 250, '2027-06-30', FALSE, TRUE),
('Adhesive Bandages', 'Band-Aid', 9, 'Sterile Adhesive Strips', 'Sterile adhesive bandages', 45.00, 18.00, 400, '2028-12-31', FALSE, TRUE),
('Cotton Gauze Roll', 'Surecare', 9, 'Sterile Cotton Gauze', 'Medical grade gauze', 35.00, 18.00, 300, '2027-09-15', FALSE, TRUE),

-- Baby Care
('Gripe Water', 'Woodwards', 10, 'Dill Oil, Sarjikakshara', 'Relief from infant colic', 90.00, 12.00, 200, '2026-05-31', FALSE, TRUE),
('Baby Lotion', 'Johnson Baby', 10, 'Glycerin, Allantoin', 'Moisturizing lotion for babies', 180.00, 18.00, 250, '2027-03-30', FALSE, TRUE),
('Diaper Rash Cream', 'Himalaya', 10, 'Zinc Oxide, Aloe Vera', 'Protective cream for diaper rash', 150.00, 18.00, 200, '2026-10-25', FALSE, TRUE);

-- ============================================
-- Sample Addresses
-- ============================================
INSERT INTO addresses (user_id, address_line1, address_line2, city, state, postal_code, is_default) VALUES
(2, '123 MG Road', 'Near City Mall', 'Mumbai', 'Maharashtra', '400001', TRUE),
(2, '456 Park Street', 'Opposite Metro Station', 'Mumbai', 'Maharashtra', '400002', FALSE),
(3, '789 Lake View', 'Tower B, Flat 501', 'Bangalore', 'Karnataka', '560001', TRUE),
(3, '321 Ring Road', 'Phase 2', 'Bangalore', 'Karnataka', '560034', FALSE);

-- ============================================
-- Sample Coupons
-- ============================================
INSERT INTO coupons (code, description, discount_type, discount_value, min_order_amount, max_discount, valid_from, valid_to, usage_limit, active) VALUES
('WELCOME10', 'Welcome offer - 10% off', 'PERCENTAGE', 10.00, 500.00, 200.00, '2024-01-01', '2026-12-31', 1000, TRUE),
('SAVE50', 'Flat Rs.50 off on orders above Rs.1000', 'FIXED', 50.00, 1000.00, 50.00, '2024-01-01', '2026-12-31', 500, TRUE),
('HEALTH20', '20% off on health supplements', 'PERCENTAGE', 20.00, 1000.00, 500.00, '2024-01-01', '2026-12-31', 300, TRUE),
('FIRSTORDER', 'First order - 15% off', 'PERCENTAGE', 15.00, 300.00, 150.00, '2024-01-01', '2026-12-31', 1, TRUE);

-- ============================================
-- Sample Cart Items (for user John Doe)
-- ============================================
INSERT INTO cart (user_id, med_id, qty) VALUES
(2, 1, 2),  -- Paracetamol 500mg
(2, 7, 1),  -- Vitamin D3
(2, 10, 2); -- Cetirizine

-- ============================================
-- Sample Orders
-- ============================================
INSERT INTO orders (order_number, user_id, subtotal, discount_amount, gst_amount, delivery_charge, final_amount, payment_method, payment_status, order_status, shipping_address, coupon_id) VALUES
('MM202412110001', 2, 500.00, 50.00, 54.00, 40.00, 544.00, 'ONLINE', 'PAID', 'DELIVERED', '123 MG Road, Near City Mall, Mumbai, Maharashtra - 400001', 2),
('MM202412110002', 3, 800.00, 0.00, 96.00, 40.00, 936.00, 'COD', 'PENDING', 'CONFIRMED', '789 Lake View, Tower B, Flat 501, Bangalore, Karnataka - 560001', NULL),
('MM202412110003', 2, 350.00, 0.00, 42.00, 40.00, 432.00, 'ONLINE', 'PAID', 'SHIPPED', '456 Park Street, Opposite Metro Station, Mumbai, Maharashtra - 400002', NULL);

-- ============================================
-- Sample Order Items
-- ============================================
-- Order 1 items
INSERT INTO order_items (order_id, med_id, qty, price, subtotal) VALUES
(1, 1, 3, 25.00, 75.00),
(1, 2, 2, 45.00, 90.00),
(1, 7, 2, 75.00, 150.00),
(1, 10, 3, 30.00, 90.00),
(1, 16, 1, 95.00, 95.00);

-- Order 2 items
INSERT INTO order_items (order_id, med_id, qty, price, subtotal) VALUES
(2, 13, 2, 35.00, 70.00),
(2, 16, 3, 95.00, 285.00),
(2, 8, 2, 250.00, 500.00);

-- Order 3 items
INSERT INTO order_items (order_id, med_id, qty, price, subtotal) VALUES
(3, 19, 2, 65.00, 130.00),
(3, 11, 1, 55.00, 55.00),
(3, 27, 1, 90.00, 90.00);
