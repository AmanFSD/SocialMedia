Implement a simple Social Networking application that keeps track of users and their friends.

Requirements

User: Create a User class. Each User object should have a username and a Set<User> friends that contains all the user's friends.

SocialNetwork: Create a SocialNetwork class. This class should contain a Set<User> users that stores all the users of the network. The following functionalities should be supported:

registerUser(String username): Adds a new user to the network. A username must be unique. If the username is already taken, throw a UserAlreadyExistsException (you have to create this exception class).
addFriend(String username1, String username2): Makes the two users with the given usernames friends. If either username does not exist, throw a UserDoesNotExistException (you also have to create this exception class).
removeFriend(String username1, String username2): Removes the friendship between the two users with the given usernames. If either username does not exist, throw a UserDoesNotExistException.
getFriends(String username): Returns a Set<User> containing all the friends of the user with the given username. If the username does not exist, throw a UserDoesNotExistException.
getMutualFriends(String username1, String username2): Returns a Set<User> containing all the mutual friends of the two users with the given usernames. If either username does not exist, throw a UserDoesNotExistException.