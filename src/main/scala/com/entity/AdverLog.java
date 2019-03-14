package com.entity;

public class AdverLog {
    private String   cuid;
    private String   imei;
    private String   android_id;
    private String   operator_type;
    private String   model;
    private String   vendor;
    private String   os_version;
    private String   os;
    private String   dpi;
    private String   device;
    private String   connection_type;
    private String   orientation;
    private String   screen_width;
    private String   screen_height;
    private String   ip;
    private String   imsi;
    private String   longitude;
    private String   latitude;
    private String   mac;
    private String   useragent;

    @Override
    public String toString() {
        return "AdverLog{" +
                "cuid='" + cuid + '\'' +
                ", imei='" + imei + '\'' +
                ", android_id='" + android_id + '\'' +
                ", operator_type='" + operator_type + '\'' +
                ", model='" + model + '\'' +
                ", vendor='" + vendor + '\'' +
                ", os_version='" + os_version + '\'' +
                ", os='" + os + '\'' +
                ", dpi='" + dpi + '\'' +
                ", device='" + device + '\'' +
                ", connection_type='" + connection_type + '\'' +
                ", orientation='" + orientation + '\'' +
                ", screen_width='" + screen_width + '\'' +
                ", screen_height='" + screen_height + '\'' +
                ", ip='" + ip + '\'' +
                ", imsi='" + imsi + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", mac='" + mac + '\'' +
                ", useragent='" + useragent + '\'' +
                '}';
    }

    public String getCuid() {
        return cuid;
    }

    public void setCuid(String cuid) {
        this.cuid = cuid;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getAndroid_id() {
        return android_id;
    }

    public void setAndroid_id(String android_id) {
        this.android_id = android_id;
    }

    public String getOperator_type() {
        return operator_type;
    }

    public void setOperator_type(String operator_type) {
        this.operator_type = operator_type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getOs_version() {
        return os_version;
    }

    public void setOs_version(String os_version) {
        this.os_version = os_version;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getConnection_type() {
        return connection_type;
    }

    public void setConnection_type(String connection_type) {
        this.connection_type = connection_type;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getScreen_width() {
        return screen_width;
    }

    public void setScreen_width(String screen_width) {
        this.screen_width = screen_width;
    }

    public String getScreen_height() {
        return screen_height;
    }

    public void setScreen_height(String screen_height) {
        this.screen_height = screen_height;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getUseragent() {
        return useragent;
    }

    public void setUseragent(String useragent) {
        this.useragent = useragent;
    }
}
