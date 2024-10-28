package board;

public class ResourceManager {
    private static ResourceManager instance;

    // Private constructor to prevent instantiation
    private ResourceManager() {}

    // Static method to get the single instance of ResourceManager
    public static ResourceManager getInstance() {
        if (instance == null) {
            instance = new ResourceManager();
        }
        return instance;
    }

    // Method to get HTML content
    public String getHtml() {
        return "<!DOCTYPE html>\n" +
               "<html lang='ko'>\n" +
               "<head>\n" +
               "    <meta charset='UTF-8'>\n" +
               "    <title>게시판</title>\n" +
               "    <link rel='stylesheet' href='styles.css'>\n" +
               "</head>\n" +
               "<body>\n" +
               "    <header>\n" +
               "        <h1>게시판</h1>\n" +
               "    </header>\n" +
               "    <div id='page'>\n" +
               "        <!-- Content goes here -->\n" +
               "    </div>\n" +
               "</body>\n" +
               "</html>";
    }

    // Method to get CSS content
    public String getCss() {
        return "body { font-family: 'Arial', sans-serif; background-color: #e9ecef; }\n" +
               "header { background-color: #343a40; color: white; padding: 20px; }\n" +
               "#page { max-width: 900px; margin: 20px auto; padding: 20px; background: white; }\n";
    }

    // Method to get JSP content
    public String getJsp() {
        return "<%@ page contentType='text/html;charset=UTF-8' language='java' %>\n" +
               "<html>\n" +
               "<head>\n" +
               "    <title>게시판</title>\n" +
               "    <link rel='stylesheet' href='styles.css'>\n" +
               "</head>\n" +
               "<body>\n" +
               "    <h1>게시판</h1>\n" +
               "    <div>\n" +
               "        <p>게시물 목록</p>\n" +
               "        <%-- 게시물 출력 코드 --%>\n" +
               "    </div>\n" +
               "</body>\n" +
               "</html>";
    }
}

