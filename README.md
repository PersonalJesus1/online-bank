# Online Bank

Welcome to the Online Bank project! This project provides a web-based platform to manage users, debit cards, mortgages, and auto loans. Users can view, edit, create, and delete these entities via the web interface.

## Features

- **Users**: Manage user accounts, including name, surname, date of birth, sex, and a list of associated mortgages and auto loans.
- **Debit Cards**: View debit card details, make deposits, edit card information, and delete cards.
- **Mortgages**: View mortgage details, make payments, edit mortgage information, and delete mortgages.
- **Auto Loans**: View auto loan details, make payments, edit loan information, and delete loans.

## Requirements

- **Java 11** or higher
- **Maven** for dependency management and building the project
- **PostgreSQL** database to store user, debit card, mortgage, and auto loan information

## Setup and Installation

### Configure the Database
- Set up a PostgreSQL database.
- Update the application.properties file in the src/main/resources directory with your database connection details:
spring.datasource.url=jdbc:postgresql://localhost:5432/bankData
spring.datasource.username=postgres
spring.datasource.password=postgresql

- Build and Run the Application
- Install the necessary dependencies and build the project with Maven:
mvn clean install
- Run the application:
mvn spring-boot:run
The application should now be running locally on http://localhost:8080.

## Usage
Once the application is running, you can access the following pages via the main page:

- Users: View Users
- Debit Cards: View Debit Cards
- Mortgages: View Mortgages
- Auto Loans: View Auto Loans

Each section allows you to:
- View a list of records.
- Create new records.
- Edit existing records.
- Delete records.

## Testing
Unit tests for the project are located in the src/test/java directory. 
To run the tests, use the following Maven command:

mvn test

## License
This project is licensed under the MIT License - see the LICENSE file for details.

### How to use the `README.md` file:
1. **Project Overview**: The file starts with a brief description of the Online Bank project and its features.
2. **Requirements**: It lists the prerequisites like Java, Maven, and PostgreSQL.
3. **Setup Instructions**: Detailed steps for cloning the repository, configuring the database, and running the project locally.
4. **Usage**: The URLs for accessing the various features of the application.
5. **Testing**: Instructions on running unit tests.
6. **License**: A section to specify the project's licensing information.

This `README.md` should help guide users in setting up and using your project on their local machine.


### Clone the repository

```bash
git clone https://github.com/PersonalJesus1/online-banky.git
cd online-bank
