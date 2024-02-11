# Dynamic PDF Generation (Spring Boot)

This project provides a REST API to generate PDF using Thymeleaf. It accepts data in the form of raw JSON and generates a PDF based on the received data. It also provides the ability to download and store the above-generated PDF on the local storage.

## Installation

1. Clone the repository:

   ```
   git clone https://github.com/kediasparsh/pdf-generator.git
   ```

2. Import the project into your preferred IDE.

3. Build the project using Maven or your IDE's build feature.

4. Run the application:

   - If using an IDE, run the main application class.
   - If using Maven, run `mvn clean spring-boot:run` from the project's root directory.

5. The application should now be running on `http://localhost:8080`.

6. Replace the `PDF_DIRECTORY` String with the appropriate path in the `BillController.java` file.

## API Usage

### Dynamic PDF Generation

#### Request

```
POST /bill
Content-Type: application/json

{
    "seller": "XYZ Pvt. Ltd.",
    "sellerGstin": "29AABBCCDD121ZD",
    "sellerAddress": "New Delhi, India",
    "buyer": "Vedant Computers",
    "buyerGstin": "29AABBCCDD131ZD",
    "buyerAddress": "New Delhi, India",
    "items": [
        {
        "name": "Product 1",
        "quantity": "12 Nos",
        "rate": 123.00,
        "amount": 1476.00
        }
    ]
}

```

Parameters:
- `seller` (required)
- `sellerGstin` (required)
- `sellerAddress` (required)
- `buyer` (required)
- `buyerGstin` (required)
- `buyerAddress` (required)
- `items` (required)
