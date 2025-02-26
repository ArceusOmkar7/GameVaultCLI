package com.project.gamevaultcli;

import com.project.gamevaultcli.entities.Game;
import com.project.gamevaultcli.entities.Order;
import com.project.gamevaultcli.entities.Transaction;
import com.project.gamevaultcli.entities.User;
import com.project.gamevaultcli.exceptions.CartEmptyException;
import com.project.gamevaultcli.exceptions.GameNotFoundException;
import com.project.gamevaultcli.exceptions.InvalidUserDataException;
import com.project.gamevaultcli.exceptions.OrderNotFoundException;
import com.project.gamevaultcli.exceptions.UserNotFoundException;
import com.project.gamevaultcli.management.CartManagement;
import com.project.gamevaultcli.management.GameManagement;
import com.project.gamevaultcli.management.OrderManagement;
import com.project.gamevaultcli.management.TransactionManagement;
import com.project.gamevaultcli.management.UserManagement;
import com.project.gamevaultcli.storage.CartStorage;
import com.project.gamevaultcli.storage.GameStorage;
import com.project.gamevaultcli.storage.OrderStorage;
import com.project.gamevaultcli.storage.TransactionStorage;
import com.project.gamevaultcli.storage.UserStorage;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class GameVaultCLI {

    public static void main(String[] args) {
        // Initialize Storages
        UserStorage userStorage = new UserStorage();
        GameStorage gameStorage = new GameStorage();
        CartStorage cartStorage = new CartStorage();
        OrderStorage orderStorage = new OrderStorage();
        TransactionStorage transactionStorage = new TransactionStorage();

        // Initialize Managements
        UserManagement userManagement = new UserManagement(userStorage);
        GameManagement gameManagement = new GameManagement(gameStorage);
        CartManagement cartManagement = new CartManagement(cartStorage);
        OrderManagement orderManagement = new OrderManagement(orderStorage, cartStorage, userStorage);
        TransactionManagement transactionManagement = new TransactionManagement(transactionStorage);

        // Predefined Data (Initialize after storages so they're populated)
        initializeData(userManagement, gameManagement, orderManagement, transactionManagement);

        Menu menu = new Menu(userManagement, gameManagement, cartManagement, orderManagement, transactionManagement);
        menu.run();
    }

    // Predefined Data Initialization
    private static void initializeData(UserManagement userManagement, GameManagement gameManagement, OrderManagement orderManagement, TransactionManagement transactionManagement) {
        try {
            // Predefined Users
            User user1 = new User("sasuke@gmail.com", "is this my password?", "Sasuke", 50.0f);
            userManagement.addUser(user1);
            User user2 = new User("naruto@gmail.com", "believeit", "Naruto", 100.0f);
            userManagement.addUser(user2);

            // Predefined Games
            Game game1 = new Game("Spider-Man Remastered", "Game created ?? lol lorem ipsum or wot", "Insomniac Games", "PC", 52.3f, new Date());
            gameManagement.addGame(game1);
            Game game2 = new Game("God of War", "A great game", "Santa Monica Studio", "PS4", 49.99f, new Date());
            gameManagement.addGame(game2);
            Game game3 = new Game("The Last of Us Part II", "A controversial masterpiece", "Naughty Dog", "PS4", 59.99f, new Date());
            gameManagement.addGame(game3);
            Game game4 = new Game("Cyberpunk 2077", "A buggy mess... or is it?", "CD Projekt Red", "PC", 39.99f, new Date());
            gameManagement.addGame(game4);

            // Predefined Orders - will need to manually create cart and place order
            // Predefined Transactions
            Transaction transaction1 = new Transaction(1, user1.getUserId(), game1.getGameId(), "Purchase", 52.3f, LocalDateTime.now());
            transactionManagement.addTransaction(transaction1);

        } catch (InvalidUserDataException e) {
            System.out.println("Error initializing data: " + e.getMessage());
        }
    }

    // Inner class for the Menu
    static class Menu {
        private final UserManagement userManagement;
        private final GameManagement gameManagement;
        private final CartManagement cartManagement;
        private final OrderManagement orderManagement;
        private final TransactionManagement transactionManagement;
        private final Scanner scanner = new Scanner(System.in);

        public Menu(UserManagement userManagement, GameManagement gameManagement, CartManagement cartManagement, OrderManagement orderManagement, TransactionManagement transactionManagement) {
            this.userManagement = userManagement;
            this.gameManagement = gameManagement;
            this.cartManagement = cartManagement;
            this.orderManagement = orderManagement;
            this.transactionManagement = transactionManagement;
        }

        public void run() {
            boolean running = true;
            while (running) {
                displayMenu();
                try {
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (choice) {
                        case 1:
                            createUser();
                            break;
                        case 2:
                            createGame();
                            break;
                        case 3:
                            addGameToCart();
                            break;
                        case 4:
                            placeOrder();
                            break;
                        case 5:
                            createTransaction();
                            break;
                        case 6:
                            listGames();
                            break;
                        case 7:
                            listUsers(); // Changed from viewUser
                            break;
                        case 8:
                            viewOrders();
                            break;
                        case 9:
                            viewTransactions();
                            break;
                        case 0:
                            running = false;
                            System.out.println("Exiting Game Vault CLI...");
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine(); // Clear the invalid input
                } catch (Exception e) {
                    System.out.println("An unexpected error occurred: " + e.getMessage());
                }
            }
        }

        private void displayMenu() {
            System.out.println("\nGame Vault CLI Menu:");
            System.out.println("1. Create User");
            System.out.println("2. Create Game");
            System.out.println("3. Add Game to Cart (User 1)");
            System.out.println("4. Place Order (User 1)");
            System.out.println("5. Create Transaction");
            System.out.println("6. List Games");
            System.out.println("7. List Users"); // Changed
            System.out.println("8. View Orders");
            System.out.println("9. View Transactions");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
        }

        private void createUser() {
            try {
                System.out.print("Enter email: ");
                String email = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter wallet balance: ");
                float walletBalance = scanner.nextFloat();
                scanner.nextLine(); // Consume newline

                User user = new User(email, password, username, walletBalance);
                userManagement.addUser(user);
                System.out.println("User created: " + user.getUsername() + " with ID: " + user.getUserId());
            } catch (InvalidUserDataException e) {
                System.out.println("Error creating user: " + e.getMessage());
            }
        }

        private void createGame() {
            try {
                System.out.print("Enter game title: ");
                String title = scanner.nextLine();
                System.out.print("Enter game description: ");
                String description = scanner.nextLine();
                System.out.print("Enter game developer: ");
                String developer = scanner.nextLine();
                System.out.print("Enter game platform: ");
                String platform = scanner.nextLine();
                System.out.print("Enter game price: ");
                float price = scanner.nextFloat();
                scanner.nextLine(); // Consume newline

                Game game = new Game(title, description, developer, platform, price, new Date());
                gameManagement.addGame(game);
                System.out.println("Game created: " + game.getTitle() + " with ID: " + game.getGameId());
            } catch (Exception e) {
                System.out.println("Error creating game: " + e.getMessage());
            }
        }

        private void addGameToCart() {
            try {
                System.out.print("Enter Game ID to add to cart: ");
                int gameId = scanner.nextInt();
                scanner.nextLine();

                Game game = gameManagement.getGame(gameId);
                cartManagement.addGameToCart(1, game);
                System.out.println("Added " + game.getTitle() + " to cart for user 1");
            } catch (GameNotFoundException e) {
                System.out.println("Game not found with ID: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error adding game to cart: " + e.getMessage());
            }
        }

        private void placeOrder() {
            try {
                orderManagement.placeOrder(1);
                System.out.println("Order placed for user 1");
            } catch (CartEmptyException e) {
                System.out.println("Cart is empty: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error placing order: " + e.getMessage());
            }
        }

        private void createTransaction() {
            try {
                System.out.print("Enter User ID: ");
                int userId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.print("Enter Game ID: ");
                int gameId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.print("Enter Transaction Type: ");
                String transactionType = scanner.nextLine();
                System.out.print("Enter Amount: ");
                Float amount = scanner.nextFloat();
                scanner.nextLine(); // Consume newline

                transactionManagement.addTransaction(new Transaction(3, userId, gameId, transactionType, amount, LocalDateTime.now()));
                System.out.println("Transaction created");
            } catch (Exception e) {
                System.out.println("Error creating Transaction: " + e.getMessage());
            }
        }

        private void listGames() {
            try {
                List<Game> games = gameManagement.getAllGames();
                System.out.println("\n--- Game List ---");
                for (Game game : games) {
                    System.out.println(game); // Assuming Game has a decent toString()
                }
            } catch (Exception e) {
                System.out.println("Error listing games: " + e.getMessage());
            }
        }

        private void listUsers() { // Changed from viewUser
            try {
                List<User> users = userManagement.getAllUsers();
                System.out.println("\n--- User List ---");
                for (User user : users) {
                    System.out.println("User ID: " + user.getUserId() + ", Username: " + user.getUsername() + ", Email: " + user.getEmail());
                }
            } catch (Exception e) {
                System.out.println("Error listing users: " + e.getMessage());
            }
        }

        private void viewOrders() {
            try {
                System.out.print("Enter User ID to view orders: ");
                int userId = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                List<Order> allOrders = orderManagement.getAllOrders();
                System.out.println("\n--- Orders List ---");
                for (Order order : allOrders) {
                    if(order.getUserId() == userId) {
                        System.out.println("Order ID: " + order.getOrderId());
                        System.out.println("Total Amount: " + order.getTotalAmount());
                        System.out.println("Order Date: " + order.getOrderDate());
                        System.out.println("Games: " + order.getGames()); //Needs to override tostring for the games
                        System.out.println("--------------------");
                    }
                }
            } catch (Exception e) {
                System.out.println("Error viewing orders: " + e.getMessage());
            }
        }

        private void viewTransactions() {
            try {
                System.out.print("Enter User ID to view transactions: ");
                int userId = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                List<Transaction> allTransactions = transactionManagement.getAllTransactions();
                System.out.println("\n--- Transactions List ---");
                for (Transaction transaction : allTransactions) {
                    if(transaction.getUserId() == userId) {
                        System.out.println("Transaction ID: " + transaction.getTransactionId());
                        System.out.println("Transaction Type: " + transaction.getTransactionType());
                        System.out.println("Amount: " + transaction.getAmount());
                        System.out.println("Transaction Date: " + transaction.getTransactionDate());
                        System.out.println("--------------------");
                    }
                }
            } catch (Exception e) {
                System.out.println("Error viewing transactions: " + e.getMessage());
            }
        }

    }
}