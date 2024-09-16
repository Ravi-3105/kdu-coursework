
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class APIResponseParser {
    /**
     * Parses the input text and returns a Book instance containing
     * the parsed data.
     * @param response text to be parsed
     * @return Book instance containing parsed data
     */
    private static Logger log = LoggerFactory.getLogger(APIResponseParser.class);
    public static Book parse(String response) {
        Book book = new Book();
        String endRule = "<";
        String[] startRule = {"<title>","<name>","<original_publication_year type=\"integer\">","<average_rating>","<ratings_count type=\"integer\">","<image_url>"};
        return parse(response, startRule, endRule);
    }

    private static Book parse(String response, String[] startRule, String endRule){
        String[] details = new String[startRule.length];
        for(int i=0; i < startRule.length; i++) {
            int index = response.indexOf(startRule[i]) + startRule[i].length();
            StringBuilder builder = new StringBuilder("");
            while (!("" + response.charAt(index)).equals(endRule)) {
                builder.append(response.charAt(index++));
            }
            details[i]=builder.toString();
        }
        Book book = new Book();
        book.setTitle(details[0]);
        book.setAuthor(details[1]);
        book.setPublicationYear(Integer.parseInt(details[2]));
        book.setAverageRating(Double.parseDouble(details[3]));
        book.setRatingsCount(Integer.parseInt(details[4].replaceAll(",","")));
        return book;
    }

    public static void main(String[] args) {
        String response = "<work>" +
                "<id type=\"integer\">2361393</id>" +
                "<books_count type=\"integer\">813</books_count>" +
                "<ratings_count type=\"integer\">1,16,315</ratings_count>" +
                "<text_reviews_count type=\"integer\">3439</text_reviews_count>" +
                "<original_publication_year type=\"integer\">1854</original_publication_year>" +
                "<original_publication_month type=\"integer\" nil=\"true\"/>" +"" +
                "<original_publication_day type=\"integer\" nil=\"true\"/>" +
                "<average_rating>3.79</average_rating>" +
                "<best_book type=\"Book\">" +
                "<id type=\"integer\">16902</id>" +
                "<title>Walden</title>" +
                "<author>" +
                "<id type=\"integer\">10264</id>" +
                "<name>Henry David Thoreau</name>" +"</author>" +
                "<image_url>" +
                "http://images.gr-assets.com/books/1465675526m/16902.jpg" +
                "</image_url>" +
                "<small_image_url>" +
                "http://images.gr-assets.com/books/1465675526s/16902.jpg" +
                "</small_image_url>" +
                "</best_book>" +
                "</work>";
        Book book = APIResponseParser.parse(response);
        log.info("Book titile: "+book.getTitle());
        log.info("Book author: "+book.getAuthor());
        log.info("Book publication year: "+book.getPublicationYear());
        log.info("Book average rating: "+book.getAverageRating());
        log.info("Book ratings count: "+book.getRatingsCount());
    }
}
