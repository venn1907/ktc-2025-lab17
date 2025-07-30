import java.util.Scanner;

import classes.Contact;
import classes.ContactManagement;

public class App {
    static Scanner scanner = new Scanner(System.in);
    public static ContactManagement contactManagement = new ContactManagement();

    public static void menu() {
        System.out.println("\n=== MENU ===");
        System.out.println("1. Hien thi danh sach lien lac");
        System.out.println("2. Tim kiem lien lac theo ma lien lac");
        System.out.println("3. Them moi lien lac");
        System.out.println("4. Sua thong tin lien lac");
        System.out.println("5. Xoa thong tin lien lac");
        System.out.println("-----------------------------------------");
        System.out.println("Moi ban chon chuc nang [1->5] hoac nhan phim khac de thoat:");
    }

    public static int inputId() {
        int id;
        while (true) {
            try {
                System.out.println("Nhap id lien lac: ");
                id = Integer.parseInt(scanner.next());
                if (!contactManagement.isExisted(id)) {
                    System.out.println("ID khong ton tai, vui long nhap lai.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Id khong hop le, vui long nhap lai.");
            }
        }

        return id;
    }

    public static String inputEmail(String message, boolean blankAccepted) {
        String email;
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";

        while (true) {
            System.out.println(message);
            email = scanner.nextLine();
            if (email.isBlank()) {
                if (blankAccepted) {
                    break;
                } else {
                    System.out.println("Email khong duoc de trong.");
                }
            } else if (contactManagement.isExisted(email)) {
                System.out.println("Email da ton tai, vui long nhap lai.");
            } else if (!email.matches(emailRegex)) {
                System.out.println("Email khong dung dinh dang, vui long nhap lai.");
            } else {
                break;
            }
        }

        return email;
    }

    public static String inputPhone(String message, boolean blankAccepted) {
        String phone;
        String phoneRegex = "^0[0-9]{9}$";

        while (true) {
            System.out.println(message);
            phone = scanner.nextLine();
            if (phone.isBlank()) {
                if (blankAccepted) {
                    break;
                } else {
                    System.out.println("So dien thoai khong duoc de trong.");
                }
            } else if (contactManagement.isExisted(phone)) {
                System.out.println("So dien thoai da ton tai, vui long nhap lai.");
            } else if (!phone.matches(phoneRegex)) {
                System.out.println("So dien thoai khong dung dinh dang, vui long nhap lai.");
            } else {
                break;
            }
        }

        return phone;
    }

    public static void getContactList() {
        contactManagement.getContactList();
    }

    public static void getContact() {
        scanner.nextLine();
        System.out.println("Nhap so dien thoai: ");
        String phone = scanner.nextLine();
        contactManagement.getContact(phone);
    }

    public static void addContact() {
        scanner.nextLine();
        System.out.println("Nhap ten lien lac: ");
        String name = scanner.nextLine();
        String phone = inputPhone("Nhap so dien thoai: ", false);
        String email = inputEmail("Nhap email: ", false);
        System.out.println("Nhap dia chi: ");
        String address = scanner.nextLine();

        contactManagement.addContact(name, phone, email, address);
    }

    public static void updateContact() {
        int id = inputId();
        scanner.nextLine();
        System.out.println("Nhap ten lien lac moi (bo trong neu khong can cap nhat): ");
        String name = scanner.nextLine();
        String phone = inputPhone("Nhap so dien thoai moi (bo trong neu khong can cap nhat): ", true);
        String email = inputEmail("Nhap email moi (bo trong neu khong can cap nhat): ", true);
        System.out.println("Nhap dia chi moi (bo trong neu khong can cap nhat): ");
        String address = scanner.nextLine();

        Contact contact = new Contact(id, name, phone, email, address);
        contactManagement.updateContact(contact);
    }

    public static void removeContact() {
        int id = inputId();
        contactManagement.removeContact(id);
    }

    public static void main(String[] args) {
        int option;

        do {
            menu();

            try {
                option = scanner.nextInt();
            } catch (Exception e) {
                option = -1;
            }

            switch (option) {
                case 1 -> getContactList();
                case 2 -> getContact();
                case 3 -> addContact();
                case 4 -> updateContact();
                case 5 -> removeContact();
                default -> System.out.println("Cam on ban da su dung dich vu.");
            }
        } while (option >= 1 && option <= 5);

        scanner.close();
    }
}
