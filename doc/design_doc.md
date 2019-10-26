**class Bank**

Represents a bank object. This class uses the singleton pattern.

**Benefits**: 

All obejcts in the program can access the same bank without passing it as reference everywhere.

Make sure that we always have only one bank instance.

Abstraction: GUI can make use of the interfaces without knowing the implementation.



**class BankAccount**

Represents a bank account.

- class CheckingAccount extends BankAccount
- class SavingAccount extends BankAccount
- class Loan extends BankAccount

**Benefits:**

Scalability: allows to add other types of bank account easily in the future.

Make use of polymorphism and increase code reusability.



**class User**

Represents a user of the bank system

- class Customer extends User
- class Manager extends User

**Benefits:**

Scalability: allows to add other types of user easily in the future.

Make use of polymorphism and increase code reusability.



**class Transaction**

Represents a transaction

- class CardTransaction extends Transaction

  Repesents an opening account transaction

- class LoanTransaction extends Transaction

  Represents a loan creation transaction

- class DepositTransaction extends Transaction

- class WithdrawTransaction extends Transaction

- class LoanDepositTransaction extends Transaction

**Benefits:**

Scalability: allows to add other types of transaction (e.g. transfer) easily in the future.

Abstraction: methods in Transaction makes it easier to update date and generate report.



class Currency

Represents a type of currency



class Report

Represents a report



**class BankUI**

The user interface of the bank system

**Benefits:**

Encapsulation: encapsulates all attributes and methods associated with user interface. This makes switching views easy to achieve.

It can be modified without touching the code of the bank system.



class StartPanel

Represents the launching view of the user interface.



**class UserPanel extends JPanel**

Represents the view for user login or registration

- class CusomerPanel extends UserPanel
- class ManagerPanel extends UserPanel

**Benefits:**

Increase code reusability.

Scalability: allows to add panel for other types of bank account easily in the future.



**class CustomerAccountPanel extends JPanel**

Represents the view to display a customer's accounts

It contains tabbed panes to display all accounts of a customer and switch between them.



**class AccountPanel extends JPanel**

Represents a tabbed pane to display info and get user action on a bank account

- class SavingAccountPanel extends AccountPanel
- class CheckingAccountPanel extends AccountPanel
- class LoanAccountPanel extends AccountPanel

**Benefits:**

Increase code reusability.

Scalability: allows to add panel for other types of bank account easily in the future.



class EventValidator

