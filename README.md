# Intergalactic Marketplace

## Introduction

Intergalactic Marketplace is a Java-based application designed for buying and selling products from different galaxies. It supports various user types, including buyers, sellers, and admins, each with unique functionalities. The application allows users to interact with different products, manage their cart, and process transactions. It also facilitates user and product management for the admin.

## Contents

This repository contains the source code for the Intergalactic Marketplace, including several classes representing various aspects of the marketplace:

1. `User`: An abstract class representing a user in the system. Contains common attributes like name, age, gender, email, and password.

2. `Product`: Class representing a product in the system. Contains attributes like product name, price, quantity, location, and condition (new or used).

3. `Session`: Singleton class to manage user sessions in the marketplace.

4. `Store`: Static class to store all the users and products. 

5. `FileHandler`: Class handling reading from and writing to files. It stores users and products in binary format for persistence.

6. `cartProduct`: Class representing a product in a user's cart. Contains attributes like the product and its quantity.

7. `Actions`: An interface defining the action handling method that all users should implement.

# Initial Products

| Product Name                        | Price | Quantity | Planet | Galaxy    | Condition |
| ----------------------------------- | ----- | -------- | ------ | --------- | --------- |
| Moon Rock Dust                      | 20    | 5        | XVI    | Milky Way | New       |
| Hydro-polymer Flux capacitor        | 200   | 5        | XVI    | Whirlpool | New       |
| Carbonised Panels                   | 100   | 5        | V      | Milky Way | New       |
| Cyro-Cooled Quantum Processor       | 370   | 5        | V      | Whirlpool | New       |
| Galactic Graviton Inductor          | 560   | 5        | IX     | Andromeda | Used      |
| Xenon Gas-filled Fusion Core        | 320   | 5        | X      | Whirlpool | New       |
| Neutrino Amplifier Array            | 490   | 5        | II     | Andromeda | New       |
| Anti-Matter Reactor Chamber         | 780   | 5        | VII    | Andromeda | New       |
| Hyperluminal Data Crystal           | 120   | 5        | III    | Whirlpool | Used      |
| Quantum Entangled Transmitter       | 490   | 5        | II     | Andromeda | New       |
| Hyperdimensional Flux capacitor     | 490   | 5        | II     | Milky Way | New       |
| Dark Matter Injector                | 490   | 5        | II     | Andromeda | New       |

# Initial Users

| User Type | User Name | Age | Gender | Email                 | Password  |
| --------- | --------- | --- | ------ | --------------------- | --------- |
| Admin     | Admin     | 43  | Male   | admin@gmail.com       | password  |
| Customer  | Customer1 | 30  | Female | customer1@gmail.com   | password  |
| Customer  | Customer2 | 345 | Male   | customer2@gmail.com   | password  |

#UML Diagram 
![UML Diagram](https://i.ibb.co/Vq4Djwt/image.png)

## Usage

To use the Intergalactic Marketplace, follow these steps:

1. Clone the repository.

2. Open the project in your preferred Java IDE.

3. Run the `Execution` class to start the application.

The application will prompt you for interactions in the console. You can create users, add products, and perform various other operations.

## Contributing

If you'd like to contribute, please fork the repository and make changes as you'd like. Pull requests are warmly welcome.

## Co-Author

This project is co-authored by Tania Hussain. You can check her contributions [here](https://github.com/ParallaX07/GalacticStore/commits?author=TaniaHussain).
