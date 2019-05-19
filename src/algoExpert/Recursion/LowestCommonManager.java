package algoExpert.Recursion;

import java.util.ArrayList;

/**
 * You're given three inputs, all of which are instances of a class that
 * have a "directReports" property pointing to their direct reports.
 * The first input is the top manager in an organizational chart
 * (i.e., the only instance that is not anybody else's direct report),
 * and the other two inputs are reports in the organizational chart.
 * Write a function that returns the lowest common manager to the two reports.

 Sample input: Node A, Node E, Node I (from the ancestral tree below)
 A
 /     \
 B        C
 /    \    /    \
 D     E F      G
 /   \
 H      I
 Sample output: Node B

 */
public class LowestCommonManager {
    // O(n) time | O(d) space - where n is the number of people
    // in the org and d is the depth (height) of the org chart
    public static OrgChart getLowestCommonManager(OrgChart topManager, OrgChart reportOne, OrgChart reportTwo) {
        return getOrgInfo(topManager, reportOne, reportTwo).lowestCommonManager;
    }

    public static OrgInfo getOrgInfo(OrgChart manager, OrgChart reportOne, OrgChart reportTwo) {
        int numImportantReports = 0;
        for (OrgChart directReport : manager.directReports) {
            OrgInfo orgInfo = getOrgInfo(directReport, reportOne, reportTwo);
            if (orgInfo.lowestCommonManager != null) return orgInfo;
            numImportantReports += orgInfo.numImportantReports;
        }
        if (manager == reportOne || manager == reportTwo) numImportantReports++;
        OrgChart lowestCommonManager = numImportantReports == 2 ? manager : null;
        OrgInfo newOrgInfo = new OrgInfo(lowestCommonManager, numImportantReports);
        return newOrgInfo;
    }

    static class OrgChart {
        public char name;
        public ArrayList<OrgChart> directReports;

        OrgChart(char name) {
            this.name = name;
            this.directReports = new ArrayList<OrgChart>();
        }

        // This method is for testing only.
        public void addDirectReports(OrgChart[] directReports) {
            for (OrgChart directReport : directReports) {
                this.directReports.add(directReport);
            }
        }
    }

    static class OrgInfo {
        public OrgChart lowestCommonManager;
        int numImportantReports;

        OrgInfo(OrgChart lowestCommonManager, int numImportantReports) {
            this.lowestCommonManager = lowestCommonManager;
            this.numImportantReports = numImportantReports;
        }
    }
}
