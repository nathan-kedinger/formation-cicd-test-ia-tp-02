package com.devops.cicd.user;

public final class EmailValidator {

    private EmailValidator() {}

    public static boolean isValid(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }

        int atIndex = email.indexOf('@');
        // Exactement un seul '@'
        if (atIndex == -1 || atIndex != email.lastIndexOf('@')) {
            return false;
        }

        // Doit avoir un caractère avant le '@' et après le '@'
        if (atIndex == 0 || atIndex == email.length() - 1) {
            return false;
        }

        // Doit contenir un '.' après le '@'
        String domain = email.substring(atIndex + 1);
        return domain.contains(".");
    }
}
