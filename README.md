# task_1_a

# Features
# .Load Management
1.Create, read, update, and delete loads
2.Filter loads by shipper, truck type, and status
3.Automatic status transitions (POSTED → BOOKED → CANCELLED)

# .Booking Management
1.Create and manage bookings
2.Accept/reject booking requests
3.Validation to prevent invalid state transitions

# Technologies
1.Backend: Spring Boot 3.2.0
2.Database: PostgreSQL
3.Build Tool: Maven
4.Java Version: 17

# Setup Instructions
1.Prerequisites
2.Java 17 JDK
3.PostgreSQL 
4.Maven 

# API Documentation
The API will be available at http://localhost:8080

# Load Endpoints
Method	Endpoint	Description
POST	/api/loads	Create a new load
GET	/api/loads	Get paginated list of loads
GET	/api/loads/{id}	Get load details
PUT	/api/loads/{id}	Update load
DELETE	/api/loads/{id}	Cancel load (set to CANCELLED)

 # Booking Endpoints
Method	Endpoint	Description
POST	/api/bookings	Create a new booking
GET	/api/bookings	Get paginated list of bookings
GET	/api/bookings/{id}	Get booking details
PUT	/api/bookings/{id}/accept	Accept booking
DELETE	/api/bookings/{id}	Delete booking

# Assumptions
# Status Transitions:
Load status automatically changes to BOOKED when first booking is created
Load status changes to CANCELLED when last booking is deleted

# Validation:
Cannot create booking for CANCELLED loads
All required fields must be provided
