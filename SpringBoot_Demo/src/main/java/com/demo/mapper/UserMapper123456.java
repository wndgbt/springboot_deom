package com.demo.mapper;
import com.common.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import java.util.List;

@Mapper
@CacheConfig(cacheNames = "user")
public interface UserMapper123456 {

    @Insert("insert into users (username,passwd,role,comments) values(#{username},#{password},#{role},#{comments})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")//默认id可以指定返回属性
    void insert(User user);

    @Delete("delete from users where id = #{id}")
    //如果指定为 true，则方法调用后将立即清空所有缓存
    @CacheEvict(key ="#p0",allEntries=true)
    void delete(String key,@Param("id") int userId);

    @UpdateProvider(type = DefindUpdate.class,method = "updateProvid")
    int update(User user);

    @Update("update users set username=#{username} where id = #{id}")
    @CachePut(key = "#p0")
    int updateByParam(String key,@Param("id") int userId,@Param("username") String username);

    @Select("select * from users")
    @Results({
            @Result(property="password",column="passwd"),
            @Result(property="role",column="role")
    })
    List<User> seletPage();

    //@Result(property="password",column="passwd")
    @Select("select * from users where id = #{id}")
    //@Cacheable(key ="#p0")
    User seletById(String key,@Param("id") int id);
    /**
     * 构造自己的方法
     */
    class DefindUpdate{

        public String updateProvid(User user){
           return new SQL(){{
               UPDATE("users");
               //条件写法.
               if(user.getUsername() != null){
                   SET("username=#{username}");
               }
               if(user.getPasswd() != null){
                   SET("passwd=#{password}");
               }if(user.getRole() != null){
                   SET("role=#{role}");
               }if(user.getComments() != null){
                   SET("comments=#{comments}");
               }
               WHERE("id=#{id}");
            }}.toString();
        }
    }
}
