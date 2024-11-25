
# Palsolar

**Palsolar** is a JavaFX-based desktop application developed as part of a database course project. It was designed for the Palsolar Company to track projects and manage employee data effectively. The application features a user-friendly graphical interface built with Scene Builder to enhance usability and efficiency.

---

## Key Features

- **Project Management**:
  - Add, update, and delete projects.
  - Track project details, deadlines, and statuses.

- **Employee Management**:
  - Add, update, and delete employee records.
  - Manage employee roles and assignments to projects.

- **Database Integration**:
  - Uses MySQL for data storage and management.
  - Implements CRUD operations for projects and employee data.

- **User-Friendly Interface**:
  - Built with JavaFX and designed using Scene Builder.
  - Provides a clean and intuitive graphical user interface.

---

## Getting Started

### Prerequisites

- **Java** (JDK 8 or higher)
- **JavaFX SDK**
- **Scene Builder** (for GUI editing)
- **MySQL**

---

### Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/YazanM23/Palsolar
   cd Palsolar
   ```

2. **Set up the database**:
   - Create a database named `palsolar`.
   - Import the `palsolar.sql` file into your MySQL database.

3. **Configure database connection**:
   Update the `db_config.java` file with your database credentials:
   ```java
   public class DBConfig {
       public static final String URL = "jdbc:mysql://localhost:3306/palsolar";
       public static final String USER = "your-username";
       public static final String PASSWORD = "your-password";
   }
   ```

4. **Run the application**:
   Compile and run the `Main.java` file:
   ```bash
   javac Main.java
   java Main
   ```

---

## File Structure

- **`src/main`**: Contains the main application source code.
- **`src/resources`**: Includes FXML files for the GUI and other resources.
- **`db_config.java`**: Configuration file for database connection settings.

---

## Customization

- Update FXML files in the `src/resources` directory to customize the interface.
- Add new database tables or modify existing ones in the `palsolar.sql` file to expand functionality.

---

## License

This project is open-source and available under the [MIT License](LICENSE).

---

## Contact

For queries or feedback, reach out:

- **Name**: Yazan Mansour
- **Email**: Yazan.mansour2003@gmail.com
