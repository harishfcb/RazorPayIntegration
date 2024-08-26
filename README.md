
# **Payment Integration System**

# https://razorpayintegration-2.onrender.com/index.html

Welcome to the **Payment Integration System** project! This comprehensive system is designed to handle secure and efficient payment processing through **Razorpay**. The project is built using **Spring Boot** and is integrated with **Razorpay's API** to manage orders, capture payments, and handle webhooks for payment status updates. Additionally, the system includes email notifications and order management features.

## **Project Overview**

This project is a robust solution for e-commerce platforms requiring secure and reliable payment processing. The key features of this system include:

- **Order Creation:** Generate and manage orders through the Razorpay API.
- **Payment Capture:** Seamlessly capture payments once authorized.
- **Webhook Handling:** Automatically update payment statuses via Razorpay's webhook events.
- **Email Notifications:** Send confirmation emails to customers upon successful order placement.
- **Order Management:** Track and manage order details, including items and shipping addresses.

## **Getting Started**

### **Prerequisites**

To run this application, you need to have the following installed:

- **Java 17** or higher
- **Maven** (for dependency management)
- **ngrok** (for testing webhooks in development)
- **Razorpay Account** (for API integration)

### **Running the Application**

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/your-repository/payment-integration-system.git
   cd payment-integration-system
   ```

2. **Set Up Configuration:**
    - Ensure that your `application.properties` file is configured with your Razorpay API credentials.
    - Replace placeholders in the `application.properties` with your **Razorpay Key ID** and **Secret**.

3. **Build and Run the Application:**
    - Use the following command to build and run the Spring Boot application:
      ```bash
      mvn clean install
      mvn spring-boot:run
      ```

4. **Access the Frontend:**
    - After the application starts, open the `index.html` file in your browser.
    - From the **index.html** page, you can create an order and initiate the payment process.

### **Testing Webhooks**

- **ngrok** is used to expose your local server to the internet, enabling webhook testing.
- To start ngrok, run:
  ```bash
  ngrok http 8080
  ```
- Update the **Razorpay** dashboard to use the provided ngrok URL for webhook events.

### **Project Structure**

- **Controller Layer:** Manages incoming requests and directs them to appropriate services.
- **Service Layer:** Contains business logic and interactions with Razorpay.
- **Model Layer:** Defines the data structures used within the application.
- **Repository Layer:** Handles database interactions.
- **Helper Classes:** Utilities for tasks such as sending emails and mapping Razorpay responses.

## **Features in Detail**

### **Order Creation**
The system allows users to create orders, which are then processed via Razorpay. Once an order is created, it's saved in the database along with associated items and shipping details.

### **Payment Capture**
After payment authorization, the system captures the payment using the Razorpay API and updates the order status accordingly.

### **Webhook Handling**
Webhooks from Razorpay are handled to ensure real-time updates of payment statuses. This ensures that the system is always in sync with Razorpay.

### **Email Notifications**
Replace mailId and password with your's
Upon successful order creation, a confirmation email is sent to the customer. This is done using the **Jakarta Mail API** with Thymeleaf templates for rich content.

## **Conclusion**

The **Payment Integration System** is a fully-fledged solution for integrating payments into your application. With easy setup and powerful features, it offers a seamless way to handle payments securely and efficiently.

**Enjoy building with the Payment Integration System!**
```

This README.md is now bold, insightful, and highly informative, providing all the necessary details to get started and run the application.
# Payment Integration Project

This project integrates Razorpay payment processing with a Spring Boot application. It includes functionalities for creating and managing orders, processing payments, handling webhooks, and sending email notifications. 

## Table of Contents

- [Project Overview](#project-overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Setup and Installation](#setup-and-installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [Webhook Testing](#webhook-testing)
- [Contributing](#contributing)
- [License](#license)

## Project Overview

The Payment Integration Project is designed to handle e-commerce transactions through Razorpay. It features:

- **Order Management:** Create and save orders, including order items and shipping addresses.
- **Payment Integration:** Process payments via Razorpay, capture payments, and handle payment authorization.
- **Email Notifications:** Send order confirmation emails to users.
- **Webhook Handling:** Process Razorpay webhooks to handle events like payment authorization.

## Features

- **Order Creation and Management**
  - Create orders with associated items and shipping details.
  - Generate unique order IDs.
  
- **Payment Processing**
  - Create Razorpay orders.
  - Capture payments after authorization.
  - Handle payment failures and retries.

- **Email Notifications**
  - Send confirmation emails upon successful order creation.
  
- **Webhook Handling**
  - Process Razorpay webhooks to capture payments and update order statuses.

## Technologies Used

- **Backend:** Spring Boot
- **Payment Gateway:** Razorpay
- **Database:** PostgreSQL
- **Email Service:** Jakarta Mail
- **Frontend:** Custom HTML and JavaScript
- **Webhook Testing:** ngrok

## Setup and Installation

1. **Clone the Repository**

   ```bash
   git clone https://github.com/your-repo/payment-integration.git
   cd payment-integration
   ```

2. **Set Up Environment Variables**

   Modify in the application properties

   ```env
   RAZORPAY_KEY_ID=your_razorpay_key_id
   RAZORPAY_KEY_SECRET=your_razorpay_key_secret
   ```

3. **Build and Run the Application**

   Ensure you have Maven installed, then build and run the application:

   ```bash
   ./mvnw spring-boot:run
   ```

4. **Run ngrok for Webhook Testing**

   Download and install ngrok from [ngrok's website](https://ngrok.com/), then start ngrok to expose your local server:

   ```bash
   ngrok http 8821
   ```

   Use the ngrok URL provided for webhook configuration in the Razorpay dashboard.

## Configuration

1. **Razorpay Configuration**

    - Go to the [Razorpay Dashboard](https://dashboard.razorpay.com/).
    - Configure your webhook URL using the ngrok URL provided.
    - Set up API keys and update your application.properties with the `RAZORPAY_KEY_ID` and `RAZORPAY_KEY_SECRET`.

2. **Email Configuration**

    - Ensure Jakarta Mail is correctly configured in your application properties for sending emails.

## Usage

1. **Place an Order**
    - Use the checkout page on your frontend to place an order.
    - The order details will be sent to the backend and saved in the database.

2. **Process Payment**
    - Upon placing an order, you will be redirected to Razorpay for payment processing.
    - Once payment is completed, the confirmation page will be displayed, and a confirmation email will be sent to the user.

3. **Handle Webhooks**
    - Razorpay will send webhook events to your configured URL (via ngrok).
    - The backend will process these events to handle payment status updates.

## Webhook Testing

1. **Start the Application**
    - Make sure your Spring Boot application is running locally.

2. **Run ngrok**
    - Use ngrok to expose your local server:

      ```bash
      ngrok http 8821
      ```

    - Copy the ngrok URL and configure it as the webhook URL in Razorpay.

3. **Test Webhooks**
    - Trigger events in Razorpay to test webhook handling.
    - Monitor your logs for processing results.

## Contributing

Contributions are welcome! To contribute:

1. **Fork the Repository**
    - Click the "Fork" button on GitHub.

2. **Create a New Branch**
    - Create a new branch for your changes:

      ```bash
      git checkout -b your-feature-branch
      ```

3. **Make Changes**
    - Implement your changes and commit them:

      ```bash
      git add .
      git commit -m "Add your message here"
      ```

4. **Push Changes**
    - Push your changes to your forked repository:

      ```bash
      git push origin your-feature-branch
      ```

5. **Open a Pull Request**
    - Go to the original repository and open a pull request.

