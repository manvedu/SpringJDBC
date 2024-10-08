-- Users table with over 1,000 users
INSERT INTO Users (name, surname, birthdate)
SELECT
  'User_' || seq AS name,
  'Surname_' || seq AS surname,
  DATE '1970-01-01' + (seq % 3650) * INTERVAL '1 day' AS birthdate
FROM generate_series(1, 1500) AS seq;
