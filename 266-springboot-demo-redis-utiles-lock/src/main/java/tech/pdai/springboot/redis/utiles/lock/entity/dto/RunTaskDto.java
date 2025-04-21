package tech.pdai.springboot.redis.utiles.lock.entity.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * 运行任务对象 ieai_devops_run_task
 *
 * @author yu_yao
 */
public class RunTaskDto implements Serializable {

    private static final long serialVersionUID = 1L;


    /** 主键 */
    private Long id;


    /** 【0.计划属性】执行计划（ieai_devops_exec_plan）（本表的外键） */
    private Long devopsExecPlanId;

    /** 工程类型,1:作业调度 3:devops 4:灾备切换 8:日常操作 */
    private Integer projectType;

    /** 【1.工程信息】工程表-主键（本表的外键） */
    private Long studioProjectId;


    /** 【1.工程信息】工程版本主键（本表的外键） */
    private Long studioProjectVerId;


    /** 【1.工程信息】工程名称 */
    private String projectName;


    /** 【1.工程信息】版本号 */
    private String projectVersion;


    /** 【1.工程信息】版本描述 */
    private String projectVersionDescription;


    /** 【2.流程信息】工作流主键 */
    private Long studioWorkflowId;


    /** 【2.流程信息】工作流名称 */
    private String workflowName;


    /** 【2.流程信息】工作流类型,1:变更流程 2:回退流程 */
    private Integer workflowType;


    /** 【3.业务系统】业务系统ID */
    private Long sysmBusinessSystemId;


    /** 【3.业务系统】业务系统编码 */
    private String businessSystemCode;


    /** 【3.业务系统】业务系统名称 */
    private String businessSystemName;


    /** 【4.执行策略-.定时】工作编号 */
    private String jobNumber;


    /** 【4.执行策略-.定时】工作描述 */
    private String jobDescription;


    /** 【5.执行策略-分批】是否分批,0:不分批 1:分批 */
    private Integer batch;


    /** 【5.执行策略-分批】分批比例,范围1-100 */
    private Integer batchPercent;


    /** 【100.记录描述】开始时间 */
    private Timestamp startTime;


    /** 【100.记录描述】结束时间 */
    private Timestamp endTime;


    /** 【100.记录描述】运行耗时,单位秒 */
    private Long runTimeConsumed;


    /** 运行状态,-1:启动失败 0:运行中(启动成功) 1:执行成功 2:手工部署 3:暂停 4:部署失败 5:启动中*/
    private Integer runState;


    /** 【100.记录描述】创建时间 */
    private Timestamp createTime;


    /** 【100.记录描述】创建人id */
    private Long creatorId;


    /** 【100.记录描述】创建人姓名 */
    private String creatorName;

    /** 环境名称 */
    private String envNames;

    /** 耗时时分秒 */
    private String timeConsumed;

    /** 异常状态 1:失败 */
    private Integer faild;

    /** 审核人 */
    private String auditorName;
    /**
     *有权限的业务系统IDs
     */
    private List<Long> sysIds;
    /** 制品包名称 */
    private String artifactsName;
    /**
     *研发中心有权限的业务系统names
     */
    private String sysCodes;
    /**
     *研发中心有权限的业务系统codes
     */
    private List<String> sysCodeList;

    /** 特殊状态 1:存在运行中UT步骤 */
    private Integer specialState;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setDevopsExecPlanId(Long devopsExecPlanId) {
        this.devopsExecPlanId = devopsExecPlanId;
    }

    public Long getDevopsExecPlanId() {
        return devopsExecPlanId;
    }

    public void setStudioProjectId(Long studioProjectId) {
        this.studioProjectId = studioProjectId;
    }

    public Long getStudioProjectId() {
        return studioProjectId;
    }

    public void setStudioProjectVerId(Long studioProjectVerId) {
        this.studioProjectVerId = studioProjectVerId;
    }

    public Long getStudioProjectVerId() {
        return studioProjectVerId;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectVersion(String projectVersion) {
        this.projectVersion = projectVersion;
    }

    public String getProjectVersion() {
        return projectVersion;
    }

    public void setProjectVersionDescription(String projectVersionDescription) {
        this.projectVersionDescription = projectVersionDescription;
    }

    public String getProjectVersionDescription() {
        return projectVersionDescription;
    }

    public void setStudioWorkflowId(Long studioWorkflowId) {
        this.studioWorkflowId = studioWorkflowId;
    }

    public Long getStudioWorkflowId() {
        return studioWorkflowId;
    }

    public void setWorkflowName(String workflowName) {
        this.workflowName = workflowName;
    }

    public String getWorkflowName() {
        return workflowName;
    }

    public void setWorkflowType(Integer workflowType) {
        this.workflowType = workflowType;
    }

    public Integer getWorkflowType() {
        return workflowType;
    }

    public void setSysmBusinessSystemId(Long sysmBusinessSystemId) {
        this.sysmBusinessSystemId = sysmBusinessSystemId;
    }

    public Long getSysmBusinessSystemId() {
        return sysmBusinessSystemId;
    }

    public void setBusinessSystemCode(String businessSystemCode) {
        this.businessSystemCode = businessSystemCode;
    }

    public String getBusinessSystemCode() {
        return businessSystemCode;
    }

    public void setBusinessSystemName(String businessSystemName) {
        this.businessSystemName = businessSystemName;
    }

    public String getBusinessSystemName() {
        return businessSystemName;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setBatch(Integer batch) {
        this.batch = batch;
    }

    public Integer getBatch() {
        return batch;
    }

    public void setBatchPercent(Integer batchPercent) {
        this.batchPercent = batchPercent;
    }

    public Integer getBatchPercent() {
        return batchPercent;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setRunTimeConsumed(Long runTimeConsumed) {
        this.runTimeConsumed = runTimeConsumed;
    }

    public Long getRunTimeConsumed() {
        return runTimeConsumed;
    }

    public void setRunState(Integer runState) {
        this.runState = runState;
    }

    public Integer getRunState() {
        return runState;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public Integer getProjectType() {
        return projectType;
    }

    public void setProjectType(Integer projectType) {
        this.projectType = projectType;
    }

    public String getEnvNames() {
        return envNames;
    }

    public void setEnvNames(String envNames) {
        this.envNames = envNames;
    }

    public String getTimeConsumed() {
        return timeConsumed;
    }

    public void setTimeConsumed(String timeConsumed) {
        this.timeConsumed = timeConsumed;
    }

    public Integer getFaild() {
        return faild;
    }

    public void setFaild(Integer faild) {
        this.faild = faild;
    }

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }

    public List<Long> getSysIds() {
        return sysIds;
    }

    public void setSysIds(List<Long> sysIds) {
        this.sysIds = sysIds;
    }

    public String getArtifactsName() {
        return artifactsName;
    }

    public void setArtifactsName(String artifactsName) {
        this.artifactsName = artifactsName;
    }

    public String getSysCodes() {
        return sysCodes;
    }

    public void setSysCodes(String sysCodes) {
        this.sysCodes = sysCodes;
    }

    public List<String> getSysCodeList() {
        return sysCodeList;
    }

    public void setSysCodeList(List<String> sysCodeList) {
        this.sysCodeList = sysCodeList;
    }

    public Integer getSpecialState() {
        return specialState;
    }

    public void setSpecialState(Integer specialState) {
        this.specialState = specialState;
    }

    @Override
    public String toString() {
        return "RunTaskDto{" +
                "id=" + id +
                ", devopsExecPlanId=" + devopsExecPlanId +
                ", projectType=" + projectType +
                ", studioProjectId=" + studioProjectId +
                ", studioProjectVerId=" + studioProjectVerId +
                ", projectName='" + projectName + '\'' +
                ", projectVersion='" + projectVersion + '\'' +
                ", projectVersionDescription='" + projectVersionDescription + '\'' +
                ", studioWorkflowId=" + studioWorkflowId +
                ", workflowName='" + workflowName + '\'' +
                ", workflowType=" + workflowType +
                ", sysmBusinessSystemId=" + sysmBusinessSystemId +
                ", businessSystemCode='" + businessSystemCode + '\'' +
                ", businessSystemName='" + businessSystemName + '\'' +
                ", jobNumber='" + jobNumber + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                ", batch=" + batch +
                ", batchPercent=" + batchPercent +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", runTimeConsumed=" + runTimeConsumed +
                ", runState=" + runState +
                ", createTime=" + createTime +
                ", creatorId=" + creatorId +
                ", creatorName='" + creatorName + '\'' +
                ", envNames='" + envNames + '\'' +
                ", timeConsumed='" + timeConsumed + '\'' +
                ", faild=" + faild +
                ", auditorName='" + auditorName + '\'' +
                ", sysIds=" + sysIds +
                ", artifactsName='" + artifactsName + '\'' +
                ", sysCodes='" + sysCodes + '\'' +
                ", sysCodeList=" + sysCodeList +
                ", specialState=" + specialState +
                '}';
    }
}
