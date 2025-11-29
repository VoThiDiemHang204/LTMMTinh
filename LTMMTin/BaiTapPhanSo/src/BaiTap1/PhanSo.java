package BaiTap1;

import java.io.Serializable;

public class PhanSo implements Serializable {
    private int tuSo;
    private int mauSo;

    public PhanSo(int tuSo, int mauSo) {
        this.tuSo = tuSo;
        this.mauSo = (mauSo == 0) ? 1 : mauSo;
    }

    public int getTuSo() {
        return tuSo;
    }

    public int getMauSo() {
        return mauSo;
    }

    @Override
    public String toString() {
        return tuSo + "/" + mauSo;
    }
}
