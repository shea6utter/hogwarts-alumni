import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Record {
    private List<Alumni> alumniList = new ArrayList<>();
    File file = new File("D://HUAWEI-PC//Documents//Repos//hogwarts-alumni//src//AlumniLog.txt");

    private void addAlumni(Alumni alumni) {
        alumniList.add(alumni);
    }

    public void printWriter() throws FileNotFoundException, IOException {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(file))) {
            for (Alumni alumni : alumniList) {
                int alumList = alumniList.size();
                pw.println(alumni);
            }
        }
    }

    private void removeAlumni(String alumniName) {
        for (Alumni alumni : alumniList) {
           if (alumni.getAlumniName() == alumniName) {
               alumniList.remove(alumni);
            }
        System.out.println("Successfully removed " + alumniName);
        System.out.println("");

        }
    }

    private void searchAlumniByName(String alumniNameSearch) {
        for (Alumni alumni : alumniList) {
            if (alumni.getAlumniName().equals(alumniNameSearch)) {
                System.out.println(alumni.getAlumniName() + "'s Record");
                System.out.println("Honors and Awards: " + alumni.getHonorsAwards());
                System.out.println("Current Company: " + alumni.getCompanyNow());
                System.out.println("Position: " + alumni.getPosition());
                System.out.println("Date Hired" + alumni.getDateHired());
            }
        }
    }

    private void showAlumniList() {
        for (Alumni alumni : alumniList) {
            System.out.println("Name: " + alumni.getAlumniName());
            System.out.println("Year Graduated: " + alumni.getYearGrad());
            System.out.println("Honors and Awards: " + alumni.getHonorsAwards());
            System.out.println("Current Company: " + alumni.getCompanyNow());
            System.out.println("Position: " + alumni.getPosition());
            System.out.println("Date Hired: " + alumni.getDateHired());
            System.out.println("");
        }
    }

    private Alumni takeAlumni() {
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Name: ");
        String alumniName = scan.nextLine();

        System.out.print("Year Graduated: ");
        int yearGrad = scan.nextInt();
        scan.nextLine();

        System.out.print("Honors & Awards: ");
        String honorsAwards = scan.nextLine();

        System.out.print("Current Company: ");
        String companyNow = scan.nextLine();
        
        System.out.print("Position: ");
        String position = scan.nextLine();

        System.out.print("Date Hired (MM/DD/YY): ");
        String dateHired = scan.nextLine();

        
        Alumni newAlumni = new Alumni(alumniName, yearGrad, honorsAwards, companyNow, position, dateHired);
        return newAlumni;
        
    }
    
    public void showMenu() {
        System.out.println("");
        System.out.println("HOGWARTS ALUMNI RECORDS");
        System.out.println("MENU");
        System.out.println("1 | Create new record");
        System.out.println("2 | Show all records");
        System.out.println("3 | Modify a record");
        System.out.println("4 | Search a record by name");
        System.out.println("5 | Save and Exit");
    }

    public static void main(String[] args) throws Exception {    
        Record main = new Record();
        Scanner s = new Scanner(System.in);
        int inputMenu;
        
        do {
            main.showMenu();
            System.out.print("Input a Number: ");
            inputMenu = s.nextInt();
            s.nextLine();
            System.out.print("");

            switch (inputMenu) {
                case 1:
                    // Add alumni
                    Alumni alumni = main.takeAlumni();
                    main.addAlumni(alumni);
                    break;
                case 2:
                    // Show Alumni
                    main.showAlumniList();
                    break;
                case 3:
                    // Modify Alumni
                    System.out.print("Enter name to modify record: ");
                    String alumniName = s.nextLine().toUpperCase();
                    main.removeAlumni(alumniName);
                    break;
                case 4:
                    // Search Alumni by Name
                    System.out.print("Enter name to search: ");
                    String alumniNameSearch = s.nextLine().toUpperCase();
                    main.searchAlumniByName(alumniNameSearch);
                    break;
                case 5:
                    // Save to text file
                    String saveAlumni;
                    System.out.print("\nWould you like to save this record? (Y/N): ");
                    saveAlumni = s.nextLine().toUpperCase();
                        if (saveAlumni == "Y") { 
                            main.printWriter();
                        } else if (saveAlumni == "N") {
                            System.out.println("Operation is cancelled.");
                        }
                    System.exit(1);
                    break;
            }
        } while (inputMenu !=5);
        s.close();
    }
}
