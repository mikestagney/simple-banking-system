# Simple Banking System

Command line bank credit card app using SQLite as a database and the Luhn algorithm to validate card numbers

## Things learned 

SQLite - great database to use for small projects

Luhn Algorithm - one way to check if credit card numbers have been entered correctly

Review of using Java Databse Connectivity (JDBC) 

### Details

User options:

On the main menu, the user can create credits cards and then login by entering the generated credit card number and PIN.

Once logged in, the options are:

* Balance - get the credit card balance
* Add income - increase the credit card balance
* Do transfer - transfer funds from one credit card to another
* Close account - delete credit card from the database 
* Logout - and go back to main menu
* Exit - quit the app

Thirteenth project created for JetBrains Academy Java Developer course - hard level project.

Credit card number anatomy

![credit card number structure](https://user-images.githubusercontent.com/49824414/119209982-e4f3f580-ba77-11eb-898c-f60ff325e7d1.png)

To calculate the checksum digit, use Luhn Algorithm. Here is how it works for a credit card with the number 4000008449433403:

![luhn algorithm](https://user-images.githubusercontent.com/49824414/119210047-3dc38e00-ba78-11eb-83a1-99de5d009c71.png)

The sum of all numbers in the final step will be divisible by 10 if the number is valid. 60 modulo 10 is 0.

### Sample input and output:

1. Create an account\
2. Log into account\
0. Exit\
\>1

Your card has been created\
Your card number:\
4000009455296122\
Your card PIN:\
1961

1. Create an account\
2. Log into account\
0. Exit\
\>1

Your card has been created\
Your card number:\
4000003305160034\
Your card PIN:\
5639

1. Create an account\
2. Log into account\
0. Exit\
\>2

Enter your card number:\
\>4000009455296122\
Enter your PIN:\
\>1961

You have successfully logged in!

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>2

Enter income:
>10000
Income was added!

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>1

Balance: 10000

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>3

Transfer
Enter card number:
>4000003305160035
Probably you made a mistake in the card number. Please try again!

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>3

Transfer
Enter card number:
>4000003305061034
Such a card does not exist.

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>3

Transfer
Enter card number:
>4000003305160034
Enter how much money you want to transfer:
>15000
Not enough money!

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>3

Transfer
Enter card number:
>4000003305160034
Enter how much money you want to transfer:
>5000
Success!

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>1

Balance: 5000

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit

>0
Bye!
