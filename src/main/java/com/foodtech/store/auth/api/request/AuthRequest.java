package com.foodtech.store.auth.api.request;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
