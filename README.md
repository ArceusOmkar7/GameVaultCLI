# Game Vault CLI

## Project Description

The Game Vault CLI is a command-line application for managing a collection of video games. It allows users to perform basic operations such as creating users, adding games, managing shopping carts, placing orders, and viewing transaction history. 
### ✅ Done

*   **Project Setup:**  Basic project structure with separate packages for entities, storage, management, interfaces, and exceptions.
*   **Entities:**  Implementation of core entities (`User`, `Game`, `Cart`, `Order`, `Transaction`).
*   **Storage Layer:** Implementation of in-memory storage classes (`UserStorage`, `GameStorage`, `CartStorage`, `OrderStorage`, `TransactionStorage`) implementing the `StorageInterface`.
*   **Management Layer:**  Implementation of management classes (`UserManagement`, `GameManagement`, `CartManagement`, `OrderManagement`, `TransactionManagement`) to handle business logic.
*   **Exception Handling:** Definition of custom exception classes for common error scenarios.
*   **Basic CLI Menu:**  Interactive command-line menu with options to create users, add games, add games to carts, and place orders.
*   **Predefined Data:** Initialization of the system with predefined users and games.
*   **List Users:**  Menu option to display a list of all users.
*   **List Games:** Menu option to display a list of all games.
*   **View Orders:** Menu option to display orders for a specific user.
*   **View Transactions:** Menu option to display transactions for a specific user.

### 🚧 To Do

*   [ ] Implement proper `toString()` methods: Ensure all entities (Game, User, Order, Transaction) have `toString()` methods that provide a clear and detailed representation of their data.
*   [ ] Implement robust error handling: Improve the robustness of error handling in menu options, providing more informative messages and handling edge cases.
*   [ ] Implement Cart Management Operations: Add methods to the menu to add game to cart, remove game from cart, list items in cart.
*   [ ] Implement Order Management Operations: Add method to the menu to view Order details, by searching for orderId
*   [ ] Implement Transaction Management Operations: Add method to the menu to view all transactions, or view transaction details by searching for transactionId
*   [ ] Implement User Wallet Management:  Allow users to deposit funds, withdraw funds, and view their current wallet balance.
*   [ ] Implement Game Searching/Filtering:  Add functionality to search for games by title, developer, platform, etc.
*   [ ] Implement Data Persistence: Replace the in-memory storage with a persistent data store (e.g., file-based storage, SQLite database).
*   [ ] Add Input Validation: Implement robust input validation to prevent invalid data from being entered.
*   [ ] Implement Transaction Management: Ensure operations that must happen together (e.g., placing an order, deducting inventory) are treated as a single transaction.

## Future Enhancements

*   **Abstract Classes:** Introduce abstract base classes (e.g., `AbstractGame`) if there are common properties or behaviors among different types of games.
*   **Interfaces:** Implement more interfaces (e.g., `Rentable`, `Discountable`) to define optional features for entities.
*   **Testing:** Write unit tests for the management and storage layers to ensure code quality.
*   **Dependency Injection Framework:** Integrate a dependency injection framework (e.g., Spring) to improve testability and maintainability.
*   **GUI:** Develop a graphical user interface (GUI) for the application.
*   **Networking:**  Allow multiple users to access the Game Vault concurrently over a network.

## Project Structure
