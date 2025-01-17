import java.util.*;

public class Assignment4 {
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {
        showMenuDisplay();
    }

    //function to get a user's choice and enter one of the menu's options
    public static void showMenuDisplay() {

        String menuDisplay = "0. End program\n"
                + "1.  Sum Edges Multiplication\n"
                + "2. Digital Root \n"
                + "3. Find Path\n"
                + "4. Is Anagram\n"
                + "5. Knapsack Problem\n"
                + "Enter a digit 0-5:";

        System.out.println(menuDisplay);

        int yourNumber = sc.nextInt();

        switch (yourNumber) {

            //user chooses to quit
            case 0:
                System.out.println("End program");
                return;

            //option 1 entire process
            case 1:

                String str;
                System.out.println("Enter a String:");
                do {

                    str = sc.nextLine();
                } while (str.length() == 0);

                sumEdgesMult(str);

                break;

            //option 2 entire process
            case 2:
                System.out.println("Enter the number:");
                int num = sc.nextInt();

                digitalRoot(num);

                break;

            //option 3 entire process
            case 3:

                System.out.println("Enter the number of rows:");
                int rows = sc.nextInt();

                System.out.println("Enter the number of columns:");
                int columns = sc.nextInt();

                int[][] board = new int[rows][columns];
                System.out.println("Enter the board values (0 for obstacle, 1 for step):");

                // Initialize board values
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        board[i][j] = sc.nextInt();
                    }
                }

                isPath(board);

                break;


            //option 4 entire process
            case 4:

                System.out.println("Enter first String:");
                sc.nextLine();
                String str1 = sc.nextLine();
                System.out.println("Enter second String:");
                String str2 = sc.nextLine();

                boolean result = isAnagram(str1, str2); //prints first text for true and second text for false
                System.out.println((result) ? "Strings are an Anagram!" : "Strings are not an Anagram!");

                break;

            //option 5 entire process
            case 5:

                System.out.println("Enter the Capacity:");
                double capacity = sc.nextDouble();

                System.out.println("Enter the amount of products:");
                int products = sc.nextInt();

                //if user inserts
                if (capacity <= 0 || products <= 0) {
                    System.out.println("Wrong input");
                    break;
                }

                //loop to initialize products' volumes
                System.out.println("Enter products volumes:");
                double[] volume = new double[products];
                for (int i = 0; i < products; i++) {
                    volume[i] = sc.nextDouble();
                }

                //loop to initialize products' values
                System.out.println("Enter products values:");
                double[] value = new double[products];
                for (int j = 0; j < products; j++) {
                    value[j] = sc.nextDouble();
                }

                //call recursive process to find max profit
                double maxProfit = knapsackProb(capacity, value, volume);
                System.out.println("The max profit is: " + maxProfit);

                break;

            //when a user inputs wrong number
            default:
                System.out.println("Wrong input");
                break;
        }

        //recursion to call function and print menu again to start a new choice
        showMenuDisplay();
    }

    //___________________________________________Option 1________________________________________________


    //container function for option 1
    public static int sumEdgesMult(String str) {

        int sum = sumEdgesMult(str);

        System.out.println("The result is: " + sum);
        return sum;

    }


    // Recursive function to sum up digits through string
    public static int sumEdgesMult(String str, int start, int end) {
        if (start > end) { // stops if the start and end meet
            return 0;
        }

        int sum = 0;

        // searching for a digit on start location
        if (str.charAt(start) >= '0' && str.charAt(start) <= '9') {
            sum += str.charAt(start) - '0'; // Subtract '0' to get the numeric value
        }

        // searching for a digit on end location 
        if (start != end && str.charAt(end) >= '0' && str.charAt(end) <= '9') {
            sum += str.charAt(end) - '0'; // Subtract '0' to get the numeric value
        }

        // Recursive call jumping to a further location from both ends
        return sum + sumEdgesMult(str, start + 1, end - 1);
    }


    //___________________________________________Option 2________________________________________________


    //Container function for menu option 2
    public static int digitalRoot(int num) {

        if (num <= 0) {
            System.out.println("Wrong Input");
            return -1;
        }

        return sumOfDigits(num);
    }

    //Container function to calculate the sum of Digits for input number
    public static int sumOfDigits(int num) {

        int sum;

        if (num <= 9) { // Stopping case 1 : when num is a single positive digit
            System.out.println("The digital root is: " + num);
            return num;
        }
        // add to sum the last digit and removes it from current num
        sum = num % 10 + sumOfDigits(num / 10);

        //as long as sum is not a single digit - recursion call
        if (sum >= 10) {
            return sumOfDigits(sum);
        }


        return sum;
    }


//___________________________________________Option 3________________________________________________

    //Container function for Menu option 3
    public static boolean isPath(int[][] board) {

        if (isPath(board, 0, 0)) {
            System.out.println("There is a path!");
        } else {
            System.out.println("There is no path!");
        }

        return isPath(null); // flag to return to the menu again???
    }

    // Recursive function to find a path
    public static boolean isPath(int[][] board, int rowIndex, int colIndex) {

        int rows = board.length;
        int cols = board[0].length;

        // Base case: Starting cell is 0 or both possible moves are blocked
        if (board[0][0] == 0 || ((rowIndex + 1 >= rows || board[rowIndex + 1][colIndex] == 0) &&
                (colIndex + 1 >= cols || board[rowIndex][colIndex + 1] == 0))) {
            return false;
        }

        // stop case: last cell
        if (rowIndex == rows - 1 && colIndex == cols - 1) {
            return true;
        }

        // checking right cell or lower cell
        return (rowIndex + 1 < rows && board[rowIndex + 1][colIndex] == 1 && isPath(board, rowIndex + 1, colIndex)) ||
                (colIndex + 1 < cols && board[rowIndex][colIndex + 1] == 1 && isPath(board, rowIndex, colIndex + 1));
    }


    //___________________________________________Option 4________________________________________________
    public static boolean isAnagram(String str1, String str2) {

        if (str1.length() != str2.length()) { //stopping case 1: when the number of characters is different
            return false;
        }

        if (str1.compareTo(str2) == 0) { //stopping case 2: the strings are perfectly equal
            return true;
        }
        if (str1.isEmpty() && str2.isEmpty()) { //stopping case 3: there are no more chars left to compare
            return true;
        }

        if (str1.length() == str2.length()) {

            char str2_CompareChar = str2.charAt(0); //keeps the current char we're comparing

            int charPosition = str1.indexOf(str2_CompareChar); //keeps position of identical char

            if (charPosition == -1) { //no identical char of str2 found in str1
                return false;
            }

            String str1_RightToLeft = str1.substring(0, charPosition); // chars in str1 before the compared char
            String str1_LeftToRight = str1.substring(charPosition + 1); // chars in str1 after the compared char

            String uniteStr1 = str1_RightToLeft + str1_LeftToRight; //str1 is paste cut short from the compared char

            return isAnagram(uniteStr1, str2.substring(1)); //recursive return for process of comparing chars
        }
        return true;
    }


//___________________________________________Option 5________________________________________________

    //Container function recursion
    public static double knapsackProb(double capacity, double[] value, double[] volume) {
        return knapsackProb(capacity, value, volume, 0, 0.0);
    }

    // Recursive function to get the max value considering capacity
    public static double knapsackProb(double capacity, double[] value, double[] volume, int i, double profit) {
        // stopping case 1: no more room for items
        if (capacity <= 0) {
            return profit;
        }
        //stopping case 2: no more items
        if (i >= value.length) {
            return profit;
        }
        //If the item's volume is too big
        if (volume[i] > capacity) {
            return knapsackProb(capacity, value, volume, i + 1, profit);
        }

        //leave out the item - ignore, and move to next array element
        double ignoreItem = knapsackProb(capacity, value, volume, i + 1, profit);
        //take the item within conditions - keep,  and move to next array element
        double keepItem = knapsackProb(capacity - volume[i], value, volume, i + 1, profit + value[i]);

        //return maximum value
        return Math.max(ignoreItem, keepItem);
    }

}