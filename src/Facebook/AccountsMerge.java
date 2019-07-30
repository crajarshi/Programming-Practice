package Facebook;

import java.util.*;

/**
 * Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name,
 * and the rest of the elements are emails representing emails of the account.
 * <p>
 * Now, we would like to merge these accounts. Two accounts definitely belong to the same person
 * if there is some email that is common to both accounts. Note that even if two accounts have the same name,
 * they may belong to different people as people could have the same name. A person can have any number of accounts initially,
 * but all of their accounts definitely have the same name.
 * <p>
 * After merging the accounts, return the accounts in the following format:
 * the first element of each account is the name, and the rest of the elements are emails in sorted order.
 * The accounts themselves can be returned in any order.
 * <p>
 * Example 1:
 * Input:
 * accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"],
 * ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
 * Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],
 * ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
 * Explanation:
 * The first and third John's are the same person as they have the common email "johnsmith@mail.com".
 * The second John and Mary are different people as none of their email addresses are used by other accounts.
 * We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
 * ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
 */
public class AccountsMerge {
    /**
     * Step1
     * ======
     * a b c // now b, c have parent a
     * d e f // now e, f have parent d
     * g a d // now abc, def all merged to group g
     * Step2
     * ======
     * parents populated after parsing 1st account: a b c
     * a->a
     * b->a
     * c->a
     * Step3
     * ======
     * parents populated after parsing 2nd account: d e f
     * d->d
     * e->d
     * f->d
     * Step4
     * ======
     * parents populated after parsing 3rd account: g a d
     * g->g
     * a->g
     * d->g
     */

    public static List<List<String>> accountsMergeUnionfind(List<List<String>>
                                                                    acts) {
        Map<String, String> owner = new HashMap<>();
        Map<String, String> parents = new HashMap<>();
        Map<String, TreeSet<String>> unions = new HashMap<>();
        for (List<String> a : acts) {
            for (int i = 1; i < a.size(); i++) {
                parents.put(a.get(i), a.get(i));// email -> email de duping
                // emails
                owner.put(a.get(i), a.get(0));// email -> name de duping the
                // owner
            }
        }
        for (List<String> a : acts) {
            String p = find(a.get(1), parents);
            for (int i = 2; i < a.size(); i++)
                parents.put(find(a.get(i), parents), p);//assign each email
            // to the first email
        }
        for (List<String> a : acts) {
            String p = find(a.get(1), parents);
            if (!unions.containsKey(p)) unions.put(p, new TreeSet<>());
            for (int i = 1; i < a.size(); i++)
                unions.get(p).add(a.get(i));
        }// This merged/grouped all the emails and created a set.
        List<List<String>> res = new ArrayList<>();
        for (String p : unions.keySet()) {
            List<String> emails = new ArrayList(unions.get(p));
            emails.add(0, owner.get(p));
            res.add(emails);
        }
        return res;
    }

    private static String find(String s, Map<String, String> p) {
        return p.get(s) == s ? s : find(p.get(s), p);
    }

    public static void main(String[] args) {
        List<String> lstAccount = Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com");
        List<String> lstAccount1 = Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com");
        List<String> lstAccount2 = Arrays.asList("Mary", "mary@mail.com");
        List<String> lstAccount3 = Arrays.asList("John", "johnnybravo@mail.com");

        List<List<String>> accounts = new ArrayList<>();
        accounts.add(lstAccount);
        accounts.add(lstAccount1);
        accounts.add(lstAccount2);
        accounts.add(lstAccount3);

        System.out.println(accountsMergeUnionfind(accounts));

    }

    /**
     * Basicly, this is a graph problem. Notice that each account[ i ] tells us some edges. What we have to do is as follows:
     * <p>
     * Use these edges to build some components.
     * Common email addresses are like the intersections that connect each single component for each account.
     * Because each component represents a merged account,
     * do DFS search for each components and add into a list.
     * Before add the name into this list, sort the emails. Then add name string into it.
     * Examples: Assume we have three accounts, we connect them like this in order to use DFS.
     * {Name, 1, 2, 3} => Name -- 1 -- 2 -- 3
     * {Name, 2, 4, 5} => Name -- 2 -- 4 -- 5 (The same graph node 2 appears)
     * {Name, 6, 7, 8} => Name -- 6 -- 7 -- 8
     * (Where numbers represent email addresses).
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Set<String>> graph = new HashMap<>();  //<email node, neighbor nodes>
        Map<String, String> name = new HashMap<>();        //<email, username>
        // Build the graph;
        for (List<String> account : accounts) {
            String userName = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                if (!graph.containsKey(account.get(i))) {
                    graph.put(account.get(i), new HashSet<>());
                }
                name.put(account.get(i), userName);

                if (i == 1) continue;
                graph.get(account.get(i)).add(account.get(i - 1));
                graph.get(account.get(i - 1)).add(account.get(i));
            }
        }

        Set<String> visited = new HashSet<>();
        List<List<String>> res = new LinkedList<>();
        // DFS search the graph;
        for (String email : name.keySet()) {
            List<String> list = new LinkedList<>();
            if (visited.add(email)) {
                dfs(graph, email, visited, list);
                Collections.sort(list);
                list.add(0, name.get(email));
                res.add(list);
            }
        }

        return res;
    }

    public void dfs(Map<String, Set<String>> graph, String email, Set<String> visited, List<String> list) {
        list.add(email);
        for (String next : graph.get(email)) {
            if (visited.add(next)) {
                dfs(graph, next, visited, list);
            }
        }
    }
}
