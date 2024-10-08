-- Users table with over 1,000 users
INSERT INTO Users (name, surname, birthdate)
SELECT
  'User_' || seq AS name,
  'Surname_' || seq AS surname,
  DATE '1970-01-01' + (seq % 3650) * INTERVAL '1 day' AS birthdate
FROM generate_series(1, 1500) AS seq;


-- Friendships table with over 70,000 friendships
INSERT INTO Friendships (userid1, userid2, timestamp)
SELECT
  FLOOR(RANDOM() * 1000) + 1 AS userid1,
  FLOOR(RANDOM() * 1000) + 1 AS userid2,
  NOW() - (seq % 1000) * INTERVAL '1 day' AS timestamp
FROM generate_series(1, 70000) AS seq
WHERE userid1 <> userid2;  -- Exclude self-friendship


-- Posts table with posts for some users
INSERT INTO Posts (userId, text, timestamp)
SELECT
  FLOOR(RANDOM() * 1000) + 1 AS userId,
  'Sample post content for post ' || seq AS text,
  NOW() - (seq % 1000) * INTERVAL '1 hour' AS timestamp
FROM generate_series(1, 5000) AS seq;

-- Likes table with over 300,000 likes
INSERT INTO Likes (postid, userid, timestamp)
SELECT
  FLOOR(RANDOM() * 5000) + 1 AS postid,
  FLOOR(RANDOM() * 1000) + 1 AS userid,
  NOW() - (seq % 1000) * INTERVAL '1 hour' AS timestamp
FROM generate_series(1, 300000) AS seq;


