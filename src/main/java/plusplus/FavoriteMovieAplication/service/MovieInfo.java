package plusplus.FavoriteMovieAplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import plusplus.FavoriteMovieAplication.config.JpaConfig;
import plusplus.FavoriteMovieAplication.entity.FullMovie;
import plusplus.FavoriteMovieAplication.entity.MovieDemo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

@Service
public class MovieInfo {
    @Autowired
    JpaConfig jpaConfig;

    public void addMovieType(String name) {
        if (!verifyMovieType(name)) return;
        String sql = "INSERT INTO TYPE (name) VALUE ('" + name + "');";
        try {
            Statement statement = jpaConfig.getConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("Add type succefully");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Add type failed");
        }
    }

    public boolean verifyMovieType(String name) {
        String sql = "SELECT 'id' FROM TYPE WHERE name ='" + name + "';";
        try (Statement statement = jpaConfig.getConnection().createStatement();) {
            ResultSet getIDHaveSameName = statement.executeQuery(sql);
            if (getIDHaveSameName.next()) {
                System.out.println("This type has already existed");
                return false;
            } else {
                System.out.println("Verify type successfully");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Verify type failed");
            return false;
        }
    }

    public void updateMovieType(int id, String name) {
        if (!isTypeIDexist(id)) {
            return;
        }
        String sql = "UPDATE TYPE SET name = '" + name + "' WHERE id = " + id + " ;";
        try {
            Statement statement = jpaConfig.getConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("Update type succefully");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Update type failed");
        }
    }

    public void deleteMovieType(int id) {
        if (!isTypeIDexist(id)) {
            return;
        }
        String sql = "DELETE FROM TYPE WHERE id = " + id + " ;";
        try {
            Statement statement = jpaConfig.getConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("Delete type succefully");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Delete type failed");
        }
    }

    public boolean isTypeIDexist(int id) {
        String sql = "SELECT 'id' FROM TYPE WHERE id ='" + id + "';";
        try (Statement statement = jpaConfig.getConnection().createStatement();) {
            ResultSet checkID = statement.executeQuery(sql);
            if (checkID.next()) {
                System.out.println("This typeID exist");
                return true;
            } else {
                System.out.println("This typeID is not existed");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("check is typeID exist failed");
            return false;
        }
    }

    public FullMovie findMovieByID(int id) {
        String sql = "SELECT * FROM MOVIE WHERE id =" + id + ";";
        FullMovie fullMovie = null;
        try (Statement statement = jpaConfig.getConnection().createStatement();) {

            ResultSet getMovie = statement.executeQuery(sql);
            if (getMovie.next()) {
                fullMovie = new FullMovie(getMovie.getInt(1),getMovie.getString(2),getMovie.getInt(3),
                        getMovie.getString(4),getMovie.getString(5),getMovie.getInt(6),getMovie.getString(7));
            }
            fullMovie.setTypes(findTypesOfMovie(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return fullMovie;
        }
    }
    public FullMovie findMovieByName(String name) {
        String sql = "SELECT * FROM MOVIE WHERE name ='" + name + "';";
        FullMovie fullMovie = null;
        try (Statement statement = jpaConfig.getConnection().createStatement();) {

            ResultSet getMovie = statement.executeQuery(sql);
            if (getMovie.next()) {
                fullMovie = new FullMovie(getMovie.getInt(1),getMovie.getString(2),getMovie.getInt(3),
                        getMovie.getString(4),getMovie.getString(5),getMovie.getInt(6),getMovie.getString(7));
            }
            fullMovie.setTypes(findTypesOfMovie(fullMovie.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return fullMovie;
        }
    }

    public List<MovieDemo> findAllMovies() {
        String sql = "SELECT id,name,year_created,url_poster,score FROM MOVIE;";
        List<MovieDemo> movieDemos = new LinkedList<>();
        MovieDemo movieDemo = null;
        try (Statement statement = jpaConfig.getConnection().createStatement();) {

            ResultSet getMovie = statement.executeQuery(sql);
            while (getMovie.next()) {
                movieDemo = new MovieDemo(getMovie.getInt(1),getMovie.getString(2),getMovie.getInt(3)
                ,getMovie.getString(4),getMovie.getInt(5));
                movieDemos.add(movieDemo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return movieDemos;
        }
    }
    public void addMovie(String name, int createdYear, String urlPoster, String overview, int score, String length) {
        if (!verifyMovie(name)) return;
        String sql = "INSERT INTO MOVIE (name,year_created,url_poster,overview,score,length) VALUE ('" + name + "','" + createdYear
                + "','" + urlPoster + "','" + overview + "','" + score + "','" + length + "');";
        try {
            Statement statement = jpaConfig.getConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("Add movie succefully");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Add movie failed");
        }
    }

    public boolean verifyMovie(String name) {
        String sql = "SELECT 'id' FROM MOVIE WHERE name ='" + name + "';";
        try (Statement statement = jpaConfig.getConnection().createStatement();) {
            ResultSet getIDMovieHaveSameName = statement.executeQuery(sql);
            if (getIDMovieHaveSameName.next()) {
                System.out.println("This movie has already existed");
                return false;
            } else {
                System.out.println("Verify movie successfully");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Verify movie failed");
            return false;
        }
    }

    public void updateMovie(int id, String name, int createdYear, String urlPoster, String overview, int score, String length) {
        // if don't want update write null for string and -1 for int
        if (!isMovieIDexist(id)) {
            return;
        }
        String sql = "UPDATE MOVIE SET id = " + id;
        if (name != null)
            sql += " ,name='" + name + "'";
        if (createdYear != -1)
            sql += " ,year_created='" + createdYear + "'";
        if (urlPoster != null)
            sql += " ,url_poster='" + urlPoster + "'";
        if (overview != null)
            sql += " ,overview='" + overview + "'";
        if (score != -1)
            sql += " ,score='" + score + "'";
        if (length != null)
            sql += " ,length='" + length + "'";
        sql += " WHERE id =" + id + ";";
        try {
            Statement statement = jpaConfig.getConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("Update movie succefully");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Update movie failed");
        }
    }

    public void deleteMovie(int id) {
        if (!isMovieIDexist(id)) {
            return;
        }
        String sql = "DELETE FROM MOVIE WHERE id = " + id + " ;";
        try {
            Statement statement = jpaConfig.getConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("Delete movie succefully");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Delete movie failed");
        }
    }

    public boolean isMovieIDexist(int id) {
        String sql = "SELECT 'id' FROM MOVIE WHERE id ='" + id + "';";
        try (Statement statement = jpaConfig.getConnection().createStatement();) {
            ResultSet checkID = statement.executeQuery(sql);
            if (checkID.next()) {
                System.out.println("This movieID exist");
                return true;
            } else {
                System.out.println("This movieID is not existed");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("check is movieID exist failed");
            return false;
        }
    }
    public List<String> findTypesOfMovie(int movieID)
    {
        List<String> types = new LinkedList<>();
        String sql = "SELECT TYPE.name FROM TYPE JOIN MOVIE_TYPE mt ON TYPE.id= mt.TYPE_id WHERE mt.MOVIE_id =" + movieID + ";";
        try (Statement statement = jpaConfig.getConnection().createStatement();) {
            ResultSet getTypes = statement.executeQuery(sql);
            while(getTypes.next())
                types.add(getTypes.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return types;
        }
    }
    public void addTypeOfMovie(int typeID, int movieID) {
        if (!verifyTypeOfMovie(typeID, movieID)) return;
        String sql = "INSERT INTO MOVIE_TYPE  VALUE (" + typeID + "," + movieID + ");";
        try {
            Statement statement = jpaConfig.getConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("Add type of movie succefully");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Add type of movie failed");
        }
    }


    public void deleteTypeOfMovie(int typeID, int movieID) {
        if (verifyTypeOfMovie(typeID,movieID)) {
            return;
        }
        String sql = "DELETE FROM MOVIE_TYPE WHERE TYPE_id =" + typeID + " AND MOVIE_id =" + movieID + " ;";
        try {
            Statement statement = jpaConfig.getConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("Delete type of movie succefully");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Delete type of movie failed");
        }
    }

    public boolean verifyTypeOfMovie(int typeID, int movieID) {
        String sql = "SELECT 'id' FROM MOVIE_TYPE WHERE TYPE_id =" + typeID + " AND MOVIE_id =" + movieID + " ;";
        try (Statement statement = jpaConfig.getConnection().createStatement();) {
            ResultSet getSameTypeOfMovie = statement.executeQuery(sql);
            if (getSameTypeOfMovie.next()) {
                System.out.println("This type of movie has already existed");
                return false;
            } else {
                System.out.println("Verify type of movie successfully");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Verify type of movie failed");
            return false;
        }
    }

}
