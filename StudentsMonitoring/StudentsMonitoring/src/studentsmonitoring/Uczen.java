package studentsmonitoring;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Klasa opisuje ucznia
 */
public class Uczen implements Serializable {
	private static final long serialVersionUID = 1L;
	private static int liczbaObiektow;
	private int numer;
	private String imie;
	private String nazwisko;
	// mapa przechowuj¹ca oceny (klucz: przedmiot - wartoœæ: lista ocen)
	private TreeMap<Ocena.Przedmiot, ArrayList<Ocena>> oceny;

	public Uczen(int numer, String imie, String nazwisko) {
		this.numer = numer;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.oceny = new TreeMap<Ocena.Przedmiot, ArrayList<Ocena>>();
		for (Ocena.Przedmiot przemiot : Ocena.Przedmiot.values()) {
			this.oceny.put(przemiot, new ArrayList<>());
		}
		liczbaObiektow++;
	}

	public Uczen(Uczen uczen) {
		this.numer = uczen.numer;
		this.imie = uczen.imie;
		this.nazwisko = uczen.nazwisko;
		this.oceny = new TreeMap<Ocena.Przedmiot, ArrayList<Ocena>>(uczen.oceny);
		liczbaObiektow++;
	}

	public static int getLiczbaObiektow() {
		return liczbaObiektow;
	}

	public int getNumer() {
		return numer;
	}

	public String getImie() {
		return imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public TreeMap<Ocena.Przedmiot, ArrayList<Ocena>> getOceny() {
		return oceny;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(numer);
		builder.append(" ... ");
		builder.append(imie);
		builder.append(" ");
		builder.append(nazwisko);
		return builder.toString();
	}

	/**
	 * Metoda zwraca statystyki ucznia
	 * 
	 * @return statystyki ucznia z poszczególnych przedmiotów
	 */
	public String podajStatystykiUcznia() {
		StringBuilder builder = new StringBuilder();
		builder.append(this);
		builder.append("\n");
		oceny.forEach((przedmiot, oceny) -> {
			builder.append("\t");
			builder.append(przedmiot.getNazwa().toUpperCase());
			builder.append(" (Œrednia: " + obliczSredniaUcznia(oceny) + ")");
			oceny.stream().forEach(ocena -> {
				builder.append("\n\t\t");
				builder.append(ocena);
			});
			builder.append("\n");
		});
		return builder.toString();
	}

	/**
	 * Metoda oblicza Œredni¹ ucznia
	 * 
	 * @param oceny
	 *            lista ocen z danego przedmiotu
	 * @return wyliczona Œrednia
	 */
	public double obliczSredniaUcznia(ArrayList<Ocena> oceny) {
		double iloczynNotWag = 0;
		double sumaWag = 0;
		for (Ocena ocena : oceny) {
			iloczynNotWag += ocena.getNota() * ocena.getWaga();
			sumaWag += ocena.getWaga();
		}
		return (double) Math.round(iloczynNotWag / sumaWag * 100) / 100;
	}
}