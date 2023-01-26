package reisinger.htl;

public class Main {

    public static void main(String[] args) {


        for (double i = 0; i <= 120; i+=0.5) {
            System.out.println(i + ": " + calculateGrade(i));
        }
    }

    public static String calculateGrade(double score) {
        return switch((int) (score/5)) {
            case 21, 22, 23, 24 -> "1,0";
            case 20 -> "1,3";
            case 19 -> "1,7";
            case 18 -> "2,0";
            case 17 -> "2,3";
            case 16 -> "2,7";
            case 15 -> "3,0";
            case 14 -> "3,3";
            case 13 -> "3,7";
            case 12 -> "4,0";
            case 11, 10 -> "4,7";
            default -> "5,0";
        };
    }
}
