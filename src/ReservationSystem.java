import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;


public class ReservationSystem {

    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        new ReservationSystem().start();
    }

    public void start() {
        System.out.println("_____________________Welcome________________");
        while (true) {
            System.out.print("""
                    1.Reserve new Room
                    2.Cancel Room Reservation
                    3.Get Room by Room Number
                    4.Update Reservation
                    5.List All Reserve Room\s
                    6.Exit
                    Enter your choice :\s"""
            );
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> reserveNewRoom();
                case 2 -> cancelRoom();
                case 3 -> getRoom();
                case 4 -> updateReservation();
                case 5 -> allReserveRoom();
                case 6 -> {
                    System.out.println("Closing all resources ....!");
                    sc.close();
                    System.exit(0);
                    System.out.println("Thanx for visit...!");
                    System.out.println("Have a nice day");
                }
                default -> System.out.println("Enter correct choice ..!");
            }
        }
    }

    void reserveNewRoom() {
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            System.out.print("Enter Your Name : ");
            sc.nextLine();
            String name = sc.nextLine();
            System.out.print("Enter your Mobile Number : ");
            String num = sc.nextLine();
            System.out.print("Enter room No. : ");
            PreparedStatement st = con.prepareStatement("INSERT INTO reservation_sys(user_mobile_no, room_id, user_name) VALUES (?, ?, ?)");
            int roomNo = sc.nextInt();
            st.setString(1, num);
            st.setInt(2, roomNo);
            st.setString(3, name);
            st.executeUpdate();
            System.out.println("Room reserve successful...!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    void cancelRoom() {
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            PreparedStatement st = con.prepareStatement("DELETE FROM reservation_sys WHERE room_id= ?");
            System.out.print("Enter Room Number : ");
            int id = sc.nextInt();
            st.setInt(1, id);
            int rs = st.executeUpdate();
            if (rs > 0)
                System.out.println("Entry " + id + " has been removed");
            else System.out.println("Cancellation failed ...!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    void getRoom() {
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            PreparedStatement st = con.prepareStatement("select *from reservation_sys where room_id = ?");
            System.out.print("Enter Room Number : ");
            int id = sc.nextInt();
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                System.out.println("Room Found:");
                System.out.println(
                        rs.getString("user_mobile_no") + " | " +
                                rs.getInt("room_id") + " | " +
                                rs.getString("user_name") + " | " +
                                rs.getTimestamp("booking_time")
                );
            } else {
                System.out.println("No reservation found for Room No. " + id);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    void updateReservation() {
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            System.out.print("Enter update details\n1.Room Number\n" +
                    "2.Mobile Number\n" +
                    "3.Name\n" +
                    "Enter your choice : ");
            int choice = sc.nextInt();
            int update = 0;
            if (choice == 1) {
                System.out.print("Enter Old room no. : ");
                int oldNo = sc.nextInt();
                System.out.print("Enter new room No : ");
                int newRoom = sc.nextInt();
                PreparedStatement st = con.prepareStatement("update reservation_sys set room_id = ? where room_id = ?");
                st.setInt(1, newRoom);
                st.setInt(2, oldNo);
                update = st.executeUpdate();

            } else if (choice == 2) {
                System.out.print("Enter room no. : ");
                int oldNo = sc.nextInt();
                System.out.print("Enter new Mobile No : ");sc.nextLine();
                String newNum = sc.nextLine();
                PreparedStatement st = con.prepareStatement("update reservation_sys set user_mobile_no = ? where room_id = ?");
                st.setString(1, newNum);
                st.setInt(2, oldNo);
                update = st.executeUpdate();
            } else {
                System.out.print("Enter room no. : ");
                int oldNo = sc.nextInt();
                System.out.print("Enter new Name : ");sc.nextLine();
                String newName = sc.nextLine();
                PreparedStatement st = con.prepareStatement("update reservation_sys set user_name = ? where room_id = ?");
                st.setString(1, newName);
                st.setInt(2, oldNo);
                update = st.executeUpdate();
            }
            if (update > 0) {
                System.out.println("Update successful...!");
            } else System.out.println("Failed to update ...!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    void allReserveRoom() {
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            PreparedStatement st = con.prepareStatement("select *from reservation_sys");
            ResultSet rs = st.executeQuery();
            boolean flag = false;
            while (rs.next()) {
                flag = true;
                System.out.println(rs.getBigDecimal(1) + " | " + rs.getInt(2) + " | " + rs.getString(3) + " | " + rs.getTimestamp(4));
            }
            if (!flag) {
                System.out.println("All rooms are Empty ...!");
            }
            System.out.println();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
