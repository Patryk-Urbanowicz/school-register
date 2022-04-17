package pl.school.register.model;

public enum Role {
    TEACHER(Values.TEACHER),
    STUDENT(Values.STUDENT),
    PARENT(Values.PARENT);

    private String value;

    private Role(String value) {
        this.value = value;
    }

    public static class Values {
        public static final String TEACHER = "TEACHER";
        public static final String STUDENT = "STUDENT";
        public static final String PARENT = "PARENT";
    }
}
