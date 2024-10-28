package board;

public class Main {
    public static void main(String[] args) {
        ResourceManager resourceManager = ResourceManager.getInstance();

        // HTML 출력
        String htmlContent = resourceManager.getHtml();
        System.out.println("HTML Content:");
        System.out.println(htmlContent);

        // CSS 출력
        String cssContent = resourceManager.getCss();
        System.out.println("CSS Content:");
        System.out.println(cssContent);

        // JSP 출력
        String jspContent = resourceManager.getJsp();
        System.out.println("JSP Content:");
        System.out.println(jspContent);
    }
}
