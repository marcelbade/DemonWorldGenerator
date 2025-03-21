package marcel.demonworld.armygenerator.security;


import marcel.demonworld.armygenerator.Exceptions.AppException;
import marcel.demonworld.armygenerator.dto.auth.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;

public class MethodLevelSecurityConfig {

    /**
     * Method tests whether the owner of the requested data is identical with the currently authenticated user.
     *
     * @param resourceOwner String: username sent as request parameter to access the data.
     * @return true, if the owner of the requested data is currently logged in. Otherwise, an Exception is thrown.
     */
    public static void authenticateUser(String resourceOwner) {

        UserDTO loggedInUser = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!resourceOwner.equals(loggedInUser.getUserName())) {
            throw new AppException("you may only access your own data", HttpStatus.BAD_REQUEST);
        }
    }
}
