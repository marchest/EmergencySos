package API;

import baseClasses.UserModel;

/**
 * Created by marchest on 30.11.2016.
 */
public class ServerEvent {
    private UserModel serverResponse;

    public ServerEvent(UserModel serverResponse) {
        this.serverResponse = serverResponse;
    }

    public UserModel getServerResponse() {
        return serverResponse;
    }

    public void setServerResponse(UserModel serverResponse) {
        this.serverResponse = serverResponse;
    }
}
