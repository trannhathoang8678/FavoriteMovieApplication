package plusplus.FavoriteMovieAplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import plusplus.FavoriteMovieAplication.config.JpaConfig;
import plusplus.FavoriteMovieAplication.entity.FamousPeople;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Service
public class FamousPeopleInfo {
    @Autowired
    JpaConfig jpaConfig;
    public FamousPeople findFamousPeopleByID(int id) {
        String sql = "SELECT * FROM POPULAR_PEOPLE WHERE id =" + id + ";";
        FamousPeople famousPeople = null;
        try (Statement statement = jpaConfig.getConnection().createStatement();) {

            ResultSet getFamousPeople = statement.executeQuery(sql);
            if (getFamousPeople.next()) {
                famousPeople = new FamousPeople(getFamousPeople.getInt(1),getFamousPeople.getString(2),getFamousPeople.getString(3),
                        getFamousPeople.getString(4),getFamousPeople.getString(5),getFamousPeople.getDate(6),getFamousPeople.getString(7),
                        getFamousPeople.getString(8),getFamousPeople.getInt(9),getFamousPeople.getString(10),getFamousPeople.getString(11));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return famousPeople;
        }
    }
    public FamousPeople findFamousPeopleByName(String name) {
        String sql = "SELECT * FROM POPULAR_PEOPLE WHERE name ='" +name + "';";
        FamousPeople famousPeople = null;
        try (Statement statement = jpaConfig.getConnection().createStatement();) {

            ResultSet getFamousPeople = statement.executeQuery(sql);
            if (getFamousPeople.next()) {
                famousPeople = new FamousPeople(getFamousPeople.getInt(1),getFamousPeople.getString(2),getFamousPeople.getString(3),
                        getFamousPeople.getString(4),getFamousPeople.getString(5),getFamousPeople.getDate(6),getFamousPeople.getString(7),
                        getFamousPeople.getString(8),getFamousPeople.getInt(9),getFamousPeople.getString(10),getFamousPeople.getString(11));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return famousPeople;
        }
    }


    public void addFamousPeople(String name, String urlAvatar, String biography, String gender, Date birthday,
    String birthplace,String popularReason,int numberCredits,String nickname,String urlSocialMedia) {
        String sql = "INSERT INTO POPULAR_PEOPLE (name,url_avatar,biography,gender,birthday,birthplace," +
                "popular_reason,number_credits,nickname,url_social_media) VALUE ('" + name + "','" + urlAvatar
                + "','" + biography + "','" + gender + "','" + birthday + "','" + birthplace + "','" + popularReason + "','" + numberCredits
                + "','" + nickname + "','" + urlSocialMedia + "');";

        try {
            Statement statement = jpaConfig.getConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("Add famous people succefully");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Add famous people failed");
        }
    }



    public void updateFamousPeople(int id, String name, String urlAvatar, String biography, String gender, Date birthday,
                                   String birthplace,String popularReason,int numberCredits,String nickname,String urlSocialMedia) {
        // if don't want update write null for string and -1 for int
        if (!isFamousPeopleIDexist(id)) {
            return;
        }
        String sql = "UPDATE POPULAR_PEOPLE SET id = " + id;
        if (name != null)
            sql += " ,name='" + name + "'";
        if (urlAvatar != null)
            sql += " ,url_avatar='" + urlAvatar + "'";
        if (biography != null)
            sql += " ,biography='" + biography + "'";
        if (gender != null)
            sql += " ,gender='" + gender + "'";
        if (birthday != null)
            sql += " ,birthday='" + birthday + "'";
        if (birthplace != null)
            sql += " ,birthplace='" + birthplace + "'";
        if (popularReason != null)
            sql += " ,popular_reason='" + popularReason + "'";
        if (numberCredits != -1)
            sql += " ,number_credits='" + numberCredits + "'";
        if (nickname != null)
            sql += " ,nickname='" + nickname + "'";
        if (urlSocialMedia != null)
            sql += " ,url_social_media='" + urlSocialMedia + "'";
        sql += " WHERE id =" + id + ";";
        try {
            Statement statement = jpaConfig.getConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("Update famous people succefully");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Update famous people failed");
        }
    }

    public void deleteFamousPeople(int id) {
        if (!isFamousPeopleIDexist(id)) {
            return;
        }
        String sql = "DELETE FROM POPULAR_PEOPLE WHERE id = " + id + " ;";
        try {
            Statement statement = jpaConfig.getConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("Delete famous people succefully");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Delete famous people failed");
        }
    }

    public boolean isFamousPeopleIDexist(int id) {
        String sql = "SELECT 'id' FROM POPULAR_PEOPLE WHERE id ='" + id + "';";
        try (Statement statement = jpaConfig.getConnection().createStatement();) {
            ResultSet checkID = statement.executeQuery(sql);
            if (checkID.next()) {
                System.out.println("This famousPeopleID exist");
                return true;
            } else {
                System.out.println("This famousPeopleID is not existed");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("check is famousPeopleID exist failed");
            return false;
        }
    }
    public void addPeopleMovieRelationship(int movieID, int peopleID, String role, int time) {
        // time by year
        if (!verifyPeopleMovieRelationship(movieID,peopleID,role)) return;
        String sql = "INSERT INTO PEOPLE_MOVIE VALUE (" + movieID + "," +peopleID+",'" + role + "'," + time + ");" ;
        try {
            Statement statement = jpaConfig.getConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("Add relationship succefully");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Add relationship failed");
        }
    }

    public boolean verifyPeopleMovieRelationship(int movieID,int peopleID,String role) {
        String sql = "SELECT 'id' FROM PEOPLE_MOVIE WHERE MOVIE_id =" + movieID + " AND PEOPLE_id=" + peopleID + " AND role='"
                + role + "';" ;
        try (Statement statement = jpaConfig.getConnection().createStatement();) {
            ResultSet getSameRelationship = statement.executeQuery(sql);
            if (getSameRelationship.next()) {
                System.out.println("This relationship has already existed");
                return false;
            } else {
                System.out.println("Verify relationship successfully");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Verify relationship failed");
            return false;
        }
    }

    public void updatePeopleMovieRelationship(int movieID,int peopleID, String role, int time) {
        //time by year
        if (!isPeopleMovieRelationshipExist(movieID,peopleID)) {
            return;
        }
        String sql = "UPDATE PEOPLE_MOVIE SET MOVIE_id = " + movieID;
        if (role != null)
            sql += " ,role='" + role + "'";
        if (time != -1)
            sql += " ,time='" + time + "'";
        sql += " WHERE MOVIE_id =" + movieID + " AND PEOPLE_id = " + peopleID +" ;";
        try {
            Statement statement = jpaConfig.getConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("Update relationship succefully");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Update relationship failed");
        }
    }

    public void deletePeopleMovieRelationship(int movieID,int peopleID) {
        if (!isPeopleMovieRelationshipExist(movieID,peopleID)) {
            return;
        }
        String sql = "DELETE FROM PEOPLE_MOVIE WHERE MOVIE_id =" + movieID + " AND PEOPLE_id =" + peopleID + ";";
        try {
            Statement statement = jpaConfig.getConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("Delete relationship succefully");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Delete relationship failed");
        }
    }

    public boolean isPeopleMovieRelationshipExist(int movieID,int peopleID) {
        String sql = "SELECT 'id' FROM PEOPLE_MOVIE WHERE MOVIE_id =" + movieID + " AND PEOPLE_id =" + peopleID + ";";
        try (Statement statement = jpaConfig.getConnection().createStatement();) {
            ResultSet checkID = statement.executeQuery(sql);
            if (checkID.next()) {
                System.out.println("This relationship ID exist");
                return true;
            } else {
                System.out.println("This relationship ID is not existed");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("check is relationship ID exist failed");
            return false;
        }
    }
    public List<Integer> findMoviesPeopleJoin(int peopleID)
    {
        List<Integer> movieIDs = new LinkedList<>();
        String sql = "SELECT MOVIE_id FROM PEOPLE_MOVIE WHERE peopleID =" + peopleID + ";";
        try (Statement statement = jpaConfig.getConnection().createStatement();) {
            ResultSet getMovieID = statement.executeQuery(sql);
          while(getMovieID.next())
          {
              movieIDs.add(getMovieID.getInt(1));
          }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return movieIDs;
        }
    }

}
