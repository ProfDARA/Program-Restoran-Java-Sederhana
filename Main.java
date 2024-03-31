//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//kelas menu
import java.util.Scanner;
class Menu {                          
    String nama;
    double harga;
    String kategori;

    public Menu(String nama, double harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//program utama
public class Main {
    public static void main(String[] args) {
        //bagian untuk inisiasi menu
        Menu[] menuList = {
            new Menu("Nasi Padang", 25000, "makanan"),
            new Menu("Mie Goreng", 20000, "makanan"),
            new Menu("Es Teh Manis", 5000, "minuman"),
            new Menu("Jus Jeruk", 8000, "minuman"),
        };

        //bagian untuk inisiasi variable dan input
        double totalBiaya = 0;
        double pajak = 0;
        double biayaPelayanan = 20000;
        boolean diskon10 = false;

        int esTehManisCount = 0; // untuk menyimpan jumlah Es Teh Manis yang dipesan
        int jusJerukCount = 0; // untuk menyimpan Jus Jeruk yang dipesan

        Scanner input = new Scanner(System.in);
        System.out.println("Daftar Menu Restoran:");

        for (Menu menu : menuList) {
            System.out.println(menu.kategori + ": " + menu.nama + " - Rp. " + menu.harga);
        }


        //bagian kode yang digunakan untuk pemesanan menu
        System.out.println("Pesan menu (maksimal 4 menu):");

        String[] pesananNama = new String[4]; // untuk menyimpan nama item yang dipesan
        int[] pesananJumlah = new int[4]; // untuk menyimpan jumlah item yang dipesan

        for (int i = 0; i < 4; i++) {
            System.out.print("Menu ke-" + (i + 1) + ": ");
            String pesanan = input.nextLine();

            for (Menu menu : menuList) {
                if (menu.nama.equalsIgnoreCase(pesanan)) {
                    System.out.print("Jumlah: ");
                    int jumlah = input.nextInt();
                    totalBiaya += menu.harga * jumlah;
                    input.nextLine(); // Membaca newline setelah input jumlah, jadi setelah di enter bisa tambah menu
                    if (menu.nama.equals("Es Teh Manis")) {
                        esTehManisCount += jumlah;
                    } else if (menu.nama.equals("Jus Jeruk")) {
                        jusJerukCount += jumlah;
                    }
                    pesananNama[i] = menu.nama;
                    pesananJumlah[i] = jumlah;
                }
            }
        }


        //Bagian Kode yang digunakan untuk diskon jika diatas 100 ribu
        if (totalBiaya > 100000) {
            diskon10 = true;
        }
        //Bagian Kode yang digunakan untuk pajak
        pajak = 0.1 * totalBiaya;
        double totalBiayaAkhir = totalBiaya + pajak + biayaPelayanan;

        // bagian kode promo beli satu gratis satu (dengan catatann hanya jika total biaya melebihi 50 ribu)
        if (totalBiaya > 50000) {
            int minumanCount = esTehManisCount + jusJerukCount;
            int promoCount = minumanCount / 2;
            int promoEsTehManis = Math.min(promoCount, esTehManisCount);
            int promoJusJeruk = promoCount - promoEsTehManis;
            totalBiaya -= (promoEsTehManis * menuList[2].harga + promoJusJeruk * menuList[3].harga);
            
            // Mengurangi jumlah makanan/minuman yang mendapat promo
            esTehManisCount -= promoEsTehManis;
            jusJerukCount -= promoJusJeruk;
        }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//bagian kode untuk menunjukan pesanan
        System.out.println("==============================================");

        System.out.println("<<<<============STRUK PESANAN============>>>>:");
        for (int i = 0; i < 4; i++) {
            if (pesananNama[i] != null) {
                int index = -1;
                for (int j = 0; j < menuList.length; j++) {
                    if (menuList[j].nama.equals(pesananNama[i])) {
                        index = j;
                        break;
                    }
                }
                double subtotal = menuList[index].harga * pesananJumlah[i];
                System.out.println(pesananNama[i] + " - " + menuList[index].harga + " x " + pesananJumlah[i] + " = Rp. " + subtotal);

                // Tampilan minuman promo
                if (pesananNama[i].equals("Es Teh Manis")) {
                    System.out.println(" (Setelah Promo: " + menuList[2].harga + " x " + esTehManisCount + " = Rp. " + menuList[2].harga * esTehManisCount + ")");
                } else if (pesananNama[i].equals("Jus Jeruk")) {
                    System.out.println(" (Setelah Promo: " + menuList[3].harga + " x " + jusJerukCount + " = Rp. " + menuList[3].harga * jusJerukCount + ")");
                }
            }
        }

        System.out.println("Total Biaya Setelah Diskon dan Promo: Rp. " + totalBiaya);

        if (diskon10) {
            double diskon = 0.1 * totalBiaya;
            System.out.println("Diskon 10%: -Rp. " + diskon);
        }

        System.out.println("Pajak (10%): Rp. " + pajak);
        System.out.println("Biaya Pelayanan: Rp. " + biayaPelayanan);
        System.out.println("Total Biaya Akhir: Rp. " + totalBiayaAkhir);


        System.out.println("=========Terimakasih Atas Kunjungannya========");
    
        System.out.println("==============================================");

        System.out.println("= Tugas 1  044112738  Danang Agung Restu Aji =");
    }
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////