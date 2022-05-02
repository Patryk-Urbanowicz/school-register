package pl.school.register.model.enumerations;

public enum WeekDay {
    MONDAY(Values.MONDAY),
    TUESDAY(Values.TUESDAY),
    WEDNESDAY(Values.WEDNESDAY),
    THURSDAY(Values.THURSDAY),
    FRIDAY(Values.FRIDAY),
    SATURDAY(Values.SATURDAY),
    SUNDAY(Values.SUNDAY);

    private final String value;

    WeekDay(String value) {
        this.value = value;
    }

    public static final class Values {
        public static final String MONDAY = "monday";
        public static final String TUESDAY = "tuesday";
        public static final String WEDNESDAY = "wednesday";
        public static final String THURSDAY = "thursday";
        public static final String FRIDAY = "friday";
        public static final String SATURDAY = "saturday";
        public static final String SUNDAY = "sunday";
    }
}
