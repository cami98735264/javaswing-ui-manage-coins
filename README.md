# Java Swing UI - Manage Coins (CRUD)

A Java Swing application for managing coins in a database with a user-friendly interface. This application provides a complete CRUD (Create, Read, Update, Delete) functionality for managing coin records.

## Project Overview

This application allows users to:
- Add new coins to the database
- Update existing coin information
- Delete coins from the database
- Enable/disable coins
- View and consult coin information

## Project Structure

```
src/
├── edu/
│   └── itfip/
│       ├── interfaz/          # UI components
│       │   ├── AñadirMoneda.java
│       │   ├── ConsultarMoneda.java
│       │   ├── EliminarMoneda.java
│       │   ├── HabilitarMoneda.java
│       │   └── InterfazPrincipal.java
│       └── logica/            # Business logic
│           └── Iniciador.java
```

## Features

### Main Interface
- Clean and intuitive user interface
- Centralized access to all coin management functions
- Color-coded buttons for better user experience

### Functionality
1. **Add/Update Coins**
   - Add new coins to the database
   - Update existing coin information

2. **Delete Coins**
   - Remove coins from the database

3. **Enable/Disable Coins**
   - Toggle coin status between enabled and disabled

4. **Consult Coins**
   - View and search coin information

## Technical Details

- Built with Java Swing for the user interface
- Uses Oracle Database for data storage
- Implements JDBC for database connectivity
- Uses MigLayout for flexible UI layout management

## Database Connection
- Uses Oracle JDBC driver
- Connects to local Oracle database (XE)
- Default connection parameters:
  - Host: localhost
  - Port: 1521
  - SID: XE

## Dependencies
- Oracle JDBC Driver
- MigLayout 15 Swing

## Demo
[Watch the video](https://github.com/cami98735264/javaswing-ui-manage-coins/blob/main/bandicam%202023-11-29%2018-59-48-570.mp4)

![](https://github.com/cami98735264/javaswing-ui-manage-coins/blob/main/sample-gif.gif)

## Getting Started

1. Ensure you have Java installed
2. Set up an Oracle database
3. Update the database connection parameters in `Iniciador.java` if needed
4. Compile and run the application

## License
This project is open source and available for educational purposes.

