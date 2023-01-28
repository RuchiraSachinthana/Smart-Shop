import java.util.Scanner;

public class AppInitializer {

    //   ==================== Database Area ===================== (for access all around the project)

    static String[][] users = new String[3][2];
    static String[][] customers = new String[100][4];
    static String[][] items = new String[100][4];
    static String[][] orders = new String[100][5];

    public static void main(String[] args) {

//        ==================== Test Data =====================

        items[0][0] = "001";
        items[0][1] = "Desc 01";
        items[0][2] = "15";
        items[0][3] = "250";

//        ==================== Test Data =====================

//        Programme initialization process
        printUi("Welcome To the Application");

//        create Scanner object
        Scanner scanner = new Scanner(System.in);
        boolean exitState = false;
//        create array for login
        String[] initializePageQuestions = {"1) Do you want to login?", "2) Do you want to register?", "3) Do you want to exit?"};

        while (!exitState) {
// create for loop to print out the questions
            for (String question : initializePageQuestions) {
                System.out.println(question);
            }


//        getting user input
            int userInput = scanner.nextInt();
//        create switch statement to handle user input
            switch (userInput) {
                case 1:
//         Just for testing process
                    users[0][0] = "ruchira@test.com";
                    users[0][1] = "test123";
                    if (login()) {
                        printUi("Dashboard");
                        openDashboard();

                    }
                    printUi("Application");
                    endUI();
                    break;
                case 2:
                    if (register()) {
                        printUi("Dashboard");
                        openDashboard();
                    }
                    printUi("Application");
                    endUI();
                    break;
                case 3:
                    System.out.println("You have selected to exit" + "Good Bye!");
                    endUI();
                    return;
                default:
                    System.out.println("Invalid input.." + "\uD83D\uDE14");
                    endUI();
                    return;
            }
        }

    }
}
