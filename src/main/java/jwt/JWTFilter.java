package jwt;

import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class JWTFilter implements ContainerRequestFilter {
    private JWTUtils jwtUtils = new JWTUtils();

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        JWTUtils tmp = new JWTUtils();
        String test = tmp.generateJwtToken();
        String authorizationHeader = containerRequestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }

        String token = authorizationHeader.substring("Bearer ".length()).trim();

        try {
            if(token != null && jwtUtils.validateJwtToken(token)){
                String userName = jwtUtils.getUserNameFromJwtToken(token);

            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
