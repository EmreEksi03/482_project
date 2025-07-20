import java.time.LocalDate;

// Storing basic goal information that will be analyzed
public class ProgressGoal {
    private String id;
    private String title;
    private String description;
    private LocalDate createdDate;
    
    public ProgressGoal(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdDate = LocalDate.now();
    }
    
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public LocalDate getCreatedDate() { return createdDate; }
}