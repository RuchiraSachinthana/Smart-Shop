import java.text.SimpleDateFormat;
import java.util.Date;
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

    //    create methods
//    Login process

    public static boolean login() {
//
        printUi("Login");
        Scanner input = new Scanner(System.in);
        System.out.println("Please Enter the Email: ");
        String email = input.nextLine();
        System.out.println("Please Enter the Password: ");
        String password = input.nextLine();

        for (int i = 0; i < users.length; i++) {
            if (users[i][0] != null && users[i][0].equals(email)) {
                if (users[i][1].equals(password)) {
                    System.out.println("Welcome again!");
                    return true;
                } else {
                    System.out.println("Wrong Password!");
                    return false;
                }
            }
        }
        System.out.println("404 Not Found!");
        return false;
    }

    //    Register process

    public static boolean register() {
// [free?] , []1?, 2?, 3?] => Empty -> insert user
        Scanner input = new Scanner(System.in);
//        Check array empty or not
        if (users[users.length - 1][0] != null) {
            System.out.println("User Database is full!");
            return false;
        }

        System.out.println("Insert your Email: ");
        String email = input.nextLine();
        System.out.println("Insert your Password: ");
        String password = input.nextLine();

        for (int x = 0; x < users.length; x++) {
            if (users[x][0] == null) {
                users[x][0] = email;
                users[x][1] = password;
                return true;
            } else {
                if (users[x][0].equalsIgnoreCase(email)) {
                    System.out.println("Email is already exist!");
                    return false;
                }
            }
        }
        return false;
    }

    //    Dashboard Area

    public static void openDashboard() {

        Scanner input = new Scanner(System.in);

        String[] dashboardQuestions = {"1) Customer Management", "2) Item Management", "3) Order Management", "4) Logout"};

        while (true) {
            for (String question : dashboardQuestions) {
                System.out.println(question);
            }
            int userInput = input.nextInt();

            switch (userInput) {
                case 1:
                    customerManagement();
                    break;
                case 2:
//                    itemManagement()
                    break;
                case 3:
                    placeNewOrder();
                    break;
                case 4:
                    break;
                default:
                    return;

            }
        }
    }

    //    Customer Management process
    public static void customerManagement() {
        //== // save,update,delete,list
        Scanner input = new Scanner(System.in);

        String[] customerQuestions = {"1) Save Customer", "2) Find Customer", "3) Update Customer", "4) Delete Customer", "5) Find All Customers", "6) Back to Home"};

        while (true) {
            for (String question : customerQuestions) {
                System.out.println(question);
            }
            int userInput = input.nextInt();
            switch (userInput) {
                case 1:
                    saveCustomer();
                    break;
                case 2:
                    findCustomer();
                    break;
                case 3:
                    updateCustomer();
                    break;
                case 4:
                    deleteCustomer();
                    break;
                case 5:
                    printAllCustomers();
                    break;
                case 6:
                    return;
                default:
                    break;
            }

        }
    }

    //    Customer save process

    public static void saveCustomer() {

        Scanner input = new Scanner(System.in);

        while (true) {

            String nic, name, address;
            double salary;

            System.out.println("Insert Customer NIC");
            nic = input.nextLine();
            System.out.println("Insert Customer Name");
            name = input.nextLine();
            System.out.println("Insert Customer Address");
            address = input.nextLine();
            System.out.println("Insert Customer Salary");
            salary = input.nextDouble();
            //=========================
            customerMainForLoop:
            for (int i = 0; i < customers.length; i++) {
                if (customers[i][0] != null) {
                    if (customers[i][0].equals(nic)) {
                        System.out.println("Customer Already Exists!");
                        break;
                    }
                } else {
                    customers[i][0] = nic;
                    customers[i][1] = name;
                    customers[i][2] = address;
                    customers[i][3] = String.valueOf(salary);  // string <= double ()
                    //====================
                    System.out.println("Customer Saved!\n");
                    System.out.println("1) Do you want to add an another customer?");
                    System.out.println("2) Back to Main Menu");
                    int option = input.nextInt();
                    switch (option) {
                        case 1:
                            break customerMainForLoop;
                        case 2:
                            return;
                        default:
                            return;
                    }
                }
            }
        }
    }

    //    Find customer process

    public static void findCustomer() {
        Scanner input = new Scanner(System.in);
        System.out.println("Insert NIC: ");
        String nic = input.nextLine();

        // using foreach loop

        for (int i = 0; i < customers.length; i++) {
            if (customers[i][0] != null) {
                if (customers[i][0].equals(nic)) {
                    System.out.println("=================== Customer ==================");
                    System.out.println("Customer NIC: " + customers[i][0]);
                    System.out.println("Customer Name: " + customers[i][1]);
                    System.out.println("Customer Address: " + customers[i][2]);
                    System.out.println("Customer Salary: " + customers[i][3]);
                    System.out.println("=================== Customer ==================");
                    return;
                }
            }
        }
        System.out.println("Customer Not Found!");
    }

    //    Update customer process

    public static void updateCustomer() {
        Scanner input = new Scanner(System.in);
        System.out.println("Insert Nic to find the customer");
        String nic = input.nextLine();
        for (int i = 0; i < customers.length; i++) {
            if (customers[i][0] != null) {
                if (customers[i][0].equals(nic)) {
                    //===========Update
                    String newName, newAddress;
                    double newSalary;
                    System.out.println("Insert Customer Name to update: ");
                    newName = input.nextLine();
                    System.out.println("Insert Customer Address to update: ");
                    newAddress = input.nextLine();
                    System.out.println("Insert Customer Salary to update: ");
                    newSalary = input.nextDouble();

                    customers[i][1] = newName;
                    customers[i][2] = newAddress;
                    customers[i][3] = String.valueOf(newSalary);
                    System.out.println("Customer Updated!");
                    //===========Update
                    return;
                }
            }
        }
        System.out.println("Customer Not Found");
    }

    // Delete customer process

    public static void deleteCustomer() {
        Scanner input = new Scanner(System.in);
        System.out.println("Insert Nic: ");
        String nic = input.nextLine();
        // foreach (can use)
        for (int i = 0; i < customers.length; i++) {
            if (customers[i][0] != null) {
                if (customers[i][0].equals(nic)) {
                    customers[i][0] = null;
                    customers[i][1] = null;
                    customers[i][2] = null;
                    customers[i][3] = null;
                    System.out.println("Customer Deleted!");
                    return;
                }
            }
        }
        System.out.println("Customer Not Found!");
    }

    //    Find all customers process

    public static void printAllCustomers() {
        for (int i = 0; i < customers.length; i++) {
            if (customers[i][0] != null) {
                System.out.println("| NIC: " + customers[i][0] + "|\tName: " + customers[i][1] + "|\tAddress: " + customers[i][2] + "|\tSalary: " + customers[i][3] + "|");
            } else {
                return;
            }
        }
    }

    //    Order Process

    public static void placeNewOrder() {
        System.out.println("Inset Customer NIC: ");
        Scanner input = new Scanner(System.in);
        String nic = input.nextLine();
        String name, address;
        double salary;

//       ====================== Customer Find ==================

        for (int i = 0; i < customers.length; i++) {
            if (customers[i][0] != null) {
                if (customers[i][0].equals(nic)) {
                    System.out.println("=================== Customer ==================");
                    name = customers[i][1];
                    address = customers[i][2];
                    salary = Double.parseDouble(customers[i][3]); //[string<=double], [double<=string]** [Double== wrapper class]
                    System.out.println("=================== Customer ==================");
                }
            }
        }

//       ====================== Customer Find ==================

//       ================== Item find ======================

        System.out.println("Insert Item Code: ");
        String code = input.nextLine();
        String description;
        double unitPrice = 0;
        int qtyOnHand;

        for (int i = 0; i < items.length; i++) {
            if (items[i][0] != null) {
                if (items[i][0].equals(code)) {
                    System.out.println("==================Item===========");
                    description = items[i][1];
                    qtyOnHand = Integer.parseInt(items[i][2]); // integer <= string // boxing vs unboxing
                    unitPrice = Double.parseDouble(items[i][3]); //[string<=double], [double<=string]** [Double== wrapper class]
                    System.out.println("==================Item===========");
                }
            }
        }


//         ============= Order =======

        System.out.println("Insert Order Code: ");
        String orderId = input.nextLine();


        for (int i = 0; i < orders.length; i++) {
            if (orders[i][0] != null) {
                if (orders[i][0].equals(orderId)) {
                    System.out.println("Order id exists");
                    return;
                } else {
                    Date date = new Date();
                    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                    String selectedDate = f.format(date);
                    orders[i][0] = orderId;
                    orders[i][1] = nic;
                    orders[i][2] = code;
                    orders[i][3] = selectedDate;
                    orders[i][4] = String.valueOf(unitPrice);
                }
            }
        }
        System.out.println("Order Completed!");

    }

    //    UI Area
    public static void printUi(String position) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        String simpleDate = dateFormat.format(date); // 2023-01-27
        String simpleTime = timeFormat.format(date); // 10:10:10

        System.out.println("==================== " + simpleDate + " ========== " + simpleTime + " =====> " + position + " ==================== ");

    }

    //    End UI
    public static void endUI() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        String year = dateFormat.format(date);

        System.out.println("==================================== @ " + year + " Order Management system ====================================");
    }

}
