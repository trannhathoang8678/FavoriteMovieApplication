package plusplus.FavoriteMovieAplication.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import plusplus.FavoriteMovieAplication.JpaConfig;
import plusplus.FavoriteMovieAplication.entity.FamousPeople;
import plusplus.FavoriteMovieAplication.entity.Movie;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
@Configuration
public class FamousPeopleInfo {
    @Autowired
    JpaConfig jpaConfig;
    public FamousPeople findFamousPeople(int id) {
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

    public void addFamousPeople(String name, String urlAvatar, String biography, String gender, Date birthday,
    String birthplace,String popularReason,int numberCredits,String nickname,String urlSocialMedia) {
        String sql = "INSERT INTO POPULAR_PEOPLE (name,url_avatar,biography,gender,bithday,birthplace" +
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
}
