package com.example.projectroom.Process;

public class CustPOJO {
//    private String cid,mtr;
    private int cid,mtr;
    private String cName,cAddress,cBdate,cPh1,cPh2,cEmail,cAdhaar,cPAN,cJoining,cLeaveing,roomNo;

/*    public CustPOJO(String cid, String cName, String cAddress, String cBdate, String cPh1,
                    String cPh2, String cEmail, String cAdhaar, String cPAN, String cJoining,
                    String cLeaveing, String mtr, String roomNo) {*/

    public CustPOJO(int cid, String cName, String cAddress, String cBdate, String cPh1,
                String cPh2, String cEmail, String cAdhaar, String cPAN, String cJoining,
                String cLeaveing, int mtr, String roomNo) {
        this.cid = cid;
        this.mtr = mtr;
        this.cName = cName;
        this.cAddress = cAddress;
        this.cBdate = cBdate;
        this.cPh1 = cPh1;
        this.cPh2 = cPh2;
        this.cEmail = cEmail;
        this.cAdhaar = cAdhaar;
        this.cPAN = cPAN;
        this.cJoining = cJoining;
        this.cLeaveing = cLeaveing;
        this.roomNo = roomNo;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getMtr() {
        return mtr;
    }

    public void setMtr(int mtr) {
        this.mtr = mtr;
    }

/*    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getMtr() {
        return mtr;
    }

    public void setMtr(String mtr) {
        this.mtr = mtr;
    }*/

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcAddress() {
        return cAddress;
    }

    public void setcAddress(String cAddress) {
        this.cAddress = cAddress;
    }

    public String getcBdate() {
        return cBdate;
    }

    public void setcBdate(String cBdate) {
        this.cBdate = cBdate;
    }

    public String getcPh1() {
        return cPh1;
    }

    public void setcPh1(String cPh1) {
        this.cPh1 = cPh1;
    }

    public String getcPh2() {
        return cPh2;
    }

    public void setcPh2(String cPh2) {
        this.cPh2 = cPh2;
    }

    public String getcEmail() {
        return cEmail;
    }

    public void setcEmail(String cEmail) {
        this.cEmail = cEmail;
    }

    public String getcAdhaar() {
        return cAdhaar;
    }

    public void setcAdhaar(String cAdhaar) {
        this.cAdhaar = cAdhaar;
    }

    public String getcPAN() {
        return cPAN;
    }

    public void setcPAN(String cPAN) {
        this.cPAN = cPAN;
    }

    public String getcJoining() {
        return cJoining;
    }

    public void setcJoining(String cJoining) {
        this.cJoining = cJoining;
    }

    public String getcLeaveing() {
        return cLeaveing;
    }

    public void setcLeaveing(String cLeaveing) {
        this.cLeaveing = cLeaveing;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }
}
