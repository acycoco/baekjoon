import java.util.*;
class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();
        Day todayD = new Day(today);
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < terms.length; i++) {
            String[] splittedTerm = terms[i].split(" ");
            map.put(splittedTerm[0], Integer.parseInt(splittedTerm[1]));
        }
        
        for (int i = 0; i < privacies.length; i++) {
            String[] splitPrivacy = privacies[i].split(" ");
            Day day = new Day(splitPrivacy[0]);
            day.plusMonth(map.get(splitPrivacy[1]));
            if (day.isPast(todayD)) answer.add(i + 1);
        }
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
    
    
    public static class Day {
        public int year;
        public int month;
        public int day;
        
        public Day(String strDay) {
             this.year = Integer.parseInt(strDay.substring(0,4));
             this.month = Integer.parseInt(strDay.substring(5, 7));
            this.day  = Integer.parseInt(strDay.substring(8, 10));
        }
        public void plusMonth(int plusMonth) {
            
            this.month--;
            this.month = this.month + plusMonth;
            if (this.month > 11) {
               this.year += this.month / 12;
                this.month = this.month % 12;
            }
            this.month++;
        }
        
      public boolean isPast(Day today) {
    if (this.year != today.year) {
        return this.year < today.year;
    }
    if (this.month != today.month) {
        return this.month < today.month;
    }
    return this.day <= today.day;
}
        
        public String toString() {
            return this.year + " " + this.month + " " + this.day + " ";
        }
    }
}