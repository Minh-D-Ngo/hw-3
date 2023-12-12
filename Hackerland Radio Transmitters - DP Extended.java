import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class Solution {

    public static int hackerlandRadioTransmitters(List<Integer> x, int k, List<Integer> costs) {
        int n = x.size();
        int[] dp = new int[n];
        Collections.sort(x);

        for (int i = 0; i < n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j <= i; j++) {
                if (x.get(i) - x.get(j) <= k) {
                    int cost = (j > 0) ? dp[j - 1] : 0;
                    dp[i] = Math.min(dp[i], cost + costs.get(i));
                }
            }
        }

        return dp[n - 1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);
        int k = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> x = Arrays.stream(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Integer> costs = Arrays.stream(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int result = hackerlandRadioTransmitters(x, k, costs);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
