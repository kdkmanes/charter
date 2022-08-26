
``
#Steps to run the project locally:
1. Import the project in IDE (IntelliJ preferred)
2. Select H2 as Database in Database Window
3. Please provide following configuration for H2 database connectivity:
Name: RewardsCalculator
User: user
Password: user
database: default
URL: jdbc:h2:mem:default
4. Run the main class file (TotalRewardsPointCalculatorApplication.java)
5. Run the following endpoints on postman or alternative application:
   - http://localhost:8080/charter/customers/1/reward
   - http://localhost:8080/charter/customers/2/reward
   - http://localhost:8080/charter/customers/3/reward
   - http://localhost:8080/charter/customers/4/reward
   - http://localhost:8080/charter/customers/5/reward
   - http://localhost:8080/charter/customers/6/reward
