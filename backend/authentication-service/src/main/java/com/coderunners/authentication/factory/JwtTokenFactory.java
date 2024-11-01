package com.coderunners.authentication.factory;

import com.coderunners.authentication.entity.User;
import org.springframework.stereotype.Component;
import com.coderunners.authentication.token.JwtToken;
import com.coderunners.authentication.token.Token;

@Component
public class JwtTokenFactory implements TokenFactory {
    @Override
    public String generateToken(User credentials) {
        Token token = new JwtToken(credentials.getUsername());
        return token.getTokenData();
    }

    @Override
    public void validateToken(String token) throws Exception {
        JwtToken jwtToken = new JwtToken(token);
        jwtToken.validate();
    }
}
