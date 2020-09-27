package com.example.projectroom.Process;

public class RoomPOJO {
    private String rno,rdep,rmain,rent;
    private  int flg;

    public int getFlg() {
        return flg;
    }

    public void setFlg(int flg) {
        this.flg = flg;
    }

    public RoomPOJO(String rno, String rdep, String rmain, String rent, int flg) {
        this.rno = rno;
        this.rdep = rdep;
        this.rmain = rmain;
        this.rent = rent;
        this.flg = flg;
    }

    public String getRno() {
        return rno;
    }

    public void setRno(String rno) {
        this.rno = rno;
    }

    public String getRdep() {
        return rdep;
    }

    public void setRdep(String rdep) {
        this.rdep = rdep;
    }

    public String getRmain() {
        return rmain;
    }

    public void setRmain(String rmain) {
        this.rmain = rmain;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    @Override
    public String toString() {
        return  "RNO:\t"+rno +", DEP:\t"+rdep+"\nMAINT:\t"+rmain+", RENT:\t" + rent;
    }
}
