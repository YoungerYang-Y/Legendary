package pers.legendary.gateway.config;

import lombok.Data;

import java.util.Date;

/**
 * Description:
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/3/28 11:00
 */
@Data
public class TokenInfo {
    private boolean active;

    private Date exp;

    private String userName;

    private String[] authorities;

    private String clientId;

    private String[] scope;

}
