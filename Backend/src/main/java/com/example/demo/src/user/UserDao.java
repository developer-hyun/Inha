package com.example.demo.src.user;


import com.example.demo.src.user.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

//    public List<GetUserRes> getUsers(){
//        String getUsersQuery = "select * from UserInfo";
//        return this.jdbcTemplate.query(getUsersQuery,
//                (rs,rowNum) -> new GetUserRes(
//                        rs.getInt("userIdx"),
//                        rs.getString("userName"),
//                        rs.getString("ID"),
//                        rs.getString("Email"),
//                        rs.getString("password"))
//                );
//    }
//
//    public List<GetUserRes> getUsersByEmail(String email){
//        String getUsersByEmailQuery = "select * from UserInfo where email =?";
//        String getUsersByEmailParams = email;
//        return this.jdbcTemplate.query(getUsersByEmailQuery,
//                (rs, rowNum) -> new GetUserRes(
//                        rs.getInt("userIdx"),
//                        rs.getString("userName"),
//                        rs.getString("ID"),
//                        rs.getString("Email"),
//                        rs.getString("password")),
//                getUsersByEmailParams);
//    }
//
//    public GetUserRes getUser(int userIdx){
//        String getUserQuery = "select * from UserInfo where userIdx = ?";
//        int getUserParams = userIdx;
//        return this.jdbcTemplate.queryForObject(getUserQuery,
//                (rs, rowNum) -> new GetUserRes(
//                        rs.getInt("userIdx"),
//                        rs.getString("userName"),
//                        rs.getString("ID"),
//                        rs.getString("Email"),
//                        rs.getString("password")),
//                getUserParams);
//    }
//
//
//    public int createUser(PostUserReq postUserReq){
//        String createUserQuery = "insert into UserInfo (userName, ID, password, email) VALUES (?,?,?,?)";
//        Object[] createUserParams = new Object[]{postUserReq.getUserName(), postUserReq.getId(), postUserReq.getPassword(), postUserReq.getEmail()};
//        this.jdbcTemplate.update(createUserQuery, createUserParams);
//
//        String lastInserIdQuery = "select last_insert_id()";
//        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
//    }
//
//    public int checkEmail(String email){
//        String checkEmailQuery = "select exists(select email from UserInfo where email = ?)";
//        String checkEmailParams = email;
//        return this.jdbcTemplate.queryForObject(checkEmailQuery,
//                int.class,
//                checkEmailParams);
//
//    }
//
//    public int modifyUserName(PatchUserReq patchUserReq){
//        String modifyUserNameQuery = "update UserInfo set userName = ? where userIdx = ? ";
//        Object[] modifyUserNameParams = new Object[]{patchUserReq.getUserName(), patchUserReq.getUserIdx()};
//
//        return this.jdbcTemplate.update(modifyUserNameQuery,modifyUserNameParams);
//    }
//
//    public User getPwd(PostLoginReq postLoginReq){
//        String getPwdQuery = "select userIdx, password,email,userName,ID from UserInfo where ID = ?";
//        String getPwdParams = postLoginReq.getId();
//
//        return this.jdbcTemplate.queryForObject(getPwdQuery,
//                (rs,rowNum)-> new User(
//                        rs.getInt("userIdx"),
//                        rs.getString("ID"),
//                        rs.getString("userName"),
//                        rs.getString("password"),
//                        rs.getString("email")
//                ),
//                getPwdParams
//                );
//
//    }

    //테스트
    public List<GetUserRes> logintest() {
          List<GetUserRes> getUserRes = this.jdbcTemplate.query("select id,password from User",
                (rs,rownum) -> new GetUserRes(
                        rs.getString("id"),
                        rs.getString("password")
                ));
          return getUserRes;
    }


    public List<GetMainPage> mainPageRes() {
        List<GetMainPage> getMainPages = this.jdbcTemplate.query("select englishTitle,imageUrl from KCulture limit 4",
                (rs,rownum)-> new GetMainPage(
                        rs.getString("englishTitle"),
                        rs.getString("imageUrl")
                ));
        return getMainPages;

    }

    /**
     * 카테고리
     */
    public List<GetCategory> getCategories(String category) {
        List<GetCategory> getCategories = this.jdbcTemplate.query("select englishTitle,imageUrl from KCulture where category=?",
                (rs,rownum)-> new GetCategory(
                        rs.getString("englishTitle"),
                        rs.getString("imageUrl")
                ),category);
        return getCategories;
    }

    /**
     * 세부화면
     */
    public GetDetailPage getDetailPage(String name) {
        GetDetailPage getDetailPage = this.jdbcTemplate.queryForObject("select koreaTitle,englishTitle,imageUrl,koreaExplain,englishExplain,movieUrl from KCulture where englishTitle=?",
                (rs,rownum) -> new GetDetailPage(
                        rs.getString("koreaTitle"),
                        rs.getString("englishTitle"),
                        rs.getString("imageUrl"),
                        rs.getString("koreaExplain"),
                        rs.getString("englishExplain"),
                        rs.getString("movieUrl")
                ),name);
        return getDetailPage;
    }


    /**
     * 메인화면 가로뷰
     */
    public List<GetCategory> getCategoriesview(String name) {
        List<GetCategory> getCategories = this.jdbcTemplate.query("select imageUrl,englishTitle from KCulture where category=?",
                (rs,rownum)-> new GetCategory(
                        rs.getString("englishTitle"),
                        rs.getString("imageUrl")
                ),name);
        return getCategories;
    }

}