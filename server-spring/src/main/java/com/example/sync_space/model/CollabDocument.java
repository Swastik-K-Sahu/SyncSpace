import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "documents")
public class CollabDocument {
    
    @Id
    private String id;
    private String title;
    private String content;
    private List<String> contributors;

    
}
