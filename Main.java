package Project1;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


class Main {
    public class UrlDatabase {
        private Map<String, String> urlMap;

        public UrlDatabase() {
            urlMap = new HashMap<>();
        }

        public void addUrlMapping(String shortUrl, String longUrl) {
            urlMap.put(shortUrl, longUrl);
        }

        public String getLongUrl(String shortUrl) {
            return urlMap.get(shortUrl);
        }

        public boolean containsShortUrl(String shortUrl) {
            return urlMap.containsKey(shortUrl);
        }
    }
    static class UrlShortener {

        private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        private static final int SHORT_URL_LENGTH = 6;
        private Map<String, String> urlMap;
        private Map<String, String> reverseUrlMap;
        private Random random;

        public UrlShortener() {
            urlMap = new HashMap<>();
            reverseUrlMap = new HashMap<>();
            random = new Random();
        }

        public String shortenUrl(String longUrl) {
            if (reverseUrlMap.containsKey(longUrl)) {
                return reverseUrlMap.get(longUrl);
            }

            String shortUrl;
            do {
                shortUrl = generateShortUrl();
            } while (urlMap.containsKey(shortUrl));

            urlMap.put(shortUrl, longUrl);
            reverseUrlMap.put(longUrl, shortUrl);

            return shortUrl;
        }

        public String expandUrl(String shortUrl) {
            return urlMap.getOrDefault(shortUrl, "Invalid short URL");
        }

        private String generateShortUrl() {
            StringBuilder shortUrl = new StringBuilder(SHORT_URL_LENGTH);
            for (int i = 0; i < SHORT_URL_LENGTH; i++) {
                shortUrl.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
            }
            return shortUrl.toString();
        }
    }

    public static void main(String[] args) {
        UrlShortener urlShortener = new UrlShortener();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Shorten URL");
            System.out.println("2. Expand URL");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter the long URL: ");
                    String longUrl = scanner.nextLine();
                    String shortUrl = urlShortener.shortenUrl(longUrl);
                    System.out.println("Shortened URL: " + shortUrl);
                    break;

                case 2:
                    System.out.print("Enter the short URL: ");
                    String shortUrlToExpand = scanner.nextLine();
                    String expandedUrl = urlShortener.expandUrl(shortUrlToExpand);
                    System.out.println("Original URL: " + expandedUrl);
                    break;

                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}





