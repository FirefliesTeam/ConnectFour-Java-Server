package base;

import database.dataSets.UserDataSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.servlet.http.HttpSession;

public interface AccountService {

    boolean singUp(@NotNull UserDataSet userDataSet);

    long singIn(@NotNull HttpSession session, @NotNull String login, @NotNull String password);

    boolean logOut(@NotNull HttpSession session);

    boolean checkAuth(@NotNull HttpSession session);

    boolean isAvailableName(@NotNull String login);

    boolean validationName(@NotNull String name);

    boolean validationEmail(@NotNull String email);

    boolean validationPassword(@NotNull String password);

    int getRegisteredUsersCount();

    int getLoggedUsersCount();

    void addUser(@NotNull UserDataSet userDataSet);

    @Nullable
    UserDataSet getUser(@NotNull String userName);

    String getNameBySession(@NotNull HttpSession session);

}
