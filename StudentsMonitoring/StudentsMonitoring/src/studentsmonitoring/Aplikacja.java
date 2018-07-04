package studentsmonitoring;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.NoSuchElementException;
import java.util.Scanner;

import util.WyjatekBlednejOpcji;
import util.WykresRozkladuNormalnego;

/**
 * Główna klasa aplikacji
 */
public class Aplikacja {
	public static boolean CZY_DRUKOWAC_WYWOLANIA_KONSTRUKTOROW = false;
	private static Scanner KLAWIATURA = new Scanner(System.in);

	public static void main(String[] args) {
		// lista uczniów
		ArrayList<Uczen> uczniowie = new ArrayList<Uczen>();
		uczniowie.add(new Uczen(1, "Tomasz", "Kowalski"));
		uczniowie.add(new Uczen(2, "Marcin", "Nowak"));
		uczniowie.add(new Uczen(3, "Karolina", "Wiśniewska"));
		uczniowie.add(new Uczen(4, "Justyna", "Zielonka"));

		// klasa zawierająca uczniów
		Klasa klasa = new Klasa("2A", uczniowie);

		String opcjaMenu = "";
		do {
			try {
				System.out.println("===================================================");
				System.out.println("[1] Przypisz ocenę uczniowi");
				System.out.println("[2] Pokaż statystyki ucznia");
				System.out.println("[3] Pokaż statystyki klasy");
				System.out.println("[4] Pokaż rozkład ocen na krzywej dzwonowej");
				System.out.println("[5] Zapisz stan klasy");
				System.out.println("[6] Odczytaj stan klasy");
				System.out.println("[0] WYJŚCIE");
				System.out.print("--> Wybierz opcję: ");
				opcjaMenu = KLAWIATURA.nextLine();
				System.out.println("===================================================");

				// wybrano opcję przypisania oceny uczniowi
				if (opcjaMenu.equalsIgnoreCase("1")) {
					klasa.getUczniowie().forEach(uczen -> System.out.println(uczen));
					System.out.print("\nWskaż ucznia podając jego numer: ");
					String numerUcznia = KLAWIATURA.nextLine();
					System.out.print("Podaj notę: ");
					double nota = Double.parseDouble(KLAWIATURA.nextLine());
					System.out.print("Wybierz przedmiot ( ");
					EnumSet.allOf(Ocena.Przedmiot.class).forEach(przedmiot -> System.out.print(przedmiot + " "));
					System.out.print("): ");
					Ocena.Przedmiot przedmiot = Ocena.Przedmiot.valueOf(KLAWIATURA.nextLine());
					System.out.print("Wybierz rodzaj oceny ( 1-Test, 2-Zadanie ): ");
					String rodzajOceny = KLAWIATURA.nextLine();

					Ocena ocena = null;
					// wybrano test
					if (rodzajOceny.equals("1")) {
						System.out.print("Wybrano Test.\n ");
						ocena = new Klasowka(nota, przedmiot);
					}
					// wybrano zadania
					else if (rodzajOceny.equals("2")) {
						System.out.print("Wybrano zadanie.\n ");
						ocena = new Zadanie(nota, przedmiot);
					}
					// wybrano nieznaną opcję
					else {
						throw new WyjatekBlednejOpcji();
					}
					// dodanie oceny wybranemu uczniowi
					klasa.getUczniowie().stream().filter(u -> u.getNumer() == Integer.parseInt(numerUcznia)).findFirst()
							.get().getOceny().get(przedmiot).add(ocena);
				}
				// wybrano opcję pokazania statystyk ucznia
				else if (opcjaMenu.equalsIgnoreCase("2")) {
					klasa.getUczniowie().forEach(uczen -> System.out.println(uczen));
					System.out.print("\nWskaż ucznia podając jego numer: ");
					String numerUcznia = KLAWIATURA.nextLine();
					System.out.println(
							klasa.getUczniowie().stream().filter(u -> u.getNumer() == Integer.parseInt(numerUcznia))
									.findFirst().get().podajStatystykiUcznia());
				}
				// wybrano opcję pokazania statystyk klasy
				else if (opcjaMenu.equalsIgnoreCase("3")) {
					System.out.println(klasa.podajStatystykiKlasy());
				}
				// wybrano opcję zaprezentowania rozkładu ocen na krzywej dzwonowej
				else if (opcjaMenu.equalsIgnoreCase("4")) {
					System.out.print("Wybierz przedmiot (");
					EnumSet.allOf(Ocena.Przedmiot.class).forEach(przedmiot -> System.out.print(przedmiot + " "));
					System.out.print("): ");
					Ocena.Przedmiot przedmiot = Ocena.Przedmiot.valueOf(KLAWIATURA.nextLine());
					new WykresRozkladuNormalnego(klasa, przedmiot);
				}
				// wybrano opcję zapisu stanu obiektów (serializacja)
				else if (opcjaMenu.equalsIgnoreCase("5")) {
					ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("state.ser"));
					outputStream.writeObject(klasa);
					outputStream.close();
					System.out.println("Stan aplikacji został zapisany.");
				}
				// wybrano opcję odczytu stanu obiektów (deserializacja)
				else if (opcjaMenu.equalsIgnoreCase("6")) {
					ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("state.ser"));
					klasa = (Klasa) inputStream.readObject();
					inputStream.close();
					System.out.println("Stan aplikacji został odczytany.");
				}
				// wybrano wyjście
				else if (opcjaMenu.equalsIgnoreCase("0")) {
					System.out.println("Koniec działania programu.");
				}
				// wybrano nieznaną opcję
				else {
					throw new WyjatekBlednejOpcji();
				}
				// obsługa wyjątków
			} catch (WyjatekBlednejOpcji | IllegalArgumentException e) {
				System.err.println("Wybrano nieprawidłową opcję!");
				uspij();
			} catch (NoSuchElementException e) {
				System.err.println("Wybrano nieprawidłowy numer ucznia!");
				uspij();
			} catch (FileNotFoundException e) {
				System.err.println("Plik nie został odnaleziony!");
				uspij();
			} catch (IOException e) {
				System.err.println("Błąd wejścia/wyjścia!");
				uspij();
			} catch (ClassNotFoundException e) {
				System.err.println("Klasa nie została odnaleziona!");
				uspij();
			}
		} while (!opcjaMenu.equalsIgnoreCase("0"));
		KLAWIATURA.close();
	}

	/**
	 * Uśpienie wątku na krótki okres czasu, żeby wyjście błędów nie zostało
	 * wyprzedzone przez standardowe wyjście konsoli.
	 */
	public static void uspij() {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}