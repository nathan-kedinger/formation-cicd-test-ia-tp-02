package com.devops.cicd.user;

public class User {

    private final String email;
    private final String password;
    private final Role role;

    public User(String email, String password, Role role) {
        if (!EmailValidator.isValid(email)) {
            throw new IllegalArgumentException("email must be valid");
        }
        if (password == null || !com.devops.cicd.PasswordPolicy.isStrong(password)) {
            throw new IllegalArgumentException("password must be strong");
        }
        if (role == null) {
            throw new IllegalArgumentException("role must not be null");
        }

        this.email = email.trim();
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public boolean canAccessAdminArea() {
        return role == Role.ADMIN;
    }

    // BONUS: vous pouvez ajouter equals/hashCode/toString si utile (non obligatoire)
}
