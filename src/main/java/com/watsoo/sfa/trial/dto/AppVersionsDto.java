package com.watsoo.sfa.trial.dto;

import com.watsoo.sfa.trial.enums.DeviceType;

public class AppVersionsDto {

	private DeviceType deviceType;
	private Double appVersion;

	public AppVersionsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public Double getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(Double appVersion) {
		this.appVersion = appVersion;
	}

}
