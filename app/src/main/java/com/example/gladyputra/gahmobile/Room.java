package com.example.gladyputra.gahmobile;

public class Room {
    private String nama,kamar,status;
    private float harga;
    private int image;

    public Room(int image,String nama, String kamar, String status, float harga) {
        this.image = image;
        this.nama = nama;
        this.kamar = kamar;
        this.status = status;
        this.harga = harga;
    }

    public int getImage() {
        return image;
    }

    public String getNama() {
        return nama;
    }

    public String getKamar() {
        return kamar;
    }

    public String getStatus() {
        return status;
    }

    public float getHarga() {
        return harga;
    }
}
