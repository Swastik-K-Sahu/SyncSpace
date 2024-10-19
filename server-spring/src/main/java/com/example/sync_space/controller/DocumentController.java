import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class DocumentController {
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private DocumentKafkaProducer kafkaProducer;

    @MessageMapping("/edit")
    @SendTo("/topic/updates")
    public CollabDocument updateDocument(DocumentEditMessage message) throws Exception {
        CollaborativeDocument doc = documentRepository.findById(message.getId()).orElse(new CollaborativeDocument());
        doc.setContent(message.getContent());
        documentRepository.save(doc);
        kafkaProducer.sendMessage(doc.getContent());
        return doc;
    }
}
