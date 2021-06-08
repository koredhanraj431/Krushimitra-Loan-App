package com.example.application;

public class lfdatabase {

    private  String loanp;
    private  String inc;
    private  String ld;
    private  String ar;
    private  String add;
    private  String distr;
    private  String vill;
    private  String pinc;
    private  String accnt;
    private  String lamt;

    public lfdatabase(){

    }

    public lfdatabase(String loanp, String inc, String ld, String ar, String add, String distr, String vill, String pinc, String accnt, String lamt) {
        this.loanp = loanp;
        this.inc = inc;
        this.ld=ld;
        this.ar = ar;
        this.add = add;
        this.distr = distr;
        this.vill = vill;
        this.pinc = pinc;
        this.accnt = accnt;
        this.lamt = lamt;
    }

   public String getLoanp() {
        return loanp;
    }

    public String getInc() {
        return inc;
    }

    public String getLd() {
        return ld;
    }

    public String getAr() {
        return ar;
    }

    public String getAdd() {
        return add;
    }

    public String getDistr() {
        return distr;
    }

    public String getVill() {
        return vill;
    }

    public String getPinc() {
        return pinc;
    }

    public String getAccnt() {
        return accnt;
    }

    public String getLamt() {
        return lamt;
    }

    public void setLoanp(String loanp) {
        this.loanp = loanp;
    }

    public void setInc(String inc) {
        this.inc = inc;
    }

    public void setLd(String ld) {
        this.ld = ld;
    }

    public void setAr(String ar) {
        this.ar = ar;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public void setDistr(String distr) {
        this.distr = distr;
    }

    public void setVill(String vill) {
        this.vill = vill;
    }

    public void setPinc(String pinc) {
        this.pinc = pinc;
    }

    public void setAccnt(String accnt) {
        this.accnt = accnt;
    }

    public void setLamt(String lamt) {
        this.lamt = lamt;
    }
}
