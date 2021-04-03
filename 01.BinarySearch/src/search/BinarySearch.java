package search;

public class BinarySearch {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Пример запуска: java BinarySearch 3 5 4 3 2 1. Ожидаемый результат: 2");
            return;
        }
        int n = args.length - 1;
        int[] a = new int[n];
        int x = Integer.parseInt(args[0]);
        for(int i = 0; i < n; ++i) {
            a[i] = Integer.parseInt(args[i + 1]);
        }
        System.out.println(recursiveBinarySearch(a, x, -1, n));

        ///System.out.println(iterativeBinarySearch(a, x, n));
    }
    // 3 - 5 4 3 2 1

    // Pred: -1 <= l < r <= a.length && (l <= i < r) a[i] >= a[i + 1]
    // Post: R such that a[R] <= x < a[R - 1]
    public static int recursiveBinarySearch(int[] a, int x, int l, int r) {
        // Pred: -1 <= l < r <= a.length
        int m = (l + r) / 2;
        if (l == m || r == m) {
            // l + 1 == r && a[r] <= x < a[l]
            return r;
        }
        // Post: l < m < r && a[r] <= x < a[l]
        if (a[m] > x) {
            // Pred: l < m < r && a[r] <= x < a[m] < a[l]
            return recursiveBinarySearch(a, x, m, r);
        }
        else {
            // Pred: l < m < r && a[r] <= a[m] <= x < a[l]
            return recursiveBinarySearch(a, x, l, m);
        }
    }

    // Pred: n >= 0 && (-1 <= i < n) a[i] >= a[i + 1]
    // Post: R such that a[R - 1] > x >= a[R]
    public static int iterativeBinarySearch(int[] a, int x, int n) {
        // Pred: n >= 0
        int l = -1;
        int r = n;
        // Post: r >= 0 && l = -1 && a[r] <= x < a[l]

        //Inv: a[l] > x >= a[r]
        while (r - l > 1) {
            // a[l] > x >= a[r] && l + 1 < r
            int m = (l + r) / 2;
            // a[l] > x >= a[r] && l < m < r
            if (a[m] > x) {
                // a[l] > x >= a[r] && l < m < r && a[m] > x
                l = m;
                // l > l' > r && a[l'] > x >= a[r]
            }
            else {
                // a[l] > x >= a[r] && l < m < r && a[m] <= x
                r = m;
                // l > r' > r && a[l] > x >= a[r']
            }
            // a[l] > x >= a[r]
        }
        //Inv: a[l] > x >= a[r] && l + 1 == r
        return r;
    }
}