package pl.school.register.model.enumerations;

public enum Role {
    ROLE_TEACHER(Values.ROLE_TEACHER),
    ROLE_STUDENT(Values.ROLE_STUDENT),
    ROLE_PARENT(Values.ROLE_PARENT);

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public static class Values {
        public static final String ROLE_TEACHER = "ROLE_TEACHER";
        public static final String ROLE_STUDENT = "ROLE_STUDENT";
        public static final String ROLE_PARENT = "ROLE_PARENT";
    }
}
