package plusplus.FavoriteMovieAplication.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import plusplus.FavoriteMovieAplication.JpaConfig;
import plusplus.FavoriteMovieAplication.entity.FamousPeople;
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
        String sql = "SELECT * FROM POPULAR_PEOPLE WHERE id =" + id + ";";
        Viewer viewer = null;
        try (Statement statement = jpaConfig.getConnection().createStatement();) {

            ResultSet getViewer = statement.executeQuery(sql);
            if (getViewer.next()) {
                viewer = new Viewer(getViewer.getInt(1),getViewer.getString(2),getViewer.getString(3),
                        getViewer.getString(4),getViewer.getString(5),getViewer.getDate(6),getViewer.getString(7),
                        getViewer.getString(8),getViewer.getInt(9),getViewer.getString(10),getViewer.getString(11));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return viewer;
        }
    }

    public void addViewer(String name, String urlAvatar, String biography, String gender, Date birthday,
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



    public void updateViewer(int id, String name, String urlAvatar, String biography, String gender, Date birthday,
                                   String birthplace,String popularReason,int numberCredits,String nickname,String urlSocialMedia) {
        // if don't want update write null for string and -1 for int
        if (!isViewerIDexist(id)) {
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

    public void deleteViewer(int id) {
        if (!isViewerIDexist(id)) {
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

    public boolean isViewerIDexist(int id) {
        String sql = "SELECT 'id' FROM POPULAR_PEOPLE WHERE id ='" + id + "';";
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
}
