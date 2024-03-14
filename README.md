# Expense Calculator - MVP

## Software Requirements Specification (SRS)

### Introduction:
The Expense and Income Management application is designed to assist users in managing their finances effectively by tracking both expenses and income. It provides users with tools to monitor their spending, track income sources, analyze profit and loss, and maintain financial stability.

### Functional Requirements:

#### User Management:

Users can register accounts by providing basic information such as name, email, and password.
Users can log in to their accounts securely.
Users can update their profile information.
Users can delete their accounts.
#### Expense Management:
- Users can add daily expenses, including various categories such as bus fare, groceries, food, etc.
- Users can view, update, and delete their daily expenses.
- Users can add weekly expenses, consisting of recurring expenses or specific expenses for the week.
- Users can view, update, and delete their weekly expenses.
- Users can add monthly expenses, such as rent, utilities, subscriptions, etc.
- Users can view, update, and delete their monthly expenses.

#### Transaction Management:
- Users can add transactions associated with their expenses, specifying the amount, category, date, and payment method.
- Users can view, update, and delete transactions.
- 
#### Expense Summary:
- Users can view summaries of their daily, weekly, and monthly expenses.
- Summaries include total expenses, categorized expenses, and trends over time.
  
#### Income Management:
- Users can add income transactions, specifying the source, amount, date, and category.
- Users can view, update, and delete income transactions.

#### Money Management:
- Users can set budgets for different expense categories and income sources.
- Users receive notifications or alerts when exceeding budget limits.

#### Profit-Loss Calculations:
- The application calculates the user's net profit or loss based on income and expenses.
- Users can view profit and loss statements for different time periods.

#### Financial Reports:
- Users can generate financial reports summarizing income, expenses, profit, and loss.
- Reports can be customized by date range, category, and other criteria.

### Non-Functional Requirements:
#### Performance:
- The application should handle a large volume of transactions efficiently.
- Calculations for profit and loss should be accurate and updated in real-time.

#### Security:
- Financial data should be stored securely with encryption and access controls.
- User authentication and authorization mechanisms should be robust.

#### Usability:
- The user interface should be intuitive and visually appealing.
- Features such as data visualization and interactive charts enhance user experience.

### Updated System Design:
#### Architecture:
- The application architecture remains a microservices-based architecture to ensure scalability and maintainability. However, additional microservices will be introduced to handle income management, budgeting, and financial reporting.

#### Components:
- **User Service:** Manages user registration, authentication, and profile management.
- **Expense Service:** Handles the management of daily, weekly, and monthly expenses.
- **Transaction Service:** Manages transactions associated with expenses.
- **Summary Service:** Generates summaries of expenses for users based on their preferences and time frames.
- **Income Service:** Manages income transactions, income categories, and income sources.
- **Budget Service:** Handles budget management, allowing users to set and track budgets for various expense categories and income sources.
- **Profit-Loss Service:** Calculates and tracks the user's profit and loss over time.
- **Reporting Service:** Generates financial reports based on user-defined criteria and preferences.

#### Technologies:
- **Backend:** Java with Spring Boot for microservices development.
- **Frontend:** React.js or Angular for building a responsive and interactive user interface.
- **Database:** PostgreSQL for storing user data, expenses, and transactions.
- **Authentication:** JSON Web Tokens (JWT) for secure user authentication.
- **Deployment:** Docker and Kubernetes for containerization and orchestration of microservices.

### Database Model
![Database Model](https://github.com/ajas-abdulazeez/expanSc/assets/84241281/fb79f9cf-2059-4c02-a68f-9c40b05bfa4d)
