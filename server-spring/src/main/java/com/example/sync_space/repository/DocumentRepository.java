import org.springframework.data.mongodb.repository.MongoRepository;

public interface DocumentRepository extends MongoRepository<CollaborativeDocument, String> {
}
