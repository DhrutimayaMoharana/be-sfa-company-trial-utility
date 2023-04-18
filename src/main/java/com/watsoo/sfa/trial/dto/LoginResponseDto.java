package com.watsoo.sfa.trial.dto;

public class LoginResponseDto {

	private Long userId;
	private String token;
	private String username;
	private String emailId;
	private String gender;
	private String userPhotoPath;
	private Long companyId;
	private CompanyDto company;
	private AppVersionsDto appVersions;
	private String attendnaceCheck;
	private SiteDto primarySite;
	private SiteDto navigateToSite;
	private Boolean trialExpire;
	private Boolean isTrialCompany;
	private Long departmentId;
	private Long designationId;
	private TypeDto type;
	private String onBoardUserResponse;
	private ReimburmentDto reimbursmentConfig;
	private EmployeeConfig employeeConfig;
	private Object siteConfig;
	private Object siteAddressDTOList;

	public LoginResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUserPhotoPath() {
		return userPhotoPath;
	}

	public void setUserPhotoPath(String userPhotoPath) {
		this.userPhotoPath = userPhotoPath;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public CompanyDto getCompany() {
		return company;
	}

	public void setCompany(CompanyDto company) {
		this.company = company;
	}

	public AppVersionsDto getAppVersions() {
		return appVersions;
	}

	public void setAppVersions(AppVersionsDto appVersions) {
		this.appVersions = appVersions;
	}

	public String getAttendnaceCheck() {
		return attendnaceCheck;
	}

	public void setAttendnaceCheck(String attendnaceCheck) {
		this.attendnaceCheck = attendnaceCheck;
	}

	public SiteDto getPrimarySite() {
		return primarySite;
	}

	public void setPrimarySite(SiteDto primarySite) {
		this.primarySite = primarySite;
	}

	public SiteDto getNavigateToSite() {
		return navigateToSite;
	}

	public void setNavigateToSite(SiteDto navigateToSite) {
		this.navigateToSite = navigateToSite;
	}

	public Boolean getTrialExpire() {
		return trialExpire;
	}

	public void setTrialExpire(Boolean trialExpire) {
		this.trialExpire = trialExpire;
	}

	public Boolean getIsTrialCompany() {
		return isTrialCompany;
	}

	public void setIsTrialCompany(Boolean isTrialCompany) {
		this.isTrialCompany = isTrialCompany;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long getDesignationId() {
		return designationId;
	}

	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
	}

	public TypeDto getType() {
		return type;
	}

	public void setType(TypeDto type) {
		this.type = type;
	}

	public String getOnBoardUserResponse() {
		return onBoardUserResponse;
	}

	public void setOnBoardUserResponse(String onBoardUserResponse) {
		this.onBoardUserResponse = onBoardUserResponse;
	}

	public ReimburmentDto getReimbursmentConfig() {
		return reimbursmentConfig;
	}

	public void setReimbursmentConfig(ReimburmentDto reimbursmentConfig) {
		this.reimbursmentConfig = reimbursmentConfig;
	}

	public EmployeeConfig getEmployeeConfig() {
		return employeeConfig;
	}

	public void setEmployeeConfig(EmployeeConfig employeeConfig) {
		this.employeeConfig = employeeConfig;
	}

	public Object getSiteConfig() {
		return siteConfig;
	}

	public void setSiteConfig(Object siteConfig) {
		this.siteConfig = siteConfig;
	}

	public Object getSiteAddressDTOList() {
		return siteAddressDTOList;
	}

	public void setSiteAddressDTOList(Object siteAddressDTOList) {
		this.siteAddressDTOList = siteAddressDTOList;
	}

	public LoginResponseDto(Long userId, String token, String username, String emailId, String gender,
			String userPhotoPath, Long companyId, CompanyDto company, AppVersionsDto appVersions,
			String attendnaceCheck, SiteDto primarySite, SiteDto navigateToSite, Boolean trialExpire,
			Boolean isTrialCompany, Long departmentId, Long designationId, TypeDto type, String onBoardUserResponse,
			ReimburmentDto reimbursmentConfig, EmployeeConfig employeeConfig, Object siteConfig,
			Object siteAddressDTOList) {
		super();
		this.userId = userId;
		this.token = token;
		this.username = username;
		this.emailId = emailId;
		this.gender = gender;
		this.userPhotoPath = userPhotoPath;
		this.companyId = companyId;
		this.company = company;
		this.appVersions = appVersions;
		this.attendnaceCheck = attendnaceCheck;
		this.primarySite = primarySite;
		this.navigateToSite = navigateToSite;
		this.trialExpire = trialExpire;
		this.isTrialCompany = isTrialCompany;
		this.departmentId = departmentId;
		this.designationId = designationId;
		this.type = type;
		this.onBoardUserResponse = onBoardUserResponse;
		this.reimbursmentConfig = reimbursmentConfig;
		this.employeeConfig = employeeConfig;
		this.siteConfig = siteConfig;
		this.siteAddressDTOList = siteAddressDTOList;
	}

}
