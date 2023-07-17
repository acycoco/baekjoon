import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
    public char solution() throws IOException {
        int n = Integer.parseInt((new BufferedReader(new InputStreamReader(System.in))).readLine());
        int leng = 3;
        int cnt = 0;
        while (leng < n){
            cnt++;
            leng = leng * 2 + cnt + 3;
        }

        return moo(leng, cnt, n - 1);
    }

    public char moo(int leng, int cnt, int n){
        int lengthPre = (leng - (cnt + 3)) / 2;

        if ( n < lengthPre){ // 첫번째구간

            return moo(lengthPre, cnt - 1, n);
        } else if (n < lengthPre + cnt + 3 ){ //가운데 구간

            if (n == lengthPre){
                return 'm';
            } else {
                return 'o';
            }

        } else { //마지막 구간
            return moo(lengthPre, cnt - 1, n - (lengthPre + cnt + 3));
        }
    }

    public static void main(String[] args) throws IOException {

        System.out.println(new Main().solution());
    }
}
