package message_client.controller;

import lombok.Getter;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class UserController {
    private static String username = null;
    private final APIController apiController = new APIController();
    public boolean loginUser(String username, String password) {

        String token = apiController.loginUser(username, password);
        // TODO use token
        if (token != null) {
            UserController.username = username;
            return true;
        } else {
            return false;
        }
    }

    public static String getUsername() {
        if (username == null) {
            throw new IllegalStateException("User is not logged in");
        }
        return username;
    }

    public boolean isLoggedIn() {
        return username != null;
    }

    public void registerUser(String username, String password) throws JSONException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", username);
        requestBody.put("password", password);

        // TODO handle response without printing
        apiController.registerUser(username, password);
    }

    public void addFriend(String friendName) {
        apiController.addFriend(username, friendName);
    }

    public List<String> getFriendRequests() {
        return apiController.getPendingFriendRequests(username);
    }

    public void acceptFriend(String friendName) {
        apiController.acceptFriend(username, friendName);
    }

    public List<String> getFriends() {
        return apiController.getFriends(username);
    }
}