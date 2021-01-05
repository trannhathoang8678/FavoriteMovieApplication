package plusplus.FavoriteMovieAplication.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import plusplus.FavoriteMovieAplication.JpaConfig;
import plusplus.FavoriteMovieAplication.entity.Viewer;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Configuration
public class ViewerInfo {
    @Autowired
    JpaConfig jpaConfig;

    public Viewer findViewer(int id) {
        String sql = "SELECT * FROM VIEWER WHERE id =" + id + ";";
        Viewer viewer = null;
        try (Statement statement = jpaConfig.getConnection().createStatement();) {
            ResultSet getViewer = statement.executeQuery(sql);
            if (getViewer.next()) {
                viewer = new Viewer(getViewer.getInt(1), getViewer.getString(2), getViewer.getString(3),
                        getViewer.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return viewer;
        }
    }

    public void addViewer(String username, String password, String email) {
        String sql = "INSERT INTO VIEWER (username,password,email) VALUE ('" + username + "','" + password
                + "','" + email + "');";
        try {
            Statement statement = jpaConfig.getConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("Add viewer succefully");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Add viewer failed");
        }
    }

    public boolean verifyViewer(String username) {
        String sql = "SELECT 'id' FROM VIEWER WHERE username ='" + username + "';";
        try (Statement statement = jpaConfig.getConnection().createStatement();) {
            ResultSet getIDHaveSameUsername = statement.executeQuery(sql);
            if (getIDHaveSameUsername.next()) {
                System.out.println("This username has already existed");
                return false;
            } else {
                System.out.println("Verify viewer successfully");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Verify viewer failed");
            return false;
        }
    }

    public void updateViewer(int id, String username, String password, String email) {
        // if don't want update write null for string and -1 for int
        if (!isViewerIDexist(id)) {
            return;
        }
        String sql = "UPDATE VIEWER SET id = " + id;
        if (username != null)
            sql += " ,username='" + username + "'";
        if (email != null)
            sql += " ,email='" + email + "'";
        if (password != null)
            sql += " ,password='" + password + "'";
        sql += " WHERE id =" + id + ";";
        try {
            Statement statement = jpaConfig.getConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("Update viewer succefully");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Update viewer failed");
        }
    }

    public void deleteViewer(int id) {
        if (!isViewerIDexist(id)) {
            return;
        }
        String sql = "DELETE FROM VIEWER WHERE id = " + id + " ;";
        try {
            Statement statement = jpaConfig.getConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("Delete viewer succefully");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Delete viewer failed");
        }
    }

    public boolean isViewerIDexist(int id) {
        String sql = "SELECT 'id' FROM VIEWER WHERE id ='" + id + "';";
        try (Statement statement = jpaConfig.getConnection().createStatement();) {
            ResultSet checkID = statement.executeQuery(sql);
            if (checkID.next()) {
                System.out.println("This viewerID exist");
                return true;
            } else {
                System.out.println("This viewerID is not existed");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("check is viewerID exist failed");
            return false;
        }
    }
    public void addMovieDisplay(int viewerID,int movieID, int rank) {
        // time by year
        if (!verifyMovieDisplay(viewerID,movieID)) return;
        String sql = "INSERT INTO MOVIE_DISPLAY VALUE (" + viewerID + "," + movieID +"," + rank +  ");" ;
        try {
            Statement statement = jpaConfig.getConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("Add movie display succefully");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Add movie display failed");
        }
    }

    public boolean verifyMovieDisplay(int viewerID,int movieID) {
        String sql = "SELECT 'id' FROM MOVIE_DISPLAY WHERE VIEWER_id =" + viewerID + " AND MOVIE_id=" + movieID + "';" ;
        try (Statement statement = jpaConfig.getConnection().createStatement();) {
            ResultSet getSameMovieDisplay = statement.executeQuery(sql);
            if (getSameMovieDisplay.next()) {
                System.out.println("This movie display has already existed");
                return false;
            } else {
                System.out.println("Verify movie display successfully");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Verify movie display failed");
            return false;
        }
    }

    public void updateMovieDisplay(int viewerID,int movieID, int rank) {
        //time by year
        if (!isMovieDisplayExist(viewerID,movieID)) {
            return;
        }
        String sql = "UPDATE MOVIE_DISPLAY SET MOVIE_id = " + movieID;

        if (rank != -1)
            sql += " ,rank='" + rank + "'";
        sql += " WHERE VIEWER_id =" + viewerID + " AND MOVIE_id =" + movieID +" ;";
        try {
            Statement statement = jpaConfig.getConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("Update movie display succefully");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Update movie display failed");
        }
    }

    public void deleteMovieDisplay(int peopleID,int viewerID) {
        if (!isMovieDisplayExist(peopleID,viewerID)) {
            return;
        }
        String sql = "DELETE FROM MOVIE_DISPLAY WHERE MOVIE_id =" + peopleID + " AND PEOPLE_id =" + viewerID + ";";
        try {
            Statement statement = jpaConfig.getConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("Delete movie display succefully");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Delete movie display failed");
        }
    }

    public boolean isMovieDisplayExist(int viewerID,int movieID) {
        String sql = "SELECT 'id' FROM MOVIE_DISPLAY WHERE VIEWER_id =" + viewerID + " AND MOVIE_id =" + movieID + ";";
        try (Statement statement = jpaConfig.getConnection().createStatement();) {
            ResultSet checkID = statement.executeQuery(sql);
            if (checkID.next()) {
                System.out.println("This movie display ID exist");
                return true;
            } else {
                System.out.println("This movie display ID is not existed");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("check is movie display ID exist failed");
            return false;
        }
    }
}
