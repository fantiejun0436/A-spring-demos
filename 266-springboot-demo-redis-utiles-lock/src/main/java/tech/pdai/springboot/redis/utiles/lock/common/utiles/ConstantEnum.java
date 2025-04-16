package tech.pdai.springboot.redis.utiles.lock.common.utiles;

/**
 * @description:
 * @return: 常用类型枚举
 * @author: jinjian_lv
 * @time: 2024/2/7 8:57
 */
public enum ConstantEnum {
    /**
     * 更新返回信息
     */
    UPDATE_MESSAGE("update.message"),
    /**
     * 保存返回信息
     */
    SAVE_MESSAGE("save.message"),
    /**
     * 删除返回信息
     */
    DELETE_MESSAGE("delete.message"),
    /**
     * 查询返回信息
     */
    SELECT_MESSAGE("select.message"),

    /**
     * 成功信息
     */
    SUCCESS("success"),
    /**
     * 失败信息
     */
    FALSE("false"),
    /**
     * 前端成功返回值
     */
    REPONSE_STATUS_SUSSCESS_CODE("10000"),

    /**
     * 前端失败返回值
     */
    REPONSE_STATUS_FAIL_CODE("00000"),

    /**
     * 待办检查失败返回值
     */
    CHECK_BACKLOG_FAIL_CODE("10001"),
    /**
     * PUB标识
     */
    PUB("pub"),

    /**
     * PUB标识
     */
    IEAI_AUTO("ieai-auto-"),

    /**
     * 工单标识
     */
    IEAI_CHG("ieai-chg-"),

    /**
     * redis分隔符
     */
    KEY_SPLIT(":"),

    /**
     * 逗号分隔符
     */
    COMMA_SPLIT(","),

    /**
     * 配置文件分隔符
     */
    CONFIG_SPLIT("&"),

    /**
     * 通用分隔符
     */
    COMMON_SPLIT(" "),
    /**
     * 参数替换符号
     */
    PARAM_REPLACE_SYMBOL("$"),
    /**
     * 参数替换符号
     */
    PARAM_REPLACE_SYMBOL_START("${"),
    /**
     * 参数替换符号
     */
    PARAM_REPLACE_SYMBOL_END("}"),
    /**
     * unix服务器拼接路径
     */
    BASIC_SCRIPT_UNIX("./baseScript/SU.sh"),
    /**
     * windows服务器拼接路径
     */
    BASIC_SCRIPT_WINDOWS("./baseScript/SU.bat"),
    /**
     * windows服务器SU脚本前缀
     */
    BASIC_SCRIPT_WINDOWS_PREFIX("cmd.exe /c "),

    /**
     * 主流程
     */
    CICD_MAIN_FLOW("1"),
    /**
     * 子流程
     */
    CICD_SON_FLOW("0"),
    /**
     * windows系统
     */
    SYSTEM_TYPE_WINDOWS("1"),
    /**
     * unix/linux系统
     */
    SYSTEM_TYPE_UNIX("2"),
    /**
     * as400系统
     */
    SYSTEM_TYPE_AS400("3"),

    /**
     * 当前步骤不允许执行（被禁用或者未匹配上）
     */
    NO_ALLOWED_EXEC("1"),

    /**
     * 定时任务调用成功
     */
    TIMING_SUCCESS("200"),

    /**
     * 数据中心
     */
    DATA_CENTER("D"),

    /**
     * 研发中心
     */
    DEVELOPMENT_CENTER("R"),

    /**数据导出提示语*/
    EXTER_EXPORT("导出数据为空，导出失败！"),

    /**获取平台管理审批权限code*/
    DEVOPS_APPLY_APPROVAL("devops_apply_approval"),
    /**类型1*/
    EXEC_PLAN_TYPE_TIME_TASK("1"),
    /**类型2*/
    EXEC_PLAN_TYPE_CYCLE_TASK("2"),
    /**导出文件的后缀名*/
    EXEC_SUFFIX_NAME(".xlsx"),

    /**
     * 模块参数key名称
     */
    APP_INDEX("应用索引"),

    /**
     * 模块参数key名称
     */
    APP_INDEX_EN("app_index"),

    /**
     * 无包名
     */
    NO_PKG_NAME("无包名"),

    /**
     * redis缓存操作名称-外部启动
     */
    EXTERNAL_START_FLOW("externalStartFlow"),

    /**
     * 外部启动平台常量
     */
    EXTERNAL_PLATFORM_NAME("CIT"),

    /**
     * 三人审核
     */
    AUDIT_THREE_PEOPEL("3"),

    /**
     * 双人复核
     */
    AUDIT_DOUBLE_PEOPEL("2"),

    /**
     * 回切服务标识,不进行回切服务
     */
    DIS_ROLL_ACP_SERVE(";disRollAcpServe;"),

    /**
     * 文件路径不合法
     */
    FILE_PATH_ERROR("文件路径不合法"),

    /**
     * 文件分隔符
     */
    PUB_SEPARTOR("@");

    private final String value;
    ConstantEnum(String value){
        this.value = value;
    }
    public String getValue(){
     return value;
    }


}
