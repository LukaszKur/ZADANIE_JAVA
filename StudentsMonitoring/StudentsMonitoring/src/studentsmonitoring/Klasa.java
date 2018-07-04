package studentsmonitoring;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Klasa opisuje klasê szkoln¹
 */
public class Klasa implements Serializable {
	private static final long serialVersionUID = 1L;
	private static int liczbaObiektow;
	private String nazwa;
	private ArrayList<Uczen> uczniowie;

	public Klasa(String nazwa, ArrayList<Uczen> uczniowie) {
		this.nazwa = nazwa;
		this.uczniowie = uczniowie;
		liczbaObiektow++;
	}

	public Klasa(Klasa klasa) {
		this.nazwa = klasa.nazwa;
		this.uczniowie = new ArrayList<Uczen>(klasa.uczniowie);
		liczbaObiektow++;
	}

	public static int getLiczbaObiektow() {
		return liczbaObiektow;
	}

	public String getNazwa() {
		return nazwa;
	}

	public ArrayList<Uczen> getUczniowie() {
		return uczniowie;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Klasa ");
		builder.append(nazwa);
		return builder.toString();
	}

	/**
	 * Metoda zwraca statystyki klasy
	 * 
	 * @return statystyki klasy z poszczególnych przedmiotów
	 */
	public String podajStatystykiKlasy() {
		StringBuilder builder = new StringBuilder();
		builder.append(this);
		builder.append("\n");
		for (Ocena.Przedmiot przedmiot : Ocena.Przedmiot.values()) {
			builder.append("\t");
			builder.append(przedmiot.getNazwa().toUpperCase());
			builder.append(" (Œrednia: " + (double) Math.round(obliczSredniaKlasy(przedmiot) * 100) / 100 + ")");
			builder.append("\n");
		}
		return builder.toString();
	}

	/**
	 * Metoda oblicza Œredniê klasy
	 * 
	 * @param przedmiot
	 *            wybrany przedmiot
	 * @return wyliczona Œrednia
	 */
	public double obliczSredniaKlasy(Ocena.Przedmiot przedmiot) {
		int liczbaUczniow = 0;
		double sumaSrednich = 0;
		double sredniaUcznia = 0;

		for (Uczen uczen : uczniowie) {
			sredniaUcznia = uczen.obliczSredniaUcznia(uczen.getOceny().get(przedmiot));
			if (sredniaUcznia > 0) {
				sumaSrednich += sredniaUcznia;
				liczbaUczniow++;
			}
		}
		return sumaSrednich / liczbaUczniow;
	}

	/**
	 * Metoda oblicza odchylenie standardowe klasy (pierwiastek kwadratowy z
	 * œredniej arytmetycznej kwadratów ró¿nic poszczególnych cech od wartoœci
	 * oczekiwanej)
	 * 
	 * @param przedmiot
	 *            wybrany przedmiot
	 * @return wyliczone odchylenie standardowe
	 */
	public double obliczOdchylenieStandardoweKlasy(Ocena.Przedmiot przedmiot) {
		int liczbaUczniow = 0;
		double sredniaKlasy = obliczSredniaKlasy(przedmiot);
		double sredniaUcznia = 0;
		double sumaKwadratowOdchylen = 0;

		for (Uczen uczen : uczniowie) {
			sredniaUcznia = uczen.obliczSredniaUcznia(uczen.getOceny().get(przedmiot));
			if (sredniaUcznia > 0) {
				sumaKwadratowOdchylen += (sredniaUcznia - sredniaKlasy) * (sredniaUcznia - sredniaKlasy);
				liczbaUczniow++;
			}
		}
		return Math.sqrt(sumaKwadratowOdchylen / liczbaUczniow);
	}
}