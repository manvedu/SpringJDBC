DROP FUNCTION IF EXISTS get_movies_count_by_genre();
DROP FUNCTION IF EXISTS get_average_rating_by_movie();

CREATE OR REPLACE FUNCTION get_movies_count_by_genre()
RETURNS TABLE (genre VARCHAR, count INT) AS $$
BEGIN
    RETURN QUERY SELECT genre, COUNT(*) FROM Movies GROUP BY genre;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_average_rating_by_movie()
RETURNS TABLE (title VARCHAR, avg_rating FLOAT) AS $$
BEGIN
    RETURN QUERY SELECT m.title, AVG(r.rating) FROM Movies m JOIN Reviews r ON m.id = r.movie_id GROUP BY m.title;
END;
$$ LANGUAGE plpgsql;