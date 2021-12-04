package dto;
import javafx.util.Pair;
import lombok.Data;
import java.util.Date;
import java.util.Map;

@Data
public class UserLinksDTO {
    private String username;
    private Map<String, Pair<String, Date>> siteLinks;
}
