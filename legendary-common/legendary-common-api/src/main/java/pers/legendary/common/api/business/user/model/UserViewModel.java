package pers.legendary.common.api.business.user.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Description: 用户视图，不包含敏感信息
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2023-02-03 21:34:01
 */
@Data
public class UserViewModel {
    private Integer id;
    private String username;
    private String nickname;
    private String phone;
    private String email;
    private Integer gender;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String city;
    private String icon;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date loginTime;
    private Integer status;
}
