
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Timer;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;


import Gamers.A;
import Gamers.B;
import Gamers.C;
import Gamers.D;

public class AltinOyunu {

	int ax = 0;
	int ay = 0;
	int bx = 0;
	int by = 0;
	int cx = 0;
	int cy = 0;
	int dx = 0;
	int dy = 0;

	int a=0;
	int b=0;
	int c=0;
	int d=0;
	int altinSayisi;
	int gizliAltinSayisi=0;
	int arttir;

	//FRAME
	JFrame frame;
	//Ana Değişkenler 
	private int kareX = 20;
	private int kareY = 20;
	private double altinYuzde = .2;
	private double galtinYuzde = .1;
	private double yogunluk = (kareY * kareX) * altinYuzde;

	//Oyunculara ait başlangıç ve bitiş noktaları
	private int baslangıcax = -1;
	private int baslangıcay = -1;
	private int bitisax = -1;
	private int bitisay = -1;
	private int baslangicbx = -1;
	private int baslangicby = -1;
	private int baslangiccx = -1;
	private int baslangiccy = -1;
	private int baslangicdx = -1;
	private int baslangicdy = -1;
	private int bitisbx = -1;
	private int bitisby = -1;
	private int bitiscx = -1;
	private int bitiscy = -1;
	private int bitisdx = -1;
	private int bitisdy = -1;



	boolean dIslem=true;

	//Oyuncuların Hareket Puanları varraılan değerler
	private int aHamlePuani = 5;
	private int bHamlePuani = 5;
	private int cHamlePuani = 5;
	private int dHamlePuani = 5;

	// Oyuncuların düşünme puanları varraılan değerler
	private int aHesapla = 5;
	private int bHesapla = 10;
	private int cHesapla = 15;
	private int dHesapla = 20;

	//Pencere boyut ayarları
	private int WIDTH = 850;
	private final int HEIGHT = 650;
	private final int HARITABOYUTU = 600;
	private int KBoyutu = HARITABOYUTU / kareX;


	//Programın ilkkez çalıştırılmasının değerlerini tutan değişkneler
	boolean k = true;
	boolean kontrolBaslangicB = true;
	boolean kontrolBaslangicC = true;
	boolean kontrolBaslangicd = true;

	// Çözümü doğru-yanlış oalrak değiştiren değişkenler
	boolean k2 = true;
	boolean k2b = true;
	boolean k2c = true;
	boolean k2d = true;

	//Oyuncular altına vardıysa yeniden yol hesabı denemek için değişkenler
	boolean deneme = true;
	boolean deneme2 = true;
	boolean deneme3 = true;
	boolean deneme4 = true;

	//çözüm doğru olduğu sürece deneme işlemleri yapıcaktır program başlayınca doğru olarak değiştirilir
	boolean cozum = false;

	//Oyuncuların puanı kalmadıysa yada altın bittiyse false olacak değişkenler
	boolean kontrola = true;
	boolean kontrolb = true;
	boolean kontrolc = true;
	boolean kontrold = true;

	//oyuncu  algoritmasının bir kere çalışması için 0 ile başlatık sonradan 1 artınca (while dögüsü ile)
	// yeniden sıfıra eşitlediğimiz l değişenleri
	int l = 0;
	int lb = 0;
	int lc = 0;
	int ld = 0;
	//maaliyet hesabı sonucu en karlı karemin hesabıyla bulduğum değerleri tutan değişkenler



	//timerlar sonucu hareket eden oyuncuların 3 kare gitmeleri yada altına varmaları sonucu
	//false olup bir sonraki oyuncuya geçmesi sağlayan değişkenelr
	boolean bitirC = false;
	boolean bitirA = true;
	boolean bitirB = false;
	boolean bitirD = false;

	//Dijktsra sonucu oyunculara ait yolların tutulduğu arraylistler ve maliyet listelerim
	ArrayList<Kare> yolum = new ArrayList<>();
	ArrayList<Kare> yolumb = new ArrayList<>();
	ArrayList<Kare> yolumc = new ArrayList<>();
	ArrayList<Kare> yolumd = new ArrayList<>();
	ArrayList<Kare> maliyet = new ArrayList<>();
	ArrayList<Kare> maliyetc = new ArrayList<>();
	ArrayList<Kare> maliyetd = new ArrayList<>();
	ArrayList<Kare> altinlar;
	ArrayList<Kare> gizliAltinTutucu=new ArrayList<>();
	ArrayList<Kare> gizliAltinTutucuB=new ArrayList<>();
	ArrayList<Kare> gizliAltinTutucuC=new ArrayList<>();
	ArrayList<Kare> gizliAltinTutucuD=new ArrayList<>();
	ArrayList<Kare> tumYolumA=new ArrayList<>();
	ArrayList<Kare> tumYolumB=new ArrayList<>();
	ArrayList<Kare> tumYolumC=new ArrayList<>();
	ArrayList<Kare> tumYolumD=new ArrayList<>();
	ArrayList<Kare> altinlargizli;


	//Oyunculara ait sınıflar
	A aOyuncusu = new A();
	B bOyuncusu = new B();
	C cOyuncusu = new C();
	D dOyuncusu = new D();

	//Oyuncuların hareket ederken yol arraylistinde yerini tutan değişkenler
	int ra = 1;
	int rb = 1;
	int rc = 1;
	int rd = 1;

	int adimSayisi;

	//oyuncuların hareket etmesini sağlayan timerlar



	boolean aninKaresiB=false;
	boolean aninKaresiBB=false;
	boolean aninKaresiBC=false;
	boolean aninKaresiBD=false;


	//Çözüm doğru olduğu sürece dijstra çalışır ve bitiş noktasını bulduğu anda bu false değeri alır
	private boolean yolAra = false;
	private boolean yolArab = false;
	private boolean yolArab2 = false;
	private boolean yolArac = false;
	private boolean yolArac2 = false;
	private boolean yolArad = false;
	private boolean yolArad2 = false;

	ArrayList<Kare> oncelikb;


	//Harita ve random değer
	Kare[][] koordinat;
	Random r = new Random();

	//LABELLER
	JLabel boyutL = new JLabel("Boyut (mxn)");
	JLabel aPuanL = new JLabel("A Puan");
	JLabel bPuanL = new JLabel("B Puan");
	JLabel cPuanL = new JLabel("C Puan");
	JLabel dPuanL = new JLabel("D Puan");
	JLabel aHamleL = new JLabel("A Hamle P");
	JLabel bHamleL = new JLabel("B Hamle P");
	JLabel cHamleL = new JLabel("C Hamle P");
	JLabel dHamleL = new JLabel("D Hamle P");
	JLabel altinYuzdeL = new JLabel("Altın %");
	JLabel gAltinYuzdeL = new JLabel("Gizli Altın %");
	JLabel toplamPuanL = new JLabel("Toplam Puan");
	JLabel adimSayisiL = new JLabel("Adım Sayısı");
	JLabel boyutL2 = new JLabel();
	JLabel puanAL = new JLabel();
	JLabel puanBL = new JLabel();
	JLabel puanCL = new JLabel();
	JLabel puanDL = new JLabel();
	//BUTTONLAR
	JButton haritaOlustur = new JButton("Harita Oluştur");
	JButton oyunaBasla = new JButton("Hamleleri Başlat");
	JButton ayarlaB = new JButton("Ayarla");


	//PANEL
	JPanel araclarPanel = new JPanel();
	//harita
	koordinat harita;
	//TEXTBOX lar
	Border aracPanelBaslik = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
	JTextField boyutX = new JTextField("20");
	JTextField boyutY = new JTextField("20");
	JTextField aHamlePuaniT = new JTextField("5");
	JTextField bHamlePuaniT = new JTextField("5");
	JTextField cHamlePuaniT = new JTextField("5");
	JTextField dHamlePuaniT = new JTextField("5");
	JTextField aHedefBelirleme = new JTextField("5");
	JTextField bHedefBelirleme = new JTextField("10");
	JTextField cHedefBelirleme = new JTextField("15");
	JTextField dHedefBelirleme = new JTextField("20");
	JTextField altinYuzdesi = new JTextField("20");
	JTextField gAltinYuzdesi = new JTextField("10");
	JTextField toplamPuanT = new JTextField("200");
	JTextField adimSayisiT = new JTextField("3");


	class Kare {

		private int kareTipi = 0;
		private int adimlar;
		private int x;
		private int y;
		private int sonX;
		private int sonY;
		public String deger;
		public int altin;

		public int getAltin() {
			return altin;
		}

		public void setAltin(int altin) {
			this.altin = altin;
		}

		private float hamle;
		public Kare sonKare;

		public Kare getsonKare() {
			return sonKare;
		}

		public void setsonKare(Kare sonKare) {
			this.sonKare = sonKare;
		}

		public double getHamle() {
			return hamle;
		}

		public void setHamle(float hamle) {
			this.hamle = hamle;
		}

		public void setX(int x) {
			this.x = x;
		}

		public void setY(int y) {
			this.y = y;
		}

		public Kare(int tip, int x, int y) {    //CONSTRUCTOR
			kareTipi = tip;
			this.x = x;
			this.y = y;
			adimlar = -1;
			deger = "-1";
		}


		public int getX() {
			return x;
		}        //GET METHODS

		public int getY() {
			return y;
		}

		public int getsonX() {
			return sonX;
		}

		public int getsonY() {
			return sonY;
		}

		public int gettip() {
			return kareTipi;
		}

		public int getadimlar() {
			return adimlar;
		}

		public String getDeger() {
			return deger;
		}

		public void setDeger(String deger) {
			this.deger = deger;
		}

		public void settip(int tip) {
			kareTipi = tip;
		}        //SET METHODS

		public void setsonKare(int x, int y) {
			sonX = x;
			sonY = y;
		}

		public void setadimlar(int adimlar) {
			this.adimlar = adimlar;
		}

	}

	public static void main(String[] args) {    //MAIN METHOD
		new AltinOyunu();
	}

	public AltinOyunu() {
		haritaTemizle();
		oyunaBasla.setVisible(false);
		haritaOlustur.setVisible(false);
		baslat();
	}

	public void haritaOlustur() {    //Harita Oluştur
		haritaTemizle();    //Harita Temizle
		altinlar = new ArrayList<Kare>();
		altinlargizli = new ArrayList<Kare>();
		Integer[] altinDegeri = {5, 10, 15, 20};
		double yogunluk = (kareY * kareX) * altinYuzde;
		altinSayisi=(int)yogunluk;
		altinSayisi=altinSayisi+((int)yogunluk * ((int)galtinYuzde));
		// Rastgele bir raı için dizi oluşturuyoruz.
		Random rastgele = new Random();
		int kacinciEleman;
		Random rastgele2 = new Random();
		int gizliAltin;

		for (int i = 0; i < yogunluk; i++) {
			Kare bulundugumKarem;
			kacinciEleman = rastgele.nextInt(altinDegeri.length);

			do {
				bulundugumKarem = kareOlusturucu();//Bir Kare oluştur
				bulundugumKarem.setDeger(Integer.toString(altinDegeri[kacinciEleman]));
				bulundugumKarem.setAltin(altinDegeri[kacinciEleman]);

			} while (bulundugumKarem.gettip() == 2);    //Kare tipi Altın ise
			bulundugumKarem.settip(2);
			altinlar.add(bulundugumKarem); // Altınlara kareyi ekle
		}
		for (int j = 0; j < yogunluk * galtinYuzde; j++) { // Gizli Altın ekleme


			gizliAltin = rastgele2.nextInt(altinlar.size());
			altinlar.get(gizliAltin).settip(6); //gizli altın tipi 6 numara
			altinlargizli.add(altinlar.get(gizliAltin));
		}

		cozum = true;
		yolAra = true;
		yolArab = true;
		oyunaBasla.setVisible(true);

	}

	public Kare kareOlusturucu() { // rasgele bir kare oluşturuyor haritam üzerinde
		boolean kont = false;
		Kare bulundugumKarem;
		while (!kont) {
			int x = r.nextInt(kareX);
			int y = r.nextInt(kareY);

			if ((x == 0 && y == 0) || (x == kareX - 1 && y == 0) || (x == 0 && y == kareY - 1) || (x == kareX - 1 && y == kareY - 1)) {
				//eğer başlangıç nokatrımda bir değer gelirse buna izin vermiyorum çünkü oyuncularım orada başlayacak
				kont = false;
			} else {
				kont = true;
				bulundugumKarem = koordinat[x][y];
				return bulundugumKarem;
			}

		}
		return null;
	}

	public void haritaTemizle() {// tüm karelerimi 3 nuamralı beyaz kare yapıyorum

		koordinat = new Kare[kareX][kareY];
		for (int x = 0; x < kareX; x++) {
			for (int y = 0; y < kareY; y++) {
				koordinat[x][y] = new Kare(3, x, y);
			}
		}

	}

	public void haritaResetle() {    //harita resetliyor

		for (int x = 0; x < kareX; x++) {
			for (int y = 0; y < kareY; y++) {
				Kare bulundugumKarem = koordinat[x][y];
				if (bulundugumKarem.gettip() == 4 || bulundugumKarem.gettip() == 0)    //Eğer kare tipi 4(keşfedilmiş mavi) veya 0(başlangıç) ise
					koordinat[x][y] = new Kare(3, x, y);    // tipini beyaz kare yap
				if (bulundugumKarem.gettip() == 3)    //Eğer kare tipi 4(keşfedilmiş mavi) veya 0(başlangıç) ise
					koordinat[x][y] = new Kare(3, x, y);    // tipini beyaz kare yap
				if (bulundugumKarem.gettip() == 2)// eğer kare altın ise üzerine yazılmış adım sayınısı resetler
					bulundugumKarem.setadimlar(-1);
				if (bulundugumKarem.gettip() == 1) {//  eğer kare altın-bitiş ise üzerine yazılmış adım sayınısı resetler
					bulundugumKarem.setadimlar(-1);
					bulundugumKarem.settip(2);
				}
				if (bulundugumKarem.gettip() == 6) {// eğer kare gizli altın ise üzerine yazılmış adım sayınısı resetler
					bulundugumKarem.setadimlar(-1);
				}
				if (bulundugumKarem.gettip() == 5) { // eğer kare oyuncu ise üzerine yazılmış adım sayınısı resetler
					bulundugumKarem.setadimlar(-1);
				}
				if (bulundugumKarem.gettip() == 7) { // eğer kare oyuncu ise üzerine yazılmış adım sayınısı resetler
					bulundugumKarem.setadimlar(-1);
				}
				if (bulundugumKarem.gettip() == 8) { // eğer kare oyuncu ise üzerine yazılmış adım sayınısı resetler
					bulundugumKarem.setadimlar(-1);
				}
				if (bulundugumKarem.gettip() == 9) { // eğer kare oyuncu ise üzerine yazılmış adım sayınısı resetler
					bulundugumKarem.setadimlar(-1);
				}
				if (bulundugumKarem.gettip() == 10) { // eğer kare oyuncu ise üzerine yazılmış adım sayınısı resetler
					bulundugumKarem.setadimlar(-1);
				}


			}
		}

		    //bazı değerleri siliyoruz resetleyip yeniden hesaplama yapabilmek için
	}

	public void haritaResetle2() {    //haritaResetle() gibi çalışır tek farkı başlangıç noktasını silmez
		// başlangıç noktasını silmeme sebebi ise maliyet hesapladığımız  B,C,D
		// oyuncuları için yol çizebilmek için başlangıç noktamızı silmiyoruz

		for (int x = 0; x < kareX; x++) {
			for (int y = 0; y < kareY; y++) {
				Kare bulundugumKarem = koordinat[x][y];

				if (bulundugumKarem.gettip() == 4)
					koordinat[x][y] = new Kare(3, x, y);


				if (bulundugumKarem.gettip() == 2)
					bulundugumKarem.setadimlar(-1);
				if (bulundugumKarem.gettip() == 1) {
					bulundugumKarem.setadimlar(-1);
					bulundugumKarem.settip(2);
				}
				if (bulundugumKarem.gettip() == 6) {
					bulundugumKarem.setadimlar(-1);
				}
				if (bulundugumKarem.gettip() == 5) {
					bulundugumKarem.setadimlar(-1);
				}
				if (bulundugumKarem.gettip() == 7) { // eğer kare oyuncu ise üzerine yazılmış adım sayınısı resetler
					bulundugumKarem.setadimlar(-1);
				}
				if (bulundugumKarem.gettip() == 8) { // eğer kare oyuncu ise üzerine yazılmış adım sayınısı resetler
					bulundugumKarem.setadimlar(-1);
				}
				if (bulundugumKarem.gettip() == 9) { // eğer kare oyuncu ise üzerine yazılmış adım sayınısı resetler
					bulundugumKarem.setadimlar(-1);
				}
				if (bulundugumKarem.gettip() == 10) { // eğer kare oyuncu ise üzerine yazılmış adım sayınısı resetler
					bulundugumKarem.setadimlar(-1);
				}
			}
		}


	}


	public void haritaResetlesa() {    // Değeri A olan oyuncunun bir sonraki adımı attığında önceki adımı silmeyi sağlar

		for (int x = 0; x < kareX; x++) {
			for (int y = 0; y < kareY; y++) {
				Kare bulundugumKarem = koordinat[x][y];
				if (bulundugumKarem.gettip() == 7)
					koordinat[x][y] = new Kare(3, x, y);
			}
		}
	}

	public void haritaResetlesb() {    // Değeri B olan oyuncunun bir sonraki adımı attığında önceki adımı silmeyi sağlar

		for (int x = 0; x < kareX; x++) {
			for (int y = 0; y < kareY; y++) {
				Kare bulundugumKarem = koordinat[x][y];
				if (bulundugumKarem.gettip() == 8)
					koordinat[x][y] = new Kare(3, x, y);


			}
		}
	}

	public void haritaResetlesc() {    // Değeri C olan oyuncunun bir sonraki adımı attığında önceki adımı silmeyi sağlar

		for (int x = 0; x < kareX; x++) {
			for (int y = 0; y < kareY; y++) {
				Kare bulundugumKarem = koordinat[x][y];
				if (bulundugumKarem.gettip() == 9)
					koordinat[x][y] = new Kare(3, x, y);


			}
		}
	}

	public void haritaResetlesd() {    // Değeri A olan oyuncunun bir sonraki adımı attığında önceki adımı silmeyi sağlar

		for (int x = 0; x < kareX; x++) {
			for (int y = 0; y < kareY; y++) {
				Kare bulundugumKarem = koordinat[x][y];
				if (bulundugumKarem.gettip() == 10)
					koordinat[x][y] = new Kare(3, x, y);


			}
		}
	}

	public void temizle() {    // Değeri A olan oyuncunun bir sonraki adımı attığında önceki adımı silmeyi sağlar

		for (int x = 0; x < kareX; x++) {
			for (int y = 0; y < kareY; y++) {
				Kare bulundugumKarem = koordinat[x][y];

				if (bulundugumKarem.getadimlar() == 0) {
					bulundugumKarem.setadimlar(-1);
				}
			}
		}
	}


	private void baslat() {    //Tasarımsal Ögeler başlatılması
		frame = new JFrame();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(WIDTH, HEIGHT);
		frame.setTitle("Altın Toplama Oyunu");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		araclarPanel.setBorder(BorderFactory.createTitledBorder(aracPanelBaslik, "Kontroller"));
		int bosluk = 25;

		araclarPanel.setLayout(null);
		araclarPanel.setBounds(10, 10, 210, 600);

		haritaOlustur.setBounds(40, bosluk, 120, 25);
		araclarPanel.add(haritaOlustur);
		bosluk += 25;

		oyunaBasla.setBounds(40, bosluk, 120, 25);
		araclarPanel.add(oyunaBasla);
		bosluk += 25;


		// Boyut Bilgileri Tasarım Ögeleri
		boyutL.setBounds(30, bosluk, 100, 25);
		araclarPanel.add(boyutL);
		boyutL2.setBounds(120, bosluk, 40, 25);
		araclarPanel.add(boyutL2);
		bosluk += 25;
		boyutX.setBounds(30, bosluk, 50, 25);
		araclarPanel.add(boyutX);
		boyutY.setBounds(120, bosluk, 50, 25);
		araclarPanel.add(boyutY);
		bosluk += 30;
		// A oyuncusu Tasarım Ögeleri
		aPuanL.setBounds(30, bosluk, 80, 25);
		araclarPanel.add(aPuanL);
		aHamleL.setBounds(120, bosluk, 80, 25);
		araclarPanel.add(aHamleL);
		bosluk += 25;
		aHamlePuaniT.setBounds(30, bosluk, 50, 25);
		araclarPanel.add(aHamlePuaniT);
		aHedefBelirleme.setBounds(120, bosluk, 50, 25);
		araclarPanel.add(aHedefBelirleme);
		bosluk += 30;
		//B oyuncusu Tasarım Ögeleri
		bPuanL.setBounds(30, bosluk, 80, 25);
		araclarPanel.add(bPuanL);
		bHamleL.setBounds(120, bosluk, 80, 25);
		araclarPanel.add(bHamleL);
		bosluk += 25;
		bHamlePuaniT.setBounds(30, bosluk, 50, 25);
		araclarPanel.add(bHamlePuaniT);
		bHedefBelirleme.setBounds(120, bosluk, 50, 25);
		araclarPanel.add(bHedefBelirleme);
		bosluk += 30;
		//C oyuncusu Tasarım Ögeleri
		cPuanL.setBounds(30, bosluk, 80, 25);
		araclarPanel.add(cPuanL);
		cHamleL.setBounds(120, bosluk, 80, 25);
		araclarPanel.add(cHamleL);
		bosluk += 25;
		cHamlePuaniT.setBounds(30, bosluk, 50, 25);
		araclarPanel.add(cHamlePuaniT);
		cHedefBelirleme.setBounds(120, bosluk, 50, 25);
		araclarPanel.add(cHedefBelirleme);
		bosluk += 30;
		//D oyuncusu Tasarım Ögeleri
		dPuanL.setBounds(30, bosluk, 80, 25);
		araclarPanel.add(dPuanL);
		dHamleL.setBounds(120, bosluk, 80, 25);
		araclarPanel.add(dHamleL);
		bosluk += 25;
		dHamlePuaniT.setBounds(30, bosluk, 50, 25);
		araclarPanel.add(dHamlePuaniT);
		dHedefBelirleme.setBounds(120, bosluk, 50, 25);
		araclarPanel.add(dHedefBelirleme);
		bosluk += 30;
		//Altın Yüzde Ögeleri
		altinYuzdeL.setBounds(20, bosluk, 80, 25);
		araclarPanel.add(altinYuzdeL);
		gAltinYuzdeL.setBounds(110, bosluk, 80, 25);
		araclarPanel.add(gAltinYuzdeL);
		bosluk += 25;
		altinYuzdesi.setBounds(20, bosluk, 50, 25);
		araclarPanel.add(altinYuzdesi);
		gAltinYuzdesi.setBounds(110, bosluk, 50, 25);
		araclarPanel.add(gAltinYuzdesi);
		bosluk += 30;
		toplamPuanL.setBounds(10, bosluk, 90, 25);
		araclarPanel.add(toplamPuanL);
		adimSayisiL.setBounds(110, bosluk, 80, 25);
		araclarPanel.add(adimSayisiL);
		bosluk += 25;
		toplamPuanT.setBounds(20, bosluk, 50, 25);
		araclarPanel.add(toplamPuanT);
		adimSayisiT.setBounds(110, bosluk, 50, 25);
		araclarPanel.add(adimSayisiT);
		bosluk += 30;
		ayarlaB.setBounds(40, bosluk, 120, 25);
		araclarPanel.add(ayarlaB);
		bosluk += 30;

		puanAL.setBounds(20, bosluk, 100, 25);
		araclarPanel.add(puanAL);
		bosluk += 25;
		puanBL.setBounds(20, bosluk, 100, 25);
		araclarPanel.add(puanBL);
		bosluk += 25;
		puanCL.setBounds(20, bosluk, 100, 25);
		araclarPanel.add(puanCL);
		bosluk += 25;
		puanDL.setBounds(20, bosluk, 100, 25);
		araclarPanel.add(puanDL);


		frame.getContentPane().add(araclarPanel);

		harita = new koordinat();
		harita.setBounds(230, 10, HARITABOYUTU + 1, HARITABOYUTU + 1);
		frame.getContentPane().add(harita);


		ayarlaB.addActionListener(new ActionListener() {// Varsayılan oalrak verilmiş değerlerin değiştrilmesini ve
			// atamasını sağlayan buton
			@Override
			public void actionPerformed(ActionEvent e) {
				kareX = Integer.parseInt(boyutX.getText());
				kareY = Integer.parseInt(boyutY.getText());

				altinYuzde = Double.parseDouble(altinYuzdesi.getText()) / 100;
				galtinYuzde = Double.parseDouble(gAltinYuzdesi.getText()) / 100;

				aHamlePuani = Integer.parseInt(aHamlePuaniT.getText());
				bHamlePuani = Integer.parseInt(bHamlePuaniT.getText());
				cHamlePuani = Integer.parseInt(cHamlePuaniT.getText());
				dHamlePuani = Integer.parseInt(dHamlePuaniT.getText());
				adimSayisi = Integer.parseInt(adimSayisiT.getText());

				aHesapla = Integer.parseInt(aHedefBelirleme.getText());
				bHesapla = Integer.parseInt(bHedefBelirleme.getText());
				cHesapla = Integer.parseInt(cHedefBelirleme.getText());
				dHesapla = Integer.parseInt(dHedefBelirleme.getText());


				aOyuncusu.setPuanA(Integer.parseInt(toplamPuanT.getText()));
				bOyuncusu.setPuanB(Integer.parseInt(toplamPuanT.getText()));
				cOyuncusu.setPuanC(Integer.parseInt(toplamPuanT.getText()));
				dOyuncusu.setPuanD(Integer.parseInt(toplamPuanT.getText()));

				if(adimSayisi>=3){
					arttir=adimSayisi-2;
				}
				if(adimSayisi<3){
					if(adimSayisi==2)
						arttir=0;
					if(adimSayisi==1)
						arttir=-1;
				}
				haritaOlustur.setVisible(true); // harita ooluştur butonu görünür

			}
		});
		haritaOlustur.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				haritaOlustur();
				yenile();
			}
		});


		oyunaBasla.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {



				Timer zamanlayicie = new Timer();
				TimerTask goreve = new TimerTask() {

					@Override
					public void run() {

						if (bitirA) {
							bitirA = false;

							if(yolum.size()>0) {

								if (koordinat[yolum.get(yolum.size() - 1).getX()][yolum.get(yolum.size() - 1).getY()].gettip() != 2){
									deneme = false;
								}

							}

							if(k){
								aAlgoritmasi();
							temizle();}

							if (!deneme || yolum.size()<1) {

								for (Kare kare : yolum) {
									if (kare.getX() == ax && kare.getY() == ay) {
										bitisax = kare.getX();
										bitisay = kare.getY();
										break;
									}
								}

								l--;
								yolum.clear();
								ra = 1;
								a=0;
								yolAra = true;
								deneme = true;
								temizle();
								aAlgoritmasi();
								//System.out.println("çalıştı");
								//System.out.println(bitisax+" "+bitisay);
							}

							aDeneme();
							if (!deneme || yolum.size()<1) {

								for (Kare kare : yolum) {
									if (kare.getX() == ax && kare.getY() == ay) {
										bitisax = kare.getX();
										bitisay = kare.getY();
										break;
									}
								}

								l--;
								yolum.clear();
								ra = 1;
								a=0;
								yolAra = true;
								deneme = true;
								temizle();
								aAlgoritmasi();
								//System.out.println("çalıştı");
								//System.out.println(bitisax+" "+bitisay);
							}




							bitirB = true;
						} else if (bitirB) {
							bitirB = false;
							if(kontrolBaslangicB){
								bAlgoritmasi();
								temizle();
							}

							if(!kontrolBaslangicB){
								if (!deneme2 || yolumb.size() < 1) {
									temizle();
									for (Kare kare : yolumb) {
										if (kare.getX() == bx && kare.getY() == by) {
											bitisbx = kare.getX();
											bitisby = kare.getY();
											break;
										}
									}
									lb--;
									yolumb.clear();
									maliyet.clear();
									b = 0;
									rb = 1;
									yolArab = true;
									deneme2 = true;
									temizle();
									bAlgoritmasi();
									//System.out.println("çalıştı b");
									//System.out.println(bitisax+" "+bitisby);
								}}
							bDeneme();
							if(!kontrolBaslangicB){
								if (!deneme2 || yolumb.size() < 1) {
									temizle();
									for (Kare kare : yolumb) {
										if (kare.getX() == bx && kare.getY() == by) {
											bitisbx = kare.getX();
											bitisby = kare.getY();
											break;
										}
									}
									lb--;
									yolumb.clear();
									maliyet.clear();
									b = 0;
									rb = 1;
									yolArab = true;
									deneme2 = true;
									temizle();
									bAlgoritmasi();
									//System.out.println("çalıştı b");
									//System.out.println(bitisax+" "+bitisby);
								}}





							bitirC = true;
						} else if (bitirC) {
							bitirC = false;
							if(kontrolBaslangicC){
							cAlgoritmasi();
							temizle();}
							if(!kontrolBaslangicC){
								if (!deneme3 || yolumc.size()<1) {

									for (Kare kare : yolumc) {
										if (kare.getX() == cx && kare.getY() == cy) {
											bitiscx = kare.getX();
											bitiscy = kare.getY();
											break;
										}
									}
									lc--;
									yolumc.clear();
									maliyetc.clear();
									rc = 1;
									c=0;
									deneme3=true;
									yolArac = true;
									gizliAltinSayisi=0;
									//	rasgeleGizli();
									temizle();
									cAlgoritmasi();
									//System.out.println("çalıştı c");
								}}

								cDeneme();
							if(!kontrolBaslangicC){
								if (!deneme3 || yolumc.size()<1) {

									for (Kare kare : yolumc) {
										if (kare.getX() == cx && kare.getY() == cy) {
											bitiscx = kare.getX();
											bitiscy = kare.getY();
											break;
										}
									}
									lc--;
									yolumc.clear();
									maliyetc.clear();
									rc = 1;
									c=0;
									deneme3=true;
									yolArac = true;
									gizliAltinSayisi=0;
								//	rasgeleGizli();
									temizle();
									cAlgoritmasi();
									//System.out.println("çalıştı c");
								}}


							bitirD = true;
						} else if (bitirD) {
							bitirD = false;
							if(kontrolBaslangicd){
								temizle();
								dAlgoritmasi();
							}
							//m.out.println(yolumd.get(yolumd.size()-1).getX()+" "+yolumd.get(yolumd.size()-1).getY());
							dDeneme();
							bitirA = true;
						}

					}
				};

				zamanlayicie.schedule(goreve, 0, 3000);

				Timer zamanlayicik = new Timer();
				TimerTask gorevk = new TimerTask() {

					@Override
					public void run() {
						
						if(yolumb.size()>0) {
							if (koordinat[yolumb.get(yolumb.size() - 1).getX()][yolumb.get(yolumb.size() - 1).getY()].gettip() != 2) {
								deneme2 = false;
							}

						}

						if(yolumc.size()>0) {
							if (koordinat[yolumc.get(yolumc.size() - 1).getX()][yolumc.get(yolumc.size() - 1).getY()].gettip() != 2) {
								deneme3 = false;
							}

						}

						if(yolumd.size()>0) {
							if (koordinat[yolumd.get(yolumd.size() - 1).getX()][yolumd.get(yolumd.size() - 1).getY()].gettip() != 2) {
								deneme4 = false;
							}
						}

						if (!kontrola && !kontrolb && !kontrolc && !kontrold) {
							zamanlayicie.cancel();
							aOyuncusu.setPuanA(0);
							bOyuncusu.setPuanB(0);
							cOyuncusu.setPuanC(0);
							dOyuncusu.setPuanD(0);
							JOptionPane.showMessageDialog(frame, "Kasadaki Altınlar \n" +
											"A  =" + aOyuncusu.getPuanA() + "\t" +
											"B  =" + bOyuncusu.getPuanB() + "  " +
											"C  =" + cOyuncusu.getPuanC() + "  " +
											"D  =" + dOyuncusu.getPuanD() + "  " + "\n" +
											"Harcanan Altınlar\n" +
											"A =" + aOyuncusu.getHarcananAltinA() + "  " +
											"B =" + bOyuncusu.getHarcananAltinB() + "  " +
											"C =" + cOyuncusu.getHarcananAltinC() + "  " +
											"D =" + dOyuncusu.getHarcananAltinD() + "  " + "\n" +
											"Toplam Adımlar\n" +
											"A =" + aOyuncusu.getToplamAdimA() + "  " +
											"B =" + bOyuncusu.getToplamAdimB() + "  " +
											"C =" + cOyuncusu.getToplamAdimC() + "  " +
											"D =" + dOyuncusu.getToplamAdimD() + "  " + "\n" +
											"Toplanan Altınlar\n" +
											"A =" + aOyuncusu.getToplananAltinA() + "  " +
											"B =" + bOyuncusu.getToplananAltinB() + "  " +
											"C =" + cOyuncusu.getToplananAltinC() + "  " +
											"D =" + dOyuncusu.getToplananAltinD() + "  ",
									"SONUÇLAR", JOptionPane.QUESTION_MESSAGE);
							zamanlayicik.cancel();
							dosyaYaz();
						}

						if (altinSayisi < 1) {
							zamanlayicie.cancel();
							JOptionPane.showMessageDialog(frame, "Kasadaki Altınlar \n" +
											"A  =" + aOyuncusu.getPuanA() + "\t" +
											"B  =" + bOyuncusu.getPuanB() + "  " +
											"C  =" + cOyuncusu.getPuanC() + "  " +
											"D  =" + dOyuncusu.getPuanD() + "  " + "\n" +
											"Harcanan Altınlar\n" +
											"A =" + aOyuncusu.getHarcananAltinA() + "  " +
											"B =" + bOyuncusu.getHarcananAltinB() + "  " +
											"C =" + cOyuncusu.getHarcananAltinC() + "  " +
											"D =" + dOyuncusu.getHarcananAltinD() + "  " + "\n" +
											"Toplam Adımlar\n" +
											"A =" + tumYolumA.size() + "  " +
											"B =" + tumYolumB.size() + "  " +
											"C =" + tumYolumC.size() + "  " +
											"D =" + tumYolumD.size() + "  " + "\n" +
											"Toplanan Altınlar\n" +
											"A =" + aOyuncusu.getToplananAltinA() + "  " +
											"B =" + bOyuncusu.getToplananAltinB() + "  " +
											"C =" + cOyuncusu.getToplananAltinC() + "  " +
											"D =" + dOyuncusu.getToplananAltinD() + "  ",
									"SONUÇLAR", JOptionPane.INFORMATION_MESSAGE);
							zamanlayicik.cancel();
							dosyaYaz();


						}


					}
				};

				zamanlayicik.schedule(gorevk, 0, 1);
			}

		});
	} //Tasarımsal

	public void dosyaYaz(){
		FileWriter writera = null;
		int t=0,y=0,u=0,o=0;
		try {

			 writera = new FileWriter("a.txt");

			while (t<tumYolumA.size()){
				writera.write((tumYolumA.get(t).getX()+ " - "+tumYolumA.get(t).getY() ) + "\n");
				t++;
			}


		} catch (IOException ex) {
			System.out.println("Dosya açılırken hata oluştu...");
		}
		finally {

			if (writera != null) {
				try {
					writera.close();
				} catch (IOException ex) {
					System.out.println("Dosya Kapatılırken bir hata oluştu...");
				}

			}
		}

		dosyaYazb();
	}
	public void dosyaYazb(){
		FileWriter writerb = null;
		int t=0;
		try {

			writerb = new FileWriter("b.txt");

			while (t<tumYolumB.size()){
				writerb.write((tumYolumB.get(t).getX()+ " - "+tumYolumB.get(t).getY() ) + "\n");
				t++;
			}


		} catch (IOException ex) {
			System.out.println("Dosya açılırken hata oluştu...");
		}
		finally {

			if (writerb != null) {
				try {
					writerb.close();
				} catch (IOException ex) {
					System.out.println("Dosya Kapatılırken bir hata oluştu...");
				}

			}
		}
		dosyaYazc();
	}
	public void dosyaYazc(){
		FileWriter writerc = null;
		int t=0;
		try {

			writerc = new FileWriter("c.txt");

			while (t<tumYolumC.size()){
				writerc.write((tumYolumC.get(t).getX()+ " - "+tumYolumC.get(t).getY() ) + "\n");
				t++;
			}


		} catch (IOException ex) {
			System.out.println("Dosya açılırken hata oluştu...");
		}
		finally {

			if (writerc != null) {
				try {
					writerc.close();
				} catch (IOException ex) {
					System.out.println("Dosya Kapatılırken bir hata oluştu...");
				}

			}
		}
		dosyaYazd();
	}
	public void dosyaYazd(){
		FileWriter writerd = null;
		int t=0;
		try {

			writerd = new FileWriter("d.txt");

			while (t<tumYolumD.size()){
				writerd.write((tumYolumD.get(t).getX()+ " - "+tumYolumD.get(t).getY() ) + "\n");
				t++;
			}


		} catch (IOException ex) {
			System.out.println("Dosya açılırken hata oluştu...");
		}
		finally {

			if (writerd != null) {
				try {
					writerd.close();
				} catch (IOException ex) {
					System.out.println("Dosya Kapatılırken bir hata oluştu...");
				}

			}
		}
	}

	public void yenile() {    //yenile ELEMENTS OF THE GUI
		yogunluk = (kareX * kareY) * altinYuzde;
		KBoyutu = HARITABOYUTU / kareX;
		harita.repaint();
	}


	public void delay() {    //eğer oyunu yavaşlatmak istersek bunu kullanıcaz
		try {
			Thread.sleep(0);
		} catch (Exception e) {
		}
	}

	class koordinat extends JPanel {

		public koordinat() {

		}

		//https://www.tutorialspoint.com/how-can-we-implement-the-paintcomponent-method-of-a-jpanel-in-java
		//temelde nasıl işler bu linkte anlatılmış
		public void paintComponent(Graphics g) {    //Tasarım aracımız
			super.paintComponent(g);
			for (int x = 0; x < kareX; x++) {    //kareleri geziyoruz ve tipine göre boyama yapıyoruz
				for (int y = 0; y < kareY; y++) {
					//bu bölümdeki paintComponent kullanımı https://www.youtube.com/watch?v=_NvD0WzKTC8
					//linkteki videoda anlatımı mevcuttur
					switch (koordinat[x][y].gettip()) {
						case 0:
							g.setColor(Color.GREEN);
							break;
						case 1:
							g.setColor(Color.YELLOW);
							break;
						case 2:
							g.setColor(Color.YELLOW);
							break;
						case 3:
							g.setColor(Color.WHITE);
							break;
						case 4:
							g.setColor(Color.WHITE);
							break;
						case 5:
							g.setColor(Color.pink);
							break;
						case 6:
							g.setColor(Color.ORANGE);
							break;
						case 7:
							g.setColor(Color.RED);
							break;
						case 8:
							g.setColor(Color.MAGENTA);
							break;
						case 9:
							g.setColor(Color.BLUE);
							break;
						case 10:
							g.setColor(Color.pink);
							break;
					}
					g.fillRect(x * KBoyutu, y * HARITABOYUTU / kareY, KBoyutu, HARITABOYUTU / kareY);
					g.setColor(Color.BLACK);
					g.drawRect(x * KBoyutu, y * HARITABOYUTU / kareY, KBoyutu, HARITABOYUTU / kareY);

					if (koordinat[x][y].getDeger() != "-1")
						g.drawString("" + koordinat[x][y].getDeger(), (x * KBoyutu) + (KBoyutu / 2), (y * HARITABOYUTU / kareY) + (HARITABOYUTU / kareY / 2));


				}
			}
		}
	}


	public void aAlgoritmasi() {
		temizle();
		/* Bu fonksiyon A oyuncuna aittir k değişkeni true değeri ile başlar bu true değeri programın ilkkez çalıştığı anlamına gelir
		 * bu sayede oyuncuya ait konum haritanın sol üst köşesine konumlanır (A konumu sol üst verildiği için)
		 * öncelikle puan hesaplama işlemleri çalışır  sonrasında başlangıç kontrolü yapılır
		 * bir öncelik array listi oluşturulur buraya bulunan komşular konulur ve yolAra boolean değişkeni doğru
		 * olduğu sürece bulunan komşular bu listeye atılıp keşfedilip silinir ve yeni komşular eklenir
		 * eğer bu bulunan komşular atına eşit olduğu anda A oyuncusu için en yakın altını yakalamış oluyoruz ve
		 * yolum array listtesine bu altına kadar bir önceki x ve y kordinatlarını kare tipinde tutarak yolum
		 * listesine ekliyoruz */
		aOyuncusu.setPuanA(aOyuncusu.getPuanA() - aHesapla); // Puan Hesaplama işlemi
		aOyuncusu.setHarcananAltinA(aOyuncusu.getHarcananAltinA() + aHesapla);// Puan Hesaplama işlemi

		deneme2 = true;// Timer işlemlerinin çalışması için kontrol değişkeni
		k2 = true;// yolAra değişkenimi ters değere eşitlemek için

		ArrayList<Kare> oncelik = new ArrayList<>();

		while (l < 1) {
			if (k) {
				Kare bulundugumKaremb = koordinat[0][0];
				bulundugumKaremb.setadimlar(0);
				oncelik.add(bulundugumKaremb);
				baslangıcax=0;
				baslangıcay=0;
				//bulundugumKaremb.settip(7);
				bitisax = kareX - 1;
				bitisay = kareY - 1;

			} else {
				//Eğer program ilk kez çalıştırılmıyorsa daha önceden bir bitiş noktası bulunmuş demektir
				// o bitiş noktasını artık başlangıç yapıyorum
				Kare yeni = koordinat[bitisax][bitisay];
				yeni.setadimlar(0);
				baslangıcax = yeni.getX();
				baslangıcay = yeni.getY();
				oncelik.add(yeni);
			}
			yolAra = k2;

			while (yolAra) {

				if (oncelik.size() <= 0) {    //oncelik dizisi 0 ise benim hiç yolum bulunamaz
					// çünkü buraya eklenen karenin komşularını bulmak için tutuyoruz
					// ilk başlangıç noktamı mutlaka while dışında eklemek gerekiyor
					yolAra = false;
					break;
				}
				int adimlar = oncelik.get(0).getadimlar() + 1;//karemin +1 adım uzaklıktaki adımlarımı tutuyorum

				ArrayList<Kare> kesfedilen = komsuKesfet(oncelik.get(0), adimlar);
				//komsu kesfet fonksiyonu ile komsularını bulmak istediğim elemanıma adimlar kadar uzaklığındaki
				// kareyleri keşfediyorum

				if (kesfedilen.size() > 0) {

					oncelik.remove(0);// oncelik verdiğim karemin etrafı keşfedildi artık öncelik değil
					oncelik.addAll(kesfedilen);    //bulunan komşularım (üst-sağ-sol-yukarı) listeye ekliyorum
					//bir sonraki çalışmada ilk elemana öncelik verip aynı işlemleri yapacağız altına bulana kadar

					yenile();// haritayı yeniliyoruz keşfedilen kareleri boyuyoruz görsel olarak görmek için
					delay();
				} else {    //herhangi bir kare keşfedilememişse komşum yoktur ve listemi temizliyorum.
					oncelik.remove(0);
				}
			}

			haritaResetle();
			yenile();
			oncelik.clear();

			k = false;//program bir kez çalıştı artık kapanma zamanı

			l++;

		}

	}

	public ArrayList<Kare> komsuKesfet(Kare bulundugumKarem, int adimlar) {
		//(sağ-sol-aşağı-yukarı) şeklinde komşuyoyu keşfedip kareTipiKesfet fonksiyonuna yolluyoruz
		ArrayList<Kare> kesfedilen = new ArrayList<Kare>();

		int xkesfet = bulundugumKarem.getX() + 1;
		int ykesfet = bulundugumKarem.getY();
		if ((xkesfet > -1 && xkesfet < kareX) && (ykesfet > -1 && ykesfet < kareY)) { //taşmaları önelemek için kontrol harita boyutumun içinde ise
			Kare komsu = koordinat[xkesfet][ykesfet];
			if ((komsu.getadimlar() == -1 || komsu.getadimlar() > adimlar)) {
				kareTipiKesfet(komsu, bulundugumKarem.getX(), bulundugumKarem.getY(), adimlar);
				kesfedilen.add(komsu);
			}
		}
		xkesfet = bulundugumKarem.getX() - 1;
		ykesfet = bulundugumKarem.getY();
		if ((xkesfet > -1 && xkesfet < kareX) && (ykesfet > -1 && ykesfet < kareY)) {//taşmaları önelemek için kontrol harita boyutumun içinde ise
			Kare komsu = koordinat[xkesfet][ykesfet];
			if ((komsu.getadimlar() == -1 || komsu.getadimlar() > adimlar)) {
				kareTipiKesfet(komsu, bulundugumKarem.getX(), bulundugumKarem.getY(), adimlar);
				kesfedilen.add(komsu);
			}
		}
		xkesfet = bulundugumKarem.getX();
		ykesfet = bulundugumKarem.getY() + 1;
		if ((xkesfet > -1 && xkesfet < kareX) && (ykesfet > -1 && ykesfet < kareY)) {
			Kare komsu = koordinat[xkesfet][ykesfet];
			if ((komsu.getadimlar() == -1 || komsu.getadimlar() > adimlar)) {
				kareTipiKesfet(komsu, bulundugumKarem.getX(), bulundugumKarem.getY(), adimlar);
				kesfedilen.add(komsu);
			}
		}
		xkesfet = bulundugumKarem.getX();
		ykesfet = bulundugumKarem.getY() - 1;
		if ((xkesfet > -1 && xkesfet < kareX) && (ykesfet > -1 && ykesfet < kareY)) {//taşmaları önelemek için kontrol harita boyutumun içinde ise
			Kare komsu = koordinat[xkesfet][ykesfet];
			if ((komsu.getadimlar() == -1 || komsu.getadimlar() > adimlar)) {
				kareTipiKesfet(komsu, bulundugumKarem.getX(), bulundugumKarem.getY(), adimlar);
				kesfedilen.add(komsu);
			}

		}
		return kesfedilen; // keşfedilen listeyi döndürüyoruz yeni öncelik dizizmiz eğer kareTipiKesfet donksiyonu altını bulmadıysa 
	}

	public void kareTipiKesfet(Kare bulundugumKarem, int sonX, int sonY, int adimlar) {    //kareTipiKesfediyoruz
		//bulundğumuz kareyi aldık buya gelmeden önceki x ve y kordinaktlarını aldık ve kaç adımda buraya geldğimizi aldık

		if (bulundugumKarem.gettip() != 0) { // eğer başlangıç noktası deilse
			if (bulundugumKarem.gettip() == 2 || bulundugumKarem.gettip() == 1) {
				// altın yada bitiş(program ilk çalışınca krılmaması için en uzak köşe belirlendi) ise kordinatları al 
				bitisax = bulundugumKarem.getX();
				bitisay = bulundugumKarem.getY();
				if(bitisax!=bitisbx && bitisay!=bitisby) {
					bulundugumKarem.settip(1); // altını bulduysam tipini 1(yine sarı renk) yap ki bitişim olduğunu bileyim
				}
				bulundugumKarem.setadimlar(adimlar);
			}

		}

		bulundugumKarem.setsonKare(sonX, sonY);    //bu kareye gelmeden önceki x ve y noktarını bu kare bilgilerine ekle
		bulundugumKarem.setadimlar(adimlar);    //başlangıç noktamdan kaç adımda bu kareye geldğimi bilgisi ekle


		if (bulundugumKarem.gettip() == 1) {
			// bitiş karesi buysa yolum array listime buraya gelene kadar (tutmuş olduğum önceki x ve y noktarı sayesinde)
			// her bir kareyi yolum listeme ekleme fonsiyonu çalıştır
			yolumuOlustur(bulundugumKarem.getsonX(), bulundugumKarem.getsonY(), adimlar, bulundugumKarem);

		}

	}

	public void yolumuOlustur(int sx, int sy, int adimlar, Kare yer) {    //yolumuOlustur

		while (adimlar > 1) {    //başlangıç noktamın adım sayısı sfır o yğzden adım sayısı 1 den büyük olduğu sürece

			Kare bulundugumKarem = koordinat[sx][sy];
			// son x ve y noktarımda kare oluştur
			sx = bulundugumKarem.getsonX();
			sy = bulundugumKarem.getsonY();
			//bu oluşan kareyi yoluma at
			yolum.add(bulundugumKarem);
			adimlar--;
		}
		// yol geri geri gittiğimiz için tersine eklendi ters çevir ki normal bir gidiş olsun
		Collections.reverse(yolum);
		//düzgün hale gelen yolun en sonuna yer karesini ekle bu kare altını buludğum nokta bunu sona ekledim ki
		// altın karesi yolumun en sonundaki kare olacak
		yolum.add(yer);

		yolAra = false;// artık fonksiyon false olucak ve krılıcak yolumu buldum sıra timer ile bu yolu ilerletip boyamakta
	}

	public void bAlgoritmasi() {
		//haritaResetle();
		/* nasıl çalışır ?
		 * bu algoritma b oyuncusu için tüm kareleri ziyaret eder ve altın olan kareleri bir listeye atar(maliyet array list)
		 * oluşan listem puan değerine göre sıralanır büyükten küçüğe en karlı kare ilk sıray geleceği için benim
		 * gitmem gereken noktam orası anlamı çıkar b yol çiz fonsiyonu çalıştırılır ve yine aynı şekilde on noktayı bulana kadar
		 * kareleri gezer ve en karlı kareyi bulunca yolumb dizisi oluşur bu dizi çizdirme işlemlerinde kullanılır */
		bOyuncusu.setPuanB(bOyuncusu.getPuanB() - bHesapla);
		bOyuncusu.setHarcananAltinB(bOyuncusu.getHarcananAltinB() + bHesapla);

		k2b = true;


		ArrayList<Kare> oncelikb = new ArrayList<>();

		while (lb < 1) {
			if (kontrolBaslangicB) {
				Kare bulundugumKaremc = koordinat[kareX - 1][0];
				baslangicbx = kareX - 1;
				baslangicby = 0;
				bulundugumKaremc.setadimlar(0);
				oncelikb.add(bulundugumKaremc);
				bitisbx = 0;
				bitisby = kareY - 1;
				kontrolBaslangicB=false;

			} else{
				yolumb.clear();
				Kare bulundugumKaremc = koordinat[bitisbx][bitisby];
				baslangicbx = bulundugumKaremc.getX();
				baslangicby = bulundugumKaremc.getY();
				bulundugumKaremc.setadimlar(0);
				oncelikb.add(bulundugumKaremc);
			}

			yolArab = k2b;

			while (yolArab) {

				if (oncelikb.size() <= 0) {
					yolArab = false;
					break;
				}
				int adimlar = oncelikb.get(0).getadimlar() + 1;

				ArrayList<Kare> kesfedilen = komsuKesfetb(oncelikb.get(0), adimlar);

				if (kesfedilen.size() > 0) {

					oncelikb.remove(0);
					oncelikb.addAll(kesfedilen);

					yenile();
					delay();
				} else {
					oncelikb.remove(0);
				}

			}

			haritaResetle2();
			yenile();

			oncelikb.clear();
			Collections.sort(maliyet, new siralaMaliyet()); //maliyet dizimi puana göre küçükten büyüğe sıraladım
			Collections.reverse(maliyet); // diziyi ters çevirdim en büyük puanlı karem başa geldi

			try {
				bitisbx = maliyet.get(0).getX();
				bitisby = maliyet.get(0).getY();
				// en büyük puanlı kareme ait x ve y noktalarını b yol çiz için
				//b için bitiş noktalarına atıyorum
				yolArab2=true;
				bYolCiz();
			} catch (Exception p) {
				//	System.out.println(p);
			}


			maliyet.clear();// sonraki hesaplama için listeleri temizliyorum
			yolArab = true;


			lb++;
		}
	}

	public ArrayList<Kare> komsuKesfetb(Kare bulundugumKarem, int adimlar) {

		ArrayList<Kare> kesfedilenb = new ArrayList<>();
		//Sağ tarafındaki kareyi keşfet
		int xkesfet = bulundugumKarem.getX() + 1;
		int ykesfet = bulundugumKarem.getY();
		if ((xkesfet > -1 && xkesfet < kareX) && (ykesfet > -1 && ykesfet < kareY)) {
			Kare komsu = koordinat[xkesfet][ykesfet];
			if ((komsu.getadimlar() == -1 || komsu.getadimlar() > adimlar)) {
				komsu.setsonKare(bulundugumKarem);
				kareTipiKesfetb(komsu, bulundugumKarem.getX(), bulundugumKarem.getY(), adimlar);
				kesfedilenb.add(komsu);
			}

		}
		//Sol tarafındaki kareyi keşfet
		xkesfet = bulundugumKarem.getX() - 1;
		ykesfet = bulundugumKarem.getY();
		if ((xkesfet > -1 && xkesfet < kareX) && (ykesfet > -1 && ykesfet < kareY)) {
			Kare komsu = koordinat[xkesfet][ykesfet];
			if ((komsu.getadimlar() == -1 || komsu.getadimlar() > adimlar)) {
				komsu.setsonKare(bulundugumKarem);
				kareTipiKesfetb(komsu, bulundugumKarem.getX(), bulundugumKarem.getY(), adimlar);
				kesfedilenb.add(komsu);
			}

		}
		//alt tarafındaki kareyi keşfet
		xkesfet = bulundugumKarem.getX();
		ykesfet = bulundugumKarem.getY() + 1;
		if ((xkesfet > -1 && xkesfet < kareX) && (ykesfet > -1 && ykesfet < kareY)) {
			Kare komsu = koordinat[xkesfet][ykesfet];
			if ((komsu.getadimlar() == -1 || komsu.getadimlar() > adimlar)) {
				komsu.setsonKare(bulundugumKarem);
				kareTipiKesfetb(komsu, bulundugumKarem.getX(), bulundugumKarem.getY(), adimlar);
				kesfedilenb.add(komsu);
			}

		}
		//üst tarafındaki kareyi keşfet
		xkesfet = bulundugumKarem.getX();
		ykesfet = bulundugumKarem.getY() - 1;
		if ((xkesfet > -1 && xkesfet < kareX) && (ykesfet > -1 && ykesfet < kareY)) {
			Kare komsu = koordinat[xkesfet][ykesfet];
			if ((komsu.getadimlar() == -1 || komsu.getadimlar() > adimlar)) {
				komsu.setsonKare(bulundugumKarem);
				kareTipiKesfetb(komsu, bulundugumKarem.getX(), bulundugumKarem.getY(), adimlar);
				kesfedilenb.add(komsu);
			}
		}
		return kesfedilenb;
	}

	public void kareTipiKesfetb(Kare bulundugumKarem, int sonX, int sonY, int adimlar) {    //kareTipiKesfet A Kare
		float deger;
		if (bulundugumKarem.gettip() != 0) {//CHECK THAT THE Kare IS NOT THE START
			if (bulundugumKarem.gettip() == 2 || bulundugumKarem.gettip() == 1) {

				deger = Float.parseFloat(bulundugumKarem.getDeger()) - ((float) adimlar / adimSayisi) * bHamlePuani;
				//o noktaya kadar geldiğim kare sayımı 3 bölüyorum
				//çünkü 3 adımda bir hamlePuanım kadar puanım eksilecek
				//bu kare altınsa bu puan kaybımı karemim değerinden çıkartıyorum.

				bulundugumKarem.setHamle(deger);
				bulundugumKarem.setadimlar(adimlar);
				maliyet.add(bulundugumKarem);

			} else {
				if (bulundugumKarem.gettip() != 7
						&& bulundugumKarem.gettip() != 6
						&& bulundugumKarem.gettip() != 8
						&& bulundugumKarem.gettip() != 9
						&& bulundugumKarem.gettip() != 10)
					bulundugumKarem.settip(4);
			}

		}
		bulundugumKarem.setsonKare(sonX, sonY);
		bulundugumKarem.setadimlar(adimlar);


		if (bulundugumKarem.gettip() == 1) {
			yolAra = false;
		}

	}

	public void bYolCiz() {
		temizle();
		/* bAlgoritması() fonksiyonu ile bitiş noktamı buldum artık başlangıç ve bitişim belli olduğu için
		 * bitiş noktamı yeniden arıyorum bu sefer yol çizmek için  !!dikkat  bAlgoritması() ile tüm noktalara
		 * olan uzaklıkları buldum ve en çok puanı alabileceğim kareyi maliyet dizimin ilk yerine yerleştirmiştim
		 * şimdi bu ilk yerdeki noktamı bitişe atadığım için bu fonksiyonda bu noktayı yeniden arayıp yol
		 * dizimi çıkartmak için yapıyorum*/
		int m = 0;
		ArrayList<Kare> oncelikb2 = new ArrayList<>();
		Kare bulundugumKaremc = koordinat[baslangicbx][baslangicby];
		//bulundugumKaremc.settip(8);
		bulundugumKaremc.setadimlar(0);
		yolArab2 = true;
		oncelikb2.add(bulundugumKaremc);

		while (m < 1) {
			while (yolArab2) {

				if (oncelikb2.size() <= 0) {
					yolArab2 = false;
					break;
				}
				int adimlar = oncelikb2.get(0).getadimlar() + 1;
				ArrayList<Kare> kesfedilen = komsuKesfetYolCizb(oncelikb2.get(0), adimlar);

				if (kesfedilen.size() > 0) {

					oncelikb2.remove(0);
					oncelikb2.addAll(kesfedilen);

					yenile();
					delay();
				} else {
					oncelikb2.remove(0);
				}
			}

			haritaResetle();
			yenile();

			oncelikb2.clear();
			m++;
		}

	}

	public ArrayList<Kare> komsuKesfetYolCizb(Kare bulundugumKarem, int adimlar) {
		ArrayList<Kare> kesfedilenb = new ArrayList<>();
		kesfedilenb.clear();


		int xkesfet = bulundugumKarem.getX() + 1;
		int ykesfet = bulundugumKarem.getY();
		if ((xkesfet > -1 && xkesfet < kareX) && (ykesfet > -1 && ykesfet < kareY)) {
			Kare komsu = koordinat[xkesfet][ykesfet];
			if ((komsu.getadimlar() == -1 || komsu.getadimlar() > adimlar)) {
				komsu.setsonKare(bulundugumKarem);
				kareTipiKesfetYolCizb(komsu, bulundugumKarem.getX(), bulundugumKarem.getY(), adimlar);
				kesfedilenb.add(komsu);
			}

		}
		xkesfet = bulundugumKarem.getX() - 1;
		ykesfet = bulundugumKarem.getY();
		if ((xkesfet > -1 && xkesfet < kareX) && (ykesfet > -1 && ykesfet < kareY)) {
			Kare komsu = koordinat[xkesfet][ykesfet];
			if ((komsu.getadimlar() == -1 || komsu.getadimlar() > adimlar)) {
				komsu.setsonKare(bulundugumKarem);
				kareTipiKesfetYolCizb(komsu, bulundugumKarem.getX(), bulundugumKarem.getY(), adimlar);
				kesfedilenb.add(komsu);
			}

		}
		xkesfet = bulundugumKarem.getX();
		ykesfet = bulundugumKarem.getY() + 1;
		if ((xkesfet > -1 && xkesfet < kareX) && (ykesfet > -1 && ykesfet < kareY)) {
			Kare komsu = koordinat[xkesfet][ykesfet];
			if ((komsu.getadimlar() == -1 || komsu.getadimlar() > adimlar)) {
				komsu.setsonKare(bulundugumKarem);
				kareTipiKesfetYolCizb(komsu, bulundugumKarem.getX(), bulundugumKarem.getY(), adimlar);
				kesfedilenb.add(komsu);
			}

		}
		xkesfet = bulundugumKarem.getX();
		ykesfet = bulundugumKarem.getY() - 1;
		if ((xkesfet > -1 && xkesfet < kareX) && (ykesfet > -1 && ykesfet < kareY)) {
			Kare komsu = koordinat[xkesfet][ykesfet];
			if ((komsu.getadimlar() == -1 || komsu.getadimlar() > adimlar)) {
				komsu.setsonKare(bulundugumKarem);
				kareTipiKesfetYolCizb(komsu, bulundugumKarem.getX(), bulundugumKarem.getY(), adimlar);
				kesfedilenb.add(komsu);
			}

		}

		return kesfedilenb;
	}

	public void kareTipiKesfetYolCizb(Kare bulundugumKarem, int sonX, int sonY, int adimlar) {

		if (bulundugumKarem.gettip() != 0 && bulundugumKarem.gettip() != 2
				&& bulundugumKarem.gettip() != 6
				&& bulundugumKarem.gettip() != 7
				&& bulundugumKarem.gettip() != 8
				&& bulundugumKarem.gettip() != 9
				&& bulundugumKarem.gettip() != 10) {//CHECK THAT THE Kare IS NOT THE START
			// eğer kare tipi sadece beyaz yani 3 numara ise bunun tipini keşfedilmiş yap diğer türler ise bu if
			//dışında sadece adım sayını ve sonx ve sony nokta bilgileri ver
			bulundugumKarem.settip(4);

		}
		bulundugumKarem.setsonKare(sonX, sonY);
		bulundugumKarem.setadimlar(adimlar);

		if (bulundugumKarem == maliyet.get(0)) {
			yolumuOlusturb(sonX, sonY, adimlar, bulundugumKarem);
		}

	}

	public void yolumuOlusturb(int sx, int sy, int adimlar, Kare yer) {

		while (adimlar > 1) {

			Kare bulundugumKarem = koordinat[sx][sy];
			sx = bulundugumKarem.getsonX();
			sy = bulundugumKarem.getsonY();

			yolumb.add(bulundugumKarem);
			adimlar--;
		}
		yolArab2 = false;
		Collections.reverse(yolumb);
		yolumb.add(yer);

	}


	public void cAlgoritmasi() {
		//haritaResetle();
		/* nasıl çalışır ?
		 * bu algoritma c oyuncusu için tüm kareleri ziyaret eder ve altın olan kareleri bir listeye atar(maliyet array list)
		 * oluşan listem puan değerine göre sıralanır büyükten küçüğe en karlı kare ilk sıray geleceği için benim
		 * gitmem gereken noktam orası anlamı çıkar c yol çiz fonsiyonu çalıştırılır ve yine aynı şekilde on noktayı bulana kadar
		 * kareleri gezer ve en karlı kareyi bulunca yolumb dizisi oluşur bu dizi çizdirme işlemlerinde kullanılır */
		cOyuncusu.setPuanC(cOyuncusu.getPuanC() - cHesapla);
		cOyuncusu.setHarcananAltinC(cOyuncusu.getHarcananAltinC() + cHesapla);

		k2c = true;
		deneme3 = true;
		ArrayList<Kare> oncelikc = new ArrayList<>();

		while (lc < 1) {
			if (kontrolBaslangicC) {
				Kare bulundugumKaremc = koordinat[kareX - 1][kareY - 1];
				baslangiccx = kareX - 1;
				baslangiccy = kareY - 1;
				bulundugumKaremc.setadimlar(0);
				oncelikc.add(bulundugumKaremc);
				bitiscx = 0;
				bitiscy = 0;

			} else {
				yolumc.clear();
				Kare bulundugumKaremc = koordinat[bitiscx][bitiscy];
				baslangiccx = bulundugumKaremc.getX();
				baslangiccy = bulundugumKaremc.getY();
				bulundugumKaremc.setadimlar(0);
				oncelikc.add(bulundugumKaremc);
			}

			yolArac = k2c;

			while (yolArac) {

				if (oncelikc.size() <= 0) {
					yolArac = false;
					break;
				}
				int adimlar = oncelikc.get(0).getadimlar() + 1;

				ArrayList<Kare> kesfedilen = komsuKesfetc(oncelikc.get(0), adimlar);

				if (kesfedilen.size() > 0) {

					oncelikc.remove(0);
					oncelikc.addAll(kesfedilen);

					yenile();
					delay();
				} else {
					oncelikc.remove(0);
				}
			}

			haritaResetle2();
			yenile();


			oncelikc.clear();
			Collections.sort(maliyetc, new siralaMaliyet());
			Collections.reverse(maliyetc);
			try {
				bitiscx = maliyetc.get(0).getX();
				bitiscy = maliyetc.get(0).getY();
				yolArac2=true;
				cYolCiz();
			} catch (Exception p) {
				//	System.out.println(p);
			}


			maliyetc.clear();
			yolArac = true;

			kontrolBaslangicC = false;

			lc++;

		}


	}

	public ArrayList<Kare> komsuKesfetc(Kare bulundugumKarem, int adimlar) {
		ArrayList<Kare> kesfedilenc = new ArrayList<>();


		int xkesfet = bulundugumKarem.getX() + 1;
		int ykesfet = bulundugumKarem.getY();
		if ((xkesfet > -1 && xkesfet < kareX) && (ykesfet > -1 && ykesfet < kareY)) {
			Kare komsu = koordinat[xkesfet][ykesfet];
			if ((komsu.getadimlar() == -1 || komsu.getadimlar() > adimlar)) {
				komsu.setsonKare(bulundugumKarem);
				kareTipiKesfetc(komsu, bulundugumKarem.getX(), bulundugumKarem.getY(), adimlar);
				kesfedilenc.add(komsu);
			}

		}
		xkesfet = bulundugumKarem.getX() - 1;
		ykesfet = bulundugumKarem.getY();
		if ((xkesfet > -1 && xkesfet < kareX) && (ykesfet > -1 && ykesfet < kareY)) {
			Kare komsu = koordinat[xkesfet][ykesfet];
			if ((komsu.getadimlar() == -1 || komsu.getadimlar() > adimlar)) {
				komsu.setsonKare(bulundugumKarem);
				kareTipiKesfetc(komsu, bulundugumKarem.getX(), bulundugumKarem.getY(), adimlar);
				kesfedilenc.add(komsu);
			}

		}
		xkesfet = bulundugumKarem.getX();
		ykesfet = bulundugumKarem.getY() + 1;
		if ((xkesfet > -1 && xkesfet < kareX) && (ykesfet > -1 && ykesfet < kareY)) {
			Kare komsu = koordinat[xkesfet][ykesfet];
			if ((komsu.getadimlar() == -1 || komsu.getadimlar() > adimlar)) {
				komsu.setsonKare(bulundugumKarem);
				kareTipiKesfetc(komsu, bulundugumKarem.getX(), bulundugumKarem.getY(), adimlar);
				kesfedilenc.add(komsu);
			}

		}
		xkesfet = bulundugumKarem.getX();
		ykesfet = bulundugumKarem.getY() - 1;
		if ((xkesfet > -1 && xkesfet < kareX) && (ykesfet > -1 && ykesfet < kareY)) {
			Kare komsu = koordinat[xkesfet][ykesfet];
			if ((komsu.getadimlar() == -1 || komsu.getadimlar() > adimlar)) {
				komsu.setsonKare(bulundugumKarem);
				kareTipiKesfetc(komsu, bulundugumKarem.getX(), bulundugumKarem.getY(), adimlar);
				kesfedilenc.add(komsu);
			}

		}

		return kesfedilenc;
	}

	public void kareTipiKesfetc(Kare bulundugumKarem, int sonX, int sonY, int adimlar) {
		float deger;
		if(bulundugumKarem.gettip()==6 && gizliAltinSayisi<2){
			gizliAltinSayisi++;
			bulundugumKarem.settip(2);
		}
		if (bulundugumKarem.gettip() != 0) {
			if (bulundugumKarem.gettip() == 2 || bulundugumKarem.gettip() == 1) {

				deger = bulundugumKarem.getAltin() - ((float) adimlar / adimSayisi) * cHamlePuani;



				bulundugumKarem.setHamle(deger);
				bulundugumKarem.setadimlar(adimlar);
				maliyetc.add(bulundugumKarem);

			} else {
				if (bulundugumKarem.gettip() != 7
						&& bulundugumKarem.gettip() != 6
						&& bulundugumKarem.gettip() != 8
						&& bulundugumKarem.gettip() != 9
						&& bulundugumKarem.gettip() != 10)
					bulundugumKarem.settip(4);
			}

		}



		bulundugumKarem.setsonKare(sonX, sonY);
		bulundugumKarem.setadimlar(adimlar);



		if (bulundugumKarem.gettip() == 1) {
			yolArac = false;
		}

	}

	public void cYolCiz() {
		temizle();
		/* cAlgoritması() fonksiyonu ile bitiş noktamı buldum artık başlangıç ve bitişim belli olduğu için
		 * bitiş noktamı yeniden arıyorum bu sefer yol çizmek için  !!dikkat  cAlgoritması() ile tüm noktalara
		 * olan uzaklıkları buldum ve en çok puanı alabileceğim kareyi maliyet dizimin ilk yerine yerleştirmiştim
		 * şimdi bu ilk yerdeki noktamı bitişe atadığım için bu fonksiyonda bu noktayı yeniden arayıp yol
		 * dizimi çıkartmak için yapıyorum*/
		int m = 0;
		ArrayList<Kare> oncelikc = new ArrayList<>();    //CREATE A oncelik QUE
		Kare bulundugumKaremc = koordinat[baslangiccx][baslangiccy];
		bulundugumKaremc.setadimlar(0);
		yolArac2=true;
		oncelikc.add(bulundugumKaremc);

		while (m < 1) {

			while (yolArac2) {

				if (oncelikc.size() <= 0) {    //IF THE QUE IS 0 THEN NO PATH CAN BE FOUND
					yolArac2 = false;
					break;
				}
				int adimlar = oncelikc.get(0).getadimlar() + 1;//INCREMENT THE adimlar VARIABLE

				ArrayList<Kare> kesfedilen = komsuKesfetYolCizc(oncelikc.get(0), adimlar);//CREATE AN ARRAYLIST OF KareS THAT WERE kesfedilen

				if (kesfedilen.size() > 0) {

					oncelikc.remove(0);    //REMOVE THE Kare FROM THE QUE
					oncelikc.addAll(kesfedilen);    //ADD ALL THE NEW KareS TO THE QUE

					yenile();
					delay();
				} else {    //IF NO KareS WERE kesfedilen THEN JUST REMOVE THE Kare FROM THE QUE
					oncelikc.remove(0);
				}
			}

			haritaResetle();
			yenile();
			oncelikc.clear();

			m++;
		}

	}

	public ArrayList<Kare> komsuKesfetYolCizc(Kare bulundugumKarem, int adimlar) {

		ArrayList<Kare> kesfedilenc = new ArrayList<>();
		int xkesfet = bulundugumKarem.getX() + 1;
		int ykesfet = bulundugumKarem.getY();
		if ((xkesfet > -1 && xkesfet < kareX) && (ykesfet > -1 && ykesfet < kareY)) {
			Kare komsu = koordinat[xkesfet][ykesfet];
			if ((komsu.getadimlar() == -1 || komsu.getadimlar() > adimlar)) {
				komsu.setsonKare(bulundugumKarem);
				kareTipiKesfetYolCizC(komsu, bulundugumKarem.getX(), bulundugumKarem.getY(), adimlar);
				kesfedilenc.add(komsu);
			}
		}
		xkesfet = bulundugumKarem.getX() - 1;
		ykesfet = bulundugumKarem.getY();
		if ((xkesfet > -1 && xkesfet < kareX) && (ykesfet > -1 && ykesfet < kareY)) {
			Kare komsu = koordinat[xkesfet][ykesfet];
			if ((komsu.getadimlar() == -1 || komsu.getadimlar() > adimlar)) {
				komsu.setsonKare(bulundugumKarem);
				kareTipiKesfetYolCizC(komsu, bulundugumKarem.getX(), bulundugumKarem.getY(), adimlar);
				kesfedilenc.add(komsu);
			}
		}
		xkesfet = bulundugumKarem.getX();
		ykesfet = bulundugumKarem.getY() + 1;
		if ((xkesfet > -1 && xkesfet < kareX) && (ykesfet > -1 && ykesfet < kareY)) {
			Kare komsu = koordinat[xkesfet][ykesfet];
			if ((komsu.getadimlar() == -1 || komsu.getadimlar() > adimlar)) {
				komsu.setsonKare(bulundugumKarem);
				kareTipiKesfetYolCizC(komsu, bulundugumKarem.getX(), bulundugumKarem.getY(), adimlar);
				kesfedilenc.add(komsu);
			}
		}
		xkesfet = bulundugumKarem.getX();
		ykesfet = bulundugumKarem.getY() - 1;
		if ((xkesfet > -1 && xkesfet < kareX) && (ykesfet > -1 && ykesfet < kareY)) {
			Kare komsu = koordinat[xkesfet][ykesfet];
			if ((komsu.getadimlar() == -1 || komsu.getadimlar() > adimlar)) {
				komsu.setsonKare(bulundugumKarem);
				kareTipiKesfetYolCizC(komsu, bulundugumKarem.getX(), bulundugumKarem.getY(), adimlar);
				kesfedilenc.add(komsu);
			}
		}

		return kesfedilenc;
	}

	public void kareTipiKesfetYolCizC(Kare bulundugumKarem, int sonX, int sonY, int adimlar) {    //kareTipiKesfet A Kare

		if (bulundugumKarem.gettip() != 0
				&& bulundugumKarem.gettip() != 2
				&& bulundugumKarem.gettip() != 7
				&& bulundugumKarem.gettip() != 6
				&& bulundugumKarem.gettip() != 8
				&& bulundugumKarem.gettip() != 9
				&& bulundugumKarem.gettip() != 10) {//CHECK THAT THE Kare IS NOT THE START

			bulundugumKarem.settip(4);
		}


		bulundugumKarem.setsonKare(sonX, sonY);
		bulundugumKarem.setadimlar(adimlar);


		if (bulundugumKarem == maliyetc.get(0)) {
			yolumuOlusturc(sonX, sonY, adimlar, bulundugumKarem);
		}

	}

	public void yolumuOlusturc(int sx, int sy, int adimlar, Kare yer) {

		while (adimlar > 1) {

			Kare bulundugumKarem = koordinat[sx][sy];
			sx = bulundugumKarem.getsonX();
			sy = bulundugumKarem.getsonY();

			yolumc.add(bulundugumKarem);
			adimlar--;
		}
		yolArac2 = false;
		Collections.reverse(yolumc);
		yolumc.add(yer);

	}


	public void dAlgoritmasi() {
		//haritaResetle();
		/* nasıl çalışır ?
		 * bu algoritma d oyuncusu için tüm kareleri ziyaret eder ve altın olan kareleri bir listeye atar(maliyet array list)
		 * oluşan listem puan değerine göre sıralanır büyükten küçüğe en karlı kare ilk sıray geleceği için benim
		 * gitmem gereken noktam orası anlamı çıkar d yol çiz fonsiyonu çalıştırılır ve yine aynı şekilde on noktayı bulana kadar
		 * kareleri gezer ve en karlı kareyi bulunca yolumb dizisi oluşur bu dizi çizdirme işlemlerinde kullanılır */

		dOyuncusu.setPuanD(dOyuncusu.getPuanD() - dHesapla);
		dOyuncusu.setHarcananAltinD(dOyuncusu.getHarcananAltinD() + dHesapla);

		k2d = true;
		deneme4 = true;

		ArrayList<Kare> oncelikd = new ArrayList<>();
		while (ld < 1) {
			if (kontrolBaslangicd) {
				Kare bulundugumKaremc = koordinat[0][kareY - 1];
				baslangicdx = 0;
				baslangicdy = kareY - 1;
				bulundugumKaremc.setadimlar(0);
				oncelikd.add(bulundugumKaremc);
				bitisdx = kareX - 1;
				bitisdy = 0;
			} else {
				yolumd.clear();
				Kare bulundugumKaremc = koordinat[bitisdx][bitisdy];
				baslangicdx = bulundugumKaremc.getX();
				baslangicdy = bulundugumKaremc.getY();
				bulundugumKaremc.setadimlar(0);
				oncelikd.add(bulundugumKaremc);

			}


			yolArad = k2d;

			while (yolArad ) {

				if (oncelikd.size() <= 0) {
					yolArad = false;
					break;
				}
				int adimlar = oncelikd.get(0).getadimlar() + 1;

				ArrayList<Kare> kesfedilen = komsuKesfetd(oncelikd.get(0), adimlar);

				if (kesfedilen.size() > 0) {

					oncelikd.remove(0);
					oncelikd.addAll(kesfedilen);

					yenile();
					delay();
				} else {
					oncelikd.remove(0);
				}

			}

			haritaResetle2();
			yenile();

			oncelikd.clear();
			Collections.sort(maliyetd, new siralaMaliyet());
			Collections.reverse(maliyetd);

			if(dIslem){
				bitisdx = maliyetd.get(0).getX();
				bitisdy = maliyetd.get(0).getY();
				if(bitisax==bitisdx && bitisay==bitisdy){
					bitisdx = maliyetd.get(1).getX();
					bitisdy = maliyetd.get(1).getY();
				}else if((bitiscx==bitisdx && bitiscy==bitisdy)){
					bitisdx = maliyetd.get(1).getX();
					bitisdy = maliyetd.get(1).getY();
				}else if((bitisbx==bitisdx && bitisby==bitisdy)){
					bitisdx = maliyetd.get(1).getX();
					bitisdy = maliyetd.get(1).getY();
				}

			}else{
				dIslem=true;
			}

			yolArad2=true;
			dYolCiz();

			maliyetd.clear();
			yolArad = true;

			kontrolBaslangicd = false;

			ld++;

		}

	}

	public ArrayList<Kare> komsuKesfetd(Kare bulundugumKarem, int adimlar) {

		ArrayList<Kare> kesfedilen = new ArrayList<>();

		int xkesfet = bulundugumKarem.getX() + 1;
		int ykesfet = bulundugumKarem.getY();
		if ((xkesfet > -1 && xkesfet < kareX) && (ykesfet > -1 && ykesfet < kareY)) {
			Kare komsu = koordinat[xkesfet][ykesfet];
			if ((komsu.getadimlar() == -1 || komsu.getadimlar() > adimlar)) {
				komsu.setsonKare(bulundugumKarem);
				kesfedilend(komsu, bulundugumKarem.getX(), bulundugumKarem.getY(), adimlar);
				kesfedilen.add(komsu);
			}

		}
		xkesfet = bulundugumKarem.getX() - 1;
		ykesfet = bulundugumKarem.getY();
		if ((xkesfet > -1 && xkesfet < kareX) && (ykesfet > -1 && ykesfet < kareY)) {
			Kare komsu = koordinat[xkesfet][ykesfet];
			if ((komsu.getadimlar() == -1 || komsu.getadimlar() > adimlar)) {
				komsu.setsonKare(bulundugumKarem);
				kesfedilen.add(komsu);
			}

		}
		xkesfet = bulundugumKarem.getX();
		ykesfet = bulundugumKarem.getY() + 1;
		if ((xkesfet > -1 && xkesfet < kareX) && (ykesfet > -1 && ykesfet < kareY)) {
			Kare komsu = koordinat[xkesfet][ykesfet];
			if ((komsu.getadimlar() == -1 || komsu.getadimlar() > adimlar)) {
				komsu.setsonKare(bulundugumKarem);
				kesfedilend(komsu, bulundugumKarem.getX(), bulundugumKarem.getY(), adimlar);
				kesfedilen.add(komsu);
			}
		}
		xkesfet = bulundugumKarem.getX();
		ykesfet = bulundugumKarem.getY() - 1;
		if ((xkesfet > -1 && xkesfet < kareX) && (ykesfet > -1 && ykesfet < kareY)) {
			Kare komsu = koordinat[xkesfet][ykesfet];
			if ((komsu.getadimlar() == -1 || komsu.getadimlar() > adimlar)) {
				komsu.setsonKare(bulundugumKarem);
				kesfedilend(komsu, bulundugumKarem.getX(), bulundugumKarem.getY(), adimlar);
				kesfedilen.add(komsu);
			}
		}

		return kesfedilen;
	}

	public void kesfedilend(Kare bulundugumKarem, int sonX, int sonY, int adimlar) {
		float deger;
		if (bulundugumKarem.gettip() != 0) {
			if (bulundugumKarem.gettip() == 2 ) {

				deger = bulundugumKarem.getAltin() - ((float) adimlar / adimSayisi) * dHamlePuani;

				bulundugumKarem.setHamle(deger);
				bulundugumKarem.setadimlar(adimlar);
				if(bulundugumKarem.getX()==bitisax && bulundugumKarem.getY()==bitisay){
					int uzaklikAd = Math.abs(baslangicdx-bitisax) + Math.abs(baslangicdy-bitisay);
					//System.out.println("uzakık a"+uzaklikAd);
					if(yolum.size()>uzaklikAd){
						if(yolum.size()-uzaklikAd>=3){
						bitisdx=yolum.get(yolum.size()-1).getX();
						bitisdy=yolum.get(yolum.size()-1).getY();
						dIslem=false;
						yolArad=false;

					}}
				}else if(bulundugumKarem.getX()==bitisbx && bulundugumKarem.getY()==bitisby) {
					int uzaklikBd = Math.abs(baslangicdx - bitisbx) + Math.abs(baslangicdy - bitisby);
					if (yolumb.size() > uzaklikBd){
						if(yolumb.size()-uzaklikBd>=3) {
						bitisdx = yolumb.get(yolumb.size() - 1).getX();
						bitisdy = yolumb.get(yolumb.size() - 1).getY();
						dIslem = false;
						yolArad = false;
					}}

				}else if (bulundugumKarem.getX() == bitiscx && bulundugumKarem.getY() == bitiscy) {
						int uzaklikCd = Math.abs(baslangicdx - bitiscx) + Math.abs(baslangicdy - bitiscy);
						if (yolumc.size() > uzaklikCd){
							if	((yolumc.size()-uzaklikCd>=3)) {
							bitisdx = yolumc.get(yolumc.size() - 1).getX();
							bitisdy = yolumc.get(yolumc.size() - 1).getY();
							dIslem = false;
							yolArad = false;

						}}
					}

				maliyetd.add(bulundugumKarem);

			} else {
				if (bulundugumKarem.gettip() != 5
						&& bulundugumKarem.gettip() != 6
						&& bulundugumKarem.gettip() != 7
						&& bulundugumKarem.gettip() != 8
						&& bulundugumKarem.gettip() != 9
						&& bulundugumKarem.gettip() != 10)
					bulundugumKarem.settip(4);
			}

		}

		bulundugumKarem.setsonKare(sonX, sonY);
		bulundugumKarem.setadimlar(adimlar);


		if (bulundugumKarem.gettip() == 1) {
			yolArad = false;
		}

	}

	public void dYolCiz() {
		temizle();
		/* dAlgoritması() fonksiyonu ile bitiş noktamı buldum artık başlangıç ve bitişim belli olduğu için
		 * bitiş noktamı yeniden arıyorum bu sefer yol çizmek için  !!dikkat  dAlgoritması() ile tüm noktalara
		 * olan uzaklıkları buldum ve en çok puanı alabileceğim kareyi maliyet dizimin ilk yerine yerleştirmiştim
		 * şimdi bu ilk yerdeki noktamı bitişe atadığım için bu fonksiyonda bu noktayı yeniden arayıp yol
		 * dizimi çıkartmak için yapıyorum*/
		int m = 0;
		ArrayList<Kare> oncelikd = new ArrayList<>();
		Kare bulundugumKaremc = koordinat[baslangicdx][baslangicdy];
		bulundugumKaremc.setadimlar(0);
		yolArad2 = true;
		oncelikd.add(bulundugumKaremc);

		while (m < 1) {

			while (yolArad2) {

				if (oncelikd.size() <= 0) {
					yolArad2 = false;
					break;
				}
				int adimlar = oncelikd.get(0).getadimlar() + 1;
				ArrayList<Kare> kesfedilen = komsuKesfetYolCizd(oncelikd.get(0), adimlar);

				if (kesfedilen.size() > 0) {

					oncelikd.remove(0);
					oncelikd.addAll(kesfedilen);
					yenile();
					delay();
				} else {
					oncelikd.remove(0);
				}
			}
			haritaResetle();
			yenile();
			oncelikd.clear();

			m++;
		}

	}

	public ArrayList<Kare> komsuKesfetYolCizd(Kare bulundugumKarem, int adimlar) {

		ArrayList<Kare> kesfedilenc = new ArrayList<>();

		int xkesfet = bulundugumKarem.getX() + 1;
		int ykesfet = bulundugumKarem.getY();
		if ((xkesfet > -1 && xkesfet < kareX) && (ykesfet > -1 && ykesfet < kareY)) {
			Kare komsu = koordinat[xkesfet][ykesfet];
			if ((komsu.getadimlar() == -1 || komsu.getadimlar() > adimlar)) {
				komsu.setsonKare(bulundugumKarem);
				kareTipiKesfetYolCizD(komsu, bulundugumKarem.getX(), bulundugumKarem.getY(), adimlar);
				kesfedilenc.add(komsu);
			}
		}
		xkesfet = bulundugumKarem.getX() - 1;
		ykesfet = bulundugumKarem.getY();
		if ((xkesfet > -1 && xkesfet < kareX) && (ykesfet > -1 && ykesfet < kareY)) {
			Kare komsu = koordinat[xkesfet][ykesfet];
			if ((komsu.getadimlar() == -1 || komsu.getadimlar() > adimlar)) {
				komsu.setsonKare(bulundugumKarem);
				kareTipiKesfetYolCizD(komsu, bulundugumKarem.getX(), bulundugumKarem.getY(), adimlar);
				kesfedilenc.add(komsu);
			}
		}
		xkesfet = bulundugumKarem.getX();
		ykesfet = bulundugumKarem.getY() + 1;
		if ((xkesfet > -1 && xkesfet < kareX) && (ykesfet > -1 && ykesfet < kareY)) {
			Kare komsu = koordinat[xkesfet][ykesfet];
			if ((komsu.getadimlar() == -1 || komsu.getadimlar() > adimlar)) {
				komsu.setsonKare(bulundugumKarem);
				kareTipiKesfetYolCizD(komsu, bulundugumKarem.getX(), bulundugumKarem.getY(), adimlar);
				kesfedilenc.add(komsu);
			}
		}
		xkesfet = bulundugumKarem.getX();
		ykesfet = bulundugumKarem.getY() - 1;
		if ((xkesfet > -1 && xkesfet < kareX) && (ykesfet > -1 && ykesfet < kareY)) {
			Kare komsu = koordinat[xkesfet][ykesfet];
			if ((komsu.getadimlar() == -1 || komsu.getadimlar() > adimlar)) {
				komsu.setsonKare(bulundugumKarem);
				kareTipiKesfetYolCizD(komsu, bulundugumKarem.getX(), bulundugumKarem.getY(), adimlar);
				kesfedilenc.add(komsu);
			}
		}

		return kesfedilenc;
	}

	public void kareTipiKesfetYolCizD(Kare bulundugumKarem, int sonX, int sonY, int adimlar) {

		if (bulundugumKarem.gettip() != 0
				&& bulundugumKarem.gettip() != 2
				&& bulundugumKarem.gettip() != 7
				&& bulundugumKarem.gettip() != 6
				&& bulundugumKarem.gettip() != 8
				&& bulundugumKarem.gettip() != 9
				&& bulundugumKarem.gettip() != 10) {
			bulundugumKarem.settip(4);
		}
		bulundugumKarem.setsonKare(sonX, sonY);
		bulundugumKarem.setadimlar(adimlar);

		if (bulundugumKarem == koordinat[bitisdx][bitisdy]) {
			yolumuOlusturd(sonX, sonY, adimlar, bulundugumKarem);
		}

	}

	public void yolumuOlusturd(int sx, int sy, int adimlar, Kare yer) {

		while (adimlar > 1) {

			Kare bulundugumKarem = koordinat[sx][sy];
			sx = bulundugumKarem.getsonX();
			sy = bulundugumKarem.getsonY();

			yolumd.add(bulundugumKarem);
			adimlar--;
		}
		yolArad2 = false;
		Collections.reverse(yolumd);
		yolumd.add(yer);
	}

	class siralaMaliyet implements Comparator<Kare> {
		@Override
		public int compare(Kare o1, Kare o2) {
			return (int) (o1.getHamle() - o2.getHamle());
		}
	}

	public void aDeneme() {

			haritaResetlesa();
			if(aOyuncusu.getPuanA()>0){



		if(yolum.size()>adimSayisi) {
			if(aninKaresiB){

				koordinat[gizliAltinTutucu.get(0).getX()][gizliAltinTutucu.get(0).getY()]=new Kare(2, gizliAltinTutucu.get(0).getX(), gizliAltinTutucu.get(0).getsonY());
				koordinat[gizliAltinTutucu.get(0).getX()][gizliAltinTutucu.get(0).getY()].setAltin(gizliAltinTutucu.get(0).getAltin());
				koordinat[gizliAltinTutucu.get(0).getX()][gizliAltinTutucu.get(0).getY()].setDeger(Integer.toString(gizliAltinTutucu.get(0).getAltin()));
				aninKaresiB=false;
				gizliAltinTutucu.clear();
			}
			if (yolum.size() - ra >= adimSayisi) {
				if (koordinat[yolum.get(ra+arttir).getX()][yolum.get(ra+arttir).getY()].gettip() == 2) {
					altinSayisi--;
					aOyuncusu.setToplananAltinA(aOyuncusu.getToplananAltinA() + (koordinat[yolum.get(ra+arttir).getX()][yolum.get(ra+arttir).getY()].getAltin()));
					aOyuncusu.setPuanA(aOyuncusu.getPuanA() + (koordinat[yolum.get(ra+arttir).getX()][yolum.get(ra+arttir).getY()].getAltin()));
				} else if (koordinat[yolum.get(ra+arttir).getX()][yolum.get(ra+arttir).getY()].gettip() == 6) {

					gizliAltinTutucu.add( koordinat[yolum.get(ra+arttir).getX()][yolum.get(ra+arttir).getY()]);
					gizliAltinTutucu.get(0).setAltin(koordinat[yolum.get(ra+arttir).getX()][yolum.get(ra+arttir).getY()].getAltin());
					aninKaresiB = true;
				}
				koordinat[yolum.get(ra+arttir).getX()][yolum.get(ra+arttir).getY()] = new Kare(7, yolum.get(ra+arttir).getX(), yolum.get(ra+arttir).getY());
				koordinat[yolum.get(ra+arttir).getX()][yolum.get(ra+arttir).getY()].setDeger("A");
				ax = yolum.get(ra+arttir).getX();
				ay = yolum.get(ra+arttir).getY();
				if (yolum.size() - ra + arttir+1 > 0) {
					ra += adimSayisi;
				} else {
					ra = yolum.size();
				}
				aOyuncusu.setPuanA(aOyuncusu.getPuanA() - aHamlePuani);
				aOyuncusu.setHarcananAltinA(aOyuncusu.getHarcananAltinA() + aHamlePuani);
				puanAL.setText("Puan A =" + (aOyuncusu.getPuanA()));
				yenile();
			} else {

				koordinat[yolum.get(yolum.size() - 1).getX()][yolum.get(yolum.size() - 1).getY()] = new Kare(7, yolum.get(yolum.size() - 1).getX(), yolum.get(yolum.size() - 1).getY());
				koordinat[yolum.get(yolum.size() - 1).getX()][yolum.get(yolum.size() - 1).getY()].setDeger("A");
				altinSayisi--;
				aOyuncusu.setToplananAltinA(aOyuncusu.getToplananAltinA() + (yolum.get(yolum.size()-1).getAltin()));
				aOyuncusu.setPuanA(aOyuncusu.getPuanA() + koordinat[yolum.get(yolum.size() - 1).getX()][yolum.get(yolum.size() - 1).getY()].getAltin());

				aOyuncusu.setPuanA(aOyuncusu.getPuanA() - aHamlePuani);
				aOyuncusu.setHarcananAltinA(aOyuncusu.getHarcananAltinA() + aHamlePuani);
				puanAL.setText("Puan A =" + (aOyuncusu.getPuanA()));
				ax = yolum.get(yolum.size() - 1).getX();
				ay = yolum.get(yolum.size() - 1).getY();
				deneme=false;
				yenile();
			}
		}else {


			altinSayisi--;
			aOyuncusu.setToplananAltinA(aOyuncusu.getToplananAltinA() + (yolum.get(yolum.size()-1).getAltin()));
			aOyuncusu.setPuanA(aOyuncusu.getPuanA() + koordinat[yolum.get(yolum.size() - 1).getX()][yolum.get(yolum.size() - 1).getY()].getAltin());

			yolum.get(yolum.size()-1).settip(7);
				yolum.get(yolum.size()-1).setDeger("A");
				aOyuncusu.setPuanA(aOyuncusu.getPuanA() - aHamlePuani);
				aOyuncusu.setHarcananAltinA(aOyuncusu.getHarcananAltinA() + aHamlePuani);
				aOyuncusu.setToplamAdimA(aOyuncusu.getToplamAdimA() + yolum.size());
				puanAL.setText("Puan A =" + (aOyuncusu.getPuanA()));
				ax=yolum.get(yolum.size() - 1).getX();
				ay=yolum.get(yolum.size() - 1).getY();
				yenile();
			deneme=false;
			}
		int q=yolum.size();


		while (a<q){


			if(yolum.get(a).gettip()==6)
				yolum.get(a).settip(2);

			tumYolumA.add(yolum.get(a));
			if(yolum.get(a).getX()==ax && yolum.get(a).getY()==ay){
				a++;
				break;
			}

			a++;
		}}

		if (aOyuncusu.getPuanA() <= 0) {
			aOyuncusu.setPuanA(0);
			aPuanL.setText("0");
			kontrola = false;
		}

	}

	public void bDeneme() {

		haritaResetlesb();

		if(bOyuncusu.getPuanB()>0) {



			if (yolumb.size() > adimSayisi && deneme2) {
				if (aninKaresiBB) {

					koordinat[gizliAltinTutucuB.get(0).getX()][gizliAltinTutucuB.get(0).getY()] = new Kare(2, gizliAltinTutucuB.get(0).getX(), gizliAltinTutucuB.get(0).getsonY());
					koordinat[gizliAltinTutucuB.get(0).getX()][gizliAltinTutucuB.get(0).getY()].setAltin(gizliAltinTutucuB.get(0).getAltin());
					koordinat[gizliAltinTutucuB.get(0).getX()][gizliAltinTutucuB.get(0).getY()].setDeger(Integer.toString(gizliAltinTutucuB.get(0).getAltin()));
					aninKaresiBB = false;
					gizliAltinTutucu.clear();
				}

				if (yolumb.size() - rb >= adimSayisi) {
					if (koordinat[yolumb.get(rb+arttir).getX()][yolumb.get(rb+arttir).getY()].gettip() == 2) {
						bOyuncusu.setToplananAltinB(bOyuncusu.getToplananAltinB() + (koordinat[yolumb.get(rb+arttir).getX()][yolumb.get(rb+arttir).getY()].getAltin()));
						altinSayisi--;
						bOyuncusu.setPuanB(bOyuncusu.getPuanB() + (koordinat[yolumb.get(rb+arttir).getX()][yolumb.get(rb+arttir).getY()].getAltin()));
					} else if (koordinat[yolumb.get(rb+arttir).getX()][yolumb.get(rb+1).getY()].gettip() == 6) {
						gizliAltinTutucuB.add(koordinat[yolumb.get(rb+arttir).getX()][yolumb.get(rb+arttir).getY()]);
						gizliAltinTutucuB.get(0).setAltin(koordinat[yolumb.get(rb+arttir).getX()][yolumb.get(rb+arttir).getY()].getAltin());
						aninKaresiBB = true;
					}

					koordinat[yolumb.get(rb+arttir).getX()][yolumb.get(rb+arttir).getY()] = new Kare(8, yolumb.get(rb+arttir).getX(), yolumb.get(rb+arttir).getY());
					koordinat[yolumb.get(rb+arttir).getX()][yolumb.get(rb+arttir).getY()].setDeger("B");
					bx = yolumb.get(rb+arttir).getX();
					by = yolumb.get(rb+arttir).getY();
					if (yolumb.size() - rb + arttir+1 > 0) {
						rb += adimSayisi;
					} else {
						rb = yolumb.size();
					}
					yenile();
					bOyuncusu.setPuanB(bOyuncusu.getPuanB() - bHamlePuani);
					bOyuncusu.setHarcananAltinB(bOyuncusu.getHarcananAltinB() + bHamlePuani);
					puanBL.setText("Puan B =" + (bOyuncusu.getPuanB()));


				} else {

					bOyuncusu.setToplananAltinB(bOyuncusu.getToplananAltinB() + (koordinat[yolumb.get(yolumb.size() - 1).getX()][yolumb.get(yolumb.size() - 1).getY()].getAltin()));
					altinSayisi--;
					bOyuncusu.setPuanB(bOyuncusu.getPuanB() + (koordinat[yolumb.get(yolumb.size() - 1).getX()][yolumb.get(yolumb.size() - 1).getY()].getAltin()));
					yolumb.get(yolumb.size() - 1).settip(8);
					yolumb.get(yolumb.size() - 1).setDeger("B");
					yenile();
					bOyuncusu.setPuanB(bOyuncusu.getPuanB() - bHamlePuani);
					bOyuncusu.setHarcananAltinB(bOyuncusu.getHarcananAltinB() + bHamlePuani);
					bOyuncusu.setToplamAdimB(bOyuncusu.getToplamAdimB() + yolumb.size());
					puanBL.setText("Puan B =" + (bOyuncusu.getPuanB()));
					bx = yolumb.get(yolumb.size() - 1).getX();
					by = yolumb.get(yolumb.size() - 1).getY();
					deneme2 = false;
				}
			} else {

				yolumb.get(yolumb.size() - 1).settip(8);
				yolumb.get(yolumb.size() - 1).setDeger("B");
				bOyuncusu.setToplananAltinB(bOyuncusu.getToplananAltinB() + (koordinat[yolumb.get(yolumb.size() - 1).getX()][yolumb.get(yolumb.size() - 1).getY()].getAltin()));
				altinSayisi--;
				bOyuncusu.setPuanB(bOyuncusu.getPuanB() + (koordinat[yolumb.get(yolumb.size() - 1).getX()][yolumb.get(yolumb.size() - 1).getY()].getAltin()));

				yenile();
				bOyuncusu.setPuanB(bOyuncusu.getPuanB() - bHamlePuani);
				bOyuncusu.setHarcananAltinB(bOyuncusu.getHarcananAltinB() + bHamlePuani);
				bOyuncusu.setToplamAdimB(bOyuncusu.getToplamAdimB() + yolumb.size());
				puanBL.setText("Puan B =" + (bOyuncusu.getPuanB()));
				bx = yolumb.get(yolumb.size() - 1).getX();
				by = yolumb.get(yolumb.size() - 1).getY();
				deneme2 = false;


			}
			int q = yolumb.size();
			while (b < q) {

				if (yolumb.get(b).gettip() == 6)
					yolumb.get(b).settip(2);

				tumYolumB.add(yolumb.get(b));
				if (yolumb.get(b).getX() == bx && yolumb.get(b).getY() == by) {
					b++;
					break;

				}

				b++;
			}
		}

			if(bOyuncusu.getPuanB()<=0) {
				bOyuncusu.setPuanB(0);
				bPuanL.setText("0");
		kontrolb = false;
	}




	}

	public void cDeneme() {
		haritaResetlesc();
		if (cOyuncusu.getPuanC() > 0  && altinlar.size() >= 1) {

			if (yolumc.size() > adimSayisi && deneme3) {

				if(aninKaresiBC){

					koordinat[gizliAltinTutucuC.get(0).getX()][gizliAltinTutucuC.get(0).getY()]=new Kare(2, gizliAltinTutucuC.get(0).getX(), gizliAltinTutucuC.get(0).getsonY());
					koordinat[gizliAltinTutucuC.get(0).getX()][gizliAltinTutucuC.get(0).getY()].setAltin(gizliAltinTutucuC.get(0).getAltin());
					koordinat[gizliAltinTutucuC.get(0).getX()][gizliAltinTutucuC.get(0).getY()].setDeger(Integer.toString(gizliAltinTutucuC.get(0).getAltin()));

					aninKaresiBC=false;
					gizliAltinTutucuC.clear();
				}
				if (yolumc.size() - rc >= adimSayisi) {
					if (koordinat[yolumc.get(rc+arttir).getX()][yolumc.get(rc+arttir).getY()].gettip() == 2) {
						altinSayisi--;
						cOyuncusu.setToplananAltinC(cOyuncusu.getToplananAltinC() + (koordinat[yolumc.get(rc+arttir).getX()][yolumc.get(rc+arttir).getY()].getAltin()));
						cOyuncusu.setPuanC(cOyuncusu.getPuanC() + (koordinat[yolumc.get(rc+arttir).getX()][yolumc.get(rc+arttir).getY()].getAltin()));
					}else if (koordinat[yolumc.get(rc+arttir).getX()][yolumc.get(rc+arttir).getY()].gettip() == 6) {

						gizliAltinTutucuC.add( koordinat[yolumc.get(rc+arttir).getX()][yolumc.get(rc+arttir).getY()]);
						gizliAltinTutucuC.get(0).setAltin(koordinat[yolumc.get(rc+arttir).getX()][yolumc.get(rc+arttir).getY()].getAltin());
						aninKaresiBC = true;
					}
					koordinat[yolumc.get(rc+arttir).getX()][yolumc.get(rc+arttir).getY()] = new Kare(9, yolumc.get(rc+arttir).getX(), yolumc.get(rc+arttir).getY());
					koordinat[yolumc.get(rc+arttir).getX()][yolumc.get(rc+arttir).getY()].setDeger("C");
					cx=yolumc.get(rc+arttir).getX();
					cy=yolumc.get(rc+arttir).getY();
					if (yolumc.size() - rc + arttir+1> 0) {
						rc += adimSayisi;
					} else {
						rc = yolumc.size();
					}
					yenile();
					cOyuncusu.setPuanC(cOyuncusu.getPuanC() - cHamlePuani);
					cOyuncusu.setHarcananAltinC(cOyuncusu.getHarcananAltinC() + cHamlePuani);
					puanCL.setText("Puan C =" + (cOyuncusu.getPuanC()));
				} else {


					if (koordinat[yolumc.get(yolumc.size() - 1).getX()][yolumc.get(yolumc.size() - 1).getY()].gettip() == 2) {
						altinSayisi--;
						cOyuncusu.setToplananAltinC(cOyuncusu.getToplananAltinC() + (koordinat[yolumc.get(yolumc.size() - 1).getX()][yolumc.get(yolumc.size() - 1).getY()].getAltin()));
						cOyuncusu.setPuanC(cOyuncusu.getPuanC() + (koordinat[yolumc.get(yolumc.size() - 1).getX()][yolumc.get(yolumc.size() - 1).getY()].getAltin()));
					}
					koordinat[yolumc.get(yolumc.size() - 1).getX()][yolumc.get(yolumc.size() - 1).getY()] = new Kare(9, yolumc.get(yolumc.size() - 1).getX(), yolumc.get(yolumc.size() - 1).getY());
					koordinat[yolumc.get(yolumc.size() - 1).getX()][yolumc.get(yolumc.size() - 1).getY()].setDeger("C");
					cx=yolumc.get(yolumc.size() - 1).getX();
					cy=yolumc.get(yolumc.size() - 1).getY();
					yenile();
					cOyuncusu.setPuanC(cOyuncusu.getPuanC() - cHamlePuani);
					cOyuncusu.setHarcananAltinC(cOyuncusu.getHarcananAltinC() + cHamlePuani);
					cOyuncusu.setToplamAdimC(cOyuncusu.getToplamAdimC() + yolumc.size());
					puanCL.setText("Puan C =" + (cOyuncusu.getPuanC()));
					deneme3 = false;
				}
			} else {


					altinSayisi--;
					cOyuncusu.setToplananAltinC(cOyuncusu.getToplananAltinC() + (koordinat[yolumc.get(yolumc.size() - 1).getX()][yolumc.get(yolumc.size() - 1).getY()].getAltin()));
					cOyuncusu.setPuanC(cOyuncusu.getPuanC() + (koordinat[yolumc.get(yolumc.size() - 1).getX()][yolumc.get(yolumc.size() - 1).getY()].getAltin()));

				koordinat[yolumc.get(yolumc.size() - 1).getX()][yolumc.get(yolumc.size() - 1).getY()] = new Kare(9, yolumc.get(yolumc.size() - 1).getX(), yolumc.get(yolumc.size() - 1).getY());
				koordinat[yolumc.get(yolumc.size() - 1).getX()][yolumc.get(yolumc.size() - 1).getY()].setDeger("C");
				yenile();
				cx=yolumc.get(yolumc.size() - 1).getX();
				cy=yolumc.get(yolumc.size() - 1).getY();
				cOyuncusu.setPuanC(cOyuncusu.getPuanC() - cHamlePuani);
				cOyuncusu.setHarcananAltinC(cOyuncusu.getHarcananAltinC() + cHamlePuani);
				cOyuncusu.setToplamAdimC(cOyuncusu.getToplamAdimC() + yolumc.size());
				puanCL.setText("Puan C =" + (cOyuncusu.getPuanC()));
				deneme3 = false;
			}

			bitirC = false;
		}
		int q=yolumc.size();
		while (c<q){


			if(yolumc.get(c).gettip()==6)
				yolumc.get(c).settip(2);

			tumYolumC.add(yolumc.get(c));
			if(yolumc.get(c).getX()==cx && yolumc.get(c).getY()==cy){
				c++;
				break;
			}
			c++;
		}


		if (cOyuncusu.getPuanC() <= 0){
			cPuanL.setText("0");
			cOyuncusu.setPuanC(0);
			kontrolc = false;
		}




	}

	public void dDeneme() {
		haritaResetlesd();

		if (dOyuncusu.getPuanD() > 0  && altinlar.size() >= 1) {

			if (!deneme4) {
				for (Kare kare : yolumd) {
					if (kare.getX() == dx && kare.getY() == dy) {
						bitisdx = kare.getX();
						bitisdy = kare.getY();
						break;
					}
				}

				yolumd.clear();
				maliyetd.clear();
				ld--;
				rd = 1;
				d = 0;
				deneme4 = true;
				yolArad = true;
				temizle();
				dAlgoritmasi();
			//	System.out.println("buraya girdi 2 ");
			}


			if (yolumd.size() > adimSayisi && deneme4) {
			//	System.out.println("buraya girdi 3");
				if (aninKaresiBD) {

					koordinat[gizliAltinTutucuD.get(0).getX()][gizliAltinTutucuD.get(0).getY()] = new Kare(2, gizliAltinTutucuD.get(0).getX(), gizliAltinTutucuD.get(0).getsonY());
					koordinat[gizliAltinTutucuD.get(0).getX()][gizliAltinTutucuD.get(0).getY()].setAltin(gizliAltinTutucuD.get(0).getAltin());
					koordinat[gizliAltinTutucuD.get(0).getX()][gizliAltinTutucuD.get(0).getY()].setDeger(Integer.toString(gizliAltinTutucuD.get(0).getAltin()));
					aninKaresiBD = false;
					gizliAltinTutucuD.clear();
				}
				if (yolumd.size() - rd >= adimSayisi) {
					if (koordinat[yolumd.get(rd+arttir).getX()][yolumd.get(rd+arttir).getY()].gettip() == 2) {
						altinSayisi--;
						dOyuncusu.setToplananAltinD(dOyuncusu.getToplananAltinD() + (koordinat[yolumd.get(rd+arttir).getX()][yolumd.get(rd+arttir).getY()].getAltin()));
						dOyuncusu.setPuanD(dOyuncusu.getPuanD() + (koordinat[yolumd.get(rd+arttir).getX()][yolumd.get(rd+arttir).getY()].getAltin()));
					} else if (koordinat[yolumd.get(rd+arttir).getX()][yolumd.get(rd+arttir).getY()].gettip() == 6) {
						gizliAltinTutucuD.add(koordinat[yolumd.get(rd+arttir).getX()][yolumd.get(rd+arttir).getY()]);
						gizliAltinTutucuD.get(0).setAltin(koordinat[yolumd.get(rd+arttir).getX()][yolumd.get(rd+arttir).getY()].getAltin());
						aninKaresiBD = true;
					}
					koordinat[yolumd.get(rd+arttir).getX()][yolumd.get(rd + arttir).getY()] = new Kare(10, yolumd.get(rd+arttir).getX(), yolumd.get(rd+arttir).getY());
					koordinat[yolumd.get(rd+arttir).getX()][yolumd.get(rd +arttir).getY()].setDeger("D");
					dx = yolumd.get(rd + arttir).getX();
					dy = yolumd.get(rd + arttir).getY();
					if (yolumd.size() - rd + arttir+1 > 0) {
						rd += adimSayisi;
					} else {
						rd = yolumd.size();
					}
					yenile();
					dOyuncusu.setPuanD(dOyuncusu.getPuanD() - dHamlePuani);
					dOyuncusu.setHarcananAltinD(dOyuncusu.getHarcananAltinD() + dHamlePuani);
					puanDL.setText("Puan D =" + (dOyuncusu.getPuanD()));


				} else {

					altinSayisi--;
					dOyuncusu.setToplananAltinD(dOyuncusu.getToplananAltinD() + (koordinat[yolumd.get(yolumd.size() - 1).getX()][yolumd.get(yolumd.size() - 1).getY()].getAltin()));
					dOyuncusu.setPuanD(dOyuncusu.getPuanD() + (koordinat[yolumd.get(yolumd.size() - 1).getX()][yolumd.get(yolumd.size() - 1).getY()].getAltin()));

					koordinat[yolumd.get(yolumd.size() - 1).getX()][yolumd.get(yolumd.size() - 1).getY()] = new Kare(10, yolumd.get(yolumd.size() - 1).getX(), yolumd.get(yolumd.size() - 1).getY());
					koordinat[yolumd.get(yolumd.size() - 1).getX()][yolumd.get(yolumd.size() - 1).getY()].setDeger("D");
					yenile();
					dOyuncusu.setPuanD(dOyuncusu.getPuanD() - dHamlePuani);
					dOyuncusu.setHarcananAltinD(dOyuncusu.getHarcananAltinD() + dHamlePuani);
					dOyuncusu.setToplamAdimD(dOyuncusu.getToplamAdimD() + yolumd.size());
					puanDL.setText("Puan D =" + (dOyuncusu.getPuanD()));
					dx = yolumd.get(yolumd.size() - 1).getX();
					dy = yolumd.get(yolumd.size() - 1).getY();
					deneme4 = false;

				}
			} else {

				altinSayisi--;
				dOyuncusu.setToplananAltinD(dOyuncusu.getToplananAltinD() + (koordinat[yolumd.get(yolumd.size() - 1).getX()][yolumd.get(yolumd.size() - 1).getY()].getAltin()));
				dOyuncusu.setPuanD(dOyuncusu.getPuanD() + (koordinat[yolumd.get(yolumd.size() - 1).getX()][yolumd.get(yolumd.size() - 1).getY()].getAltin()));
				yolumd.get(yolumd.size()-1).settip(10);
				yolumd.get(yolumd.size()-1).setDeger("D");
				yenile();
				dOyuncusu.setPuanD(dOyuncusu.getPuanD() - dHamlePuani);
				dOyuncusu.setToplamAdimD(dOyuncusu.getToplamAdimD() + yolumd.size());
				puanDL.setText("Puan D =" + (dOyuncusu.getPuanD()));
				dx = yolumd.get(yolumd.size() - 1).getX();
				dy = yolumd.get(yolumd.size() - 1).getY();
				deneme4 = false;

			}
			int q=yolumd.size();
			while (d<q){
				if(yolumd.get(d).gettip()==6)
					yolumd.get(d).settip(2);
				tumYolumD.add(yolumd.get(d));
				if(yolumd.get(d).getX()==dx && yolumd.get(d).getY()==dy){
					d++;
					break;
				}
				d++;
			}
		}

		if (dOyuncusu.getPuanD() <= 0){
			dPuanL.setText("0");
			dOyuncusu.setPuanD(0);
			kontrold = false;
		}
	}
	}
