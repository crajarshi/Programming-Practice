package leetcode;

/**
 * Let us look at the first scenario above where isBadVersion(mid) = false
 * We know that all versions preceding and including mid  are all good.
 * So we set left = mid + 1left=mid+1 to indicate that the new search space is the
 * interval [mid + 1, right][mid+1,right] (inclusive).
 * The only scenario left is where isBadVersion(mid)⇒true. This tells us that mid may or may not be the first bad version,
 * but we can tell for sure that all versions after midmid can be discarded.
 * Therefore we set right = midright=mid as the new search space of interval [left,mid][left,mid] (inclusive).
 */
public class FirstBadVersion {
    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * Just to make the code compile: False implementation
     *
     * @param version
     * @return
     */
    public boolean isBadVersion(int version) {
        return true;
    }
}
