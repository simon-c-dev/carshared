package com.coderunners.authentication.token;

public interface Token {
    String getTokenData();
    void validate() throws Exception;
}
