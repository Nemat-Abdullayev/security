package az.security.enums;

public enum UserRole {

    ADMIN(0), SUPERVISOR(1), OPERATOR(2);

    UserRole(int value) {
        this.value = value;
    }

    private final int value;

    public int getValue() {
        return value;
    }
}
