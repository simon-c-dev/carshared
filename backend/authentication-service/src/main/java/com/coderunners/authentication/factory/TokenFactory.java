package com.coderunners.authentication.factory;
import com.coderunners.authentication.entity.User;

public interface TokenFactory {
    String generateToken(User user);
    void validateToken(String token) throws Exception;
}
