import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SearchBook
{
    private final static List<String> books = Arrays.asList("les miserables 1", "les miserables 2", "toto");

    public static List<String> execute(String title)
    {
        return books
                .stream()
                .filter(t -> t.contains(title))
                .collect(Collectors.toList());
    }
}
