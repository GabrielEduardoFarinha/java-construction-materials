# Material Management System
This Java application is designed to manage materials It provides functionalities for adding, displaying, selecting, and calculating costs associated with materials.

Features:
Material Addition: Allows users to add materials with details such as name, value, size, and unit of measure.
Material Display: Displays all the materials currently stored in the system.
Material Selection: Enables users to select materials from the list and specify quantities.
Cost Calculation: Calculates the total cost of selected materials, including engineer cost, labor cost, and taxes. Also allows removing materials from the calculation.



How to Use:
Run the Application: Execute the Main class to start the application.

Add Materials: Fill in the details for the material in the input fields and click "Adicionar" to add it to the system.

Display Materials: Click "Mostrar Materiais Cadastrados" to view all materials stored in the system.

Select Materials: Click "Selecionar Materiais" to choose materials from the list and specify quantities.

Calculate Cost: Click "Calculadora" to calculate the total cost based on the selected materials, engineer cost, labor cost, and taxes.

Remove Materials: Click "Remover do Cálculo" to remove selected materials from the calculation.

File Storage
Materials are stored in a file named materials.txt. The application uses object serialization to save and load materials from this file.

Exception Handling
The application handles various exceptions such as invalid input, file not found, and class not found gracefully, providing appropriate error messages to the user.

Requirements:

Java Development Kit (JDK) 8 or higher.

Java Swing library for GUI components.

Contributors:

Gabriel Eduardo Farinha

License:

This project is licensed under the MIT License.
