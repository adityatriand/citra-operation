/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import operationcitra.*;
/**
 *
 * @author Adit,Diaz,Angga
 */
public class Main {
    public static void main(String[] args) {
        String nama_gambar = "gambar";
        for(int i=1;i<=10;i++){
            nama_gambar += i;           
            Thresholding.Operation(nama_gambar, "jpg",120);
            nama_gambar = "gambar";
        }
    }
}
