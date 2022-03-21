package pers.legendary.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Description: Demo 实体
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/3/18 0:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Demo implements Serializable {
    private Integer id;
    private String name;
    private Integer age;
}
