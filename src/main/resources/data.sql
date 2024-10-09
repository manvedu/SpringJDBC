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
    gen.userid1,
    gen.userid2,
    NOW() - (gen.seq % 1000) * INTERVAL '1 day' AS timestamp
FROM (
    SELECT
        FLOOR(RANDOM() * 1000) + 1 AS userid1,
        FLOOR(RANDOM() * 1000) + 1 AS userid2,
        generate_series(1, 70000) AS seq
) gen
WHERE gen.userid1 <> gen.userid2;


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

-- For having users with friendships from March 2025
INSERT INTO Friendships (userid1, userid2, timestamp)
SELECT
  1 as userid1,
  seq as userid2,
  DATE '2025-03-20' AS timestamp
FROM generate_series(1, 101) AS seq;

-- Populate Posts and Likes tables similarly with data in March 2025
INSERT INTO Posts (userId, text, timestamp)
VALUES (1, 'Post by Alice', '2025-03-15');

INSERT INTO Likes (postid, userid, timestamp) VALUES (1,1, '2025-03-15');



INSERT INTO Likes (postid, userid, timestamp)
SELECT
  1 as postid,
  1 as userid,
  DATE '2025-03-14' AS timestamp
FROM generate_series(1, 101) AS seq;




INSERT INTO Posts (userId, text, timestamp)
SELECT
  1 as userId,
  'Post text some ' || seq AS text,
  DATE '2025-03-14' AS timestamp
FROM generate_series(1, 101) AS seq;


