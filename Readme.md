**Bank account kata**

_`Requirements`_

· Deposit and Withdrawal

· Account statement (date, amount, balance)

· Statement printing

`User Stories`

US 1:

In order to save money
As a bank client
I want to make a deposit in my account

US 2:

In order to retrieve some or all of my savings
As a bank client
I want to make a withdrawal from my account

US 3:

In order to check my operations
As a bank client
I want to see the history (operation, date, amount, balance) of my operation

`Scenarios` 

_Scenario 1_: A client withdraws from his account<br/>

**Given** an account with balance 0.0 EUR<br/>
**When** he withdraws 200.0 EUR from his account<br/>
**Then** the new balance is -200.0 EUR<br/>

_Scenario 2_:  A client deposits into his account<br/>

**Given** an account with balance 0.0 EUR<br/>
**When** he deposits 200.0 EUR into his account<br/>
**Then** his new balance is 200.0 EUR<br/>

_Scenario 3_ :  A client prints operations history<br/>

**Given** an account with balance 0.0 EUR<br/>
**When** he deposit 10.0 EUR into his account<br/>
**And** he withdraws 10.0 EUR from his account<br/> 
**And** he display operations history<br/>
**Then** he see the folowing operations history:<br/><br/>
operation |                 date |     amount |    balance<br/>
Deposit |  2021-04-15 00:20:00 |      10,00 |      10,00<br/>
Withdraw |  2021-04-15 00:20:00 |      10,00 |       0,00<br/>

`Testing results :`

us 1 / 2 : OperationServiceTest ------
us 3 : StatementServiceTest

Tests run: 12, Passed: 12, Failures: 0, Errors: 0, Skipped: 0

