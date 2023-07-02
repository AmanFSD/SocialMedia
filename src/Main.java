import java.util.*;

class User {
    private String username;
    private Set<User> friends;

    public User(String username) {
        this.username = username;
        this.friends = new HashSet<>();
    }

    public String getUsername() {
        return this.username;
    }

    public Set<User> getFriends() {
        return this.friends;
    }

    public void addFriend(User friend) {
        this.friends.add(friend);
    }

    public void removeFriend(User friend) {
        this.friends.remove(friend);
    }
}

class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}

class UserDoesNotExistException extends Exception {
    public UserDoesNotExistException(String message) {
        super(message);
    }
}

class SocialNetwork {
    private Set<User> users;

    public SocialNetwork() {
        this.users = new HashSet<>();
    }

    public void registerUser(String username) throws UserAlreadyExistsException {
        for(User user : users) {
            if(user.getUsername().equals(username)) {
                throw new UserAlreadyExistsException("Username already exists.");
            }
        }
        users.add(new User(username));
    }

    public User getUser(String username) throws UserDoesNotExistException {
        for(User user : users) {
            if(user.getUsername().equals(username)) {
                return user;
            }
        }
        throw new UserDoesNotExistException("User does not exist.");
    }

    public void addFriend(String username1, String username2) throws UserDoesNotExistException {
        User user1 = getUser(username1);
        User user2 = getUser(username2);
        user1.addFriend(user2);
        user2.addFriend(user1);
    }

    public void removeFriend(String username1, String username2) throws UserDoesNotExistException {
        User user1 = getUser(username1);
        User user2 = getUser(username2);
        user1.removeFriend(user2);
        user2.removeFriend(user1);
    }

    public Set<User> getFriends(String username) throws UserDoesNotExistException {
        User user = getUser(username);
        return user.getFriends();
    }

    public Set<User> getMutualFriends(String username1, String username2) throws UserDoesNotExistException {
        User user1 = getUser(username1);
        User user2 = getUser(username2);

        Set<User> mutualFriends = new HashSet<>(user1.getFriends());
        mutualFriends.retainAll(user2.getFriends());
        return mutualFriends;
    }
}



public class Main {
    public static void main(String[] args) {
        SocialNetwork socialNetwork = new SocialNetwork();
        Scanner scanner = new Scanner(System.in);
        String choice;

        while (true) {
            System.out.println("\nSocial Network:");
            System.out.println("1. Register user");
            System.out.println("2. Add friend");
            System.out.println("3. Show friends");
            System.out.println("4. Show mutual friends");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        System.out.print("Enter username to register: ");
                        String username = scanner.nextLine();
                        socialNetwork.registerUser(username);
                        System.out.println("User registered.");
                        break;

                    case "2":
                        System.out.print("Enter your username: ");
                        String yourName = scanner.nextLine();
                        System.out.print("Enter friend's username: ");
                        String friendName = scanner.nextLine();
                        socialNetwork.addFriend(yourName, friendName);
                        System.out.println("Friend added.");
                        break;

                    case "3":
                        System.out.print("Enter username to view friends: ");
                        String userName = scanner.nextLine();
                        Set<User> friends = socialNetwork.getFriends(userName);
                        System.out.println(userName + "'s friends:");
                        for (User friend : friends) {
                            System.out.println(friend.getUsername());
                        }
                        break;

                    case "4":
                        System.out.print("Enter first username: ");
                        String firstUser = scanner.nextLine();
                        System.out.print("Enter second username: ");
                        String secondUser = scanner.nextLine();
                        Set<User> mutualFriends = socialNetwork.getMutualFriends(firstUser, secondUser);
                        System.out.println("Mutual friends of " + firstUser + " and " + secondUser + ":");
                        for (User friend : mutualFriends) {
                            System.out.println(friend.getUsername());
                        }
                        break;

                    case "5":
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice! Try again.");
                }
            } catch (UserAlreadyExistsException | UserDoesNotExistException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
