package pers.legendary.common.core.util;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * <p>
 * 日志表 TODO
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysLog {//extends BaseEntity

	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
//	@TableId(value = "id", type = IdType.ASSIGN_ID)
//	@ExcelProperty("日志编号")
//	@Schema(name = "日志编号")
//	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;

	/**
	 * 日志类型
	 */
	@NotBlank(message = "日志类型不能为空")
//	@ExcelProperty("日志类型（0-正常 9-错误）")
//	@Schema(name = "日志类型")
	private String type;

	/**
	 * 日志标题
	 */
	@NotBlank(message = "日志标题不能为空")
//	@ExcelProperty("日志标题")
//	@Schema(name = "日志标题")
	private String title;

	/**
	 * 操作IP地址
	 */
//	@ExcelProperty("IP")
//	@Schema(name = "操作ip地址")
	private String remoteAddr;

	/**
	 * 用户浏览器
	 */
//	@ExcelProperty("浏览器类型")
//	@Schema(name = "用户代理")
	private String userAgent;

	/**
	 * 请求URI
	 */
//	@ExcelProperty("请求URI")
//	@Schema(name = "请求uri")
	private String requestUri;

	/**
	 * 操作方式
	 */
//	@ExcelProperty("操作方式")
//	@Schema(name = "操作方式")
	private String method;

	/**
	 * 操作提交的数据
	 */
//	@ExcelProperty("请求参数")
//	@Schema(name = "数据")
	private String params;

	/**
	 * 执行时间
	 */
//	@ExcelProperty("方法执行时间")
//	@Schema(name = "方法执行时间")
	private Long time;

	/**
	 * 异常信息
	 */
//	@ExcelProperty("异常信息")
//	@Schema(name = "异常信息")
	private String exception;

	/**
	 * 服务ID
	 */
//	@ExcelProperty("应用标识")
//	@Schema(name = "应用标识")
	private String serviceId;

	/**
	 * 删除标记
	 */
//	@TableLogic
//	@ExcelIgnore
	private String delFlag;

	/**
	 * 创建者
	 */
//	@TableField(fill = FieldFill.INSERT)
	private String createBy;

	/**
	 * 创建时间
	 */
//	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	/**
	 * 更新者
	 */
//	@TableField(fill = FieldFill.INSERT_UPDATE)
	private String updateBy;

	/**
	 * 更新时间
	 */
//	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;
}
