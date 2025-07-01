public class Goal {
    private final String title;
    private final String description;
    private final long deadlineTimestamp;

    private Goal(Builder builder) {
        this.title = builder.title;
        this.description = builder.description;
        this.deadlineTimestamp = builder.deadlineTimestamp;
    }

    public static class Builder {
        private String title;
        private String description;
        private long deadlineTimestamp;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setDeadlineTimestamp(long deadlineTimestamp) {
            this.deadlineTimestamp = deadlineTimestamp;
            return this;
        }

        public Goal build() {
            return new Goal(this);
        }
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public long getDeadlineTimestamp() { return deadlineTimestamp; }
}