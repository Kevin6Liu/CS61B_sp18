/*
print out cumulative sum of numbers from 0 to 9
*/
public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0;
        int n = 0;
        while (x < 10) {
            n = n + x;
            System.out.print(n + " ");
            x = x + 1;
        }
        System.out.println("");
    }
}
