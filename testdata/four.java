import javafx.util.Pair;

import java.util.*;

public class four {
    /**
     *  main comment.
     * @param args
     */
    //main comment
    public static void main(String[] args) {
        Pair<Long, Long> []p =new Pair[10000];
        long ans[] = new long[10000];
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int num =0,cnt=0;
        for (int i=0;i<n;i++){
            long a = in.nextLong();
            long b = in.nextLong();
            p[++num] = new Pair<>(a, 1L);
            p[++num] = new Pair<>(b+1, -1L);
        }
        Arrays.sort(p, new Comparator<Pair<Long, Long>>() {
            /**
            compare coment
             */
            public int compare(Pair<Long, Long> o1, Pair<Long, Long> o2) {
                return Long.compare(o1.getKey(),o2.getKey());
            }
        });

        for (int i=1;i<=num;i++){
            ans[cnt]+=p[i].getKey()-p[i-1].getKey();
            cnt+=p[i].getValue();
        }
        int count=0;
        for (int i=1;i<=n;i++){
            count+=ans[i];
        }
        System.out.println(count);

    }
}
