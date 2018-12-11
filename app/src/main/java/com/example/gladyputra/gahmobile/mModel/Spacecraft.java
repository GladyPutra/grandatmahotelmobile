package com.example.gladyputra.gahmobile.mModel;

public class Spacecraft {

    private String tanggal_transaksi;
    private float jumlah_tarif;
    private String jenis_tamu;
    private String tahun_transaksi;

    public String getTanggal_transaksi() {
        return tanggal_transaksi;
    }

    public void setTanggal_transaksi(String tanggal_transaksi) {
        this.tanggal_transaksi = tanggal_transaksi;
    }

    public float getJumlah_tarif() {
        return jumlah_tarif;
    }

    public void setJumlah_tarif(float jumlah_tarif) {
        this.jumlah_tarif = jumlah_tarif;
    }

    public String getJenis_tamu() {
        return jenis_tamu;
    }

    public void setJenis_tamu(String jenis_tamu) {
        this.jenis_tamu = jenis_tamu;
    }

    public String getTahun_transaksi() {
        return tahun_transaksi;
    }

    public void setTahun_transaksi(String tahun_transaksi) {
        this.tahun_transaksi = tahun_transaksi;
    }

    @Override
    public String toString() {
        return tanggal_transaksi;
    }
}
