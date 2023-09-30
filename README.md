# MySQLWithJava2

## Current Results:
The Db_Sign_In class is the gateway for employees to access the main features of the application. The sign-in page comes equipped with fields for email and password input, providing mechanisms to authenticate existing users. Additionally, it offers a registration button for new users to navigate to the registration page.

The page is initialized with a size of 1000x1000 and a title "Juan and Sons Inc."
It uses a JPanel named contentPane as its main container, with a white background.
For branding purposes, the company logo (logo.png) is added to the top-center of the page, with a shadow effect for enhanced aesthetics.
A heading labeled "Employee Login" is prominently displayed.
Input Fields:

#### Email Field:
Located at coordinates (480, 170) with a size of 280x68. It's preceded by a label titled "Email".
#### Password Field: 
Positioned at (481, 286) with dimensions 281x68. It is accompanied by a label named "Password". The password entered here remains concealed for security.

### Buttons:
#### Register New Employee Button: 
Found at (350, 395) with dimensions 165x55. Clicking this navigates the user to the registration page (Register.java), while the current sign-in window is closed.
#### Login Button: 
Located right next to the registration button at (550, 395) with the same dimensions. When clicked, the application attempts to authenticate the user's credentials against the database. Successful authentication transitions the user to the homepage (Db_Home_Page). An unsuccessful attempt results in a message prompting the user to verify their email or password.

#### Database Connection & Validation:
clicking the Login button, the app fetches the entered email and password. Then prepares an SQL statement to compare these against the 'emp_login' table in the database.
Passwords are securely hashed using SHA2 with 256-bit encryption.
Successful validation redirects the user to the home page and displays a success message. In contrast, a failure prompts the user to check their input.

![sign_in](https://github.com/jcast6/MySQLWithJava2/assets/89822103/c1c80103-fec1-47b7-a4e9-067ead29064e)

The Register class provides a graphical interface for registering a new employee in the system. It ensures that the user provides the necessary information and then stores it in the appropriate database tables.

Size: 650x650 pixels.
Resizable.
Title: "Register form".
Layout: Absolute positioning (null layout).
Fields & Labels:

pageHeading: A label for the registration page heading.
#### Labels indicating what the user should input in each text field: 
id, fnameLabel, lnameLabel, departmentLabel, emailLabel, passwordLabel
#### Text fields for user input. The password field masks the user's password for security: 
idField, firstNameField, lastNameField, departmentField, emailField, passwordField.

#### Buttons:
#### registerButton: 
A button for the user to confirm and submit their registration.

####Listeners and Logic:
The idField uses a FocusListener to validate the entered ID based on a specific pattern.
The registerButton has an ActionListener that:
Validates if all fields have been filled.
Displays a message once the registration is successful and provides the user with options to either login or exit.
Inserts the entered details into the employee and emp_login tables in the database, after hashing the password with SHA2 for security.

#### Validation:
idField is validated to ensure that the ID consists of exactly three characters.
Before inserting data into the database, it checks to ensure that all fields are filled.
Hashed Passwords: Passwords are not stored in plain text. Instead, they are hashed using SHA2 for enhanced security.

#### Database Interaction:
New employee details are inserted into the employee table.
The email and hashed password are stored in the emp_login table for authentication purposes.

#### Post-Registration:
Once registration is successful, users are given the option to navigate to the login page or exit the application.

![newEmp](https://github.com/jcast6/MySQLWithJava2/assets/89822103/a746947b-4bf9-4d53-86b8-ef713b306772)

![regi_confirmation](https://github.com/jcast6/MySQLWithJava2/assets/89822103/25bde045-15d2-4673-b582-b50f901bc53b)

![success](https://github.com/jcast6/MySQLWithJava2/assets/89822103/60cd6c49-0dfd-4cd5-903b-00c44a4cfc8d)

![stockPage](https://github.com/jcast6/MySQLWithJava2/assets/89822103/ccdb925e-d6b0-406a-9c8a-6e1b65d51d02)

![tables](https://github.com/jcast6/MySQLWithJava2/assets/89822103/9778aae2-3cb1-4453-84cc-4eac85858756)


![addItem](https://github.com/jcast6/MySQLWithJava2/assets/89822103/64e242cb-4cb6-4dc9-81cf-157ce7935ca6)


![addItem2](https://github.com/jcast6/MySQLWithJava2/assets/89822103/f3d89e61-c333-475f-bb9a-437d4404b44d)

![create_invoice](https://github.com/jcast6/MySQLWithJava2/assets/89822103/a6618357-5847-41cc-a8c1-a9607374ac5c)

![order_success](https://github.com/jcast6/MySQLWithJava2/assets/89822103/fd55cff0-a96f-4473-8c1a-1498a1f3270a)

![invoice_form_with_items](https://github.com/jcast6/MySQLWithJava2/assets/89822103/be5f2982-c877-4a37-8b6f-e70b3c646001)


