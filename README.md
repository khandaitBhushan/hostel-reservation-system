📌 Hostel Room Reservation System
A simple Java-based backend project using JDBC and MySQL to simulate a hostel room booking system. It supports core operations like reserving, canceling, updating, and fetching reservations using a CLI-based interface.

<hr>
✅ Features
<ul>
<li>  Reserve a hostel room by providing a mobile number and name </li>
<li>  Cancel an existing reservation  </li>
<li>  View reservation by room number  </li>
<li>  Update reservation details (room number, mobile number, or name)  </li>
<li>  View all reserved rooms  </li>
<li>  Auto-tracks booking time using MySQL TIMESTAMP  </li>
</ul>
<hr>

🛠️ Technologies Used
<ul>
<li>Java (Core + JDBC)</li>
<li>MySQL (Database)</li>
<li>SQL (DDL + DML)</li>
</ul>
<hr>
🗃️ Database Schema

```CREATE DATABASE Hostel_System;

CREATE TABLE reservation_sys (
  user_mobile_no VARCHAR(15),
  room_id INT PRIMARY KEY,
  user_name VARCHAR(250),
  booking_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```
<hr>
📂 Project Structure

```HostelReservationSystem/
├── ReservationSystem.java
├── DBConnection.java
└── README.md
```
<hr>
📌 Future Improvements
<ul>
<li>Add frontend (Swing or Web-based)</li>
<li>Add validation for duplicate entries</li>
<li>Improve room availability logic</li>
<li>Add user login/authentication system</li>
</ul>
<hr>
🤝 Contributing
This is a personal learning project. Feel free to fork, suggest improvements, or use it as a base for more advanced systems.
<hr>
✍ Author
<pre>Bhushan Khandait
Java | Backend | DSA Enthusiast</pre>
