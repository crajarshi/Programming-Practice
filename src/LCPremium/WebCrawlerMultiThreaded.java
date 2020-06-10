package LCPremium;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Given a url startUrl and an interface HtmlParser, implement a Multi-threaded
 * web crawler to crawl all links that are under the same hostname as startUrl.
 * <p>
 * Return all urls obtained by your web crawler in any order.
 * <p>
 * Your crawler should:
 * <p>
 * Start from the page: startUrl Call HtmlParser.getUrls(url) to get all urls
 * from a webpage of given url. Do not crawl the same link twice. Explore only
 * the links that are under the same hostname as startUrl.
 * <p>
 * <p>
 * As shown in the example url above, the hostname is example.org. For
 * simplicity sake, you may assume all urls use http protocol without any port
 * specified. For example, the urls http://leetcode.com/problems and
 * http://leetcode.com/contest are under the same hostname, while urls
 * http://example.org/test and http://example.com/abc are not under the same
 * hostname.
 * <p>
 * The HtmlParser interface is defined as such:
 * <p>
 * interface HtmlParser { // Return a list of all urls from a webpage of given
 * url. // This is a blocking call, that means it will do HTTP request and
 * return when this request is finished. public List<String> getUrls(String
 * url); } Note that getUrls(String url) simulates performing a HTTP request.
 * You can treat it as a blocking function call which waits for a HTTP request
 * to finish. It is guaranteed that getUrls(String url) will return the urls
 * within 15ms.  Single-threaded solutions will exceed the time limit so, can
 * your multi-threaded web crawler do better?
 * <p>
 * Below are two examples explaining the functionality of the problem, for
 * custom testing purposes you'll have three variables urls, edges and startUrl.
 * Notice that you will only have access to startUrl in your code, while urls
 * and edges are not directly accessible to you in code.
 * <p>
 * <p>
 * <p>
 * Follow up:
 * <p>
 * Assume we have 10,000 nodes and 1 billion URLs to crawl. We will deploy the
 * same software onto each node. The software can know about all the nodes. We
 * have to minimize communication between machines and make sure each node does
 * equal amount of work. How would your web crawler design change? What if one
 * node fails or does not work? How do you know when the crawler is done?
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input: urls = [ "http://news.yahoo.com", "http://news.yahoo.com/news",
 * "http://news.yahoo.com/news/topics/", "http://news.google.com",
 * "http://news.yahoo.com/us" ] edges = [[2,0],[2,1],[3,2],[3,1],[0,4]] startUrl
 * = "http://news.yahoo.com/news/topics/" Output: [ "http://news.yahoo.com",
 * "http://news.yahoo.com/news", "http://news.yahoo.com/news/topics/",
 * "http://news.yahoo.com/us" ] Example 2:
 * <p>
 * <p>
 * <p>
 * Input: urls = [ "http://news.yahoo.com", "http://news.yahoo.com/news",
 * "http://news.yahoo.com/news/topics/", "http://news.google.com" ] edges =
 * [[0,2],[2,1],[3,2],[3,1],[3,0]] startUrl = "http://news.google.com" Output:
 * ["http://news.google.com"] Explanation: The startUrl links to all other pages
 * that do not share the same hostname.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= urls.length <= 1000 1 <= urls[i].length <= 300 startUrl is one of the
 * urls. Hostname label must be from 1 to 63 characters long, including the
 * dots, may contain only the ASCII letters from 'a' to 'z', digits from '0' to
 * '9' and the hyphen-minus character ('-'). The hostname may not start or end
 * with the hyphen-minus character ('-'). See:  https://en.wikipedia.org/wiki/Hostname#Restrictions_on_valid_hostnames
 * You may assume there're no duplicates in url library.
 */
public class WebCrawlerMultiThreaded {

    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        String hostname = getHostname(startUrl);

        // We don't want to re-crawl pages, so we're going to use a Set that can be modified concurrently
        // The following link describes how it is implemented, if you're interested
        // https://github.com/frohoff/jdk8u-jdk/blob/master/src/share/classes/java/util/concurrent/ConcurrentHashMap.java#L271
        Set<String> visited = ConcurrentHashMap.newKeySet();
        visited.add(startUrl);

        // This is similar to map-reduce, but instead of reducing to a single value we collect the values
        return crawl(startUrl, htmlParser, hostname, visited)
                .collect(Collectors.toList());
    }

    private Stream<String> crawl(String startUrl, HtmlParser htmlParser, String hostname, Set<String> visited) {
        // htmlParser#getUrls returns a List<String>
        Stream<String> stream = htmlParser.getUrls(startUrl)
                // We process each url in parallel. The number of threads (parallelism) is decided by the JDK.
                .parallelStream()
                // We filter out external urls, meaning we don't continue processing them and they're not a part of the result
                // This method (isSameHostname) is thread-safe
                .filter(url -> isSameHostname(url, hostname))
                // visited is the concurrent set. The add method returns false if the url is already in the set. In that case we ignore the url
                // A Set is normally not thread-safe but the one we're using is.
                .filter(url -> visited.add(url))
                // If the url passed both filters above, we call crawl on it (recursivelly). A url generates a Stream<String>
                // If we were to use .map we would get something like a List<Stream<String>>
                // but we need to return a single Stream so we can chain the calls. flatMap concats themultiple streams into a single one
                .flatMap(url -> crawl(url, htmlParser, hostname, visited));

        // We want to return the original crawled url as well as the url's we found by crawling it
        // Since startUrl is not part of the stream, we need to add it
        //
        // Think about what happens to the original url in the flatMap phase: it gets replaced by the Stream<String> of the crawl method
        // To keep the startUrl we concat it to the stream.
        return Stream.concat(Stream.of(startUrl), stream);
    }

    /**
     * Returns the url without the path. (method name wasn't the best)
     */
    private String getHostname(String url) {
        // Start the search after the protocol (http:// is always in the url)
        int idx = url.indexOf('/', 7);
        // Return the url without the path
        return (idx != -1) ? url.substring(0, idx) : url;
    }

    private boolean isSameHostname(String url, String hostname) {
        if (!url.startsWith(hostname)) {
            return false;
        }
        return url.length() == hostname.length() || url.charAt(hostname.length()) == '/';
    }

    abstract class HtmlParser {
        public abstract List<String> getUrls(String url);
    }

}
