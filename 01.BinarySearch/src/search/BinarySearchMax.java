package search;

public class BinarySearchMax {
    public static void main(String[] args) {
        // Pred: true
        int n = args.length;
        // Post: n >= 0
        if(n == 0) {
            System.out.println("Пример запуска: java BinarySearchMax 3 5 4 3 2 1. Ожидаемый результат: 5");
            return;
        }
        // Pred: n > 0
        int[] a = new int[n];
        // Post: n > 0 && forall i: a[i] = null;
        for(int i = 0; i < n; ++i) {
            assert args[i] != null;
            a[i] = Integer.parseInt(args[i]);
        }
        // Post: n > 0 && forall i: a[i] != null
        int maxInd = 0;
        for(int i = 0; i < n; ++i) {
            if (a[i] > a[maxInd]) {
                maxInd = i;
            }
        }
        // Post: n > 0 && forall i: a[i] != null && max(a) == a[maxInd]
        for(int i = 0; i < n - 1; ++i) {
            if (i < maxInd) {
                assert a[i] < a[i + 1];
            } else if (i == maxInd) {
                assert a[i] >= a[i + 1];
            } else {
                assert a[i] > a[i + 1];
            }
        }
        // n > 0 && Exists X that forall (0 <= i < X) a[i] < a[i + 1] && forall (X < i < n) a[i - 1] >= a[i]
        System.out.println(a[iterativeBinarySearchMax(a, n)]);

        ///System.out.println(a[recursiveBinarySearchMax(a, 0, n - 1)]);
    }

    /// Immutable: Exists X: (0 <= i < X) a[i] < a[i + 1] && (X < i < a.length) a[i - 1] >= a[i]
    /// Pred: Immutable
    /// Post: R = X
    public static int iterativeBinarySearchMax(int[] a, int n) {
        /// Pred: Immutable && a.length == n && (0 <= i < n) a[i] != null
        int l = 0;
        int r = n - 1;
        /// Inv: Immutable && a[l - 1] <= a[l] && a[r] > a[r + 1] && l <= X <= r
        while (l < r) {
            // Pred: Immutable && a[l - 1] <= a[l] && a[r] >= a[r + 1] && l != r && l <= X <= r
            int m1 = (l + r) / 2;
            int m2 = m1 + 1;
            // Post: a[l - 1] <= a[l] && a[r] >= a[r + 1] && l <= m1 < m2 < r

            // Pred: Immutable && a[l - 1] <= a[l] && a[r] >= a[r + 1] && l <= X <= r && l <= m1 < m2 < r
            if (m1 + 1 < a.length && a[m1] < a[m2]) {
                // Pred: Immutable && a[l - 1] <= a[l] && a[r] >= a[r + 1] && l <= m1 < m2 <= X <= r || l <= m1 < X <= m2 <= r
                l = m2;
                // Post: a[l - 1] <= a[l] && a[r] >= a[r + 1] && l <= X <= r
            } else {
                // Pred: Immutable && a[l - 1] <= a[l] && a[r] >= a[r + 1] && l <= X <= m1 < m2 < r || l <= m1 <= X < r
                r = m1;
                // Post: Immutable && a[l - 1] <= a[l] && a[r] < a[r + 1] && l <= X <= r
            }
            // Post: Immutable && a[l - 1] <= a[l] && a[r] >= a[r + 1] && l <= X <= r
        }
        // Post: Immutable && a[l - 1] <= a[l] && a[r] >= a[r + 1] && Post: l <= X <= r && l == r
        return r;
    }

    /// Inv: Immutable && -1 < l <= X <= r < a.length
    /// Post: R = X
    public static int recursiveBinarySearchMax(int[] a, int l, int r) {
        // Pred: Immutable && a[l - 1] < a[l] && a[r] < a[r + 1] && l <= X <= r
        if (l == r) {
            // Pred: Immutable && a[l - 1] < a[l] && a[r] > a[r + 1] && l <= X <= r && l == r
            return r;
        }

        // Pred: Immutable && l <= X <= r && l != r
        int m1 = (l + r) / 2;
        int m2 = m1 + 1;
        // Post: Immutable && l <= m1 < m2 < r && l <= X <= r

        if (m1 + 1 < a.length && a[m1] < a[m2]) {
            // Pred: Immutable && (l <= m1 < m2 <= X <= r || l <= m1 < X <= m2 <= r)
            return recursiveBinarySearchMax(a, m1 + 1, r);
        } else {
            // Pred: Immutable && (l <= X <= m1 < m2 < r || l <= m1 <= X < r)
            return recursiveBinarySearchMax(a, l, m1);
        }
    }
}
