import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {
    static int[] checkArr;
    static int[] myArr;
    static int checkOk;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        //입력받기
        int totalLth = Integer.parseInt(st.nextToken());
        int partLth = Integer.parseInt(st.nextToken());
        checkArr = new int[4];
        myArr = new int[4];
        char[] dnaStr = new char[totalLth];
        dnaStr = br.readLine().toCharArray();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            checkArr[i] = Integer.parseInt(st.nextToken());
             if (checkArr[i] == 0) checkOk++;
        }
        
        int cnt = 0;
        //처음 윈도우 배열 추가
        for (int i = 0; i < partLth; i++) {
            add(dnaStr[i]);
        }
        
        //4ㅐ의 조건이 다 맞으면 cnt++
        if (checkOk == 4) cnt++;
        
        //한칸씩 옆으로 가면서 끝까지 배열에 추가하고 제거하기
        for (int i = partLth; i < totalLth; i++) {
            add(dnaStr[i]);
            int left = i - partLth;
            remove(dnaStr[left]);
            if (checkOk == 4) cnt++;
        }
        System.out.println(cnt);
    }
    //add 배열에 추가하기
    private static void add(char c) {
        switch (c) {
            case 'A':
                myArr[0]++;
                if (checkArr[0] == myArr[0]) checkOk++;
                break;
            case 'C':
                myArr[1]++;
                if (checkArr[1] == myArr[1]) checkOk++;
                break;
            case 'G':
                myArr[2]++;
                if (checkArr[2] == myArr[2]) checkOk++;
                break;
            case 'T':
                myArr[3]++;
                if (checkArr[3] == myArr[3]) checkOk++;
                break;
        }
    }
    //remove 배열에서 제거하기
    private static void remove(char c) {
        switch (c) {
            case 'A':
                if (checkArr[0] == myArr[0]) checkOk--;
                myArr[0]--;
                break;
            case 'C':
                if (checkArr[1] == myArr[1]) checkOk--;
                myArr[1]--;
                break;
            case 'G':
                if (checkArr[2] == myArr[2]) checkOk--;
                myArr[2]--;
                break;
            case 'T':
                if (checkArr[3] == myArr[3]) checkOk--;
                myArr[3]--;
                break;
        }
    }
}