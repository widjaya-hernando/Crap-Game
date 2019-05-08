# CMPSC221FinalProject

## Database Creation
```
DROP TABLE CrapsTable;
DROP TABLE UserTable;
DROP TABLE RowTable;

CREATE TABLE UserTable
(
    User_ID INT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    User_FName VARCHAR (15) NOT NULL,
    User_LName VARCHAR (30) NOT NULL,
    User_Win INT,
    User_Lose INT
);

CREATE TABLE CrapsTable
(
    Craps_SessionID INT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    User_ID INT REFERENCES UserTable(User_ID),
    Craps_Outcome VARCHAR (5) NOT NULL,
    Roll_Total INT NOT NULL,
    Craps_Points INT NOT NULL
);

CREATE TABLE RowTable
(
    Roll_ID INT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    Craps_SessionID INT REFERENCES CrapsTable(Craps_SessionID),
    Roll_Die1Val INT,
    Roll_Die2Val INT
);

```
`Username: craps`

`Password : craps`

`Table name:  CRAPS`